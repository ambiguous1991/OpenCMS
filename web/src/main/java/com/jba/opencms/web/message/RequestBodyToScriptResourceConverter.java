package com.jba.opencms.web.message;

import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;

import java.util.List;
import java.util.stream.Collectors;

public class RequestBodyToScriptResourceConverter extends AbstractConverter<File> {

    private FileService fileService;

    public RequestBodyToScriptResourceConverter(FileService fileService, String... supportedParams) {
        super(supportedParams);
        this.fileService=fileService;
    }

    @Override
    protected final List<File> extract(List<String> tokens) {
        return tokens.stream()
                .map(element -> fileService.findOne(Long.parseLong(element), true))
                .collect(Collectors.toList());
    }
}
