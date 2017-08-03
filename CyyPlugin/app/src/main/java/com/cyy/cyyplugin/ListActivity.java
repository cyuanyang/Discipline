package com.cyy.cyyplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cyy.gatherlib.annotation.Collection;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    protected ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list);
        initView();

        list.setAdapter(new MyAdapter());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.listView) {

        }
    }

    private void initView() {
        list = (ListView) findViewById(R.id.listView);
    }

    class MyAdapter extends BaseAdapter {

        List<String> strings = new ArrayList<>();

        public MyAdapter(){
            for (int i = 0; i < 30; i++) {
                strings.add("i ="+ i);
            }
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {

                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.onMyClick();

            return convertView;
        }

        class ViewHolder implements View.OnClickListener{
            protected Button btn1234;
            protected ImageView itemImageView;
            protected RelativeLayout relativeLayout;
            protected ImageButton imageBtn1;
            protected ImageButton imageBtn2;
            protected ImageButton imageBtn3;

            ViewHolder(View rootView) {
                initView(rootView);
            }

            private void initView(View rootView) {
                btn1234 = (Button) rootView.findViewById(R.id.btn1234);
                itemImageView = (ImageView) rootView.findViewById(R.id.itemImageView);
                relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);
                imageBtn1 = (ImageButton) rootView.findViewById(R.id.imageBtn1);
                imageBtn2 = (ImageButton) rootView.findViewById(R.id.imageBtn2);
                imageBtn3 = (ImageButton) rootView.findViewById(R.id.imageBtn3);
            }

            public void onMyClick(){
                btn1234.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                itemImageView.setOnClickListener(this);
                relativeLayout.setOnClickListener(this);
                imageBtn1.setOnClickListener(this);
                imageBtn2.setOnClickListener(this);
                imageBtn3.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

            }
        }
    }
}
