package com.geniusvjr.http;

import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Stay on 4/7/15.
 * Powered by www.stay4it.com
 */
public abstract class AbstractCallback<T> implements ICallback<T> {

    private String path;

    @Override
    public T parse(HttpURLConnection connection) throws Exception {
        int status = connection.getResponseCode();
        if (status == HttpStatus.SC_OK) {
            if(path == null){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                is.close();
                out.flush();
                out.close();
                String result = new String(out.toByteArray());
                return bindData(result);
            }else {
                FileOutputStream out = new FileOutputStream(path);
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                is.close();
                out.flush();
                out.close();
                return bindData(path);
            }

        }
        return null;
    }

    protected abstract T bindData(String result) throws Exception;

    public ICallback setCachePath(String path) {
        this.path = path;
        return this;
    }

}
