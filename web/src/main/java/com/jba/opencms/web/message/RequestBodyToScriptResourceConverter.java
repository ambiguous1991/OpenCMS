package com.jba.opencms.web.message;

import com.jba.opencms.file.FileFacadeService;
import com.jba.opencms.type.file.Script;

import java.util.List;
import java.util.stream.Collectors;

public class RequestBodyToScriptResourceConverter extends AbstractConverter<Script> {

    private FileFacadeService fileService;

    public RequestBodyToScriptResourceConverter(String parameterName, FileFacadeService fileService) {
        super(parameterName);
        this.fileService=fileService;
    }

    @Override
    protected final List<Script> extract(List<String> tokens) {
        return tokens.stream()
                .map(element -> fileService.script().findOne(Long.parseLong(element), true))
                .collect(Collectors.toList());
    }
}
