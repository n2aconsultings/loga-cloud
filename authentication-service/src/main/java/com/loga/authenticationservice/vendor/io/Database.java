package com.loga.authenticationservice.vendor.io;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Getter
@Component
public class Database
{
    private static Connection connection;

    @Autowired
    private Database(Environment env) {
        final String DRIVER = "org.postgresql.Driver";
        final String JDBC_URL = env.getProperty("spring.datasource.url");
        final String USERNAME = env.getProperty("spring.datasource.username");
        final String PASSWORD = env.getProperty("spring.datasource.password");

        try {
            Class.forName(DRIVER);
            assert JDBC_URL != null;
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}