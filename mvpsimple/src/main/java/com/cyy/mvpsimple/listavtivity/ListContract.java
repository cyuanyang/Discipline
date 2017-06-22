package com.cyy.mvpsimple.listavtivity;


import com.cyy.mvpsimple.BasePresent;
import com.cyy.mvpsimple.BaseView;

/**
 * Created by study on 17/6/21.
 *
 */

public interface ListContract {

    interface Present extends BasePresent{
        void addNewMessage();
    }

    interface View extends BaseView<Present>{


        void showNewMessageView();

    }


}
