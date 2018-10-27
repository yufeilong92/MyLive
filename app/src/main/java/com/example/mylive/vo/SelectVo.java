package com.example.mylive.vo;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.vo
 * @Description: 用户选择vo
 * @author: L-BackPacker
 * @date: 2018.10.23 下午 4:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class SelectVo {
    /**
     * 日期
     */
    private int d;
    /**
     * 是否选中
     */
    private boolean isSelect;
    /**
     * 0 为休息 1为夜班 2 白班
     */
    private int SunnyOrDayOrNight;
    /**
     * 选择的天数
     */
    private int selectNumber;

    public int getSelectNumber() {
        return selectNumber;
    }

    public void setSelectNumber(int selectNumber) {
        this.selectNumber = selectNumber;
    }

    public int getSunnyOrDayOrNight() {
        return SunnyOrDayOrNight;
    }

    public void setSunnyOrDayOrNight(int sunnyOrDayOrNight) {
        SunnyOrDayOrNight = sunnyOrDayOrNight;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


}
