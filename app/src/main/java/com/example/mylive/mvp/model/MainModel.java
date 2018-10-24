package com.example.mylive.mvp.model;

import com.example.mylive.R;
import com.example.mylive.base.DataManageVo;
import com.example.mylive.mvp.contract.MainContract;
import com.example.mylive.vo.DataYMDWVo;
import com.example.mylive.vo.SelectVo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive.mvp.model
 * @Description: 主界面数据类
 * @author: L-BackPacker
 * @date: 2018.10.23 下午 5:39
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class MainModel implements MainContract.Model {

    /**
     * @param mTimteDatas 当月时间集合
     * @param dayNum      当月总共多少天数
     * @return
     */
    @Override
    public ArrayList<SelectVo> getIniTSelectLists(ArrayList<DataYMDWVo> mTimteDatas, int dayNum) {
        ArrayList<SelectVo> mLists = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DATE);
        for (int i = 0; i < dayNum; i++) {
            DataYMDWVo vo = mTimteDatas.get(i);
            SelectVo selectVo = new SelectVo();
            if (vo.getDay().equals(String.valueOf(d))) {
                selectVo.setSelect(true);
            } else {
                selectVo.setSelect(false);
            }

            selectVo.setD(i);
            mLists.add(selectVo);
        }
        return mLists;
    }

    /**
     * 储存得数据
     *
     * @param datas     当月得集合
     * @param selectDay 选择得天数 type 0 为夜间时，0 为第一天夜班，1第二天夜班；
     *                  type 1 为白天时，2 为第一天白班，3为第二天白班，4为第三天白班，5为第四天白班， ；
     * @param daynum    设置天数
     * @param type
     * @return
     */
    @Override
    public ArrayList<SelectVo> getSaveTimeDatas(ArrayList<DataYMDWVo> datas, int selectDay, int daynum, int type) {
        ArrayList<SelectVo> selectVos = getIniTSelectLists(datas, datas.size());


        return null;
    }

    /**
     * 定义接口
     */
    private interface TypeInterface {
        /**
         * @param dates     显示得集合
         * @param selectDay 第几天
         * @param numday    要显示得天数
         * @return
         */
        public ArrayList<SelectVo> addTypeTime(ArrayList<SelectVo> dates, int selectDay, int numday);
    }

    /**
     * 定义管理器
     */
    private class TypeManage {
        /**
         * @param dates     显示得集合
         * @param selectDay 第几天
         * @param numday    要显示得天数
         * @return
         */
        public ArrayList<SelectVo> selectType(TypeInterface typeFace, int selectDay, ArrayList<SelectVo> dates, int numday) {
            return typeFace.addTypeTime(dates, selectDay, numday);
        }
    }
//    选择得天数 type 0 为夜间时，0 为第一天夜班，1第二天夜班；
//    type 1 为白天时，2 为第一天白班，3为第二天白班，4为第三天白班，5为第四天白班， ；

    /**
     * 夜间逻辑
     */
    private class NightList implements TypeInterface {
        /**
         * @param dates     显示得集合
         * @param selectDay 第几天
         * @param numday    要显示得天数
         * @return
         */
        @Override
        public ArrayList<SelectVo> addTypeTime(ArrayList<SelectVo> dates, int selectDay, int numday) {
            DataManageVo dataManageVo = DataManageVo.get_Instance();
            int length = dates.size();
            for (int i = 0; i < dates.size(); i++) {
                SelectVo vo = dates.get(i);
                int j = i + 1;
                switch (selectDay) {
                    case 0://第一天夜班 正好月初为第一天上白班
                        if (numday == 1) {
                            if ((i + 1) == numday) {
                                addItem(dates, dataManageVo, length, i, j);
                            }
                        } else {
                            //夜间
//                            差天数
                            int mistake = length - numday;
                            int bad = mistake / 8;
                            //如果bad 等于0 就是一轮
//                            否则就是倍循环
                            if (bad == 0) {
                                addItem(dates, dataManageVo, length, i, j);
                            } else {//倍数
                                int u = mistake - 8 * bad;
                                int num = 0;//循环数
                                for (int k = 0; k < bad; k++) {
                                    num++;
                                    addItem(dates, dataManageVo, length, i, j);
                                }
                            }

                        }

                        break;
                    case 1://第二天夜班
                        if ((i + 1) == numday) {
                            //夜间
                            if (j - 2 > 0) {

                                SelectVo selectVo6 = dates.get(i - 2);
                                selectVo6.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j - 1 > 0) {

                                SelectVo selectVo7 = dates.get(i - 1);
                                selectVo7.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }

                            vo.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            if (j + 1 <= length) {

                                SelectVo selectVo5 = dates.get(i + 1);
                                selectVo5.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                            // 白天
                            if (j + 2 <= length) {

                                SelectVo selectVo = dates.get(i + 2);
                                selectVo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            if (j + 3 <= length) {

                                SelectVo selectVo1 = dates.get(i + 3);
                                selectVo1.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            if (j + 4 <= length) {

                                SelectVo selectVo2 = dates.get(i + 4);
                                selectVo2.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            if (j + 5 <= length) {

                                SelectVo selectVo3 = dates.get(i + 5);
                                selectVo3.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                        }

                        break;
                    default:

                }


            }

            return dates;
        }

        /**
         * @param dates        数据集合
         * @param dataManageVo 管理器
         * @param length       总数
         * @param i            当前循环坐标
         * @param j            当前循环坐标+1
         */
        private void addItem(ArrayList<SelectVo> dates, DataManageVo dataManageVo, int length, int i, int j) {
            //前一天
            if (j - 8 > 0) {
                SelectVo selectVo1 = dates.get(i - 8);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            if (j - 7 > 0) {
                SelectVo selectVo1 = dates.get(i - 7);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            if (j - 6 > 0) {
                SelectVo selectVo1 = dates.get(i - 6);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            if (j - 5 > 0) {
                SelectVo selectVo1 = dates.get(i - 5);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            if (j - 4 > 0) {
                SelectVo selectVo1 = dates.get(i - 4);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            if (j - 3 > 0) {
                SelectVo selectVo = dates.get(i - 3);
                selectVo.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
            }
            //前一天
            if (j - 2 > 0) {
                SelectVo selectVo1 = dates.get(i - 2);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            //下一天
            if (j - 1 > 0) {
                SelectVo selectVo2 = dates.get(i - 1);
                selectVo2.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
            }

            //夜间
            SelectVo vo = dates.get(i);
            vo.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);


            if (j + 1 <= length) {
                SelectVo selectVo5 = dates.get(i + 1);
                selectVo5.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
            }
            if (j + 2 <= length) {
                SelectVo selectVo6 = dates.get(i + 2);
                selectVo6.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
            if (j + 3 <= length) {
                SelectVo selectVo7 = dates.get(i + 3);
                selectVo7.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
            }
            // 白天
            if (j + 4 <= length) {
                SelectVo selectVo = dates.get(i + 4);
                selectVo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
            }
            if (j + 5 <= length) {

                SelectVo selectVo1 = dates.get(i + 5);
                selectVo1.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
            }
            if (j + 6 <= length) {
                SelectVo selectVo2 = dates.get(i + 6);
                selectVo2.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
            }
            if (j + 7 <= length) {
                SelectVo selectVo3 = dates.get(i + 7);
                selectVo3.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
            }
            if (j + 8 <= length) {
                SelectVo selectVo3 = dates.get(i + 8);
                selectVo3.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
            }
        }
    }

    /**
     * 白天逻辑
     */
    private class SunnyList implements TypeInterface {
        /**
         * @param dates     显示得集合
         * @param selectDay 第几天
         * @param numday    要显示得天数
         * @return
         */
        @Override
        public ArrayList<SelectVo> addTypeTime(ArrayList<SelectVo> dates, int selectDay, int numday) {
            DataManageVo dataManageVo = DataManageVo.get_Instance();
            int length = dates.size();
            for (int i = 0; i < length; i++) {
                SelectVo vo = dates.get(i);
                int j = i + 1;
                switch (selectDay) {
                    case 2://第一天
                        if ((i + 1) == numday) {
                            // 白天

                            vo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            if (j + 1 <= length) {
                                SelectVo selectVo = dates.get(i + 1);
                                selectVo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            if (j + 2 <= length) {
                                SelectVo selectVo1 = dates.get(i + 2);
                                selectVo1.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            if (j + 3 <= length) {

                                SelectVo selectVo2 = dates.get(i + 3);
                                selectVo2.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            if (j + 4 <= length) {

                                SelectVo selectVo3 = dates.get(i + 4);
                                selectVo3.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //夜间
                            if (j + 5 <= length) {

                                SelectVo selectVo4 = dates.get(i + 5);
                                selectVo4.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 6 <= length) {

                                SelectVo selectVo5 = dates.get(i + 6);
                                selectVo5.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                            if (j + 7 <= length) {

                                SelectVo selectVo6 = dates.get(i + 7);
                                selectVo6.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 8 <= length) {

                                SelectVo selectVo7 = dates.get(i + 8);
                                selectVo7.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                        }
                        break;
                    case 3://第二天
                        if ((i + 1) == numday) {
                            // 白天
                            //前一天
                            if (j - 1 > 0) {
                                SelectVo selectVo = dates.get(i - 1);
                                selectVo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            ;
                            //当天
                            vo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            //下一天
                            if (j + 1 <= length) {

                                SelectVo selectVo1 = dates.get(i + 1);
                                selectVo1.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //下二天
                            if (j + 2 <= length) {
                                SelectVo selectVo2 = dates.get(i + 2);
                                selectVo2.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //夜间
                            if (j + 3 <= length) {

                                SelectVo selectVo3 = dates.get(i + 3);
                                selectVo3.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 4 <= length) {

                                SelectVo selectVo4 = dates.get(i + 4);
                                selectVo4.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                            if (j + 5 <= length) {

                                SelectVo selectVo5 = dates.get(i + 5);
                                selectVo5.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 6 <= length) {

                                SelectVo selectVo6 = dates.get(i + 6);
                                selectVo6.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                        }
                        break;
                    case 4://第三天
                        if ((i + 1) == numday) {
                            // 白天
                            //前二天
                            if (j - 2 > 0) {

                                SelectVo selectVo = dates.get(i - 2);
                                selectVo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //前一天
                            if (j - 1 > 0) {

                                SelectVo selectVo1 = dates.get(i - 1);
                                selectVo1.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //当天
                            vo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            //下一天
                            if (j + 1 <= length) {

                                SelectVo selectVo2 = dates.get(i + 1);
                                selectVo2.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //夜间
                            if (j + 2 <= length) {

                                SelectVo selectVo3 = dates.get(i + 2);
                                selectVo3.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 3 <= length) {

                                SelectVo selectVo4 = dates.get(i + 3);
                                selectVo4.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                            if (j + 4 <= length) {

                                SelectVo selectVo5 = dates.get(i + 4);
                                selectVo5.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 5 > length) {

                                SelectVo selectVo6 = dates.get(i + 5);
                                selectVo6.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                        }
                        break;
                    case 5://第四天
                        if ((i + 1) == numday) {
                            // 白天
                            //前二天
                            if (j - 3 > 0) {

                                SelectVo selectVo = dates.get(i - 3);
                                selectVo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //前一天
                            if (j - 2 > 0) {

                                SelectVo selectVo1 = dates.get(i - 2);
                                selectVo1.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //下一天
                            if (j - 1 > 0) {

                                SelectVo selectVo2 = dates.get(i - 1);
                                selectVo2.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            }
                            //当天
                            vo.setSunnyOrDayOrNight(dataManageVo.SUNNYTYPE);
                            //夜间
                            if (j + 1 <= length) {

                                SelectVo selectVo3 = dates.get(i + 1);
                                selectVo3.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 2 <= length) {

                                SelectVo selectVo4 = dates.get(i + 2);
                                selectVo4.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                            if (j + 3 <= length) {

                                SelectVo selectVo5 = dates.get(i + 3);
                                selectVo5.setSunnyOrDayOrNight(dataManageVo.NIGHTTYPE);
                            }
                            if (j + 4 <= length) {

                                SelectVo selectVo6 = dates.get(i + 4);
                                selectVo6.setSunnyOrDayOrNight(dataManageVo.RESTTYPE);
                            }
                        }
                        break;

                    default:

                }
            }
            return dates;
        }
    }
}
