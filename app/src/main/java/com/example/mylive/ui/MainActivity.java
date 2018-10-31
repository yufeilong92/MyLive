package com.example.mylive.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylive.R;
import com.example.mylive.adapter.GridContentAdapter;
import com.example.mylive.adapter.GridTitleAdapter;
import com.example.mylive.base.BaseActivity;
import com.example.mylive.base.DataManageVo;
import com.example.mylive.db.MangeDb;
import com.example.mylive.db.UserSetting;
import com.example.mylive.mvp.contract.MainContract;
import com.example.mylive.mvp.model.MainModel;
import com.example.mylive.mvp.presenter.MainPresenter;
import com.example.mylive.utils.DialogUtil;
import com.example.mylive.utils.Util;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MainActivity
 * @Package com.example.mylive.ui
 * @Description: 主界面展示
 * @author: L-BackPacker
 * @date: 2018.10.22 下午 12:05
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.10.22
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, MainContract.View {
    private static final String TAG = "【" + MainActivity.class + "】==";
    private GridView mGridTitle;
    private GridView mGridContent;
    private Context mContext;
    private Util mUtil;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private TextView mTvTitleTime;
    private MainPresenter mPresenter;
    private GridContentAdapter mContentAdapter;
    private ArrayList<DataYMDWVo> mTimeListDates;
    private ArrayList<SelectVo> mSelectLists;
    private DataManageVo mManageVo;
    private MangeDb mDb;
    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
*/

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapterListener();
        initShowView();
    }

    private void initShowView() {
        UserSetting userSeting = mDb.findUserSeting();
        refreshData(userSeting.getUserSelectDay(), userSeting.getWriterSelectDay(), userSeting.getType());
    }

    private void setAdapterListener() {
        mGridContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }

    private void initData() {
        mDb = MangeDb.get_Instance(mContext);
        mPresenter = new MainPresenter();
        mPresenter.initModelView(new MainModel(), this);
        mUtil = Util.get_Instance(mContext);
        String time = mUtil.getDataTime(Calendar.getInstance());
        mManageVo = DataManageVo.get_Instance();
        mTvTitleTime.setText(time);
        ArrayList<String> mTitles = mUtil.getArralistWithArray(R.array.girl_title);
        GridTitleAdapter mTitleAdapter = new GridTitleAdapter(mContext, mTitles);
        mGridTitle.setAdapter(mTitleAdapter);
        String firstData = mUtil.getFirstOrLastDate(0);
        String lastDate = mUtil.getFirstOrLastDate(1);
        mTimeListDates = mUtil.getDatas(firstData, lastDate);
        mSelectLists = mPresenter.getInitSelectList(mTimeListDates);
        mContentAdapter = new GridContentAdapter(mContext, mTimeListDates, mSelectLists);
        mGridContent.setAdapter(mContentAdapter);

    }


    private void initView() {
        mGridTitle = (GridView) findViewById(R.id.grid_title);
        mGridContent = (GridView) findViewById(R.id.grid_content);
        mContext = this;
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mIvLeft.setOnClickListener(this);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        mIvRight.setOnClickListener(this);
        mTvTitleTime = (TextView) findViewById(R.id.tv_title_time);
        mTvTitleTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:

                break;
            case R.id.iv_right:

                break;
            default:


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                DialogUtil dialogUtil = DialogUtil.get_Instance(mContext);
                dialogUtil.ShowSettingDialog(mPresenter, true);
                dialogUtil.setOnItemButtonClickListener(new DialogUtil.OnItemButtonClickListener() {
                    @Override
                    public void onButtonClickItem() {
                        refreshData(mManageVo.getSelectDay(),
                                mManageVo.getWriteDay(), mManageVo.getNightOrSunnytype());
                    }
                });
                break;
            case 0:
                break;
            default:

        }
        return false;
    }

    /**
     * @param selectDay 用户选择天数
     * @param numberDay 用户填写天数
     * @param type      类型
     */
    private void refreshData(int selectDay, int numberDay, int type) {
        DialogUtil dialogUtil = DialogUtil.get_Instance(mContext);
        AlertDialog mDialog = dialogUtil.showDialog(mContext, "计算中", true);
        mSelectLists = mPresenter.getSelectVoDatas(mTimeListDates, selectDay,
                numberDay, type);
        mContentAdapter.setSelectDataVo(mSelectLists);
        mPresenter.putUserSetting(mContext, selectDay, numberDay, type);
        Util util = Util.get_Instance(mContext);
        util.HandlepostDelayed(1000, new Util.OnHandleClickListener() {
            @Override
            public void onClickItem() {
                if (mDialog != null && mDialog.isShowing())
                    mDialog.dismiss();
            }
        });


    }

}
