package com.hensen.shixiongsdk.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 许英俊 2017/5/8
 */
public class DateUtils {

    /**
     * 字符串时间戳格式化时间
     *
     * @param date
     * @return
     */
    public static String convertTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date(Long.parseLong(date) * 1000);
        return sdf.format(dt);
    }
}
