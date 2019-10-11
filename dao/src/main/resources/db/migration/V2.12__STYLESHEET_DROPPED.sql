alter table page_stylesheet drop foreign key CSTR_PAGE_STYLESHEET_FK_STYLESHEET_ID;
alter table page_stylesheet
    add constraint CSTR_PAGE_STYLESHEET_FK_STYLESHEET_ID
        foreign key (FK_STYLESHEET_ID) references file (ID);