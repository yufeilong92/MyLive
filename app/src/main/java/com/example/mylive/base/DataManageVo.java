package com.example.mylive.base;

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
    public final int RESTTYPE = 0;
    /**
     * 夜班
     */
    public final int NIGHTTYPE = 1;
    /**
     * 白班
     */
    public final int SUNNYTYPE = 2;

}
