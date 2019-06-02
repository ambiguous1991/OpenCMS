package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "entry")
public class Entry extends BaseTypeSimpleKey<Entry> {

    @Column(name = "LABEL")
    private String label;

    @ManyToOne
    @JoinColumn(name = "FK_PAGE_ID")
    private Page page;

    @ManyToOne
    @JoinColumn(name = "FK_MENU_ID")
    @ToString.Exclude
    private Menu menu = null;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Entry> subentires = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FK_ENTRY_PARENT_ID")
    @ToString.Exclude
    private Entry parent;

    public static Entry of(String label){
        Entry entry = new Entry();
        entry.setLabel(label);
        return entry;
    }
}
