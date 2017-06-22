package com.cyy.mvpsimple.addmessage;

/**
 * Created by study on 17/6/22.
 *
 */

public class AddMessagePresent implements AddMessageContract.Present {

    private AddMessageContract.View mView;

    public AddMessagePresent(AddMessageContract.View view){
        mView = view;


    }
}
