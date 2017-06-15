package com.cyy.mywidget.myviewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * Created by study on 17/6/13.
 * 自定义的ViewGroup
 */

public class ViewPager extends ViewGroup {

    private static String TAG = "MY-ViewPager";

    private int mTouchSlop;

    private boolean isBeginedDrag;

    private int mLastMotionX;
    private int mLastMotionY;

    public ViewPager(Context context) {
        super(context);
        init();
    }

    public ViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG , "onInterceptTouchEvent====="+ev);
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:

                mLastMotionX = (int) ev.getX();
                mLastMotionY = (int) ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                int deltaX = (int) (mLastMotionX - ev.getX());
                int deltaY = (int) (mLastMotionY - ev.getY());

                if (deltaX > mTouchSlop && deltaX > deltaY){
                    Log.e(TAG , "x=====");
                    isBeginedDrag = true;
                }else if (deltaY > mTouchSlop){
                    Log.e(TAG , "y=====");
                    isBeginedDrag = true;
                }

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return isBeginedDrag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.e(TAG , "onTouchEvent====="+event);

        return true;
    }
}
