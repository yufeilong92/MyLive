package com.example.mylive.mvp.contract;

import android.content.Context;

import com.example.mylive.db.UserSetting;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.mvp.contract
 * @Description: 主界面控制
 * @author: L-BackPacker
 * @date: 2018.10.23 下午 5:39
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public interface MainContract {
    interface Model {
        public ArrayList<SelectVo> getIniTSelectLists(ArrayList<DataYMDWVo> mTimteDatas, int dayNum);

        public ArrayList<SelectVo> getSaveTimeDatas(ArrayList<DataYMDWVo> datas, int selectDay, int daynum, int type,int out);

        public ArrayList<String> getTypeNightAndSun();

        public ArrayList<String> getTypeDayList(int type);
        public void putUserSetting(Context context, int selectDay, int numberDay, int type);
        public UserSetting getUserSetting(Context context);
    }

    interface View {
    }

    interface Presenter {
        public void initModelView(Model model, View view);

        public ArrayList<SelectVo> getInitSelectList(ArrayList<DataYMDWVo> mTimteDatas);

        public ArrayList<SelectVo> getSelectVoDatas(ArrayList<DataYMDWVo> datas, int selectDay, int daynum, int type,int out);

        public ArrayList<String> getTypeNightAndSun();

        public ArrayList<String> getTypeLists(int type);
        public void putUserSetting(Context context, int selectDay, int numberDay, int type);
        public UserSetting getUserSetting(Context context);
    }
}
