package com.sample.gaoqingyu.mvpsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sample.gaoqingyu.mvpsample.mvc.MVCActivity;
import com.sample.gaoqingyu.mvpsample.mvp.MVPActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.Button mvcbtn;
    private android.widget.Button mvpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initView() {
        mvpbtn = (Button) findViewById(R.id.mvp_btn);
        mvcbtn = (Button) findViewById(R.id.mvc_btn);
    }

    private void initDate() {
        mvpbtn.setOnClickListener(this);
        mvcbtn.setOnClickListener(this);
    }

    public void toMvc(){
        startActivity(new Intent(MainActivity.this, MVCActivity.class));
    }

    public void toMvp(){
        startActivity(new Intent(MainActivity.this, MVPActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mvc_btn:
                toMvc();
                break;
            case R.id.mvp_btn:
                toMvp();
                break;
            default:
                break;

        }
    }
}
