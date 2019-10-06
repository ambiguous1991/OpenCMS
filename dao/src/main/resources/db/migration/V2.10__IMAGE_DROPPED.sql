ALTER TABLE page_type
    DROP FOREIGN KEY FK_IMAGE_ID;
ALTER TABLE page_type
    DROP COLUMN FK_IMAGE_ID;
ALTER TABLE page_type
    ADD COLUMN FK_FILE_ID bigint(20) REFERENCES file (ID);
ALTER TABLE page_type
    MODIFY COLUMN FK_FILE_ID bigint AFTER LAYOUT_NAME;

ALTER TABLE image_user
    DROP FOREIGN KEY FKIMAGE_USER75242;
ALTER TABLE image_user
    DROP COLUMN FK_IMAGE_ID;
ALTER TABLE image_user
    ADD COLUMN FK_FILE_ID bigint(20) REFERENCES file (ID);
ALTER TABLE image_user
    MODIFY COLUMN FK_FILE_ID bigint first;
alter table image_user drop primary key;
alter table image_user
    add constraint image_user_pk
        primary key (FK_FILE_ID, FK_USER_ID);

