package com.example.mylive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mylive.base.BaseActivity;
import com.example.mylive.ui.MainActivity;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: GuideActivity
 * @Package com.example.mylive
 * @Description: 引导页
 * @author: L-BackPacker
 * @date: 2018.10.22 下午 12:00
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.10.22
 */
public class GuideActivity extends BaseActivity {

    private Context mContext;
    private ImageView mIvLogo;

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }*/

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        initView();
        initPermission();
        initData();
    }

    private void initView() {
        mContext = this;
        mIvLogo = (ImageView) findViewById(R.id.iv_logo);
    }

    private void initData() {
    }

    private void initPermission() {
        XXPermissions.with(GuideActivity.this)
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        } else {
                            initPermission();
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(GuideActivity.this);
                        } else {
                            initPermission();
                        }
                    }
                });
    }
}
