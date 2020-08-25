package com.example.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParam {
    private final DateTimeFormatter dateFormating = DateTimeFormat.forPattern(DateFormatter.DATE_TIME)
            .withZoneUTC();
    private final DateFormat formatter = new SimpleDateFormat(DateFormatter.DATE_TIME);
    private String formattedDate;
    private Date date;

    public DateParam(String in) throws WebApplicationException {
        try {
            if (!in.equals(null) && !in.equals("null")) {
                formattedDate = dateFormating.print(Long.parseLong(in));
                date = formatter.parse(formattedDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }

    public String format() {
        return date.toString();
    }

}
