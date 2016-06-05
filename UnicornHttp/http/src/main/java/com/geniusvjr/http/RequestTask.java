package com.geniusvjr.http;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by Stay on 28/6/15.
 * Powered by www.stay4it.com
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {


    private Request request;

    public RequestTask(Request request) {
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        try {
            HttpURLConnection connection = HttpUrlConnectionUtil.execute(request);
            if (request.enableProgressUpdated){
                return request.iCallback.parse(connection, new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(curLen,totalLen);
                    }
                });
            }else {
                return request.iCallback.parse(connection);
            }
        } catch (AppException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof AppException) {
            if (request.onGlobalExceptionListener != null){
                if(!request.onGlobalExceptionListener.handleException((AppException)o)){
                    request.iCallback.onFailure((AppException) o);
                }
            }
        } else {
            request.iCallback.onSuccess(o);
        }


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallback.onProgressUpdated(values[0],values[1]);

    }
}
