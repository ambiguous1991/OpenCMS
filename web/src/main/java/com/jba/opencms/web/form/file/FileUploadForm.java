package com.jba.opencms.web.form.file;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Getter
public class FileUploadForm {
    public FileUploadForm(StandardMultipartHttpServletRequest request){
        this.multipart=request.getFile("multipart");
        this.fileName=request.getParameter("fileName");
        this.filePath=request.getParameter("filePath");
        this.mimeContent=request.getParameter("mimeContent");
        this.fileSize=request.getParameter("fileSize");
    }
    private MultipartFile multipart;
    private String fileName;
    private String filePath;
    private String mimeContent;
    private String fileSize;
}
