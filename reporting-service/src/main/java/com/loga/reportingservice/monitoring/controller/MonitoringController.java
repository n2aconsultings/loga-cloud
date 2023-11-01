package com.loga.reportingservice.monitoring.controller;

import com.loga.reportingservice.monitoring.exception.DataNotFoundException;
import com.loga.reportingservice.monitoring.exception.EtlFailedException;
import com.loga.reportingservice.monitoring.exception.ReportingFailedException;
import com.loga.reportingservice.monitoring.factory.Dashboard;
import com.loga.reportingservice.monitoring.factory.Diagnoses;
import com.loga.reportingservice.monitoring.service.IMonitoring;
import com.loga.reportingservice.report.exception.ReportingErrorException;
import com.loga.reportingservice.report.service.IReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/monitoring-service")
@RequiredArgsConstructor
public class MonitoringController {

    private final IMonitoring monitoringService;
    private final IReportService reportService;

    @PostMapping(path = "/etl")
    public void etl(){
        try {
            monitoringService.etl();
        } catch (InterruptedException | IOException | ExecutionException | TimeoutException e) {
            throw new EtlFailedException("Failed to process ETL job : \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/load",produces = MediaType.APPLICATION_JSON_VALUE)
    public Dashboard load(){
        try {
            return monitoringService.load();
        } catch (SQLException e) {
            throw new DataNotFoundException("Failed to load dashboard data : "+e.getMessage());
        }
    }

    @GetMapping(path = "/diagnoses",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Diagnoses> diagnose(){
        try {
            return monitoringService.diagnose();
        } catch (SQLException e) {
            throw new DataNotFoundException("Failed to load diagnoses : "+e.getMessage());
        }
    }

    @PostMapping(path = "/report/{src}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@PathVariable String src, HttpServletResponse response)  {
        try {
            reportService.modelReport(src,response.getOutputStream());
        } catch (ReportingErrorException | IOException e) {
            throw new ReportingFailedException(e.getMessage());
        }
    }
}
