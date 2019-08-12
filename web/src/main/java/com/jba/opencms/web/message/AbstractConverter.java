package com.jba.opencms.web.message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractConverter<T> implements RequestBodyToResourceConverter<T> {

    private final String PARAM_NAME;
    private final Predicate<String> startsWithParam;

    protected AbstractConverter(String parameterName) {
        PARAM_NAME = parameterName;
        startsWithParam = el -> el.startsWith(PARAM_NAME);
    }

    @Override
    public final boolean applies(String string) {
        return string.contains(PARAM_NAME);
    }

    @Override
    public final List<T> read(String body) {
        return extract(tokenize(body));
    }

    protected final List<String> tokenize(String input) {
        String[] parameters = input.split("&");
        List<String> result = Arrays.stream(parameters)
                .filter(startsWithParam)
                .map(element -> element.substring(element.indexOf('=')+1))
                .collect(Collectors.toList());
        return result;
    }

    protected abstract List<T> extract(List<String> tokens);
}
