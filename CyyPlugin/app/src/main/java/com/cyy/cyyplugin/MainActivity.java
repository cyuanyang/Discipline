package com.cyy.cyyplugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cyy.gatherlib.annotation.Collection;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    protected Button btn1;
    protected Button btn2;
    protected Button btn3;

    protected WaveView waveView;
    protected TextView textView;
    protected Button button4;

    WaveHelper helper;

    final OkHttpClient client = new OkHttpClient.Builder()
            .dispatcher(new Dispatcher())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, Main2Activity.class));

                gatherTest();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        Log.e("b----" , System.currentTimeMillis()+"");
//                        synchronized (MainActivity.this){
//                            try {
//                                MainActivity.this.wait(2000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
                        Log.e("a----" , System.currentTimeMillis()+"");
                        Request request = new Request.Builder()
                                .url("http://www.baidu.com")
                                .get()
                                .build();

                        Call call = client.newCall(request);
                        try {
                            Response response = call.execute();

                            Log.e("TAG" , response.body().string()+"");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        waveView.setShapeType(WaveView.ShapeType.SQUARE);
        waveView.setWaveColor(
                Color.parseColor("#28f16d7a"),
                Color.parseColor("#3cf16d7a"));
        waveView.setBorder(10, Color.GREEN);

        helper = new WaveHelper(waveView);

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(l, sm.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_UI);
    }

    private SensorEventListener l = new SensorEventListener() {

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            int d = (int) Math.toDegrees(Math.acos(x / 9.8));
            textView.setText("x=" + x + "\ny=" + y + "\nz=" + z + " \nd=" + d);
        }


        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.start();
    }

    @Collection
    private void gatherTest() {
        //测试自定义收集方法

    }


    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        waveView = (WaveView) findViewById(R.id.waveView);
        textView = (TextView) findViewById(R.id.textView);
        button4 = (Button) findViewById(R.id.button4);
    }
}
