package com.cyy.mvpsimple;

import android.app.Application;

import butterknife.ButterKnife;

/**
 * Created by study on 17/6/22.
 *
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
