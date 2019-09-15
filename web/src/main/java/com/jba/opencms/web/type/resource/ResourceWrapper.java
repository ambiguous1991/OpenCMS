package com.jba.opencms.web.type.resource;

import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.file.Stylesheet;
import lombok.Data;

@Data
public class ResourceWrapper {
    private Long value;
    private String text;

    public ResourceWrapper(){}
    public ResourceWrapper(Script script){
        this.value=script.getId();
        this.text=script.getTitle();
    }
    public ResourceWrapper(Stylesheet stylesheet){
        this.value=stylesheet.getId();
        this.text=stylesheet.getTitle();
    }
}
