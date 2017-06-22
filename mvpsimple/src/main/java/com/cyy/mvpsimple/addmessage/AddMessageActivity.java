package com.cyy.mvpsimple.addmessage;

import android.app.Activity;
import android.content.Intent;
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

public class AddMessageActivity extends AppCompatActivity implements AddMessageContract.View {

    @BindView(R.id.editText) EditText editText;

    private AddMessageContract.Present mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_message);
        ButterKnife.bind(this);
        mPresent = new AddMessagePresent(this);

    }

    @OnClick(R.id.addBtn) void onClick(View view){
        Toast.makeText(this, "ffff", Toast.LENGTH_SHORT).show();
        if (view.getId() == R.id.addBtn){
            mPresent.addNewMessage(editText.getText().toString());
        }
    }

    @Override
    public void complete() {
        setResult(Activity.RESULT_OK);
        this.finish();
    }
}
