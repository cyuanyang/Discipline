package com.cyy.mywidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cyy.mywidget.myviewgroup.MyViewPagerSimple;
import com.cyy.mywidget.scrollview.MyScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected MyScrollView scrollView;
    protected LinearLayout scrollViewContainer;
    protected Button myScrollViewBtn;
    protected Button myViewGroupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();


    }


    private void initView() {
        scrollView = (MyScrollView) findViewById(R.id.scrollView);
        scrollViewContainer = (LinearLayout) findViewById(R.id.scrollViewContainer);
        myScrollViewBtn = (Button) findViewById(R.id.myScrollViewBtn);
        myScrollViewBtn.setOnClickListener(MainActivity.this);
        myViewGroupBtn = (Button) findViewById(R.id.myViewGroupBtn);
        myViewGroupBtn.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.myScrollViewBtn) {

        } else if (view.getId() == R.id.myViewGroupBtn) {
            startActivity(new Intent(this , MyViewPagerSimple.class));
        }
    }
}
