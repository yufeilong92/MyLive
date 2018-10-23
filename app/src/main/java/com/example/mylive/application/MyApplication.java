package com.example.mylive.application;

import android.app.Application;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.application
 * @Description: 主题
 * @author: L-BackPacker
 * @date: 2018.10.22 下午 2:42
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化litePal数据库
        LitePal.initialize(this);
    }

}
