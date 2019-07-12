package com.jba.opencms.web.configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.jba.opencms.image.ImageService;
import com.jba.opencms.web.repository.AmazonS3FileRepository;
import com.jba.opencms.web.repository.DatabaseImageRepository;
import com.jba.opencms.web.repository.FileRepository;
import com.jba.opencms.web.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class RepositoryConfiguration {
    @Bean
    public ImageRepository imageRepository(ImageService imageService){
        log.info("Initializing "+ImageRepository.class.getSimpleName()+" with: "+imageService.toString());
        return new DatabaseImageRepository(imageService);
    }

    @Bean
    @Profile("aws")
    public FileRepository fileRepository(AmazonS3 bucketClient, @Value("${cloud.aws.bucket}") String bucketName){
        log.info("Initializing "+FileRepository.class+" with: "+bucketClient.getClass().getSimpleName()+" and bucket name: "+bucketName);
        return new AmazonS3FileRepository(bucketClient, bucketName);
    }
}
