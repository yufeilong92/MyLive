package com.example.mylive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylive.R;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;
import java.util.HashMap;
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
public class GridContentAdapter extends BaseAdapter {

    private Context mContext;
    private List<?> mListDatas;
    private ArrayList<SelectVo> mSelectDatas;

    public GridContentAdapter(Context mContext, List<?> mListDatas, ArrayList<SelectVo> mSelectDatas) {
        this.mContext = mContext;
        this.mListDatas = mListDatas;
        this.mSelectDatas = mSelectDatas;
    }

    public void setSelectDataVo(ArrayList<SelectVo> mSelectDatas) {
        this.mSelectDatas = mSelectDatas;
        notifyDataSetChanged();
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
        SelectVo selectVo = mSelectDatas.get(position);
        ViewHolder holder = null;
        DataYMDWVo vo = (DataYMDWVo) mListDatas.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_content, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.mTvContent.setText(vo.getDay());
        Boolean mIsSelect = selectVo.isSelect();
        if (mIsSelect) {
            holder.mTvContent.setBackgroundResource(R.drawable.select_s);
        } else {
            holder.mTvContent.setBackgroundResource(R.drawable.select_n);
        }
        boolean night = true;
        if (night) {
            holder.mIvNightSunny.setImageResource(R.mipmap.nightday);
        } else {
            holder.mIvNightSunny.setImageResource(R.mipmap.sunnyday_one);
        }


        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvContent;
        public ImageView mIvNightSunny;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
            this.mIvNightSunny = (ImageView) rootView.findViewById(R.id.iv_night_sunny);
        }
    }
}
