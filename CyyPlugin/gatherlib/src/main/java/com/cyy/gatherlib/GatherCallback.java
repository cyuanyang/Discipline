package com.cyy.gatherlib;

import android.view.View;

/**
 * Created by study on 17/7/19.
 *
 */

public interface GatherCallback {

    void onViewClick(View view , String path);

    void onActivityVisibleChanged(String name , boolean isVisible);

    void onFragmentVisibleChanged(String fragmentName , boolean isVisible);
}
