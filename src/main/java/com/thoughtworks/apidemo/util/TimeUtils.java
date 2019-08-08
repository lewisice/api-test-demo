package com.thoughtworks.apidemo.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;

import static java.time.Instant.now;


@Component
public class TimeUtils {
    public int getCurrentMinutes() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
}
