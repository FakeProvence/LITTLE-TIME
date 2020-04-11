package com.example.little_time.utils;

import java.util.Date;
import java.util.Random;

public class RandomUtils {

    //返回时间戳
    public static String getRandomId() {
        long a = new Date().getTime();
        String id = String.valueOf(a);
//        id.substring(7, 13);
        return id;
    }

    //返回随机字符串，长度由用户定
    public static String generateCode(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
