package com.jba.opencms.web.type.resource;

import com.jba.opencms.type.file.Script;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class ScriptToResourceWrapperConverter implements Converter<List<Script>, List<ResourceWrapper>> {
    @Override
    public List<ResourceWrapper> convert(List<Script> source) {
        return source.stream()
                .map(ResourceWrapper::new)
                .collect(Collectors.toList());
    }
}
