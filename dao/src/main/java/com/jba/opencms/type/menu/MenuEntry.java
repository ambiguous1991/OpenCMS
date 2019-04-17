package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "MENU_ENTRY")
public class MenuEntry extends BaseTypeSimpleKey<MenuEntry> {

    @ManyToOne
    @JoinColumn(name = "FK_MENU_ID", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "FK_ENTRY_ID", nullable = false)
    private Entry entry;

    @Column(name = "ACTIVE")
    private Boolean isActive;

}
