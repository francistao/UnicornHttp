package com.geniusvjr.unicornhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.geniusvjr.http.ICallback;
import com.geniusvjr.http.Request;
import com.geniusvjr.http.RequestTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRunOnSubThreadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRunOnSubThreadBtn = (Button)findViewById(R.id.mRunOnSubThreadBtn);
        mRunOnSubThreadBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mRunOnSubThreadBtn:
                testHttpPostOnSubThread();
                break;
        }

    }

    private void testHttpPostOnSubThread() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";

        Request request = new Request(url, Request.RequestMethod.POST);
        request.setCallback(new ICallback() {
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
}
