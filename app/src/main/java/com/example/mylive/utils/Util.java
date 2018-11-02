package com.example.mylive.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.example.mylive.vo.DataYMDWVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyLive
 * @Package com.example.mylive
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.10.22 下午 1:44
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class Util {
    private static volatile Util _singleton;

    private Util() {
    }

    public static Util get_Instance() {
        if (_singleton == null) {
            synchronized (Util.class) {
                if (_singleton == null) {
                    _singleton = new Util();
                }
            }
        }
        return _singleton;
    }

    public boolean isEmpty(String trim) {
        if (trim == null || trim.trim().equals("")) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getArralistWithArray(Context mContext, int id) {
        ArrayList<String> mDataLists = new ArrayList<>();
        String[] stringArray = mContext.getResources().getStringArray(id);
        if (stringArray == null || stringArray.length == 0)
            return mDataLists;
        for (int i = 0; i < stringArray.length; i++) {
            mDataLists.add(stringArray[i]);
        }
        return mDataLists;
    }

    /**
     * 截取 两个时间 2018-3-30 与2018-3-1
     *
     * @param firstDate 开始时间
     * @param lastDate  结束时间
     * @return
     */
    public ArrayList<DataYMDWVo> getDatas(String firstDate, String lastDate) {
        ArrayList<DataYMDWVo> list = new ArrayList<>();
        String[] fistDatas = getDateStrings(firstDate);
        String[] lastDatas = getDateStrings(lastDate);
        String fistData = fistDatas[fistDatas.length - 1];
        String lastData = lastDatas[lastDatas.length - 1];
        //第一天
        int first = Integer.valueOf(fistData);
        int Y = Integer.parseInt(fistDatas[0]);
        int M = Integer.parseInt(fistDatas[1]);
        boolean isSame = true;
        while (isSame) {
            if (first == Integer.valueOf(lastData)) {
                isSame = false;
            }
            DataYMDWVo vo = new DataYMDWVo();
            int wek = getWek(first, Y, M, vo);

            if (first == 1) {//判断当月1号时星期几往前推几天填充数据
                switch (wek) {
                    case 2://星期一
                        break;
                    case 3://星期二
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            getWek(Integer.parseInt(lastDate1), y_new, m_new, vo);
                            list.add(vo);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            getWek(Integer.parseInt(lastDate1), y_new, m_new, vo);
                            list.add(vo);
                        }
                        break;
                    case 4://星期三
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 1);
                        } else {
                            int y_new = Y;
                            int m_new = M-1;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list,  y_new, m_new, lastDate1, 1);
                        }
                        break;
                    case 5://星期四
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 2);
                        } else {
                            int y_new = Y;
                            int m_new = M-1;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 2);
                        }
                        break;
                    case 6://星期五
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 3);
                        } else {
                            int y_new = Y;
                            int m_new = M-1;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 3);
                        }
                        break;
                    case 7://星期六
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 4);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 4);
                        }
                        break;
                    case 1://星期日
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 5);
                        } else {
                            int y_new = Y;
                            int m_new = M-1;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, y_new, m_new, lastDate1, 5);
                        }
                        break;
                }
            }
            first++;
            list.add(vo);
        }
        return list;

    }

    private void addList(ArrayList<DataYMDWVo> list, int y_new, int m_new, String lastDate1, int num) {
        String[] dateStrings = getDateStrings(lastDate1);
        lastDate1 = dateStrings[dateStrings.length - 1];
        for (int i = 0; i < num + 1; i++) {
            DataYMDWVo mDataVo = new DataYMDWVo();
            int day=Integer.parseInt(lastDate1) - (num - i);
            getWek(day, y_new, m_new, mDataVo);
            list.add(mDataVo);
        }


    }

    @NonNull
    private String[] getDateStrings(String lastDate) {
        return lastDate.split("-");
    }

    /**
     * @param d  第一天
     * @param y  年
     * @param m  月
     * @param vo 添加vo
     * @return
     */
    private int getWek(int d, int y, int m, DataYMDWVo vo) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(format.parse(y + "-" + m + "-" + d));
            int wek = c.get(Calendar.DAY_OF_WEEK);
            vo.setYear(String.valueOf(y));
            vo.setMonth(String.valueOf(m));
            vo.setDay(String.valueOf(d));
            int a = 1;
            if (wek == 1) {
                a = 7;
            } else {
                a = wek - 1;
            }
            vo.setWeek(String.valueOf(a));
            return wek;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * 获取每个月得第一天，或者最后一天 默认位最后一天
     *
     * @param firstOrlast 0位第一天  1位最后一天
     * @return
     */
    public String getFirstOrLastDate(int firstOrlast) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        if (firstOrlast == 0) {
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
        } else if (firstOrlast == 1) {
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
        }
        return format.format(cale.getTime());
    }

    /**
     * 获取某年某月的第一天，最后一天
     *
     * @param y       年
     * @param m       月
     * @param d       日
     * @param isFasle 是否最后一天
     * @return
     */
    public String getFirstOrLastDate(int y, int m, int d, boolean isFasle) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = new Date();
        try {
            parse = format.parse(y + "-" + m + "-" + d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cale = Calendar.getInstance();
        cale.setTime(parse);
        if (isFasle) {
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
        } else {
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
        }
        return format.format(cale.getTime());
    }

    public String getDataTime(Calendar c) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(c.get(Calendar.YEAR) + "年");
        buffer.append(c.get(Calendar.MONTH) + 1 + "月");
        buffer.append(c.get(Calendar.DATE) + "日");
        return buffer.toString();
    }


    public String[] getDataArray(String data) {
        String[] split = data.split("-");
        return split;
    }

    /**
     * 设置监听
     */
    private OnHandleClickListener onHandleClickListener;

    public interface OnHandleClickListener {
        public void onClickItem();

    }

    public void setOnHandleClickListener(OnHandleClickListener onHandleClickListener) {
        this.onHandleClickListener = onHandleClickListener;
    }

    private static final int HANDLERTAG = 12;

    public static void HandlepostDelayed(long m, OnHandleClickListener onHandleClickListener) {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case HANDLERTAG:
                        if (onHandleClickListener != null) {
                            onHandleClickListener.onClickItem();
                        }
                        break;
                }
            }
        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = handler.obtainMessage();
                message.what = HANDLERTAG;
                message.sendToTarget();
            }
        }, m);
    }
}
