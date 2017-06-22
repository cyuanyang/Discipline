package com.cyy.mvpsimple.addmessage;

import com.cyy.mvpsimple.BasePresent;
import com.cyy.mvpsimple.BaseView;

/**
 * Created by study on 17/6/22.
 *
 */

public interface AddMessageContract {

    interface View extends BaseView<Present>{

        void complete();
    }

    interface Present extends BasePresent{
        void addNewMessage(String message);
    }
}
