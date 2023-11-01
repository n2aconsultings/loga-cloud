package com.loga.logisticservice.vendor.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Log {
    private static Log log;
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH'h'mm");

    private Log() {
        log = this;
    }

    public static void info(String message) {
        logger.info("["+dateFormat.format(new Date())+"]"+message+"\n");
    }

    public static void warn(String message) {
        logger.warn("["+dateFormat.format(new Date())+"]"+message+"\n");
    }

    public static void error(String message) {
        logger.error("["+dateFormat.format(new Date())+"]"+message+"\n");
    }
}
