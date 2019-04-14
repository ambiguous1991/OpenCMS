package com.jba.opencms.type.message;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "MESSAGE")
public class Message extends BaseTypeSimpleKey<Message> {

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID_FROM")
    private User from;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID_TO")
    private User to;

    @Column(name = "RESPONSE_EMAIL")
    private String responseEmail;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

}
