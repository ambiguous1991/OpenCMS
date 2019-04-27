package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "MENU")
public class Menu extends BaseTypeSimpleKey<Menu> {

    @OneToMany(mappedBy = "menu")
    private List<MenuEntry> menuEntryList;

    @Column(name = "ACTIVE", length = 1, nullable = false)
    private Boolean isActive = Boolean.FALSE;

    @Column(name = "NAME")
    private String name;
}
