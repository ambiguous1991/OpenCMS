package com.jba.opencms.web.form.resource;

import com.jba.opencms.web.type.resource.ResourceWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceForm {
    public List<ResourceWrapper> availableResources;
    public List<ResourceWrapper> pageResources;
}
