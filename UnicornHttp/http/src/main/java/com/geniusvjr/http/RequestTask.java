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
            return request.iCallback.parse(connection);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception) {
            request.iCallback.onFailure((Exception) o);
        } else {
            request.iCallback.onSuccess(o);
        }


    }
}
