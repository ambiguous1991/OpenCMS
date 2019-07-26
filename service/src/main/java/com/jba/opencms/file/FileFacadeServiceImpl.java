package com.jba.opencms.file;

public class FileFacadeServiceImpl implements FileFacadeService {

    private ScriptService scriptService;
    private StylesheetService stylesheetService;
    private FileService fileService;

    public FileFacadeServiceImpl(ScriptService scriptService, StylesheetService stylesheetService, FileService fileService) {
        this.scriptService = scriptService;
        this.stylesheetService = stylesheetService;
        this.fileService = fileService;
    }

    @Override
    public ScriptService script() {
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
