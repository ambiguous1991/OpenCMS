package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "menu")
public class Menu extends BaseTypeSimpleKey<Menu> {

    @Column(name = "ACTIVE", length = 1, nullable = false)
    private Boolean isActive = Boolean.FALSE;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_MENU_ID")
    public List<Entry> entries = new ArrayList<>();

    @Override
    public String toString() {
        return "Menu{" +
                "isActive=" + isActive +
                ", name='" + name + '\'' +
                ", entries=" + entries +
                ", id=" + id +
                '}';
    }
}
