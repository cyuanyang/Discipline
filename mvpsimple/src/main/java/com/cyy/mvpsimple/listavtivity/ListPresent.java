package com.cyy.mvpsimple.listavtivity;

import android.util.Log;

import com.cyy.mvpsimple.Message;
import com.fire.table.DBBase;

import java.util.List;

/**
 * Created by study on 17/6/22.
 *
 */

class ListPresent implements ListContract.Present {

    private ListContract.View view;

    public ListPresent(ListContract.View view){
        this.view = view;
    }

    @Override
    public void showMessage() {
        List<Message> messages = DBBase.newDBHelper().find(Message.class);

        Log.e("TAg" , messages.size()+"dd");
    }

    @Override
    public void addNewMessage() {
        view.showNewMessageView();
    }
}
