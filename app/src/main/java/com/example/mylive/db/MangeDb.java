package com.example.mylive.db;

import android.content.Context;

import com.example.mylive.base.DataManageVo;
import com.example.mylive.utils.StringUtls;
import com.example.mylive.utils.Util;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.db
 * @Description: 数据manage
 * @author: L-BackPacker
 * @date: 2018.10.30 上午 10:08
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class MangeDb {
    private static volatile MangeDb _singleton;
    private Context mContext;

    private MangeDb(Context context) {
        this.mContext = context;
    }

    public static MangeDb get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (MangeDb.class) {
                if (_singleton == null) {
                    _singleton = new MangeDb(context);
                }
            }
        }
        return _singleton;
    }

    /**
     * 添加用户设置
     *
     * @param mData
     */
    public void addUserSetting(UserSetting mData) {
        UserSetting userSeting = findUserSeting();
        if (userSeting != null) {
            mData.update(userSeting.getId());
        } else
            mData.save();
    }

    public void upDataUserSetting(UserSetting userSetting) {
        if (userSetting == null) return;
        UserSetting seting = findUserSeting();
        userSetting.update(seting.getId());
    }

    /**
     * 查询用户设置
     *
     * @return
     */
    public UserSetting findUserSeting() {
        List<UserSetting> list = LitePal.findAll(UserSetting.class);
        if (list == null || list.isEmpty()) {
            UserSetting setting = new UserSetting();
            setting.setName(DataManageVo.db_name);
            Util util = Util.get_Instance(mContext);
            String date = util.getFirstOrLastDate(0);
            String[] dataArray = util.getDataArray(date);
            setting.setTime(date);
            setting.setType(DataManageVo.SUNNYTYPE);
            setting.setYear(dataArray[0]);
            setting.setMonth(dataArray[1]);
            setting.setDay(dataArray[2]);
            setting.setUserSelectDay(DataManageVo.ONE_DAY);
            setting.setWriterSelectDay(DataManageVo.ONE_DAY);
            setting.save();
            return setting;
        }
        return list.get(0);
    }

    /**
     * 查询用所有事件
     *
     * @return
     */
    public List<UserInfom> findUserInfomAll() {
        List<UserInfom> infomList = LitePal.findAll(UserInfom.class);
        return infomList;
    }

    /**
     * 根据日期查询当天的事件
     *
     * @param time
     * @return
     */
    public UserInfom findUserInfomWithDate(String time) {
        if (StringUtls.isEmpty(time)) return null;
        List<UserInfom> list = findUserInfomAll();
        for (int i = 0; i < list.size(); i++) {
            UserInfom infom = list.get(i);
            if (infom.getTime().equals(time)) {
                return infom;
            }
        }
        return null;
    }
}
