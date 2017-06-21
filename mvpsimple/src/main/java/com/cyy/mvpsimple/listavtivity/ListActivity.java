package com.cyy.mvpsimple.listavtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.cyy.mvpsimple.R;

public class ListActivity extends AppCompatActivity {

    protected RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list);
        initView();
    }

    private void initView() {
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("add");
        return super.onCreateOptionsMenu(menu);
    }
}
