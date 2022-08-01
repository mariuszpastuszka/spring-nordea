drop table PERSON if exists;

CREATE TABLE PERSON
(
    ID          BIGINT IDENTITY PRIMARY KEY,
    USERNAME    VARCHAR2(50) NOT NULL,
    FIRSTNAME   VARCHAR2(50),
    LASTNAME    VARCHAR2(50),
    PASSWORD    VARCHAR2(50) NOT NULL,
    HIRINGDATE  TIMESTAMP,
    VERSION     INT,
    CREATED_AT  TIMESTAMP NOT NULL,
    MODIFIED_AT TIMESTAMP NOT NULL,
    UNIQUE (USERNAME)
);

drop table DETECTIVE if exists;

CREATE TABLE DETECTIVE
(
    ID          BIGINT IDENTITY PRIMARY KEY,
    PERSON_ID   INT       NOT NULL,
    BADGENUMBER VARCHAR2(50),
    RANK        VARCHAR2(50),
    ARMED       BOOLEAN,
    STATUS      VARCHAR2(50),
    VERSION     INT,
    CREATED_AT  TIMESTAMP NOT NULL,
    MODIFIED_AT TIMESTAMP NOT NULL,
    UNIQUE (BADGENUMBER)
);