CREATE TABLE image
(
  ID          bigint(20)  NOT NULL AUTO_INCREMENT,
  NAME        varchar(100) DEFAULT NULL,
  DESCRIPTION varchar(200) DEFAULT NULL,
  DATA        mediumblob  NOT NULL,
  EXTENSION   char(5)      DEFAULT NULL,
  CDATE       datetime(6) NOT NULL,
  UDATE       datetime(6) NOT NULL,
  PRIMARY KEY (ID)
);