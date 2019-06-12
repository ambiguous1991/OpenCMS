CREATE TABLE system_variable
(
  ID           bigint(20)   NOT NULL AUTO_INCREMENT,
  SYSTEM_KEY   varchar(100) NOT NULL,
  SYSTEM_VALUE text         NOT NULL,
  CDATE        datetime(6)  NOT NULL,
  UDATE        datetime(6)  NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE KEY KEY_UNIQUE (SYSTEM_KEY)
)