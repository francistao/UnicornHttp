package com.geniusvjr.http;

import android.os.AsyncTask;

import java.io.IOException;

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
            return HttpUrlConnectionUtil.execute(request);
        } catch (IOException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception) {
            request.iCallback.onFailure((Exception) o);
        } else {
            request.iCallback.onSuccess((String) o);
        }


    }
}
