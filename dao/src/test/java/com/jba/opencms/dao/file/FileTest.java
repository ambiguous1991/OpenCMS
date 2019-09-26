package com.jba.opencms.dao.file;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.TestDatasourceConfiguration;
import com.jba.opencms.dao.ifs.FileDao;
import com.jba.opencms.type.file.projection.FileProjection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatasourceConfiguration.class, DaoConfiguration.class})
@EnableTransactionManagement
public class FileTest extends BaseSpringIntegrationTest {

    @Autowired
    FileDao fileDao;

    @Test
    public void getImageProjection(){
        List<FileProjection> imageProjections = fileDao.getImagesMetadata();

        imageProjections.forEach(item->logger.info(item));
    }


}