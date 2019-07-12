package com.jba.opencms.web.repository;

import java.io.File;
import java.io.IOException;

public interface FileRepository {
    File get(String path) throws IOException;
    void save(File file) throws IOException;
    boolean delete(File file) throws IOException;

}
