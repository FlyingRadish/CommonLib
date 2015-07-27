package com.quxue.common.network.upload;

import java.io.InputStream;

/**
 * Created by houxg on 2015/7/20.
 */
public interface UploadParam {
    public final static int TYPE_STREAM = 1;
    public final static int TYPE_KEY_VAL = 2;

    String getHead();

    InputStream getInputStream();

    int getType();
}
