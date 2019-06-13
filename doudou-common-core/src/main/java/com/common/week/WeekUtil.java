package com.common.week;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekUtil {

    /**
     * 根据日期字符串判断当月第几周
     * @param str  2012-5-27
     * @return
     * @throws Exception
     */
    public static int getWeek(String str){
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.WEEK_OF_MONTH);
        }catch (Exception e){
            return -1;
        }
    }
    /**
     * 根据日期字符串判断第几月
     * @param str  2012-5-27
     * @return
     * @throws Exception
     */
    public static int getMon(String str){
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.MONTH)+1;
        }catch (Exception e){
            return -1;
        }
    }
    public static int getQuarter(String str){
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int nowMonth = calendar.get(Calendar.MONTH)+1;
            if(1<=nowMonth&&nowMonth<=3){
                return 1;
            }
            if(4<=nowMonth&&nowMonth<=6){
                return 2;
            }
            if(7<=nowMonth&&nowMonth<=9){
                return 3;
            }
            if(10<=nowMonth&&nowMonth<=12){
                return 4;
            }
        }catch (Exception e){ }
        return -1;
    }
}
