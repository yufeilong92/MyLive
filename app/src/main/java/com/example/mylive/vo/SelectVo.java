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
     * 是否是夜班
     */
    private boolean isNight;
    /**
     * 是否第几个夜班
     */
    private int selectNight;
    /**
     * 是否第几个白天
     */
    private int selectDay;
    /**
     * 是否休息
     */
    private int isRest;
    /**
     * 第几个休
     */
    private int selectRest;

    public int getSelectRest() {
        return selectRest;
    }

    public void setSelectRest(int selectRest) {
        this.selectRest = selectRest;
    }

    public int getIsRest() {
        return isRest;
    }

    public void setIsRest(int isRest) {
        this.isRest = isRest;
    }

    public int getSelectNight() {
        return selectNight;
    }

    public void setSelectNight(int selectNight) {
        this.selectNight = selectNight;
    }

    public int getSelectDay() {
        return selectDay;
    }

    public void setSelectDay(int selectDay) {
        this.selectDay = selectDay;
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

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }
}
