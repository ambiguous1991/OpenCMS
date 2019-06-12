ALTER TABLE page
  ADD FK_PAGE_TYPE_ID bigint(20) DEFAULT NULL,
  ADD KEY FK_PAGE_TYPE_ID_idx (FK_PAGE_TYPE_ID),
  ADD CONSTRAINT FK_PAGE_TYPE_ID FOREIGN KEY (FK_PAGE_TYPE_ID) REFERENCES page_type (ID);