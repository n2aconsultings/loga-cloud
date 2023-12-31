package com.loga.cloudnative.vendor.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class Logger {
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy HH'h'mm");

    public void info(String message) {
         log.info("["+ DATE_FORMAT.format(new Date(System.currentTimeMillis()))+"] "+log+"\n");
    }

    public void warn(String message) {
        log.warn("["+ DATE_FORMAT.format(new Date(System.currentTimeMillis()))+"] "+log+"\n");
    }

    public void error(String message) {
        log.error("["+ DATE_FORMAT.format(new Date(System.currentTimeMillis()))+"] "+log+"\n");
    }
}
