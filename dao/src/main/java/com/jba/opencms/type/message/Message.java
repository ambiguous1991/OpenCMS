package com.jba.opencms.type.message;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "message")
public class Message extends BaseTypeSimpleKey<Message> {

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID_FROM")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID_TO", nullable = false)
    private User userTo;

    @ManyToOne
    @JoinColumn(name="FK_STATUS_ID", nullable = false)
    private Status status;

    @Column(name = "RESPONSE_EMAIL")
    private String responseEmail;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

}
