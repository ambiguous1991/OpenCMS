package com.jba.opencms.web.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

public class AmazonS3FileRepository implements FileRepository {

    private AmazonS3 repository;
    private String bucketName;

    public AmazonS3FileRepository(AmazonS3 repository, String bucketName) {
        this.repository = repository;
        this.bucketName=bucketName;
    }

    @Override
    public InputStream get(String path) throws IOException {
        S3Object object = repository.getObject(bucketName, path);
        return object.getObjectContent();
    }

    @Override
    public void save(String path, String filename, File file) throws IOException {
        repository.putObject(bucketName, path+filename, file);
    }

    @Override
    public boolean delete(String path) throws IOException {
        repository.deleteObject(bucketName, path);
        return true;
    }

    @Override
    public void update(String path, String filename, File file) throws IOException {
        boolean isSuccessful = delete(path + filename);
        if(isSuccessful){
            save(path, filename, file);
        }
        else throw new IOException("Deletion of "+filename+" was unsuccessful!");
    }

    @Override
    public String list(String path) throws IOException {
        ObjectListing objectListing = repository.listObjects(bucketName, path);
        String listing = objectListing.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.joining("\n"));
        return listing;
    }
}
