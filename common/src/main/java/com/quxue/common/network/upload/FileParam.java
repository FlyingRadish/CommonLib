package com.quxue.common.network.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by houxg on 2015/7/20.
 */
public class FileParam implements UploadParam {

    File file;
    String key = "";
    String contentType = "image/jpeg";

    public FileParam(String key, File file) {
        this.key = key;
        this.file = file;
    }

    @Override
    public String getHead() {
        return "Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + file.getName() + "\""
                + "\r\nContent-Type: " + contentType;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public int getType() {
        return UploadParam.TYPE_STREAM;
    }
}
