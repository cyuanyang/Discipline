package com.cyy.cyyplugin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyy.cyyplugin.R;
import com.cyy.gatherlib.annotation.GatherFragment;

/**
 * Created by study on 17/7/21.
 *
 */

@GatherFragment
public class Fragment3 extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3 , container , false);
    }
}
