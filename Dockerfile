FROM python:3.8-slim-buster

WORKDIR /app

RUN apt-get update && \
    apt-get install -y apt-transport-https build-essential cmake curl gcc g++ git python3-numpy tree sudo unzip wget htop && \
    python -m venv venv && \
    /usr/local/bin/python -m pip install --upgrade pip && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get purge   --auto-remove && \
    apt-get clean

COPY ./requirements.txt /app/requirements.txt

RUN pip install --upgrade -r /app/requirements.txt

COPY . /app

CMD ["uvicorn", "app.api:app", "--host", "0.0.0.0", "--port", "6677"]
