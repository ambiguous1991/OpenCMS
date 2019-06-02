package com.jba.opencms.web.repository;

public interface ImageRepository {
    byte[] get(Long id);
    String getName(Long id);
    String getExtension(Long id);
    String getFullName(Long id);
}
