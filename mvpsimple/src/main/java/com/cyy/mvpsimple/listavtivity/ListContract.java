package com.cyy.mvpsimple.listavtivity;


import android.content.Intent;

import com.cyy.mvpsimple.BasePresent;
import com.cyy.mvpsimple.BaseView;
import com.cyy.mvpsimple.Message;

import java.util.List;

/**
 * Created by study on 17/6/21.
 *
 */

public interface ListContract {

    interface Present extends BasePresent{
        void addNewMessage();
        void showMessage();
        void result(int requestCode, int resultCode, Intent data);
    }

    interface View extends BaseView<Present>{
        void showNewMessageView();

        void refreshView(List<Message> messages);
    }


}
