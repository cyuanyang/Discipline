package com.cyy.gatherlib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.cyy.gatherlib.annotation.Collection;
import com.cyy.gatherlib.annotation.GatherActivity;

/**
 * Created by study on 17/7/19.
 *
 */

public class ActivityLifeCycleCallback implements Application.ActivityLifecycleCallbacks {

    private final String TAG = "ActivityLifeCycle";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (isGather(activity))
            Gather.gather().activityOnResume(activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (isGather(activity))
            Gather.gather().activityOnPause(activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private boolean isGather(Activity activity){

        GatherActivity gatherActivity = activity.getClass().getAnnotation(GatherActivity.class);
        if (gatherActivity != null){
            if (!gatherActivity.isGather()){
                return false;
            }
        }

        Collection collection = activity.getClass().getAnnotation(Collection.class);
        if (collection!=null){
            return collection.isGather();
        }
        return true;
    }

}
