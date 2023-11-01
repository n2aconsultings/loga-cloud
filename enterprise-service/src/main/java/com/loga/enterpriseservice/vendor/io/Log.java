package com.loga.enterpriseservice.vendor.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Log {
    private static final Logger log = LoggerFactory.getLogger(Log.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH'h'mm");

    public static void info(String message){
        log.info("["+dateFormat.format(new Date(System.currentTimeMillis()))+"]"+message+"\n");
    }

    public static void warn(String message){
        log.warn("["+dateFormat.format(new Date(System.currentTimeMillis()))+"]"+message+"\n");
    }

    public static void error(String message){
        log.error("["+dateFormat.format(new Date(System.currentTimeMillis()))+"]"+message+"\n");
    }
}
