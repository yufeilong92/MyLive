package com.example.mylive.listenerInterface;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.listenerInterface
 * @Description: 通用接口类
 * @author: L-BackPacker
 * @date: 2018.11.02 下午 2:29
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class CommonFace {
    /**
     * 设置监听
     */
    private OnDialogClickListener onDialogClickListener;

    public interface OnDialogClickListener {
        public void onCancel();
        public void onSure(String o);
    }

    public void setOnItemClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }
}

