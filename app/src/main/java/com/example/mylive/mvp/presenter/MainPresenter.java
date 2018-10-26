package com.example.mylive.mvp.presenter;

import com.example.mylive.mvp.contract.MainContract;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.mvp.presenter
 * @Description: 主界面控制
 * @author: L-BackPacker
 * @date: 2018.10.23 下午 5:39
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class MainPresenter implements MainContract.Presenter {
    MainContract.Model model;
    MainContract.View view;

    @Override
    public void initModelView(MainContract.Model model, MainContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public ArrayList<SelectVo> getInitSelectList(ArrayList<DataYMDWVo> mTimteDatas) {
        return model.getIniTSelectLists(mTimteDatas, mTimteDatas.size());
    }

    /**
     * 获取数据
     * @param datas 当月集合数据
     * @param selectDay 选中得第几天
     * @param daynum 当月得天数
     * @param type 0为夜间，1为白班
     * @return
     */
    @Override
    public ArrayList<SelectVo> getSelectVoDatas(ArrayList<DataYMDWVo> datas, int selectDay, int daynum, int type) {
        return model.getSaveTimeDatas(datas,selectDay,daynum,type);
    }

    /**
     * 获取白天夜间
     *
     * @return
     */
    @Override
    public ArrayList<String> getTypeNightAndSun() {
        return model.getTypeNightAndSun();
    }

    /**
     * 获取对应得数据集合
     *
     * @param type
     * @return
     */
    @Override
    public ArrayList<String> getTypeLists(int type) {
        return model.getTypeDayList(type);
    }
}
