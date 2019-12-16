package com.idgenerali.backendmybatis.model.upload;

import java.io.Serializable;
import java.util.Arrays;

public class UploadFile implements Serializable {

    private String id;
    private String fileName;
    private String fileType;
    private byte[] fileData;

    public UploadFile(){}


    public UploadFile(String id, String fileName, String fileType, byte[] fileData) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileData=" + Arrays.toString(fileData) +
                '}';
    }
}
