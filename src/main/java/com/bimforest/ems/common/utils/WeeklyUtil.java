package com.bimforest.ems.common.utils;

import com.bimforest.ems.pojo.DateWeekly;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-11-19 14:47
 */
@Slf4j
@Data
public class WeeklyUtil {
    /**
     * 通过开始时间和当前时间获取目前所处的周次
     *
     * @return
     */
    //
    public static List<DateWeekly> getWeekly(String sDateTime, String eDateTime) {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(sDateTime);
            d2 = format.parse(eDateTime);
            //每周天数(7天)
            long week = 24 * 60 * 60 * 1000 * 7;
            long day = 24 * 60 * 60 * 1000;
            //毫秒ms
            long diff = d2.getTime() + 1 - d1.getTime() + 1;
            if (diff <= 0) {
                return null;
            }
            //毫秒转天
            long diffDays = diff / (24 * 60 * 60 * 1000);

            //创建所有周次及开始结束时间list
            List<DateWeekly> list = new ArrayList<>();
            //共多少周次
            long endWeekly = diffDays / 7 == 0 ? (diffDays / 7) : (diffDays / 7) + 1;
            for (int i = 1; i <= endWeekly; i++) {
                DateWeekly dateWeekly = new DateWeekly();
                //当前周次
                dateWeekly.setWeekly(i + 1);
                //当前周次结束日期(当前周次开始日期为 开始日期 = 周次*周+ 开始日期)
                long s = i * week;
                long e = (i - 1) * week;
                long edt = s + d1.getTime() - day;
                //当前周次结束日期(结束日期为 =  开始日期 + 周次 * 周 )
                long sdt = e + d1.getTime();
                String sdate = format.format(sdt);
                String edate = format.format(edt);
                //将得到是数据放入list
                dateWeekly.setWeekly(i);
                dateWeekly.setSDate(sdate);
                dateWeekly.setEDate(edate);
                list.add(i - 1, dateWeekly);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
