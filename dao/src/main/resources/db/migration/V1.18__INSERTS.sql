INSERT INTO USER(USERNAME, PASSWORD, EMAIL, FIRSTNAME, LASTNAME, CDATE, UDATE)
VALUES ('admin', '$2a$10$Qsu1WtW6YQRXbPmwg8Zk2eKP9/tFXTrk6zCbLT4xxlnD9RVun7U9.', 'jakub.bartusiak@gmail.com', 'Jakub', 'Bartusiak', now(), now());

INSERT INTO AUTHORITY(ROLE, CDATE, UDATE)
VALUE ('Administrator', now(), now());

INSERT INTO USER_AUTHORITY(FK_USER_ID, FK_AUTORITY_ID)
  VALUES ('1','1');

INSERT INTO SYSTEM_VARIABLE(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('page.name', 'OpenCMS', now(), now());

INSERT INTO SYSTEM_VARIABLE(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('system.dashboard.dateformat', 'yyyy-MM-dd hh:mm:ss', now(), now());

INSERT INTO SYSTEM_VARIABLE(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('system.dashboard.login.backdrop', 'none', now(), now());

INSERT INTO MENU(ACTIVE, NAME, CDATE, UDATE)
VALUES (true, 'default', now(), now());