package com.jba.opencms.type.page;

import com.jba.opencms.type.base.BaseTypeCompoundKey;
import com.jba.opencms.type.user.Authority;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "PAGE_AUTHORITY")
public class PageAuthority extends BaseTypeCompoundKey<Page, Authority> {

    @Id
    @JoinColumn(name = "FK_PAGE_ID")
    @ManyToOne
    private Page page;

    @Id
    @JoinColumn(name = "FK_AUTHORITY_ID")
    @ManyToOne
    private Authority authority;

}
