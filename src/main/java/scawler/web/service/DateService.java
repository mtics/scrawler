package scawler.web.service;

import java.io.Serializable;
import java.util.Date;

public class DateService {

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
}
