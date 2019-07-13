package com.jba.opencms.web.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class AmazonS3FileRepository implements FileRepository {

    private final static String MESSAGE_INVALID_PATH="Path %s is invalid. S3Bucket paths cannot start with '/'!";
    private final static String MESSAGE_FILE_NOT_FOUND="File %s not found in bucket %s";

    private AmazonS3 repository;
    private String bucketName;


    public AmazonS3FileRepository(AmazonS3 repository, String bucketName) {
        this.repository = repository;
        this.bucketName=bucketName;
    }

    @Override
    public InputStream get(String path) throws FileNotFoundException, IllegalArgumentException{
        if(checkDoesFileExist(path)) {
            S3Object object = repository.getObject(bucketName, path);
            return object.getObjectContent();
        }
        else throw new FileNotFoundException(String.format(MESSAGE_FILE_NOT_FOUND, path, bucketName));
    }

    @Override
    public void save(String path, InputStream input) throws IllegalArgumentException{
        if(isPathInvalid(path)) throw new IllegalArgumentException(String.format(MESSAGE_INVALID_PATH, path));
        ObjectMetadata metadata = new ObjectMetadata();
        repository.putObject(bucketName, path, input, metadata);
    }

    @Override
    public boolean delete(String path) throws FileNotFoundException, IllegalArgumentException {
        if(checkDoesFileExist(path)) {
            repository.deleteObject(bucketName, path);
            return true;
        }
        else throw new FileNotFoundException(String.format(MESSAGE_FILE_NOT_FOUND, path, bucketName));
    }

    @Override
    public void update(String path, InputStream input) throws FileNotFoundException, IllegalArgumentException {
        if(checkDoesFileExist(path)) {
            boolean isSuccessful = delete(path);
            if (isSuccessful) {
                save(path, input);
            }
        }
        else throw new FileNotFoundException(String.format(MESSAGE_FILE_NOT_FOUND, path, bucketName));
    }

    @Override
    public List<String> list(String path) throws IllegalArgumentException {
        if(isPathInvalid(path)) throw new IllegalArgumentException(String.format(MESSAGE_INVALID_PATH, path));
        ObjectListing objectListing = repository.listObjects(bucketName, path);
        List<String> listing = objectListing.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
        return listing;
    }

    private boolean checkDoesFileExist(String path) throws IllegalArgumentException{
        if(isPathInvalid(path)){
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_PATH, path));
        }
        ObjectListing objectListing = repository.listObjects(bucketName, path);
        return objectListing.getObjectSummaries().size()>0 && objectListing.getObjectSummaries().get(0).getKey().equals(path);
    }

    private boolean isPathInvalid(String path){
        return path.startsWith("/");
    }
}
