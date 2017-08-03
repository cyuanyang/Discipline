package com.cyy.cyyplugin;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.cyy.gatherlib.Gather;
import com.cyy.gatherlib.GatherCallback;

/**
 * Created by study on 17/7/19.
 *
 *
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Gather.gather().init(this, new GatherCallback() {
            @Override
            public void onViewClick(View view, String path) {

                Log.e("App" , path );

                /*上传 path 就知道点击的哪一个view
                ....
                */
            }

            @Override
            public void onActivityVisibleChanged(String name, boolean isVisible) {
                Log.e("App" , "name = "+ name  + " isVisible=" + isVisible);
            }

            @Override
            public void onFragmentVisibleChanged(String fragmentName, boolean isVisible) {
                Log.e("App" , "fragmentName = "+ fragmentName  + " isVisible=" + isVisible);
            }
        });
    }
}
