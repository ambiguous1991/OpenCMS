package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@Table(name = "ENTRY")
public class Entry extends BaseTypeSimpleKey<Entry> {

    @ManyToOne
    @JoinColumn(name = "FK_PAGE_ID")
    private Page page;

    @Column(name = "LABEL")
    private String label;

    @ManyToMany
    @JoinTable(
            name = "SUBENTRY",
            joinColumns = {@JoinColumn(name = "FK_ENTRY_ID_PARENT")},
            inverseJoinColumns = {@JoinColumn(name="FK_ENTRY_ID_CHILD")}
    )
    @ToString.Exclude
    private List<Entry> subentires;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MENU_ENTRY",
            joinColumns = {@JoinColumn(name = "FK_MENU_ID")},
            inverseJoinColumns= {@JoinColumn(name = "FK_ENTRY_ID")}
    )
    private List<Menu> menu;

    public static Entry of(String label){
        Entry entry = new Entry();
        entry.setLabel(label);
        return entry;
    }
}
