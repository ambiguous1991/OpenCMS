CREATE TABLE menu
(
  ID     bigint(20)  NOT NULL AUTO_INCREMENT,
  ACTIVE bit(1)      NOT NULL default b'0',
  NAME varchar(100),
  CDATE  datetime(6) NOT NULL,
  UDATE  datetime(6) NOT NULL,
  PRIMARY KEY (ID)
)