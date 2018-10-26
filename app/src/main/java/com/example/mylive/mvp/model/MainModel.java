package com.example.mylive.mvp.model;

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
     * @param type      0为夜间，1为白天
     * @return
     */
    @Override
    public ArrayList<SelectVo> getSaveTimeDatas(ArrayList<DataYMDWVo> datas, int selectDay, int daynum, int type) {
        ArrayList<SelectVo> selectVos = getIniTSelectLists(datas, datas.size());
        TypeManage manage = new TypeManage();
        ArrayList<SelectVo> selectLists = null;
        switch (type) {
            case DataManageVo.NIGHTTYPE://夜间
                selectLists = manage.selectType(new NightList(), selectDay, selectVos, daynum);
                break;
            case DataManageVo.SUNNYTYPE://白天
                selectLists = manage.selectType(new SunnyList(), selectDay, selectVos, daynum);
                break;
            default:

        }
        return selectLists;
    }

    @Override
    public ArrayList<String> getTypeNightAndSun() {
        ArrayList<String> list = new ArrayList<>();
        list.add("白班");
        list.add("夜班");
        return list;
    }


    @Override
    public ArrayList<String> getTypeDayList(int type) {
        ArrayList<String> list = new ArrayList<>();
        switch (type) {
            case DataManageVo.NIGHTTYPE://夜班
                list.clear();
                list.add("一");
                list.add("二");
                break;
            case DataManageVo.SUNNYTYPE://白班
                list.clear();
                list.add("一");
                list.add("二");
                list.add("三");
                list.add("四");
                break;
        }

        return list;
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

            int length = dates.size();
            for (int i = 0; i < dates.size(); i++) {
                SelectVo vo = dates.get(i);
                int j = i + 1;
                if (j == numday)
                    switch (selectDay) {
                        case DataManageVo.ONE_DAY://第一天夜班 正好月初为第一天上白班
                            addListData(0, dates, numday, length, i, j);
                            break;
                        case DataManageVo.TWO_DAY://第二天夜班
                            addListData(1, dates, numday, length, i, j);
                            break;
                    }


            }
            return dates;
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
            int length = dates.size();
            for (int i = 0; i < length; i++) {
                SelectVo vo = dates.get(i);
                int j = i + 1;
                if (j == numday)
                    switch (selectDay) {
                        case DataManageVo.ONE_DAY://第一天
                            addListData(2, dates, numday, length, i, j);
                            break;
                        case DataManageVo.TWO_DAY://第二天
                            addListData(3, dates, numday, length, i, j);
                            break;
                        case DataManageVo.THREE_DAY://第三天

                            addListData(4, dates, numday, length, i, j);
                            break;
                        case DataManageVo.FOUR_DAY://第四天

                            addListData(5, dates, numday, length, i, j);
                            break;

                        default:

                    }
            }
            return dates;
        }

    }

    /**
     * @param oneDay
     * @param dates  数据集合
     * @param numday 当天集合
     * @param length 集合长度
     * @param i      循环当前坐标
     * @param j      循环当前坐标+1
     * @return
     */
    private void addListData(int oneDay, ArrayList<SelectVo> dates, int numday, int length, int i, int j) {
        if (numday == 1) {
            //差天数
            //差天数
            int bad = length / 8;
            for (int k = 0; k < bad + 1; k++) {
                i += k * 8;
                j += k * 8;
                if (i > length) {
                    int m = bad * 8;
                    selectViewData(oneDay, dates, length, m, m + 1);

                } else {
                    selectViewData(oneDay, dates, length, i, j);

                }
            }
        } else {
            //夜间
//                            差天数
            int mistake = length - numday;
            /**
             * 往后算
             */
            int q=0;
            if (mistake != 0) {
                q = mistake / 8;
            }
            /**
             * 往前算
             */
            int bad = numday / 8;
            //如果bad 等于0 就是一轮
//                            否则就是倍循环
            if (bad == 0) {
                selectViewData(oneDay, dates, length, i, j);
                i += 8;
                j += 8;
                //重新获取倍数，在倍数加一
                int n = length / 8;
                for (int k = 0; k < n + 1; k++) {
                    i += k * 8;
                    j += k * 8;
                    if (i > length) {
                        int m = n * 8;
                        selectViewData(oneDay, dates, length, m, m + 1);
                    } else {
                        selectViewData(oneDay, dates, length, i, j);
                    }
                }
            } else {//倍数
                int w = bad * 8;
                int r = numday - w;
                listForEach(bad + 1, oneDay, dates, length, r);
                for (int k = 0; k < q + 1; k++) {
                    i += k * 8;
                    j += k * 8;
                    if (i > length) {
                        int m = q * 8 + numday;
                        selectViewData(oneDay, dates, length, m, m + 1);
                    } else {
                        selectViewData(oneDay, dates, length, i, j);
                    }
                }
            }

        }
    }

    private void listForEach(int num, int oneDay, ArrayList<SelectVo> dates, int length, int i) {
        for (int k = 0; k < num; k++) {
            int y = i + 8 * k >= length ? length - 1 : i + 8 * k;
            selectViewData(oneDay, dates, length,y , y+ 1);

        }
    }

    private void selectViewData(int oneDay, ArrayList<SelectVo> dates, int length, int i, int j) {
        switch (oneDay) {
            case 0:
                addNightOneItem(dates, length, i, j);
                break;
            case 1:
                addNightTwoItem(dates, length, i, j);
                break;
            case 2:
                addSunnyOneItem(dates, length, i, j);
                break;
            case 3:
                addSunnyTwoItem(dates, length, i, j);
                break;
            case 4:
                addSunnyThreeItem(dates, length, i, j);
                break;
            case 5:
                addSunnyFourItem(dates, length, i, j);
                break;

        }
    }

    /**
     * @param dates  数据集合
     * @param length 总数
     * @param i      当前循环坐标
     * @param j      当前循环坐标+1
     */
    private void addNightOneItem(ArrayList<SelectVo> dates, int length, int i, int j) {
        //前一天
        if (j - 8 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 8);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 7 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 7);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 6 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 6);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 5 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 4 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 4);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 3 > 0 && j <= length) {
            SelectVo selectVo = dates.get(i - 3);
            selectVo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //前一天
        if (j - 2 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 2);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //下一天
        if (j - 1 > 0 && j <= length) {
            SelectVo selectVo2 = dates.get(i - 1);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }

        //夜间
        SelectVo vo = dates.get(i);
        vo.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);


        if (j + 1 <= length) {
            SelectVo selectVo5 = dates.get(i + 1);
            selectVo5.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 2 <= length) {
            SelectVo selectVo6 = dates.get(i + 2);
            selectVo6.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 3 <= length) {
            SelectVo selectVo7 = dates.get(i + 3);
            selectVo7.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        // 白天
        if (j + 4 <= length) {
            SelectVo selectVo = dates.get(i + 4);
            selectVo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 5 <= length) {

            SelectVo selectVo1 = dates.get(i + 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 6 <= length) {
            SelectVo selectVo2 = dates.get(i + 6);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 7 <= length) {
            SelectVo selectVo3 = dates.get(i + 7);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 8 <= length) {
            SelectVo selectVo3 = dates.get(i + 8);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
    }

    /**
     * @param dates  数据集合
     * @param length 总数
     * @param i      当前循环坐标
     * @param j      当前循环坐标+1
     */
    private void addNightTwoItem(ArrayList<SelectVo> dates, int length, int i, int j) {
        //前一天
        if (j - 8 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 8);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 7 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 7);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 6 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 6);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 5 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 4 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 4);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 3 > 0 && j <= length) {
            SelectVo selectVo = dates.get(i - 3);
            selectVo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //前一天
        if (j - 2 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 2);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //下一天
        if (j - 1 > 0 && j <= length) {
            SelectVo selectVo2 = dates.get(i - 1);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }

        //夜间
        SelectVo vo = dates.get(i);
        vo.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);


        if (j + 1 <= length) {
            SelectVo selectVo5 = dates.get(i + 1);
            selectVo5.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 2 <= length) {
            SelectVo selectVo6 = dates.get(i + 2);
            selectVo6.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 3 <= length) {
            SelectVo selectVo7 = dates.get(i + 3);
            selectVo7.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        // 白天
        if (j + 4 <= length) {
            SelectVo selectVo = dates.get(i + 4);
            selectVo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 5 <= length) {

            SelectVo selectVo1 = dates.get(i + 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 6 <= length) {
            SelectVo selectVo2 = dates.get(i + 6);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 7 <= length) {
            SelectVo selectVo3 = dates.get(i + 7);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 8 <= length) {
            SelectVo selectVo3 = dates.get(i + 8);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
    }

    /**
     * @param dates  数据集合
     * @param length 总数
     * @param i      当前循环坐标
     * @param j      当前循环坐标+1
     */
    private void addSunnyOneItem(ArrayList<SelectVo> dates, int length, int i, int j) {
        //前一天
        if (j - 8 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 8);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 7 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 7);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 6 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 6);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 5 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 4 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 4);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 3 > 0 && j <= length) {
            SelectVo selectVo = dates.get(i - 3);
            selectVo.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        //前一天
        if (j - 2 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 2);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        //下一天
        if (j - 1 > 0 && j <= length) {
            SelectVo selectVo2 = dates.get(i - 1);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }

        //夜间
        SelectVo vo = dates.get(i);
        vo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);


        if (j + 1 <= length) {
            SelectVo selectVo5 = dates.get(i + 1);
            selectVo5.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 2 <= length) {
            SelectVo selectVo6 = dates.get(i + 2);
            selectVo6.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 3 <= length) {
            SelectVo selectVo7 = dates.get(i + 3);
            selectVo7.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        // 白天
        if (j + 4 <= length) {
            SelectVo selectVo = dates.get(i + 4);
            selectVo.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 5 <= length) {

            SelectVo selectVo1 = dates.get(i + 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 6 <= length) {
            SelectVo selectVo2 = dates.get(i + 6);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 7 <= length) {
            SelectVo selectVo3 = dates.get(i + 7);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 8 <= length) {
            SelectVo selectVo3 = dates.get(i + 8);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
    }

    /**
     * @param dates  数据集合
     * @param length 总数
     * @param i      当前循环坐标
     * @param j      当前循环坐标+1
     */
    private void addSunnyTwoItem(ArrayList<SelectVo> dates, int length, int i, int j) {
        //前一天
        if (j - 8 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 8);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 7 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 7);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 6 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 6);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 5 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 4 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 4);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 3 > 0 && j <= length) {
            SelectVo selectVo = dates.get(i - 3);
            selectVo.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        //前一天
        if (j - 2 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 2);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        //下一天
        if (j - 1 > 0 && j <= length) {
            SelectVo selectVo2 = dates.get(i - 1);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }

        //夜间
        SelectVo vo = dates.get(i);
        vo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);


        if (j + 1 <= length) {
            SelectVo selectVo5 = dates.get(i + 1);
            selectVo5.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 2 <= length) {
            SelectVo selectVo6 = dates.get(i + 2);
            selectVo6.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 3 <= length) {
            SelectVo selectVo7 = dates.get(i + 3);
            selectVo7.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        // 白天
        if (j + 4 <= length) {
            SelectVo selectVo = dates.get(i + 4);
            selectVo.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 5 <= length) {

            SelectVo selectVo1 = dates.get(i + 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 6 <= length) {
            SelectVo selectVo2 = dates.get(i + 6);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 7 <= length) {
            SelectVo selectVo3 = dates.get(i + 7);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 8 <= length) {
            SelectVo selectVo3 = dates.get(i + 8);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
    }

    /**
     * @param dates  数据集合
     * @param length 总数
     * @param i      当前循环坐标
     * @param j      当前循环坐标+1
     */
    private void addSunnyThreeItem(ArrayList<SelectVo> dates, int length, int i, int j) {
        //前一天
        if (j - 8 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 8);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 7 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 7);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 6 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 6);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 5 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 4 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 4);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 3 > 0 && j <= length) {
            SelectVo selectVo = dates.get(i - 3);
            selectVo.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        //前一天
        if (j - 2 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 2);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //下一天
        if (j - 1 > 0 && j <= length) {
            SelectVo selectVo2 = dates.get(i - 1);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }

        //夜间
        SelectVo vo = dates.get(i);
        vo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);


        if (j + 1 <= length) {
            SelectVo selectVo5 = dates.get(i + 1);
            selectVo5.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 2 <= length) {
            SelectVo selectVo6 = dates.get(i + 2);
            selectVo6.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 3 <= length) {
            SelectVo selectVo7 = dates.get(i + 3);
            selectVo7.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        // 白天
        if (j + 4 <= length) {
            SelectVo selectVo = dates.get(i + 4);
            selectVo.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 5 <= length) {

            SelectVo selectVo1 = dates.get(i + 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 6 <= length) {
            SelectVo selectVo2 = dates.get(i + 6);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 7 <= length) {
            SelectVo selectVo3 = dates.get(i + 7);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 8 <= length) {
            SelectVo selectVo3 = dates.get(i + 8);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
    }

    /**
     * @param dates  数据集合
     * @param length 总数
     * @param i      当前循环坐标
     * @param j      当前循环坐标+1
     */
    private void addSunnyFourItem(ArrayList<SelectVo> dates, int length, int i, int j) {
        //前一天
        if (j - 8 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 8);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j - 7 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 7);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 6 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 6);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 5 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j - 4 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 4);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j - 3 > 0 && j <= length) {
            SelectVo selectVo = dates.get(i - 3);
            selectVo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //前一天
        if (j - 2 > 0 && j <= length) {
            SelectVo selectVo1 = dates.get(i - 2);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        //下一天
        if (j - 1 > 0 && j <= length) {
            SelectVo selectVo2 = dates.get(i - 1);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }

        //夜间
        SelectVo vo = dates.get(i);
        vo.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);


        if (j + 1 <= length) {
            SelectVo selectVo5 = dates.get(i + 1);
            selectVo5.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        if (j + 2 <= length) {
            SelectVo selectVo6 = dates.get(i + 2);
            selectVo6.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 3 <= length) {
            SelectVo selectVo7 = dates.get(i + 3);
            selectVo7.setSunnyOrDayOrNight(DataManageVo.NIGHTTYPE);
        }
        // 白天
        if (j + 4 <= length) {
            SelectVo selectVo = dates.get(i + 4);
            selectVo.setSunnyOrDayOrNight(DataManageVo.RESTTYPE);
        }
        if (j + 5 <= length) {

            SelectVo selectVo1 = dates.get(i + 5);
            selectVo1.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 6 <= length) {
            SelectVo selectVo2 = dates.get(i + 6);
            selectVo2.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 7 <= length) {
            SelectVo selectVo3 = dates.get(i + 7);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
        if (j + 8 <= length) {
            SelectVo selectVo3 = dates.get(i + 8);
            selectVo3.setSunnyOrDayOrNight(DataManageVo.SUNNYTYPE);
        }
    }

}
