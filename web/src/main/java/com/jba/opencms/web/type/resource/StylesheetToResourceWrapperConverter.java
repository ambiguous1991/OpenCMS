package com.jba.opencms.web.type.resource;

import com.jba.opencms.type.file.Stylesheet;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class StylesheetToResourceWrapperConverter implements Converter<List<Stylesheet>, List<ResourceWrapper>> {
    @Override
    public List<ResourceWrapper> convert(List<Stylesheet> source) {
        return source.stream()
                .map(ResourceWrapper::new)
                .collect(Collectors.toList());
    }
}
