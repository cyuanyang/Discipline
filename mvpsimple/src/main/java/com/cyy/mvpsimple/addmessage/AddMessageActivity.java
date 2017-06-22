package com.cyy.mvpsimple.addmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyy.mvpsimple.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMessageActivity extends AppCompatActivity {

    @BindView(R.id.editText)  EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_message);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.addBtn) void onClick(View view){
        Toast.makeText(this, "ffff", Toast.LENGTH_SHORT).show();

        
    }


}
