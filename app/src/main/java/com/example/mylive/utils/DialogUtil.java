package com.example.mylive.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylive.R;
import com.example.mylive.base.DataManageVo;
import com.example.mylive.mvp.presenter.MainPresenter;

import java.util.ArrayList;

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
        this.mContext = context;
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

    /**
     * 设置监听
     */
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onClickItem(Object o);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置监听
     */
    private OnItemListClickListener onItemListClickListener;

    public interface OnItemListClickListener {
        public void onListClickItem(Object o);
    }

    public void setOnItemListClickListener(OnItemListClickListener onItemListClickListener) {
        this.onItemListClickListener = onItemListClickListener;
    }

    /**
     * 设置监听
     */
    private OnItemButtonClickListener onItemButtonClickListener;

    public interface OnItemButtonClickListener {
        public void onButtonClickItem();
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemClickListener) {
        this.onItemButtonClickListener = onItemClickListener;
    }


    private boolean isSubmti = false;


    public void ShowSettingDialog(final MainPresenter mPresenter, boolean mCancelable) {
        final DataManageVo vo = DataManageVo.get_Instance();
        vo.setNightOrSunnytype(DataManageVo.SUNNYTYPE);
        vo.setSelectDay(DataManageVo.ONE_DAY);
        final ArrayList<String> nightAndSun = mPresenter.getTypeNightAndSun();
        final ArrayList<String> typeLists = mPresenter.getTypeLists(DataManageVo.SUNNYTYPE);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
        final Spinner mSpinnerType = (Spinner) view.findViewById(R.id.spinner_type);
        final Spinner mSpinnerNumber = (Spinner) view.findViewById(R.id.spinner_number);
        final TextView mTvSettingRange = (TextView) view.findViewById(R.id.tv_setting_range);
        final EditText mEtSettingDay = (EditText) view.findViewById(R.id.et_setting_day);
        final Button mBtnSure = (Button) view.findViewById(R.id.btn_sure);
        builder.setView(view);
        builder.setCancelable(mCancelable);
        Util instance = Util.get_Instance(null);
        final String lastDate = instance.getFirstOrLastDate(1);
        String rangeString = getRangeString();
        mTvSettingRange.setText(rangeString);
        mEtSettingDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = mEtSettingDay.getText().toString().trim();
                if (!StringUtls.isEmpty(s1))
                    showTast(s1, lastDate, vo);
            }
        });
        final ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_checked, nightAndSun);
        mSpinnerType.setAdapter(typeAdapter);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_checked, typeLists);
        mSpinnerNumber.setAdapter(listAdapter);
        builder.create();
        final AlertDialog dialog = builder.show();

        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = nightAndSun.get(position);
                switch (s) {
                    case "白班":
                        vo.setNightOrSunnytype(DataManageVo.SUNNYTYPE);
                        ArrayList<String> lists = mPresenter.getTypeLists(DataManageVo.SUNNYTYPE);
                        typeLists.clear();
                        typeLists.addAll(lists);
                        typeAdapter.notifyDataSetChanged();
                        break;
                    case "夜班":
                        vo.setNightOrSunnytype(DataManageVo.NIGHTTYPE);
                        ArrayList<String> nightList = mPresenter.getTypeLists(DataManageVo.NIGHTTYPE);
                        typeLists.clear();
                        typeLists.addAll(nightList);
                        typeAdapter.notifyDataSetChanged();
                        break;
                    default:

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = typeLists.get(position);
                switch (s) {
                    case "一":
                        vo.setSelectDay(DataManageVo.ONE_DAY);
                        break;
                    case "二":
                        vo.setSelectDay(DataManageVo.TWO_DAY);
                        break;
                    case "三":
                        vo.setSelectDay(DataManageVo.THREE_DAY);
                        break;
                    case "四":
                        vo.setSelectDay(DataManageVo.FOUR_DAY);
                        break;

                    default:

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSubmti) {
                    Toast.makeText(mContext, "请输入有效日期范围", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (onItemButtonClickListener != null) {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onItemButtonClickListener.onButtonClickItem();
                        }
                    }, 500);
                }
            }
        });
    }

    /**
     *
     * @param s1  输入得数字
     * @param lastDate 最大数字
     * @param vo 保存数据
     */
    private void showTast(String s1, String lastDate, DataManageVo vo) {
        String[] split = lastDate.split("-");
        lastDate = split[split.length - 1];
        if (Integer.parseInt(s1) <= 0 || Integer.parseInt(s1) > Integer.parseInt(lastDate)) {
            isSubmti = false;
            Toast.makeText(mContext, "输入超出计算范围", Toast.LENGTH_SHORT).show();
        } else {
            isSubmti = true;
            vo.setWriteDay(Integer.parseInt(s1));
        }
    }

    /**
     * 获取输入范围
     *
     * @return
     */
    private String getRangeString() {
        Util instance = Util.get_Instance(null);
        String firstDate = instance.getFirstOrLastDate(0);
        String[] split = firstDate.split("-");
        firstDate = split[split.length - 1];
        String lastDate = instance.getFirstOrLastDate(1);
        String[] last = lastDate.split("-");
        lastDate = last[last.length - 1];
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(firstDate);
        stringBuffer.append("-");
        stringBuffer.append(lastDate);
        return stringBuffer.toString().trim();
    }

    public AlertDialog showDialog(Context context,String content, boolean isCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.show_dialog, null);
        TextView tv_dialgo = (TextView) view.findViewById(R.id.tv_dialog_content);
        tv_dialgo.setText(content);
        builder.setView(view);
        builder.setCancelable(isCancel);
        builder.create();
        AlertDialog show = builder.show();
        return show;

    }
}
