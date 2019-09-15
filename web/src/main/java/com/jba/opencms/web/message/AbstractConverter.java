package com.jba.opencms.web.message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractConverter<T> implements RequestBodyToResourceConverter<T> {

    private final List<String> SUPPORTS_PARAMS;
    private final Predicate<String> STARTS_WITH;

    protected AbstractConverter(String... parameterName) {
        SUPPORTS_PARAMS = Arrays.asList(parameterName);
        STARTS_WITH = element->SUPPORTS_PARAMS.stream().anyMatch(element::startsWith);
    }

    @Override
    public final boolean supports(String string) {
        return SUPPORTS_PARAMS.stream().anyMatch(element->element.contains(string));
    }

    @Override
    public final List<T> read(String body) {
        return extract(tokenize(body));
    }

    protected final List<String> tokenize(String input) {
        String[] parameters = input.split("&");
        List<String> result = Arrays.stream(parameters)
                .filter(STARTS_WITH)
                .map(element -> element.substring(element.indexOf('=')+1))
                .collect(Collectors.toList());
        return result;
    }

    protected abstract List<T> extract(List<String> tokens);
}
