package com.jba.opencms.image;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.image.Image;

public class ImageServiceImpl extends AbstractService<Image> implements ImageService {

    public ImageServiceImpl(GenericDao<Image> dao) {
        super(dao);
    }

}
