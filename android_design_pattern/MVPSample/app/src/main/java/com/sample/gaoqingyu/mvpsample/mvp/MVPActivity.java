package com.sample.gaoqingyu.mvpsample.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sample.gaoqingyu.mvpsample.R;
import com.sample.gaoqingyu.mvpsample.mvp.presenter.MvpPresenter;
import com.sample.gaoqingyu.mvpsample.mvp.view.MvpView;

import java.util.List;


public class MVPActivity extends AppCompatActivity implements MvpView,AdapterView.OnItemClickListener{

    ListView mvpListView;
    MvpPresenter mvpPresenter;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mvpListView = (ListView)findViewById(R.id.mvp_listview);
        mvpListView.setOnItemClickListener(this);
        pb = (ProgressBar) findViewById(R.id.mvp_loading);
        mvpPresenter = new MvpPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mvpPresenter.onItemClick(position);
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void setListItem(List<String> data) {
        ArrayAdapter adapter = new ArrayAdapter(MVPActivity.this,android.R.layout.simple_list_item_1,data);
        mvpListView.setAdapter(adapter);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
