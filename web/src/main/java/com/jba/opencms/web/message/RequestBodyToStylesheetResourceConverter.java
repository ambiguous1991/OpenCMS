package com.jba.opencms.web.message;

import com.jba.opencms.file.FileFacadeService;
import com.jba.opencms.type.file.Stylesheet;

import java.util.List;
import java.util.stream.Collectors;

public class RequestBodyToStylesheetResourceConverter extends AbstractConverter<Stylesheet> {
    private FileFacadeService fileService;

    public RequestBodyToStylesheetResourceConverter(FileFacadeService fileService, String... supportedParams) {
        super(supportedParams);
        this.fileService=fileService;
    }

    @Override
    protected List<Stylesheet> extract(List<String> tokens) {
        return tokens.stream()
                .map(element -> fileService.stylesheet().findOne(Long.parseLong(element), true))
                .collect(Collectors.toList());
    }
}
