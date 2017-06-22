package com.cyy.mvpsimple.listavtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyy.mvpsimple.Message;
import com.cyy.mvpsimple.R;
import com.cyy.mvpsimple.addmessage.AddMessageActivity;
import com.fire.table.DBBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListActivity extends AppCompatActivity implements ListContract.View{

    protected RecyclerView recycleView;
    private ListContract.Present mPresent;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list);
        initView();

        mPresent = new ListPresent(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(new ArrayList<>(0));
        recycleView.setAdapter(messageAdapter);

        mPresent.showMessage();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresent.result(requestCode, resultCode, data);
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
        startActivityForResult(new Intent(this , AddMessageActivity.class) , 1000);
    }

    @Override
    public void refreshView(List<Message> messages) {
        messageAdapter.refreshData(messages);
    }

    static class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

        private List<Message> messages;
        public MessageAdapter(List<Message> messages ){
            this.messages = messages;
        }

        public void refreshData(List<Message> messages){
            this.messages.clear();
            this.messages.addAll(messages);
            notifyDataSetChanged();
        }

        @Override
        public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message , parent , false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(messages.get(position).getMessage());
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            public TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.message);
            }
        }
    }
}
