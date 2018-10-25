package com.example.mylive.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mylive.R;
import com.example.mylive.mvp.presenter.MainPresenter;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.utils
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.10.23 下午 4:53
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class DialogUtil {
    private static volatile DialogUtil _singleton;
    private static Context mContext;


    private DialogUtil(Context context) {
        this.mContext = context;
    }

    public static DialogUtil get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (DialogUtil.class) {
                if (_singleton == null) {
                    _singleton = new DialogUtil(context);
                }
            }
        }
        return _singleton;
    }

    /**
     * 设置监听
     */
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onClickItem(Object o);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void ShowSettingDialog(MainPresenter mPresenter, boolean mCancelable) {
        TextView mTvDilaogType, mTvDialogNumber;
        Spinner mSpinnerType, mSpinnerNumber;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
        mTvDialogNumber = (TextView) view.findViewById(R.id.tv_dialog_number);
        mTvDilaogType = (TextView) view.findViewById(R.id.tv_dilaog_type);
        builder.setView(view);
        builder.setCancelable(mCancelable);


    }
}
