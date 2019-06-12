INSERT INTO user(USERNAME, PASSWORD, EMAIL, FIRSTNAME, LASTNAME, CDATE, UDATE)
VALUES ('admin', '$2a$10$duMldOs6/.gQC9BxdKlmKu0IIrR0z09iVUn2/DJmSDD5SL6HXRApe', 'jakub.bartusiak@gmail.com', 'Jakub', 'Bartusiak', now(), now());

INSERT INTO authority(ROLE, CDATE, UDATE)
VALUE ('Administrator', now(), now());

INSERT INTO user_authority(FK_USER_ID, FK_AUTORITY_ID)
  VALUES ('1','1');

INSERT INTO system_variable(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('page.name', 'OpenCMS', now(), now());

INSERT INTO system_variable(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('system.dashboard.dateformat', 'yyyy-MM-dd hh:mm:ss', now(), now());

INSERT INTO system_variable(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('system.dashboard.login.backdrop', 'none', now(), now());

INSERT INTO menu(ACTIVE, NAME, CDATE, UDATE)
VALUES (true, 'default', now(), now());