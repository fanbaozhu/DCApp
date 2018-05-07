package com.xunchijn.administrator.event;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/8.
 */
public  class DataHelper {
    /***
     * 获取当前时间
     * @param formatstr 时间格式字符串
     * @return 时间string
     */
    public  static String GetDate(String formatstr)
    {
        SimpleDateFormat format = new SimpleDateFormat(formatstr);

        return format.format(new Date());
    }
}
