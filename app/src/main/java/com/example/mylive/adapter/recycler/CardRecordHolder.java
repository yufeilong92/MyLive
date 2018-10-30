package com.example.mylive.adapter.recycler;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylive.R;
import com.example.mylive.vo.Study;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.adapter.recycler
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.10.30 下午 5:15
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class CardRecordHolder extends BaseViewHolder<Study> {
    private TextView mTvName;
    private static  String TAG = "【" + CardRecordHolder.class + "】==";

    public CardRecordHolder(ViewGroup parent, int layoutId) {
        super(parent, R.layout.item_card_layout);
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        mTvName = findViewById(R.id.tv_name);
    }

    @Override
    public void onItemViewClick(Study data) {
        super.onItemViewClick(data);
        Log.e(TAG, "onItemViewClick: " + data.getName());
    }
}
