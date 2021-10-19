package com.cietrzew.wp.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateServiceImpl implements DateService {

    @Override
    public String parseDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date parsedDate = null;

        try {
            parsedDate = sdf.parse(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        sdf.applyPattern("dd-MM-yyyy HH:mm:ss");

        return sdf.format(parsedDate);
    }

    @Override
    public String getActualDate() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }
}
