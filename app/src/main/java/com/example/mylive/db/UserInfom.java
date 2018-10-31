package com.example.mylive.db;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.db
 * @Description: 用户记录的事件
 * @author: L-BackPacker
 * @date: 2018.10.31 上午 11:16
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class UserInfom extends LitePalSupport {
    private Long id;
    /**
     * 用户年
     */
    private String year;
    /**
     * 月份
     */
    private String month;
    /**
     * 天数
     */
    private String day;
    /**
     * 姓名
     */
    private String name;
    /**
     * 任何事件
     */
    private String obj;
    /**
     * 星级
     */
    private String star;
    /**
     * 事件
     */
    private String sffair;
    /**
     * 用户选择时间
     */
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getSffair() {
        return sffair;
    }

    public void setSffair(String sffair) {
        this.sffair = sffair;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
