package com.example.mylive.db;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;


/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.db
 * @Description: 用户的设置vo
 * @author: L-BackPacker
 * @date: 2018.10.22 下午 2:25
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class UserSetting extends LitePalSupport {

    private Long id;

    /**
     * 用户唯一标识
     */
    private String name;
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String month;
    /**
     * 日
     */
    private String day;
    /**
     * 时间
     */
    private String time;

    /**
     * 类型type  0 为夜间，1为白天
     */
    private int type;
    /**
     * 第几天上班
     */
    private int userSelectDay;
    /**
     * 用户输入的时间
     */
    private int writerSelectDay;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserSelectDay() {
        return userSelectDay;
    }

    public void setUserSelectDay(int userSelectDay) {
        this.userSelectDay = userSelectDay;
    }

    public int getWriterSelectDay() {
        return writerSelectDay;
    }

    public void setWriterSelectDay(int writerSelectDay) {
        this.writerSelectDay = writerSelectDay;
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


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserSetting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                ", userSelectDay=" + userSelectDay +
                ", writerSelectDay=" + writerSelectDay +
                '}';
    }
}
