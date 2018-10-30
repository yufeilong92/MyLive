package com.example.mylive.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.utils
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.10.30 上午 11:27
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class SaveUserUtil {
    private static final String KEY_NAME = SaveUserUtil.class.getName() + "_Interface";

    private static SaveUserUtil shareUserUtils;

    private final SharedPreferences msp;
    private String s_User = null;

    @SuppressLint("WrongConstant")
    public SaveUserUtil(Context context) {
        msp = context.getSharedPreferences("data", Context.MODE_PRIVATE | Context.MODE_APPEND);
    }

    public static synchronized void initSharedPreference(Context context) {
        if (shareUserUtils == null) {
            shareUserUtils = new SaveUserUtil(context);
        }
    }

    public static synchronized SaveUserUtil getInstance() {
        return shareUserUtils;
    }

    public SharedPreferences getSharedPref() {
        return msp;
    }

    public synchronized void putUser(String vo) {
        SharedPreferences.Editor editor = msp.edit();
        String str = "";
        try {
            str = SerializableUtil.obj2String(vo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(KEY_NAME, str);
        editor.commit();
        s_User = vo;
    }

    public synchronized String getUser() {
        if (s_User == null) {
            s_User = new String();
            String str = msp.getString(SaveUserUtil.KEY_NAME, "");
            try {
                Object obj = SerializableUtil.str2Obj(str);
                if (obj != null) {
                    s_User = (String) obj;
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s_User;
    }

    public synchronized void delectUser() {
        SharedPreferences.Editor edit = msp.edit();
        edit.putString(KEY_NAME, "");
        edit.commit();
        s_User = null;
    }
}
