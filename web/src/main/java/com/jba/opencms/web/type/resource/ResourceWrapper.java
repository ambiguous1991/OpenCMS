package com.jba.opencms.web.type.resource;

import com.jba.opencms.type.file.File;
import lombok.Data;

@Data
public class ResourceWrapper {
    private Long value;
    private String text;

    public ResourceWrapper(){}
    public ResourceWrapper(File file){
        this.value=file.getId();
        this.text=file.getName();
    }
}
