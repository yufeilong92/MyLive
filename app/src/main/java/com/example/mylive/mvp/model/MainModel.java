package com.example.mylive.mvp.model;

import com.example.mylive.mvp.contract.MainContract;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.mvp.model
 * @Description: 主界面数据类
 * @author: L-BackPacker
 * @date: 2018.10.23 下午 5:39
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class MainModel implements MainContract.Model {
    @Override
    public ArrayList<SelectVo> getIniTSelectLists(int dayNum) {
        ArrayList<SelectVo> mLists = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DATE);
        for (int i = 0; i < dayNum; i++) {
            DataYMDWVo vo = mDates.get(i);
            SelectVo selectVo = new SelectVo();
            if (vo.getDay().equals(String.valueOf(d))) {
                selectVo.setSelect(true);
            } else {
                selectVo.setSelect(false);
            }
            setDataType(selectVo,d);
            selectVo.setD(i);
            mLists.add(selectVo);
        }
        return mLists
    }
}
