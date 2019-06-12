CREATE TABLE file
(
  ID          bigint(20)  NOT NULL AUTO_INCREMENT,
  NAME        varchar(100)         DEFAULT NULL,
  ENABLED     bit(1)      NOT NULL DEFAULT b'0',
  DESCRIPTION varchar(200)         DEFAULT NULL,
  DATA        mediumblob  NOT NULL,
  EXTENSION   char(5)              DEFAULT NULL,
  CDATE       datetime(6) NOT NULL,
  UDATE       datetime(6) NOT NULL,
  PRIMARY KEY (ID)
)