package com.example.mylive.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylive.R;
import com.example.mylive.adapter.recycler.CardRecordAdapter;
import com.example.mylive.base.DataManageVo;
import com.example.mylive.db.MangeDb;
import com.example.mylive.db.UserSetting;

import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private RefreshRecyclerView mRrclvContent;
    private Context mContent;
    private CardRecordAdapter mAdapter;
    private Button mBtnAdd;
    private TextView mTvContent;
    private MangeDb mMangeDb;
    private Button mBtnFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        mAdapter = new CardRecordAdapter(mContent);
        mMangeDb = MangeDb.get_Instance(mContent);

    }

    private void initView() {
        mContent = this;
        mRrclvContent = (RefreshRecyclerView) findViewById(R.id.rrclv_content);
        mRrclvContent.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRrclvContent.setLayoutManager(new GridLayoutManager(mContent, 2));
        mRrclvContent.setAdapter(mAdapter);
        mRrclvContent.addRefreshAction(new Action() {
            @Override
            public void onAction() {
                getData();
            }
        });
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvContent.setOnClickListener(this);
        mBtnFind = (Button) findViewById(R.id.btn_find);
        mBtnFind.setOnClickListener(this);
    }

    private void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                UserSetting userSetting = new UserSetting();
                userSetting.setName(DataManageVo.db_name);
                userSetting.setDay("20");
                userSetting.setMonth("1");
                userSetting.setYear("2018");
                userSetting.setType(1);
                userSetting.setUserSelectDay(1);
                userSetting.setWriterSelectDay(20);
                mMangeDb.addUserSetting(userSetting);
                break;
            case R.id.btn_find:
                UserSetting userSeting = mMangeDb.findUserSeting();
                mTvContent.setText(userSeting.toString());
                break;
        }
    }
}
