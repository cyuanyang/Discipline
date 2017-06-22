package com.cyy.mvpsimple.listavtivity;

import android.app.Activity;
import android.content.Intent;
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
        view.refreshView(messages);
    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK){
            showMessage();
        }
    }

    @Override
    public void addNewMessage() {
        view.showNewMessageView();
    }
}
