package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@Table(name = "MENU")
public class Menu extends BaseTypeSimpleKey<Menu> {

    @OneToMany(mappedBy = "menu")
    private List<MenuEntry> menuEntryList;

    @Column(name = "ACTIVE", length = 1, nullable = false)
    private Boolean isActive = Boolean.FALSE;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MENU_ENTRY",
            joinColumns = {@JoinColumn(name = "FK_ENTRY_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_MENU_ID")}
    )
    @ToString.Exclude
    public List<Entry> entries;
}
