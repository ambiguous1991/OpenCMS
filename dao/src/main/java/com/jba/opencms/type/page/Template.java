package com.jba.opencms.type.page;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "template")
public class Template extends BaseTypeSimpleKey<Template> {

    @Column(name = "LAYOUT_TITLE", nullable = false, length = 100)
    private String layoutTitle;

    @Column(name = "LAYOUT_NAME", nullable = false, length = 200)
    private String layoutName;

    @Column(name = "CONTENT")
    private String content;

}
