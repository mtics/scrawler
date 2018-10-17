package scawler.web.service;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

public class DateService {

    /**
     * 将获得的秒转化为年月日，并以字符串形式返回
     * @param secondStr
     * @return
     */
    public String Second2Date(String  secondStr){
        long seconds = Long.parseLong(secondStr);
        Date date = new Date(seconds);
        int year = 1900+date.getYear();
        int month = date.getMonth() + 1;
        int day = date.getDate();
        Serializable hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        Serializable minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        Serializable second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        String  currentTime = year + "-" + month + "-" + day + "  " + hour + ":" + minute + ":" + second;
        return currentTime;
    }

    /**
     * 将字符串转化为整数
     * 若碰到汉字，则为0
     * @param str
     * @return
     */
    public int Str2Num(String str){
        boolean isNum = Pattern.matches("//d+", str);
        if (isNum)
            return Integer.parseInt(str);
        return 0;
    }
}
