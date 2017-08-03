package com.cyy.cyyplugin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cyy.cyyplugin.fragments.Fragment1;
import com.cyy.cyyplugin.fragments.Fragment2;
import com.cyy.cyyplugin.fragments.Fragment3;
import com.cyy.gatherlib.annotation.GatherActivity;

@GatherActivity(isGather = false)
public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    protected LinearLayout btnContainer;
    protected FrameLayout fragmentContainer;
    protected Button btn1;
    protected Button btn2;
    protected Button btn3;
    protected Button btn4;

    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    Fragment3 fragment3 = new Fragment3();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main3);
        initView();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment1, Fragment1.class.getName())
                .add(R.id.fragmentContainer, fragment2, Fragment2.class.getName())
                .add(R.id.fragmentContainer, fragment3, Fragment3.class.getName())
                .show(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .commit();

    }

    private void initView() {
        btnContainer = (LinearLayout) findViewById(R.id.btnContainer);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(Main3Activity.this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(Main3Activity.this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(Main3Activity.this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(Main3Activity.this);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        if (view.getId() == R.id.btn1) {
            t.hide(fragment2).hide(fragment3)
                    .show(fragment1).commit();
        } else if (view.getId() == R.id.btn2) {
            t.hide(fragment1).show(fragment2).hide(fragment3).commit();
        } else if (view.getId() == R.id.btn3) {
            t.hide(fragment2).hide(fragment1).show(fragment3).commit();
        } else if (view.getId() == R.id.btn4) {
            t.hide(fragment1).hide(fragment3).show(fragment2).commit();
        }
    }
}
