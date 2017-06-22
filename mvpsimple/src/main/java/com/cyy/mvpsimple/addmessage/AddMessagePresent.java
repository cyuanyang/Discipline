package com.cyy.mvpsimple.addmessage;

import com.cyy.mvpsimple.Message;
import com.fire.table.DBBase;

/**
 * Created by study on 17/6/22.
 *
 */

public class AddMessagePresent implements AddMessageContract.Present {

    private AddMessageContract.View mView;


    public AddMessagePresent(AddMessageContract.View view){
        mView = view;

    }

    @Override
    public void addNewMessage(String message) {
        //添加新消息
        DBBase.newDBHelper().save(new Message(message));
        mView.complete();
    }
}
