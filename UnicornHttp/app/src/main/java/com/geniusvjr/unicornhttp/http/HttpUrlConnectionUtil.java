package com.geniusvjr.unicornhttp.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.apache.http.HttpStatus;

/**
 * Created by dream on 16/6/2.
 */
public class HttpUrlConnectionUtil {

    public static String execute(Request request) throws IOException {
        switch (request.method) {
            case GET:
                return get(request);
            case POST:
                return post(request);
            case PUT:
                return get(request);
            case DELETE:
                return get(request);
        }

        return null;
    }



    private static String get(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);

        addHeader(connection, request.headers);

        int status = connection.getResponseCode();
        if (status == HttpStatus.SC_OK) {
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
            return new String(out.toByteArray());
        }
        return null;
    }


    private static String post(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);
        connection.setDoOutput(true);


        addHeader(connection, request.headers);

        OutputStream os = connection.getOutputStream();
        os.write(request.content.getBytes());

        int status = connection.getResponseCode();
        if (status == HttpStatus.SC_OK) {
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
            return new String(out.toByteArray());
        }
        return null;
    }





    private static void addHeader(HttpURLConnection connection, Map<String, String> headers){
        if(headers == null || headers.size() == 0){
            return;
        }

        for(Map.Entry<String, String> entry : headers.entrySet()){
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }
}