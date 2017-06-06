package com.webgis.utils;

import com.webgis.web.BaseResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by CCMEOW on 2017/6/5.
 */
public class DateUtil {
    public static String longToTimestamp(Long date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date time = new Date(date);
        return sdf.format(time);
    }

    public static Long timestampToLong(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date date = simpleDateFormat.parse(dateStr);
        return date.getTime();
    }
}
