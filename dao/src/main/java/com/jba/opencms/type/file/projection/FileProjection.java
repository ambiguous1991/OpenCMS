package com.jba.opencms.type.file.projection;

import lombok.Data;

@Data
public class FileProjection {
    private String name;
    private String path;
    private String mime;
    private String description;

    public FileProjection(String name, String path, String mime, String description) {
        this.name = name;
        this.path = path;
        this.mime = mime;
        this.description = description;
    }
}
