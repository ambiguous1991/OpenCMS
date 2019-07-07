package com.jba.opencms.web.controller.admin.systemvariables.form;

import com.jba.opencms.type.system.SystemVariable;
import com.jba.opencms.web.controller.admin.systemvariables.validators.UniqueSystemVariable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UniqueSystemVariable
public class SystemVariableForm {

    private Long id;

    @Size(max = 100, min = 1)
    private String key;

    @NotNull
    private String value;

    protected OffsetDateTime created;

    protected OffsetDateTime updated;

    public static SystemVariableForm from(SystemVariable v){
        return new SystemVariableForm(v.getId(), v.key, v.value, v.getCreated(), v.getUpdated());
    }
}
