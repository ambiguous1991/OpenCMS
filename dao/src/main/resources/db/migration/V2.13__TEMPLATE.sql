CREATE TABLE template
(
  ID      bigint(20)           NOT NULL AUTO_INCREMENT,
  NAME   varchar(200)         NOT NULL,
  CONTENT text,
  CDATE   datetime(6)          NOT NULL,
  UDATE   datetime(6)          NOT NULL,
  PRIMARY KEY (ID)
);