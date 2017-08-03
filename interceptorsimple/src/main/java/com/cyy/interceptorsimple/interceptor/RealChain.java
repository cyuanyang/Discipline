package com.cyy.interceptorsimple.interceptor;

import java.util.List;

/**
 * Created by study on 17/8/3.
 *
 */

public class RealChain implements Interceptor.Chain {

    private int index;
    private List<Interceptor> interceptors;

    public RealChain(List<Interceptor> interceptors, int index){
        this.interceptors = interceptors;
        this.index = index;
    }

    @Override
    public void proceed() {

        Interceptor interceptor = interceptors.get(index);

        RealChain chain = new RealChain(interceptors , index+1);
        interceptor.intercept(chain);

    }
}
