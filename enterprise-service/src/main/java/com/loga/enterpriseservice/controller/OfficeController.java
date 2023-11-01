package com.loga.enterpriseservice.controller;

import com.loga.enterpriseservice.entity.Assets;
import com.loga.enterpriseservice.entity.Department;
import com.loga.enterpriseservice.entity.Office;
import com.loga.enterpriseservice.service.IAssetsService;
import com.loga.enterpriseservice.service.IDepartmentService;
import com.loga.enterpriseservice.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/enterprise-service")
public class OfficeController {

    private final IOfficeService officeService;
    private final IAssetsService assetsService;
    private final IDepartmentService departmentService;

    @Autowired
    public OfficeController(IOfficeService officeService,
                            IAssetsService assetsService,
                            IDepartmentService departmentService
    ) {
        this.officeService = officeService;
        this.assetsService = assetsService;
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/offices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Office> registrate(@RequestBody Office office){
        Office created = officeService.create(office);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/offices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Office>> list(){
        List<Office> offices = officeService.list();
        return ResponseEntity.ok(offices);
    }

    @GetMapping(path = "/offices/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Office> read(@PathVariable Long id){
        Office office = officeService.read(id);
        return ResponseEntity.ok(office);
    }

    @PutMapping(path = "/offices/{id}")
    public void edit(@RequestBody Office office, @PathVariable Long id){
        officeService.edit(office,id);
    }

    @DeleteMapping(path = "/offices/{id}")
    public void delete(@PathVariable Long id){
        officeService.delete(id);
    }

    @PutMapping(path = "/offices/department/{id}")
    public void edit(@RequestBody Department department, @PathVariable Long id){
        Office office = officeService.read(id);
        office.getDepartments().add(department);
        officeService.edit(office,office.getId());
    }

    @DeleteMapping(path = "/offices/departments/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.delete(id);
    }

    @PutMapping(path = "/offices/assets/{id}")
    public void edit(@RequestBody Assets assets, @PathVariable Long id){
        Office office = officeService.read(id);
        office.getAssets().add(assets);
        officeService.edit(office,office.getId());
    }

    @DeleteMapping(path = "/offices/assets/{id}")
    public void deleteAssets(@PathVariable Long id){
        assetsService.delete(id);
    }
}
