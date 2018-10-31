package com.example.mylive.base;

import android.os.Build;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.base
 * @Description: 常量设置
 * @author: L-BackPacker
 * @date: 2018.10.24 上午 11:15
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class DataManageVo {
    private volatile static DataManageVo _instance;

    private DataManageVo() {
    }

    public static DataManageVo get_Instance() {
        if (_instance == null) {
            synchronized (DataManageVo.class) {
                if (_instance == null) {
                    _instance = new DataManageVo();
                }
            }
        }
        return _instance;
    }

    /**
     * 休息
     */
    public static final int RESTTYPE = 0;
    /**
     * 夜班
     */
    public static final int NIGHTTYPE = 1;
    /**
     * 白班
     */
    public static final int SUNNYTYPE = 2;
//，0 为第一天夜班，1第二天夜班；
//    type 1 为白天时，2 为第一天白班，3为第二天白班，4为第三天白班，5为第四天白班， ；
    /**
     * 一
     */
    public static final int ONE_DAY = 1;
    /**
     * 二
     */
    public static final int TWO_DAY = 2;
    /**
     * 三
     */
    public static final int THREE_DAY = 3;
    /**
     * 四
     */
    public static final int FOUR_DAY = 4;

    /**
     * 0 为夜间，1为白天
     */
    private int NightOrSunnytype = DataManageVo.SUNNYTYPE;
    /**
     * 第几天（设置选择）
     */
    private int selectDay = DataManageVo.ONE_DAY;
    /**
     * 手机型号
     */
    public static final String db_name = Build.BRAND;
    /**
     * 当月天数 (用户填写)
     */
    private int writeDay = 1;

    public int getWriteDay() {
        return writeDay;
    }

    public void setWriteDay(int writeDay) {
        this.writeDay = writeDay;
    }

    public int getNightOrSunnytype() {
        return NightOrSunnytype;
    }

    public void setNightOrSunnytype(int nightOrSunnytype) {
        this.NightOrSunnytype = nightOrSunnytype;
    }

    public int getSelectDay() {
        return selectDay;
    }

    public void setSelectDay(int selectDay) {
        this.selectDay = selectDay;
    }
}
