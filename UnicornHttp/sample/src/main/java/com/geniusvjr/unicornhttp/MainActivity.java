package com.geniusvjr.unicornhttp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.geniusvjr.http.FileCallback;
import com.geniusvjr.http.JsonCallback;
import com.geniusvjr.http.Request;
import com.geniusvjr.http.RequestTask;

import java.io.File;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mRunOnSubThreadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRunOnSubThreadBtn = (Button) findViewById(R.id.mRunOnSubThreadBtn);
        mRunOnSubThreadBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mRunOnSubThreadBtn:
//                testHttpPostOnSubThread();
//                testHttpPostOnSubThreadForGeneric();
//                testHttpPostOnSubThreadForDownload();
                testHttpPostOnSubThreadForDownloadProgress();
                break;
        }
    }

    public void testHttpPostOnSubThread() {

        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        request.setCallback(new JsonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("stay", "testHttpGet return:" + result);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();
    }

    public void testHttpPostOnSubThreadForGeneric() {

        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        request.setCallback(new JsonCallback<User>() {

            @Override
            public void onSuccess(User result) {
                Log.e("stay", "testHttpGet return:" + result.toString());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }


        });
        request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();
    }

    public void testHttpPostOnSubThreadForDownload() {

        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        String path = Environment.getExternalStorageDirectory() + File.separator + "demo.txt";
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String path) {
                Log.e("stay",path);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }.setCachePath(path));
        request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();
    }

    public void testHttpPostOnSubThreadForDownloadProgress() {

        String url = "http://api.stay4it.com/uploads/test.jpg";
        Request request = new Request(url, Request.RequestMethod.GET);
        String path = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
        request.setCallback(new FileCallback() {

            @Override
            public void onSuccess(String path) {
                Log.e("stay",path);
            }

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onProgressUpdated(int curLen, int totalLen) {

            }
        }.setCachePath(path));
        request.enableProgressUpdated(true);
        RequestTask task = new RequestTask(request);
        task.execute();
    }
}
