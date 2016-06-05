package com.geniusvjr.http;

/**
 * Created by Stay on 6/7/15.
 * Powered by www.stay4it.com
 */
public class AppException extends Exception {

    public int statusCode;
    public String responseMessage;

    public AppException(int status, String responseMessage) {
        super(responseMessage);
        this.statusCode = status;
        this.responseMessage = responseMessage;
    }

    public AppException(String detailMessage) {
        super(detailMessage);
    }
}
