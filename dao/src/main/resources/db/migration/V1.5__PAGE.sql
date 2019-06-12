CREATE TABLE page
(
  ID      bigint(20)           NOT NULL AUTO_INCREMENT,
  TITLE   varchar(200)         NOT NULL,
  CONTENT text,
  VISIBLE bit(1) NOT NULL DEFAULT b'0',
  CDATE   datetime(6)          NOT NULL,
  UDATE   datetime(6)          NOT NULL,
  PRIMARY KEY (ID)
);