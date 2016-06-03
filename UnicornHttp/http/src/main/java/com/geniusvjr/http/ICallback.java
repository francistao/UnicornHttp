package com.geniusvjr.http;

/**
 * Created by Stay on 28/6/15.
 * Powered by www.stay4it.com
 */
public interface ICallback {

    void onSuccess(String result);
    void onFailure(Exception e);
}
