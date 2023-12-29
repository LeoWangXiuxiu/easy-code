package util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import constant.TimeType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     *
     * @return
     */
    public static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        // 格式化时间并输出
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }

    /**
     * 获取Rfc3339时间
     *
     * @param timeType 时间类型
     * @param offset   偏移量  整数为多长时间后  负数为多久之前
     * @return
     */
    public static String getRfc3339(Integer timeType, Integer offset) {
        Date currentDate = new Date();
        if (timeType != null) {
            if (timeType == TimeType.hour.value) {
                currentDate = DateUtil.offsetHour(currentDate, offset);
            } else if (timeType == TimeType.minute.value) {
                currentDate = DateUtil.offsetMinute(currentDate, offset);
            } else {
                currentDate = DateUtil.offsetSecond(currentDate, offset);
            }
        }
        ZonedDateTime zonedDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String rfc3339String = zonedDateTime.format(formatter);
        return rfc3339String;
    }

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
        DateTime dateTime = DateUtil.offsetMinute(d, 5);
        System.out.println(dateTime);
    }

}
