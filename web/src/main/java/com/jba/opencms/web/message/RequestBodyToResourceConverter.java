package com.jba.opencms.web.message;

import java.util.List;

public interface RequestBodyToResourceConverter<T> {
    boolean supports(String string);
    List<T> read(String body);
}
