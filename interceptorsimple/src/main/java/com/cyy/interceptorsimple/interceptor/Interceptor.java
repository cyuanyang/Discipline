package com.cyy.interceptorsimple.interceptor;

/**
 * Created by study on 17/8/3.
 *
 *
 */

public interface Interceptor {

    //拦截
    void intercept(Chain chain);

    /**
     * 事件响应链
     */
    interface Chain{

        void proceed();
    }
}
