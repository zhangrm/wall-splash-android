package com.tbl.pumblr;

import android.app.Application;
import android.content.Context;

import com.tbl.pumblr.utils.SessionManager;


public class CustomApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        SessionManager.init(context);

        /*SMSSDK.initSDK(context,"7056c08ddf79","3d1941ff58be5fa88cb2d62deb5deca8");*/
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
