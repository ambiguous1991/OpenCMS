package com.jba.opencms.type.user;

import com.jba.opencms.type.base.BaseTypeCompoundKey;
import com.jba.opencms.type.file.File;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image_user")
public class ImageUser extends BaseTypeCompoundKey<File, User> {

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "FK_FILE_ID")
    private File imageId;

    @Column(name = "PROFILE", nullable = false)
    private Boolean isProfilePicture;

}
