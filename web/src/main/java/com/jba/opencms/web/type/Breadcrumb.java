package com.jba.opencms.web.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Breadcrumb {
    private String label;
    private String href;
}
