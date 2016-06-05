package com.geniusvjr.http;

import java.net.HttpURLConnection;

/**
 * Created by Stay on 28/6/15.
 * Powered by www.stay4it.com
 */
public interface ICallback<T> {

    void onSuccess(T result);

    void onFailure(AppException e);

    T parse(HttpURLConnection connection, OnProgressUpdatedListener listener) throws AppException;
    T parse(HttpURLConnection connection) throws AppException;

    void onProgressUpdated(int curLen, int totalLen);

}
