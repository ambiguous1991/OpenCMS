package com.jba.opencms.type.page;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "template")
public class ThymeleafTemplate extends BaseTypeSimpleKey<ThymeleafTemplate> {
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "CONTENT")
    private String content;
}
