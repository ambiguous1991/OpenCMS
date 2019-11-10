alter table page_type modify LAYOUT_NAME varchar(200) not null;
alter table page_type add CONTENT TEXT null after LAYOUT_NAME;
alter table page_type drop column FK_FILE_ID;
rename table page_type to template;
alter table page drop foreign key FK_PAGE_TYPE_ID;
alter table page change FK_PAGE_TYPE_ID FK_TEMPLATE_ID bigint null;
alter table page add constraint FK_TEMPLATE_ID foreign key (FK_TEMPLATE_ID) references opencms.template (ID);

