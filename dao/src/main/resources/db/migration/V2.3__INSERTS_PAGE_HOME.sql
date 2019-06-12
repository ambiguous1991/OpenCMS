INSERT INTO system_variable(SYSTEM_KEY, SYSTEM_VALUE, CDATE, UDATE)
VALUES ('page.homepage', 1, now(), now());

INSERT INTO page(TITLE, CONTENT, CDATE, UDATE, FK_PAGE_TYPE_ID)
VALUES ('Strona główna', 'Witaj!', now(), now(), 1);