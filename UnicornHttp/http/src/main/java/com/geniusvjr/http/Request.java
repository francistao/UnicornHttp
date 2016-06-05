package com.geniusvjr.http;

import java.util.Map;

/**
 * Created by Stay on 24/6/15.
 * Powered by www.stay4it.com
 */
public class Request {
    public ICallback iCallback;
    public boolean enableProgressUpdated = false;
    public OnGlobalExceptionListener onGlobalExceptionListener;

    public void setCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }

    public void enableProgressUpdated(boolean enable) {
        this.enableProgressUpdated = enable;
    }

    public void setGlobalExceptionListener(OnGlobalExceptionListener onGlobalExceptionListener) {
        this.onGlobalExceptionListener = onGlobalExceptionListener;
    }

    public enum RequestMethod {GET, POST, PUT, DELETE}


    public String url;
    public String content;
    public Map<String, String> headers;

    public RequestMethod method;

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method = RequestMethod.GET;
    }
}
