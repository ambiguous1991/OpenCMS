CREATE TABLE authority
(
  ID    bigint(20)  NOT NULL AUTO_INCREMENT,
  ROLE  varchar(30) NOT NULL,
  CDATE datetime(6) NOT NULL,
  UDATE datetime(6) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE KEY ROLE (ROLE)
);