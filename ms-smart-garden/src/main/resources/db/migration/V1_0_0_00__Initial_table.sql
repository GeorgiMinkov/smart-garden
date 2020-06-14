CREATE TABLE IF NOT EXISTS CONFIGURATION (
    ID INT NOT NULL AUTO_INCREMENT,
    PARAM_NAME VARCHAR(256) NOT NULL,
    PARAM_VALUE VARCHAR(256) NOT NULL,
    
    CONSTRAINT CONFIGURATION_PK PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS HISTORY (
    ID INT NOT NULL AUTO_INCREMENT,
    REQUEST_TIME VARCHAR(256) NOT NULL,
    PAYLOAD VARCHAR(1024) NOT NULL,
    
    CONSTRAINT HISTORY_PK PRIMARY KEY (ID)
);