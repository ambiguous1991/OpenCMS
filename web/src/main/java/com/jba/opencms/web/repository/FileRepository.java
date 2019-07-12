package com.jba.opencms.web.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileRepository {
    InputStream get(String path) throws IOException;
    void save(String path, String filename, File file) throws IOException;
    void update(String path, String filename, File file) throws IOException;
    boolean delete(String path) throws IOException;
    String list(String path) throws IOException;
}
