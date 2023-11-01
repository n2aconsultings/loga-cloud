package com.loga.reportingservice.report.service;

import com.loga.reportingservice.report.exception.ReportingErrorException;
import com.loga.reportingservice.vendor.io.Database;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService implements IReportService {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void modelReport(String report, OutputStream outputStream) throws ReportingErrorException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, Database.getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException e) {
            e.printStackTrace();
            throw new ReportingErrorException(e);
        }
    }

    @Override
    public void modelReportById(String report, Long id,OutputStream outputStream) throws ReportingErrorException {
        Map<String, Object> param = new HashMap<>();
        param.put("ID",id);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,Database.getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException e) {
            throw new ReportingErrorException(e);
        }

    }

    @Override
    public void modelReportByDate(String report, Date date, OutputStream outputStream) throws ReportingErrorException {
        Map<String, Object> param = new HashMap<>();
        param.put("EXERCICE",date);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param, Database.getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException e) {
            e.printStackTrace();
            throw new ReportingErrorException(e);
        }
    }

    @Override
    public void modelReportBetweenDate(String report, Date debut, Date fin, OutputStream outputStream) throws ReportingErrorException {
        Map<String, Object> param = new HashMap<>();
        param.put("DATEDEBUT",debut);
        param.put("DATEFIN",fin);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param, Database.getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException e) {
            e.printStackTrace();
            throw new ReportingErrorException(e);
        }
    }
}
