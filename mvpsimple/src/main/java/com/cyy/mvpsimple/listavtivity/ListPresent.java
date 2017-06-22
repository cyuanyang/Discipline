package com.cyy.mvpsimple.listavtivity;

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
    public void addNewMessage() {
        view.showNewMessageView();
    }
}
