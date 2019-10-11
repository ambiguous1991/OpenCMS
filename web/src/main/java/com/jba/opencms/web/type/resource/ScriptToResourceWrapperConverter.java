package com.jba.opencms.web.type.resource;

import com.jba.opencms.type.file.File;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class ScriptToResourceWrapperConverter implements Converter<List<File>, List<ResourceWrapper>> {
    @Override
    public List<ResourceWrapper> convert(List<File> source) {
        return source.stream()
                .map(ResourceWrapper::new)
                .collect(Collectors.toList());
    }
}
