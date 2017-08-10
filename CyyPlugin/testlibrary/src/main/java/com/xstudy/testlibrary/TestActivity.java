package com.xstudy.testlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_test);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {

        }
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(TestActivity.this);
    }
}
