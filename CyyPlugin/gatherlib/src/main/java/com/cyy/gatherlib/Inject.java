package com.cyy.gatherlib;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by study on 17/7/21.
 *
 */

public class Inject {

    /**
     * collect click event
     *
     * 这个方法会通过ASM注入到{@link android.view.View.OnClickListener#onClick(View)} 的第一行
     * @param view event for view
     */
    public static void onClickView(View view){
        String activityname = view.getContext().getClass().getSimpleName();
        Log.i("Collection" , activityname);
        ViewPath viewPath = new ViewPath(view);
        Log.i("Collection" , viewPath.getPath() );
        Gather.gather().onViewClick(view , viewPath.getPath());
    }

    public static void onFragmentCreatedView(Fragment fragment , View view){
        Gather.gather().onFragmentCreatedView(fragment , view);
    }

    public static void onFragmentResumeOrPause(Fragment fragment , boolean isResume){
        Gather.gather().onFragmentResumeOrPause(fragment , isResume);
    }

    public static void onHiddenChanged(Fragment fragment , boolean isHidden){
        Gather.gather().onFragmentHiddenChanged(fragment , isHidden);
    }

    public static void onFragmentSettUserVisibleHint(Fragment fragment , boolean isVisibleToUser){
        Gather.gather().onFragmenSettUserVisibleHint(fragment , isVisibleToUser);
    }

    public static void customGather(String key , String value){

    }


}
