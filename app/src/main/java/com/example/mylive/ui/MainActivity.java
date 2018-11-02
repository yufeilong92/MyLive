package com.example.mylive.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.mylive.listenerInterface.CommonFace;
import com.example.mylive.mvp.contract.MainContract;
import com.example.mylive.mvp.model.MainModel;
import com.example.mylive.mvp.presenter.MainPresenter;
import com.example.mylive.utils.DialogUtil;
import com.example.mylive.utils.Util;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.lemon.view.RefreshRecyclerView;

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
    private MainPresenter mPresenter;
    private GridContentAdapter mContentAdapter;
    private ArrayList<DataYMDWVo> mTimeListDates;
    private ArrayList<SelectVo> mSelectLists;
    private DataManageVo mManageVo;
    private MangeDb mDb;
    private RefreshRecyclerView mRfrlcvEvent;
    private TextView mTvActionTitle;
    private TextView mTvInputAffair;

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
        setCustomActionBar();
        initView();
        initData();
        setAdapterListener();
        initShowView();
    }

    private void setCustomActionBar() {
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View view = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);
        mTvActionTitle = view.findViewById(R.id.tv_title_action);
        ActionBar bar = getSupportActionBar();
        bar.setCustomView(view, layoutParams);
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
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
        mUtil = Util.get_Instance();
        //获取本月年月日
        String time = mUtil.getDataTime(Calendar.getInstance());
        mManageVo = DataManageVo.get_Instance();
        mTvActionTitle.setText(time);
        ArrayList<String> mTitles = mUtil.getArralistWithArray(mContext, R.array.girl_title);
        GridTitleAdapter mTitleAdapter = new GridTitleAdapter(mContext, mTitles);
        mGridTitle.setAdapter(mTitleAdapter);
        //获取本月的第一天
        String firstData = mUtil.getFirstOrLastDate(0);
        //获取本月的最后一天
        String lastDate = mUtil.getFirstOrLastDate(1);
        //获取本月的所有时间
        mTimeListDates = mUtil.getDatas(firstData, lastDate);
        //获取本月的所有安排上班时间
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
        mRfrlcvEvent = (RefreshRecyclerView) findViewById(R.id.rfrlcv_event);
        mRfrlcvEvent.setOnClickListener(this);
        mTvInputAffair = (TextView) findViewById(R.id.tv_input_affair);
        mTvInputAffair.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left://向左
                AlertDialog dialog = DialogUtil.showDialog(mContext, "加载中...", false);
                Util.HandlepostDelayed(2000, new Util.OnHandleClickListener() {
                    @Override
                    public void onClickItem() {
                        dialog.dismiss();
                    }
                });
                liftAffair();
                break;
            case R.id.iv_right://向右
                AlertDialog dialog1 = DialogUtil.showDialog(mContext, "加载中...", false);
                Util.HandlepostDelayed(2000, new Util.OnHandleClickListener() {
                    @Override
                    public void onClickItem() {
                        dialog1.dismiss();
                    }
                });
                rightAffair();
                break;
            case R.id.tv_input_affair://输入记录内容
                showInputDailog();
                break;
            default:


        }
    }

    private void showInputDailog() {
        DialogUtil.showInputDialog(MainActivity.this, new CommonFace.OnDialogClickListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onSure(String o) {

            }
        });
    }

    /**
     * 向右事件
     */
    private void rightAffair() {

    }

    /**
     * 向左事件
     */
    private void liftAffair() {


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
                DialogUtil dialogUtil = DialogUtil.get_Instance();
                dialogUtil.ShowSettingDialog(mContext, mPresenter, true);
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
        AlertDialog mDialog = DialogUtil.showDialog(mContext, "计算中", true);
        String lastDate = mUtil.getFirstOrLastDate(1);
        String[] dataArray = mUtil.getDataArray(lastDate);
        String s = dataArray[dataArray.length - 1];
//        mTimeListDates = listToArrayList(mTimeListDates,mTimeListDates.size()- Integer.parseInt(s));
        mSelectLists = mPresenter.getSelectVoDatas(mTimeListDates, selectDay,
                numberDay, type,mTimeListDates.size()- Integer.parseInt(s));
        mContentAdapter.setSelectDataVo(mSelectLists);
        mPresenter.putUserSetting(mContext, selectDay, numberDay, type);
        Util util = Util.get_Instance();
        util.HandlepostDelayed(1000, new Util.OnHandleClickListener() {
            @Override
            public void onClickItem() {
                if (mDialog != null && mDialog.isShowing())
                    mDialog.dismiss();
            }
        });


    }

    private ArrayList<DataYMDWVo> listToArrayList(ArrayList<DataYMDWVo> list, int i) {
        ArrayList<DataYMDWVo> arrayList = new ArrayList<>();
        for (int k = 0; k < list.size(); k++) {
            if (k < i) {
                continue;
            } else
                arrayList.add(list.get(k));
        }
        return arrayList;
    }
}
