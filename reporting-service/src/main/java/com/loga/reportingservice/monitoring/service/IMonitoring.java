package com.loga.reportingservice.monitoring.service;


import com.loga.reportingservice.monitoring.factory.Dashboard;
import com.loga.reportingservice.monitoring.factory.Diagnoses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IMonitoring {
    Dashboard load() throws SQLException;
    void etl() throws InterruptedException, IOException, ExecutionException, TimeoutException;
    List<Diagnoses> diagnose() throws SQLException;
}
