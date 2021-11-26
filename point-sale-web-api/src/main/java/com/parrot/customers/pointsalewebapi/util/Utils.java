package com.parrot.customers.pointsalewebapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.parrot.customers.pointsalewebapi.constans.Constans;

public class Utils {
    
    public static Date parseStringToDate(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constans.DATE_FORMAT);
        return simpleDateFormat.parse(dateString);
    }
}
