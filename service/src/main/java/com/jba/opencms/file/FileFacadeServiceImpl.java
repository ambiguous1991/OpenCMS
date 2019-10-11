package com.jba.opencms.file;

public class FileFacadeServiceImpl implements FileFacadeService {

    //TODO -- Remove this

    private FileService scriptService;
    private StylesheetService stylesheetService;
    private FileService fileService;

    public FileFacadeServiceImpl(FileService scriptService, StylesheetService stylesheetService, FileService fileService) {
        this.scriptService = scriptService;
        this.stylesheetService = stylesheetService;
        this.fileService = fileService;
    }

    @Override
    public FileService script() {
        return scriptService;
    }

    @Override
    public StylesheetService stylesheet() {
        return stylesheetService;
    }

    @Override
    public FileService file() {
        return fileService;
    }
}
