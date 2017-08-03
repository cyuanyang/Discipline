package com.cyy.interceptorsimple.interceptor;

import android.util.Log;

/**
 * Created by study on 17/8/3.
 */

public class FirstInterceptor implements Interceptor {


    @Override
    public void intercept(Chain chain) {

        Log.i("FirstInterceptor" , "before");
        chain.proceed();
        Log.i("FirstInterceptor" , "end");
    }


}
