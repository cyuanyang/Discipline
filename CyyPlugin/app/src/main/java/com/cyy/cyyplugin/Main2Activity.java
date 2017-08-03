package com.cyy.cyyplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    protected Button btn1;
    protected Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main2);

        Log.e("TAG", "Main2Activity");
        initView();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {

        } else if (view.getId() == R.id.btn2) {

        }
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(Main2Activity.this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(Main2Activity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
