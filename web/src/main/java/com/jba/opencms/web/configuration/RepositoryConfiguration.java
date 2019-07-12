package com.jba.opencms.web.configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.jba.opencms.image.ImageService;
import com.jba.opencms.web.repository.AmazonS3FileRepository;
import com.jba.opencms.web.repository.DatabaseImageRepository;
import com.jba.opencms.web.repository.FileRepository;
import com.jba.opencms.web.repository.ImageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ImageRepository imageRepository(ImageService imageService){
        return new DatabaseImageRepository(imageService);
    }

    @Bean
    @Profile("aws")
    public FileRepository fileRepository(AmazonS3 bucketClient){
        return new AmazonS3FileRepository(bucketClient);
    }
}
