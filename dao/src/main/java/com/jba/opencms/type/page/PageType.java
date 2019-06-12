package com.jba.opencms.type.page;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "page_type")
@Data
public class PageType extends BaseTypeSimpleKey<PageType> {

    @Column(name = "LAYOUT_TITLE", nullable = false, length = 100)
    private String layoutTitle;

    @Column(name = "LAYOUT_NAME", nullable = false, length = 100)
    private String layoutName;

    @ManyToOne
    @JoinColumn(name = "FK_IMAGE_ID", nullable = true)
    private Image image;

}
