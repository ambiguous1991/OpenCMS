package com.jba.opencms.web.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.jba.opencms.web.repository.AmazonS3FileRepository;
import com.jba.opencms.web.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("aws")
@Slf4j
public class AWSS3Configuration {
    @Bean
    public AWSCredentials awsCredentials(@Value("${cloud.aws.credentials.accessKey}") String accessKey, @Value("${cloud.aws.credentials.secretKey}") String secretKey){
        log.info("Retrieved accessKey: "+accessKey);
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonS3 bucketClient(AWSCredentials awsCredentials, Regions region){
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_WEST_3)
                .build();
    }

    @Bean
    public Regions regionByString(@Value("${cloud.aws.region}") String region){
        if(region.equals("EU-Paris")){
            return Regions.EU_WEST_3;
        }
        else return Regions.EU_CENTRAL_1;
    }

    @Bean
    public FileRepository fileRepository(AmazonS3 bucketClient, @Value("${cloud.aws.bucket}") String bucketName) {
        log.info("Initializing " + FileRepository.class + " with: " + bucketClient.getClass().getSimpleName() + " and bucket name: " + bucketName);
        return new AmazonS3FileRepository(bucketClient, bucketName);
    }
}
