package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeCompoundKey;
import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "MENU_ENTRY")
public class MenuEntry extends BaseTypeCompoundKey<Menu, Entry> {

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_MENU_ID", nullable = false)
    @ToString.Exclude
    private Menu menu;

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_ENTRY_ID", nullable = false)
    private Entry entry;

    @Column(name = "ACTIVE")
    private Boolean isActive;

    public static MenuEntry of(Menu menu, Entry entry, Boolean isActive){
        MenuEntry menuEntry = new MenuEntry();
        menuEntry.entry=entry;
        menuEntry.isActive=isActive;
        menuEntry.menu=menu;
        return menuEntry;
    }

    @Override
    public String toString() {
        return "MenuEntry{" +
                "menu=" + menu.getId() +
                ", entry=" + entry +
                ", isActive=" + isActive +
                '}';
    }
}
