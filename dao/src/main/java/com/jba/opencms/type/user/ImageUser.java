package com.jba.opencms.type.user;

import com.jba.opencms.type.base.BaseTypeCompoundKey;
import com.jba.opencms.type.image.Image;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "IMAGE_USER")
public class ImageUser extends BaseTypeCompoundKey<Image, User> {

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    private Image imageId;

    @Column(name = "PROFILE", nullable = false)
    private Boolean isProfilePicture;

}
