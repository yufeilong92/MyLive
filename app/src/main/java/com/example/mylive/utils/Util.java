package com.example.mylive.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.example.mylive.R;
import com.example.mylive.vo.DataYMDWVo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;

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
    private Context mContext;

    private Util(Context context) {
        this.mContext = context;
    }

    public static Util get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (Util.class) {
                if (_singleton == null) {
                    _singleton = new Util(context);
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

    public ArrayList<String> getArralistWithArray(int id) {
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
            first++;
            if (first == 1) {
                switch (wek) {
                    case 1://星期一
                        break;
                    case 2://星期二
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
                    case 3://星期三
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 1);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 1);
                        }
                        break;
                    case 4://星期四
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 2);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 2);
                        }
                        break;
                    case 5://星期五
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 3);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 3);
                        }
                        break;
                    case 6://星期六
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 4);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 4);
                        }
                        break;
                    case 7://星期日
                        if (M == 1) {
                            int y_new = Y - 1;
                            int m_new = 12;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 5);
                        } else {
                            int y_new = Y;
                            int m_new = M;
                            String lastDate1 = getFirstOrLastDate(y_new, m_new, 1, true);
                            addList(list, vo, y_new, m_new, lastDate1, 5);
                        }
                        break;
                }
            }
            list.add(vo);
        }
        return list;

    }

    private void addList(ArrayList<DataYMDWVo> list, DataYMDWVo vo, int y_new, int m_new, String lastDate1, int num) {
        for (int i = 0; i < num + 1; i++) {
            getWek(Integer.parseInt(lastDate1) - (num - i), y_new, m_new, vo);
            list.add(vo);
        }
/*        getWek(Integer.parseInt(lastDate1)-2, y_new, m_new, vo);
        list.add(vo);
        getWek(Integer.parseInt(lastDate1)-1, y_new, m_new, vo);
        list.add(vo);
        getWek(Integer.parseInt(lastDate1), y_new, m_new, vo);
        list.add(vo);*/
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
        Date date = new Date();
        date.setYear(y);
        date.setMonth(m);
        date.setDate(d);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int wek = c.get(Calendar.DAY_OF_WEEK);
        vo.setYear(String.valueOf(y));
        vo.setMonth(String.valueOf(m));
        vo.setDay(String.valueOf(d));
        vo.setWeek(String.valueOf(wek));
        return wek;
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
     * 获取第一天，最后一天
     *
     * @param y       年
     * @param m       月
     * @param d       日
     * @param isFasle 是否最后一天
     * @return
     */
    public String getFirstOrLastDate(int y, int m, int d, boolean isFasle) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(y, m, d);
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
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
        buffer.append(c.get(Calendar.MONTH) + "月");
        buffer.append(c.get(Calendar.DATE) + "日");
        return buffer.toString();
    }

    /**
     * @param selectNight
     * @param select
     * @param type
     * @return
     */
    public int getDayOrNightOrRest(int selectNight, boolean select, int type) {

        return 0;
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
    public void HandlepostDelayed(long m ,OnHandleClickListener onHandleClickListener) {
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
