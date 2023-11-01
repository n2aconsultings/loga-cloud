package com.loga.reportingservice.report.service;

import com.loga.reportingservice.report.exception.ReportingErrorException;

import java.io.OutputStream;
import java.util.Date;

public interface IReportService {
    void modelReport(String report, OutputStream outputStream) throws ReportingErrorException;
    void modelReportById(String report, Long id, OutputStream outputStream) throws ReportingErrorException;
    void modelReportByDate(String report, Date date, OutputStream outputStream) throws ReportingErrorException;
    void modelReportBetweenDate(String report, Date start, Date end, OutputStream outputStream) throws ReportingErrorException;
}
