package com.loga.reportingservice.monitoring.service;

import com.loga.reportingservice.monitoring.factory.*;
import com.loga.reportingservice.monitoring.repository.DashboardDao;
import com.loga.reportingservice.vendor.io.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service
public class Monitoring implements IMonitoring {

    @Autowired
    private DashboardDao dashboardDao;

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    @Override
    @Scheduled(fixedRate = 5000)
    public void etl() throws InterruptedException, IOException{

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        ProcessBuilder builder = new ProcessBuilder();

        if (isWindows) {
            builder.command(System.getProperty("user.dir") + "\\etl\\tfe_master\\tfe_master_run.bat");
        } else {
            builder.command("sh","-c", "./etl/tfe_master/tfe_master_run.sh");
        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Process process = builder.start();

        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);

        executorService.submit(streamGobbler);

        int exitCode = process.waitFor();

        if (exitCode == 0) {
            Logger.info("ETL Success !");
        } else {
            Logger.error("ETL Failed !");
        }
    }

    public Dashboard load() throws SQLException {
        Stats stats = dashboardDao.getStats();
        List<Diagnosis> diagnoses = dashboardDao.getDiagnosis();
        List<Spares> spares = dashboardDao.getSpares();
        List<Tasks> tasks = dashboardDao.getTasks();
        return new Dashboard(stats,diagnoses,tasks,spares);
    }

    @Override
    public List<Diagnoses> diagnose() throws SQLException {
        return dashboardDao.getDiagnoses();
    }
}
