package com.jba.opencms.web.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.Transfer;
import com.amazonaws.services.s3.transfer.TransferManager;

import java.io.File;
import java.io.IOException;

public class AmazonS3FileRepository implements FileRepository {

    private AmazonS3 AWSs3;
    private TransferManager transferManager;
    private String bucketName;

    @Override
    public File get(String path) throws IOException {
        Transfer download = transferManager.download(bucketName, "key", new File(path));
        return null;
    }

    @Override
    public void save(File file) throws IOException {
        Transfer transfer = transferManager.upload(bucketName, "awd", file);
        try {
            transfer.waitForCompletion();
            Transfer.TransferState state = transfer.getState();
            if(state== Transfer.TransferState.Failed || state== Transfer.TransferState.Canceled){
                throw new IOException("Failure during file upload. "+transfer.getDescription());
            }
        }
        catch (InterruptedException e){
            throw new IOException("Interrupted exception during upload!", e);
        }
    }

    @Override
    public boolean delete(File file) throws IOException {
        return false;
    }
}
