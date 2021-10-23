CREATE TABLE patient
(
    id                BIGINT NOT NULL,
    login             VARCHAR(255),
    first_name        VARCHAR(255),
    last_name         VARCHAR(255),
    gender            VARCHAR(255),
    age               INTEGER,
    cancer_type       VARCHAR(255),
    radiation_therapy BOOLEAN,
    height            DOUBLE PRECISION,
    weight            DOUBLE PRECISION,
    stage             INTEGER,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);


CREATE TABLE history
(
    id               BIGINT NOT NULL,
    historyOfDecease VARCHAR(255),
    patient_id       BIGINT,
    CONSTRAINT pk_history PRIMARY KEY (id)
);

ALTER TABLE history
    ADD CONSTRAINT FK_HISTORY_ON_PATIENT FOREIGN KEY (patient_id) REFERENCES patient (id);


CREATE TABLE form
(
    id              BIGINT NOT NULL,
    dateTime        TIMESTAMP WITHOUT TIME ZONE,
    mood            INTEGER,
    sleep           VARCHAR(255),
    weight          DOUBLE PRECISION,
    blood_pressure  DOUBLE PRECISION,
    pulse           DOUBLE PRECISION,
    bmi             DOUBLE PRECISION,
    skin_change     INTEGER,
    hair_loss       BOOLEAN,
    pain_chest      BOOLEAN,
    custom_symptoms VARCHAR(255),
    patient_id      BIGINT,
    CONSTRAINT pk_form PRIMARY KEY (id)
);

ALTER TABLE form
    ADD CONSTRAINT FK_FORM_ON_PATIENT FOREIGN KEY (patient_id) REFERENCES patient (id);