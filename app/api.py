import asyncio
import os
import re
from collections import Counter
from typing import FrozenSet, List, Optional

import asyncpg
import numpy as np
import pandas as pd
import requests
import tqdm
from bs4 import BeautifulSoup
from fastapi import FastAPI, Request, Response
from fastapi.responses import FileResponse
import os
import seaborn as sns
import time
import json
import plotly
import plotly.express as px


from app.logging import get_logger

logger = get_logger(__name__)

app = FastAPI()


async def fetch_as_dataframe(con: asyncpg.Connection, query: str, *args):
    stmt = await con.prepare(query)
    columns = [a.name for a in stmt.get_attributes()]
    data = await stmt.fetch(*args)
    return pd.DataFrame(data, columns=columns)


async def connect_db():
    """Connect to db
    """
    conn = await asyncpg.connect(
        user=os.environ['DB_USER'],
        password=os.environ['DB_PASSWORD'],
        database=os.environ['DB_db'],
        host=os.environ['DB_HOST']
    )
    logger.info('Connected to db!')
    return conn


def _get_health_index(data: pd.DataFrame):
    mood_index = data['mood'].values

    # bmi_index = data['bmi'].apply(lambda x: 1 if x > 35 else 3).values

    # sleep_dict = {'bad': 1, 'good': 5}
    # sleep_index = data['sleep'].apply(lambda x: sleep_dict.get(x, 0)).values

    # pain_chest_index = data['pain_chest'].apply(lambda x: 1 if x else 3).values
    # hair_loss_index = data['hair_loss'].apply(lambda x: 1 if x else 3).values

    # health_index = mood_index + bmi_index
    # health_index += sleep_index + pain_chest_index 
    # health_index += hair_loss_index
    # health_index = health_index / 5.0

    return mood_index


@app.get("/get_health_index/{patient_id}")
async def get_health_index(patient_id: str, response: Response):
    response.headers['Access-Control-Allow-Origin'] = '*'

    conn = await connect_db()

    patient_data = await fetch_as_dataframe(
        con=conn,
        query=f'''
            select * from form 
            where patient_id = {patient_id}
            order by datetime desc limit 1
        '''
    )

    await conn.close()
    logger.info(patient_data)
    health_index = float(patient_data['mood'].values[0])

    return {'health_index': health_index}


@app.get("/get_health_index_plot/{patient_id}/{plot_type}")
async def get_health_index_plot(patient_id: str, plot_type: str):
    # plot_type: health_index/bmi/blood_pressure/skin_change

    conn = await connect_db()

    patient_data = await fetch_as_dataframe(
        con=conn,
        query=f'''
            select * from form 
            where patient_id = {patient_id}
            order by datetime
        '''
    )

    # calculate health score
    patient_data['Date'] = patient_data['datetime'].values

    if plot_type == 'health_index':
        patient_data['Health index'] = patient_data['mood'].values
        logger.info(patient_data['Health index'])

    if plot_type == 'health_index':
        y = 'Health index'
    elif plot_type == 'bmi':
        y = 'BMI'
    elif plot_type == 'blood_pressure':
        y = 'Blood pressure'
    elif plot_type == 'skin_change':
        y = 'Skin change'

    if plot_type != 'health_index':
        patient_data[y] = patient_data[plot_type].values

    plot_path = f'/app/images/{plot_type}_plot_'
    plot_path += str(time.time()) + '.jpg'

    plot = sns.lineplot(
        x='Date',
        y=y,
        data=patient_data,
        color='black'
    )
    plot.figure.savefig(plot_path)
    plot.figure.clf()

    await conn.close()

    # graphJSON = json.dumps(
    #     health_index_plot.figure, 
    #     cls=plotly.utils.PlotlyJSONEncoder
    # )

    return FileResponse(plot_path)
    #return graphJSON


@app.post("/get_search_keywords/")
async def get_search_keywords(info: Request):
    request = await info.json()

    logger.info('Searching for keywords!')
    
    prev_keywords = request['prev_keywords']
    n_keywords = int(request['n_keywords'])

    n_links = 20
    base_pubmed_url = 'https://pubmed.ncbi.nlm.nih.gov/'
    prev_keywords_str = '+'.join(prev_keywords)
    search_url = f'https://pubmed.ncbi.nlm.nih.gov/?term={prev_keywords_str}&filter=simsearch1.fha&filter=datesearch.y_1&size=50'
    search_page = requests.get(search_url)
    
    soup = BeautifulSoup(
        search_page.content,
        'html.parser'
    )
    articles_links = soup.find_all('a', class_='docsum-title')
    
    all_keywords = []
    links = []
    
    for article_link in tqdm.tqdm(articles_links):
        article_number = article_link['href'].replace('/', '')
        article_url = base_pubmed_url + article_number

        article_page = requests.get(article_url)
        article_html = BeautifulSoup(
            article_page.content,
            'html.parser'
        )

        keyword_pattern = """
          Keywords:
        """

        keywords = article_html.find('strong', class_='sub-title', string=keyword_pattern)
        if keywords:
            keywords = keywords.parent.get_text()
            keywords = keywords.replace('Keywords:', '')
            keywords = keywords.strip().rstrip()
            keywords = keywords.lstrip()
            keywords = keywords.replace('.', '')
            keywords = keywords.lower()
            keywords = keywords.split('; ')

            all_keywords += keywords

            links.append(article_url)
                
    keywords_counter = Counter(all_keywords)
    keywords_counter = keywords_counter.most_common(n_keywords + 1)
    keywords_counter = dict(keywords_counter)
    keywords_counter = list(keywords_counter.keys())
    keywords_counter = keywords_counter[1:n_keywords + 1]  

    return {'keywords': keywords_counter, 'links': links[:n_links]}
