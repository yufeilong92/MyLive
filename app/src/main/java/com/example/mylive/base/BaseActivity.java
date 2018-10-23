package com.example.mylive.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.base
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.10.22 上午 11:55
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
    }

    protected abstract void initContentView(Bundle savedInstanceState);


    protected String getTextViewContent(View view) {
        String content="";
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            content = tv.getText().toString().trim();
        }
        if (view instanceof EditText) {
            EditText et = (EditText) view;
            content = et.getText().toString().trim();
        }
        if (view instanceof Button) {
            Button btn = (Button) view;
            content = btn.getText().toString().trim();
        }
        return content;
    }

}
