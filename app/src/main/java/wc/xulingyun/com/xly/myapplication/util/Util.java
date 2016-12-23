package wc.xulingyun.com.xly.myapplication.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static final int SIZE_UNIT = 1024;

    private static final int MS_UNIT = 1000;
    private static final int TIME_UNIT = 60;
    public static String VideoOrAudioSize(String size ){
        String stringSize="0b";
        if(size==null) return stringSize;
        long intSize = Long.parseLong(size);
        if(intSize<SIZE_UNIT&&intSize>0){
            stringSize = intSize+"b";
        }else if(intSize<SIZE_UNIT*SIZE_UNIT&&intSize>=SIZE_UNIT){
            stringSize = new DecimalFormat("#0.00").format(intSize*1.0/SIZE_UNIT)+"K";
        }else if(intSize<SIZE_UNIT*SIZE_UNIT*SIZE_UNIT&&intSize>=SIZE_UNIT*SIZE_UNIT){
            stringSize = new DecimalFormat("#0.00").format(intSize*1.0/SIZE_UNIT/SIZE_UNIT)+"M";
        }else if(intSize<SIZE_UNIT*SIZE_UNIT*SIZE_UNIT*SIZE_UNIT&&intSize<SIZE_UNIT*SIZE_UNIT*SIZE_UNIT){
            stringSize = new DecimalFormat("#0.00").format(intSize*1.0/SIZE_UNIT/SIZE_UNIT/SIZE_UNIT)+"G";
        }else if(intSize>=SIZE_UNIT*SIZE_UNIT*SIZE_UNIT*SIZE_UNIT){
            stringSize = new DecimalFormat("#0.00").format(intSize*1.0/SIZE_UNIT/SIZE_UNIT/SIZE_UNIT/SIZE_UNIT)+"T";
        }
        return stringSize;
    }

    public static String formatTime(int time){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date d1=new Date(time);
        return format.format(d1);
    }


}
