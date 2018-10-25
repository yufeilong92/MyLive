package com.example.mylive.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylive.R;
import com.example.mylive.adapter.GridContentAdapter;
import com.example.mylive.adapter.GridTitleAdapter;
import com.example.mylive.base.BaseActivity;
import com.example.mylive.mvp.contract.MainContract;
import com.example.mylive.mvp.model.MainModel;
import com.example.mylive.mvp.presenter.MainPresenter;
import com.example.mylive.utils.DialogUtil;
import com.example.mylive.utils.Util;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
    }

    private ArrayList<SelectVo> getSelectDatas(ArrayList<DataYMDWVo> mDates) {
        ArrayList<SelectVo> mLists = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DATE);
        for (int i = 0; i < mDates.size(); i++) {
            DataYMDWVo vo = mDates.get(i);
            SelectVo selectVo = new SelectVo();
            if (vo.getDay().equals(String.valueOf(d))) {
                selectVo.setSelect(true);
            } else {
                selectVo.setSelect(false);
            }
            setDataType(selectVo, d);
            selectVo.setD(i);
            mLists.add(selectVo);
        }
        return mLists;
    }

    private void setDataType(SelectVo vo, int d) {

    }

    private void initData() {
        mPresenter = new MainPresenter();
        mPresenter.initModelView(new MainModel(), this);
        mUtil = Util.get_Instance(mContext);
        String time = mUtil.getDataTime(Calendar.getInstance());
        mTvTitleTime.setText(time);
        ArrayList<String> mTitles = mUtil.getArralistWithArray(R.array.girl_title);
        GridTitleAdapter mTitleAdapter = new GridTitleAdapter(mContext, mTitles);
        mGridTitle.setAdapter(mTitleAdapter);
        String firstData = mUtil.getFirstOrLastDate(0);
        String lastDate = mUtil.getFirstOrLastDate(1);
        ArrayList<DataYMDWVo> mDates = mUtil.getDatas(firstData, lastDate);
        ArrayList<SelectVo> selectList = mPresenter.getInitSelectList(mDates);
        GridContentAdapter contentAdapter = new GridContentAdapter(mContext, mDates, selectList);
        mGridContent.setAdapter(contentAdapter);

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
                dialogUtil.ShowSettingDialog(mPresenter,true);

                Toast.makeText(mContext, "点击设置", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                break;
            default:

        }
        return false;
    }
}
