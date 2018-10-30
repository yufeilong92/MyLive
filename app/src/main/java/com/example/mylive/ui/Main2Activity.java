package com.example.mylive.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.example.mylive.R;
import com.example.mylive.adapter.GridContentAdapter;
import com.example.mylive.adapter.recycler.CardRecordAdapter;

import cn.lemon.view.RefreshRecyclerView;

public class Main2Activity extends AppCompatActivity {

    private RefreshRecyclerView mRrclvContent;
    private Context mContent;
    private CardRecordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        mAdapter = new CardRecordAdapter(mContent);

    }

    private void initView() {
        mContent = this;
        mRrclvContent = (RefreshRecyclerView) findViewById(R.id.rrclv_content);
        mRrclvContent.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRrclvContent.setLayoutManager(new GridLayoutManager(mContent,2));
        mRrclvContent.setAdapter(mAdapter);
        mRrclvContent.addRefreshAction(()->getData());
    }

    private void getData() {

    }
}
