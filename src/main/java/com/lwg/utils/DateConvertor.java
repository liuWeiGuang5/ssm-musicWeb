package com.lwg.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return sim.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}