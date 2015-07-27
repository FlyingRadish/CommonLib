package com.quxue.common.network.upload;

import android.os.Build;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.quxue.common.CancelRunnable;
import com.quxue.common.logger.Log;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * HTTP上传实现
 */
public abstract class Uploader extends CancelRunnable {
    private final static int DEFAULT_BUFFER_SIZE = 4 * 1024;
    private final static String ENDL = "\r\n";
    private final static String PREFIX = "--";
    private final static String CHARSET = "UTF-8";
    protected List<UploadParam> params;
    String url;
    String boundaryStr = "----Webkit" + java.util.UUID.randomUUID().toString(); //仿IE
    InputStream stream;

    Response.ErrorListener errorListener;

    public Uploader(String url, List<UploadParam> params, Response.ErrorListener errorListener) {
        this.url = url;
        this.params = params;
        this.errorListener = errorListener;
    }

    public Response.ErrorListener getErrorListener() {
        return errorListener;
    }

    protected void deliveryError(VolleyError error) {
        if (errorListener != null) {
            errorListener.onErrorResponse(error);
        }
    }

    @Override
    public void run() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundaryStr);
            if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                conn.setRequestProperty("Connection", "close");
            }
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30 * 1000);
            conn.setChunkedStreamingMode(DEFAULT_BUFFER_SIZE);

            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            if (params != null && params.size() > 0) {
                for (UploadParam param : params) {
                    if (param == null) {
                        continue;
                    }
                    stream = param.getInputStream();
                    if (stream == null) {
                        continue;
                    }
                    String bound = getBoundary(param.getHead()) + ENDL; //必须加多一个换行
                    out.write(bound.getBytes());
                    do {
                        int numRead = stream.read(buffer);
                        if (numRead < 0) {
                            out.flush();
                            stream.close();
                            break;
                        }
                        out.write(buffer, 0, numRead);
                    } while (isRunning());
                    out.write(ENDL.getBytes());
                }
            }
            String end = getBoundaryEnd();
            out.write(end.getBytes());
            out.flush();



            int responseCode;
            responseCode = conn.getResponseCode();
            InputStream in;
            if (responseCode == 200) {
                in = new BufferedInputStream(conn.getInputStream());
            } else {
                in = new BufferedInputStream(conn.getErrorStream());
            }
            String result = readInStream(in);
            in.close();
            out.close();
            conn.disconnect();
            deliverResponse(result);
        } catch (IOException e) {
            e.printStackTrace();
            deliveryError(new VolleyError(e.getCause()));
        }
    }

    private String readInStream(InputStream in) {
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private String getBoundary(String head) throws IOException {
        return PREFIX + boundaryStr + ENDL + head + ENDL;
    }

    private String getBoundaryEnd() throws IOException {
        return PREFIX + boundaryStr + PREFIX + ENDL;
    }

    abstract void deliverResponse(String rsp);


}