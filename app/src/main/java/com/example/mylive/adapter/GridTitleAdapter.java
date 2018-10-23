package com.example.mylive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylive.R;

import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.adapter
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.10.22 下午 3:10
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class GridTitleAdapter extends BaseAdapter {

    private Context mContext;
    private List<?> mListDatas;

    public GridTitleAdapter(Context mContext, List<?> mListDatas) {
        this.mContext = mContext;
        this.mListDatas = mListDatas;
    }

    @Override
    public int getCount() {
        return mListDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mListDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        String title = (String) mListDatas.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_title, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.mTvContent.setText(title);
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvContent;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvContent = (TextView) rootView.findViewById(R.id.tv_title);
        }
    }
}
