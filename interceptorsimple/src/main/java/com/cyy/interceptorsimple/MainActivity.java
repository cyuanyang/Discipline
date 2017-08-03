package com.cyy.interceptorsimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cyy.interceptorsimple.interceptor.FirstInterceptor;
import com.cyy.interceptorsimple.interceptor.Interceptor;
import com.cyy.interceptorsimple.interceptor.LastInterceptor;
import com.cyy.interceptorsimple.interceptor.RealChain;
import com.cyy.interceptorsimple.interceptor.SecondInterceptor;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button launchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.launchBtn) {

            //开始事件

            begin();
        }
    }

    private void initView() {
        launchBtn = (Button) findViewById(R.id.launchBtn);
        launchBtn.setOnClickListener(MainActivity.this);
    }

    private List<Interceptor> interceptors = new ArrayList<>();
    private void begin(){
        interceptors.add(new FirstInterceptor());
        interceptors.add(new SecondInterceptor());


        //这个要放在最后
        interceptors.add(new LastInterceptor());
        RealChain chain = new RealChain(interceptors , 0);
        chain.proceed();
    }
}
