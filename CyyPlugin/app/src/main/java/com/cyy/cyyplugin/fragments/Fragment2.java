package com.cyy.cyyplugin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.cyy.cyyplugin.R;
import com.cyy.gatherlib.annotation.GatherFragment;

/**
 * Created by study on 17/7/20.
 *
 */

@GatherFragment
public class Fragment2 extends Fragment implements View.OnClickListener {

    protected View rootView;
    protected Button button2;
    protected RadioButton radioButton;
    protected TextView textView;
    protected ProgressBar progressBar;
    protected Switch switch1;
    protected Button button3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment2, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button2) {

        } else if (view.getId() == R.id.textView) {

        } else if (view.getId() == R.id.button3) {

        }
    }

    private void initView(View rootView) {
        button2 = (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(Fragment2.this);
        radioButton = (RadioButton) rootView.findViewById(R.id.radioButton);
        textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setOnClickListener(Fragment2.this);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        switch1 = (Switch) rootView.findViewById(R.id.switch1);
        button3 = (Button) rootView.findViewById(R.id.button3);
        button3.setOnClickListener(Fragment2.this);
    }
}
