package com.jba.opencms.web.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.jba.opencms.web.configuration.AWSS3Configuration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("aws")
@Import({AWSS3Configuration.class})
@TestPropertySource(locations = "/application.properties")
@Slf4j
public class AmazonS3FileRepositoryTest {

    @TestConfiguration
    static class AWSS3TestConfiguration{
        @Bean
        public AmazonS3FileRepository repository(AmazonS3 repository, @Value("${cloud.aws.bucket}") String bucketname){
            return new AmazonS3FileRepository(repository, bucketname);
        }
    }

    @Autowired
    private AmazonS3FileRepository repository;

    @Test
    @Order(1)
    @Ignore
    public void save(){
        String writeToFile = "Sample content";
        String path = "test/test.txt";

        InputStream input = new ByteArrayInputStream(writeToFile.getBytes());
        repository.save(path, input);
    }

    @Test
    @Order(2)
    @Ignore
    public void update() throws FileNotFoundException {
        String writeToFile = "New sample content";
        String path = "test/test.txt";

        InputStream inputStream = new ByteArrayInputStream(writeToFile.getBytes());
        repository.update(path, inputStream);
    }

    @Test
    @Order(3)
    @Ignore
    public void delete() throws FileNotFoundException{
        String path = "test/test.txt";
        boolean successful = repository.delete(path);
        assertThat(successful, is(true));
    }

    @Test
    @Order(4)
    public void listTest() {
        List<String> list = repository.list("resources/css");
        list.forEach(log::info);
        assertThat("Downloaded sources should not be empty!", list, not(empty()));
    }

    @Test
    @Order(5)
    public void getTest() throws IOException{
        InputStream inputStream = repository.get("resources/css/opencms.css");
        byte[] buffer = FileCopyUtils.copyToByteArray(inputStream);
        inputStream.close();

        File temp = new File("src/test/resources/opencms.css");
        OutputStream os = new FileOutputStream(temp);
        os.write(buffer);
        os.close();

        File temp2 = new File("src/test/resources/opencms.css");
        assertThat(temp2.exists(), is(true));
        byte[] buffer2 = FileCopyUtils.copyToByteArray(temp2);

        assertThat(buffer.length, equalTo(buffer2.length));
        boolean wasDeletionSuccessful = temp2.delete();
    }
}