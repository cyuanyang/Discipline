package com.cyy.cyyplugin.waveview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by study on 17/7/31.
 * todo 波峰高度不一样怎么实现
 */

public class WaveView extends View {

    private Shader mWaveSahder;
    private Matrix mWaveShaderMatrix;
    private Paint mWavePaint;

    private int DEFAULT_WAVE_LENGTH = 1; // View的宽度为一个周期

    private float mWaveShiftRatio;//shader 挪动的比例
    private float mWaveScaleY;//y方向放大的比例
    private float mStartScaleY; //
    private int rotation = 0;

    private AnimatorSet waveAnimSet;
    private ObjectAnimator mWaveScaleAnim;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        SensorManager sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(l, sm.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(l2 , sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER ),SensorManager.SENSOR_DELAY_NORMAL);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (waveAnimSet!=null){
                    mStartScaleY = 2.0f;
                    start();
                }
                return false;
            }
        });
    }

    private SensorEventListener l = new SensorEventListener() {

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            int xr = (int) Math.toDegrees(Math.acos(x/9.8));
            int yr = (int) Math.toDegrees(Math.acos(y/9.8));
            int zr = (int) Math.toDegrees(Math.acos(z/9.8));

            if (x>0){
                rotation = 90-xr;
            }else {
                rotation = 90-xr;
            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //检测晃动的速度
    private SensorEventListener l2 = new SensorEventListener() {

        // 速度阈值，当摇晃速度达到这值后产生作用
        private static final int SPEED_SHRESHOLD = 20;
        // 两次检测的时间间隔
        private static final int UPTATE_INTERVAL_TIME = 50;
        // 手机上一个位置时重力感应坐标
        private float lastX;
        private float lastY;
        private float lastZ;
        // 上次检测时间
        private long lastUpdateTime;

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            // 现在检测时间
            long currentUpdateTime = System.currentTimeMillis();
            // 两次检测的时间间隔
            long timeInterval = currentUpdateTime - lastUpdateTime;
            // 判断是否达到了检测时间间隔
            if (timeInterval < UPTATE_INTERVAL_TIME) return;
            // 现在的时间变成last时间
            lastUpdateTime = currentUpdateTime;
            // 获得x,y,z的变化值
            float deltaX = x - lastX;
            float deltaY = y - lastY;
            float deltaZ = z - lastZ;
            // 将现在的坐标变成last坐标
            lastX = x;
            lastY = y;
            lastZ = z;
            float speed = (float) (Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                                * deltaZ)
                                / timeInterval * 10000);
            // 达到速度阀值，发出提示
            if (speed >= SPEED_SHRESHOLD){
                speed = speed>400f?400f:speed;
                Log.e("speed"  , "speed =="+speed);
                mStartScaleY = speed/200;
                if ((mWaveScaleAnim!=null&&!mWaveScaleAnim.isStarted() || mWaveScaleAnim == null)){
                    start();
                }else {
                    //如果正在动画
                    float value = (float) mWaveScaleAnim.getAnimatedValue();
                    if (mStartScaleY>value){
                        startWaveAnim();
                    }
                }
            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        initWaveShader();
    }

    private void initWaveShader(){

        double A = 2 * Math.PI / DEFAULT_WAVE_LENGTH / getWidth();

        /*
           画 波浪行的bitmap
            y = Asin(msx+n)+h

             ^
             |
             |
             |
             |    *
             |  *   *
             | *     *
            _0_1_2_3_4_5_6 ..... _________________________
             |                               width
             |

         */
        Bitmap bitmap = Bitmap.createBitmap(getWidth() , getHeight() , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        int width = getWidth();

        Paint wavePaint = new Paint();
        wavePaint.setStrokeWidth(2);
        wavePaint.setAntiAlias(true);
        wavePaint.setColor(Color.parseColor("#66ff0000"));
        float lastX = 0 , lastY = 0;
        int waveHeight = 100;

        //后面的波浪
        Path path = new Path();
        path.moveTo(0 , getBottom() - waveHeight);
        for (int x = 0 ; x <= width; x++) {
            float y = (float) ((30 * Math.sin(A*x))+waveHeight);
            path.quadTo((lastX+x)/2 , (lastY+y)/2 , x , y);
            lastX = x;
            lastY = y;
        }

        path.lineTo(width , getBottom());
        path.lineTo(0 , getBottom());
        path.close();
        canvas.drawPath(path , wavePaint);

        //前面的波浪
        path.reset();
        lastX = 0;
        lastY = 0;
        path.moveTo(0 , getBottom() - waveHeight);
        for (int x = 0 ; x <= width; x++) {
            float y = (float) ((30 * Math.sin(A*(x-200))+waveHeight));
            path.quadTo((lastX+x)/2 , (lastY+y)/2 , x , y);
            lastX = x;
            lastY = y;
        }
        path.lineTo(width , getBottom());
        path.lineTo(0 , getBottom());
        path.close();
        wavePaint.setColor(Color.parseColor("#aaff0000"));
        canvas.drawPath(path , wavePaint);

        mWaveSahder = new BitmapShader(bitmap ,Shader.TileMode.REPEAT, Shader.TileMode.CLAMP );
        mWaveShaderMatrix = new Matrix();
        mWaveSahder.setLocalMatrix(mWaveShaderMatrix);

        if (mWavePaint==null){
            mWavePaint = new Paint();
        }
        mWavePaint.setShader(mWaveSahder);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWaveShaderMatrix.setScale(1.0f , mWaveScaleY , 0 , 100);
        mWaveShaderMatrix.postTranslate(
                mWaveShiftRatio * getWidth(),
                0);
        mWaveShaderMatrix.postRotate(rotation , getWidth()/2 , 100);
        mWaveSahder.setLocalMatrix(mWaveShaderMatrix);
        canvas.drawRect(0 , 0 , getWidth(),getBottom() , mWavePaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mStartScaleY = 2.0f;
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
        //停止监听
    }

    public void setWaveShiftRatio(float waveShiftRatio) {
        if (this.mWaveShiftRatio!=waveShiftRatio){
            this.mWaveShiftRatio = waveShiftRatio;
            invalidate();
        }
    }

    public void setWaveScaleY(float waveScaleY){
        this.mWaveScaleY = waveScaleY;
        Log.e("mWaveScaleY " , "mWaveScaleY="+mWaveScaleY);
    }

    public void start(){
        if (waveAnimSet!=null){
            waveAnimSet.cancel();
            waveAnimSet = null;
        }
        waveAnimSet = new AnimatorSet();

        //波浪平移动画
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                this, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        waveAnimSet.playTogether(waveShiftAnim);

        waveAnimSet.start();

        startWaveAnim();

    }

    //波浪动画
    private void startWaveAnim(){
        //波浪放大动画
        if (mWaveScaleAnim!=null){
            mWaveScaleAnim.cancel();
            mWaveScaleAnim = null;
        }
        mWaveScaleAnim = ObjectAnimator.ofFloat(
                this, "waveScaleY", mStartScaleY, 0.0001f , mStartScaleY);
        mWaveScaleAnim.setInterpolator(new LinearInterpolator());
        mWaveScaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        mWaveScaleAnim.setDuration(10000);
        mWaveScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.e("value = ", "value == "+ value);
            }
        });
        mWaveScaleAnim.start();
    }

    private void stop(){
        if (waveAnimSet!=null){
            waveAnimSet.cancel();
        }
    }

}
