package com.cyy.gatherlib;

import android.app.Application;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by study on 17/7/19.
 *
 *  自动收集的方法有
 *
 *  ### activity
 *      * activity.onResume
 *      * activity.onPause
 *
 *  ### fragment
 *      * fragment.onCreatedView
 *      * fragment.onResume
 *      * fragment.onPause
 *      * fragment.onHiddenChanged
 *
 * ### all View.OnClickListener.onClick
 */

public class Gather {

    static boolean DEBUG = true;

    private static Gather gather;
    private Gather(){}

    public static Gather gather(){
        if (gather == null){
            synchronized (Gather.class){
                gather = new Gather();
            }
        }
        return gather;
    }

    private GatherCallback callback;

    /**
     * 统计fragment
     */
    private Set<Fragment> resumeFragments = new HashSet<>(5);
    @Nullable private Fragment currentFragment; //当先显示的fragment

    public void init(Application app , GatherCallback callback){
        this.callback = callback;
        app.registerActivityLifecycleCallbacks(new ActivityLifeCycleCallback());
    }

    void onViewClick(View view , String viewPath){
        callback.onViewClick(view , viewPath);
    }

    void onFragmentCreatedView(Fragment fragment , View contentView){}

    void onFragmentResumeOrPause(Fragment fragment , boolean isResume){
        log("onFragmentResumeOrPause" + fragment + " is isResume = "+ isResume);

        View fragmentRootView = fragment.getView();
        boolean isVisible = fragment.isAdded() && !fragment.isHidden()
                && fragmentRootView!=null && fragmentRootView.getVisibility() == View.VISIBLE;
        if (isResume){
            if (resumeFragments.add(fragment)){
                //根据 add hidden 和 rootView的状态判断fragment是否是隐藏的
                //只对显示的发送事件
                if (isVisible)
                    dispatchFragmentVisibleEvent(fragment , true , true);
            }
        }else {
            if (resumeFragments.remove(fragment)){
                if (isVisible)
                    dispatchFragmentVisibleEvent(fragment , false , true);
            }
            currentFragment = null;
        }
        log("resumeFragments size == " + resumeFragments.size());
    }

    void onFragmentHiddenChanged(Fragment fragment , boolean isHidden){
        log("onFragmentHiddenChanged" + fragment + " is hidden = "+ isHidden);
        if (resumeFragments.contains(fragment)){
            dispatchFragmentVisibleEvent(fragment , !isHidden , false);
        }else {
//            log("该fragment不再Resume状态 出现这种状态应该是不合法的 ");
//            throw  new IllegalStateException("该fragment不再Resume状态");
        }
    }

    private void dispatchFragmentVisibleEvent(Fragment fragment , boolean isVisible , boolean isFromResume){
        if (isVisible){
            currentFragment = fragment;
        }else {
            currentFragment = null;
        }
        callback.onFragmentVisibleChanged(fragment.getClass().getSimpleName() , isVisible);
    }

    /**
     * 返回当前显示的fragment
     * 可能为空
     * @return current showing Fragment
     */
    @Nullable Fragment getCurrentFragment(){
        return currentFragment;
    }

    void onFragmenSettUserVisibleHint(Fragment fragment , boolean isVisibleToUser){
        Log.e("GATHER" , "onFragmenSettUserVisibleHint fragment name == " +
                fragment.getClass().getSimpleName() +" isVisibleToUser ===" +isVisibleToUser +"");
    }

    /**
     * collection activity show
     */
    void activityOnResume(String activityName){
        callback.onActivityVisibleChanged(activityName , true);
    }

    /**
     * collection activity hide
     */
    void activityOnPause(String activityName){
        callback.onActivityVisibleChanged(activityName , false);
    }

    static void log(String msg){
        if (DEBUG){
            Log.e("GATHER" , msg);
        }
    }

}
