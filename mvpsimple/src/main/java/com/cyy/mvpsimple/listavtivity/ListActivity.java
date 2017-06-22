package com.cyy.mvpsimple.listavtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cyy.mvpsimple.R;
import com.cyy.mvpsimple.addmessage.AddMessageActivity;

public class ListActivity extends AppCompatActivity implements ListContract.View{

    protected RecyclerView recycleView;
    private ListContract.Present mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list);
        initView();

        mPresent = new ListPresent(this);
    }

    private void initView() {
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0 , 1 , 0 , "add");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == 1){
            mPresent.addNewMessage();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showNewMessageView() {
        startActivity(new Intent(this , AddMessageActivity.class));
    }
}
