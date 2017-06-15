package com.cyy.mywidget.myviewgroup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cyy.mywidget.R;

public class MyViewPagerSimple extends AppCompatActivity {

    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_my_view_gropu_simple);
        initView();

        //system
        viewPager.setAdapter(new MyPageAdapter());

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    class MyPageAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(android.view.ViewGroup container, int position) {
            Button textView = new Button(MyViewPagerSimple.this);
            textView.setText(""+position);
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.gravity = Gravity.TOP;
            container.addView(textView , lp);
            return textView;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
