alter table page_script drop foreign key CSTR_PAGE_SCRIPT_FK_SCRIPT_ID;
alter table page_script
    add constraint CSTR_PAGE_SCRIPT_FK_SCRIPT_ID
        foreign key (FK_SCRIPT_ID) references file(ID);