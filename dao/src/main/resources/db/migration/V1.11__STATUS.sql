CREATE TABLE status
(
  ID          bigint(20)   NOT NULL AUTO_INCREMENT,
  STATUS_NAME varchar(100) NOT NULL,
  CDATE       datetime(6)     NOT NULL,
  UDATE       datetime(6)     NOT NULL,
  PRIMARY KEY (ID)
)