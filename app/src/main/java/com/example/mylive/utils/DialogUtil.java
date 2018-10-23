package com.example.mylive.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.mylive.R;

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
            this.mContext=context;
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
    public static void ShowDialog(String title,String content,String isHide){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(R.layout.dialog);
    }
}
