package com.jba.opencms.web.repository;

public interface ImageRepository {
    byte[] get(String id);
    String getName(String id);
    String getExtension(String id);
    String getFullName(String id);
}
