package com.jba.opencms.type.menu;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "ENTRY")
public class Entry extends BaseTypeSimpleKey<Entry> {

    @ManyToOne
    @JoinColumn(name = "FK_PAGE_ID")
    private Page page;

    @Column(name = "LABEL")
    private String label;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SUBENTRY",
            joinColumns = {@JoinColumn(name = "FK_ENTRY_ID_CHILD")},
            inverseJoinColumns = {@JoinColumn(name="FK_ENTRY_ID_PARENT")}
    )
    private List<Entry> subentires;

    public static Entry of(String label){
        Entry entry = new Entry();
        entry.setLabel(label);
        return entry;
    }
}
