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
}
