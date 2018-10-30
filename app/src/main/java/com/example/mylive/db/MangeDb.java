package com.example.mylive.db;

import android.content.Context;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.db
 * @Description: 数据manage
 * @author: L-BackPacker
 * @date: 2018.10.30 上午 10:08
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class MangeDb {
    private static volatile MangeDb _singleton;
    private Context mContext;

    private MangeDb(Context context) {
        this.mContext = context;
    }

    public static MangeDb get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (MangeDb.class) {
                if (_singleton == null) {
                    _singleton = new MangeDb(context);
                }
            }
        }
        return _singleton;
    }



}
