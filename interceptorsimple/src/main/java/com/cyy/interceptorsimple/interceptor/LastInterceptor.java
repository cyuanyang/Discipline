package com.cyy.interceptorsimple.interceptor;

import android.util.Log;

/**
 * Created by study on 17/8/3.
 *
 * 这个放在最后
 */

public class LastInterceptor implements Interceptor {

    /**
     * 这个放在最后  不再调用 chain.proceed()
     */
    @Override
    public void intercept(Chain chain) {

        Log.i("LastInterceptor" , "before");
    }
}
