package com.cyy.cyyplugin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cyy.cyyplugin.Main3Activity;
import com.cyy.cyyplugin.R;
import com.cyy.gatherlib.Gather;
import com.cyy.gatherlib.Inject;
import com.cyy.gatherlib.annotation.GatherFragment;

/**
 * Created by study on 17/7/21.
 */

@GatherFragment
public class Fragment1 extends BaseFragment implements View.OnClickListener{

    protected Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {

        }
    }

    private void initView(View rootView) {
        button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
