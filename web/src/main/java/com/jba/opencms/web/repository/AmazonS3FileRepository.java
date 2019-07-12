package com.jba.opencms.web.repository;

import com.amazonaws.services.s3.AmazonS3;

import java.io.File;
import java.io.IOException;

public class AmazonS3FileRepository implements FileRepository {

    private AmazonS3 bucketClient;
    private String bucketName;

    public AmazonS3FileRepository(AmazonS3 bucketClient, String bucketName) {
        this.bucketClient = bucketClient;
        this.bucketName=bucketName;
    }

    @Override
    public File get(String path) throws IOException {
        return null;
    }

    @Override
    public void save(File file) throws IOException {

    }

    @Override
    public boolean delete(File file) throws IOException {
        return false;
    }
}
