package com.loga.enterpriseservice.vendor.config;

import com.loga.enterpriseservice.entity.Department;
import com.loga.enterpriseservice.service.IDepartmentService;
import com.loga.enterpriseservice.vendor.io.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Loader {
    @Autowired
    private IDepartmentService departmentService;

    @Bean
    CommandLineRunner init(){
        Department department = new Department();
        department.setName("DIRECTION");
        department = departmentService.create(department);
        if(department.getId()!=null){
            return args -> {
                Log.info("Enterprise Service Initialized !!!");
            };
        }else {
            return args -> {
                Log.error("Enterprise Service Failed !!!");
            };
        }
    }
}
