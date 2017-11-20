package com.cyy.cyyplugin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private YellowLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main2);

        Log.e("TAG", "Main2Activity");

        contentLayout = (YellowLayout) findViewById(R.id.contentLayout);

        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn2) {
            Animator anim = ViewAnimationUtils.createCircularReveal(
                    contentLayout,
                    0,
                    0,
                    contentLayout.getHeight(),
                    0);
            anim.setDuration(1000);
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    Log.e("---", "start anim");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Log.e("---", "end anim");
                    //show views
                }
            });
            anim.start();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static class YellowLayout extends LinearLayout{

        public YellowLayout(Context context) {
            super(context);
        }

        public YellowLayout(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public YellowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }
    }

    public static class PurpleLayout extends LinearLayout{

        public PurpleLayout(Context context) {
            super(context);
        }

        public PurpleLayout(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public PurpleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return super.onTouchEvent(event);
        }
    }

    public static class GreenLayout extends LinearLayout{

        public GreenLayout(Context context) {
            super(context);
        }

        public GreenLayout(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public GreenLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            switch (action){
               case MotionEvent.ACTION_DOWN:
                return true;
            }
            return super.onTouchEvent(event);
        }
    }

    public static class RedLayout extends LinearLayout{

        public RedLayout(Context context) {
            super(context);
        }

        public RedLayout(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public RedLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }
    }


}
