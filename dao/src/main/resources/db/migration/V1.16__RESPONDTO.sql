CREATE TABLE respondto
(
  ID       bigint(20)  NOT NULL AUTO_INCREMENT,
  RECEIVER varchar(20) NOT NULL,
  CDATE    datetime(6) NOT NULL,
  UDATE    datetime(6) NOT NULL,
  PRIMARY KEY (ID)
)