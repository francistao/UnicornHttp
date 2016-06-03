package com.geniusvjr.http;

/**
 * Created by Stay on 4/7/15.
 * Powered by www.stay4it.com
 */
public abstract class FileCallback extends AbstractCallback<String> {

    @Override
    protected String bindData(String path) throws Exception {


        return path;
    }


}
