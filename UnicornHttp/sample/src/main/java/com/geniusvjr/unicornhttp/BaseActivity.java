package com.geniusvjr.unicornhttp;

import android.support.v7.app.ActionBarActivity;

import com.geniusvjr.http.AppException;
import com.geniusvjr.http.OnGlobalExceptionListener;

/**
 * Created by dream on 16/6/4.
 */
public class BaseActivity extends ActionBarActivity implements OnGlobalExceptionListener{

    @Override
    public boolean handleException(AppException e) {
        if (e.statusCode == 403) {
            if("token invalid".equals(e.responseMessage)) {
//                        TODO relogin
                return true;
            }
        }
        return false;
    }
}
