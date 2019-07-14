package com.jba.opencms.web.repository;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface FileRepository {
    InputStream get(String path) throws FileNotFoundException, IllegalArgumentException;
    void save(String path, InputStream input, FileAccessMode mode, String... contentTypes) throws IllegalArgumentException;
    void update(String path, InputStream input, FileAccessMode mode, String... contentTypes) throws FileNotFoundException, IllegalArgumentException;
    boolean delete(String path) throws FileNotFoundException, IllegalArgumentException;
    List<String> list(String path) throws IllegalArgumentException;
}
