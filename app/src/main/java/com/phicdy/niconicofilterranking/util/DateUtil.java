package com.phicdy.niconicofilterranking.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static String convertNicoChartDate(String date) {
        if(date.contains("T")) {
            String replaced = date.replace("T", " ");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPAN);
            Date formatWithW3cdtf = null;
            try {
                formatWithW3cdtf = format.parse(replaced);
                DateFormat jpFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.JAPAN);
                return jpFormat.format(formatWithW3cdtf);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
