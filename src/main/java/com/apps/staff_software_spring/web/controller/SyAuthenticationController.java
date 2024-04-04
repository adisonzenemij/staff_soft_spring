package com.apps.staff_software_spring.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.staff_software_spring.persistence.entity.SyAuthenticationEntity;
import com.apps.staff_software_spring.service.SyAuthenticationService;
import com.apps.staff_software_spring.service.dto.SyAuthenticationDto;

@RestController
@RequestMapping(value = "/api/syAuthentication")
public class SyAuthenticationController {
    private final SyAuthenticationService syAuthenticationService;
    //private final SyAuthenticationEntity syAuthenticationEntity;
    
    @Autowired
    public SyAuthenticationController(
        //SyAuthenticationEntity syAuthenticationEntity,
        SyAuthenticationService syAuthenticationService
    ) {
        //this.syAuthenticationEntity = syAuthenticationEntity;
        this.syAuthenticationService = syAuthenticationService;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<SyAuthenticationEntity>> getAll() {
        return ResponseEntity.ok(
            this.syAuthenticationService.getAll()
        );
    }

    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<SyAuthenticationEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.syAuthenticationService.getById(idRegister)
        );
    }

    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<SyAuthenticationEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.syAuthenticationService.getOrderByColumn(column)
        );
    }

    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<SyAuthenticationEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.syAuthenticationService.getSearchData(columnName, columnValue)
        );
    }

    @GetMapping(value = "/get/today")
    public ResponseEntity<List<SyAuthenticationEntity>> getToday() {
        return ResponseEntity.ok(
            this.syAuthenticationService.getToday()
        );
    }

    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<SyAuthenticationEntity>> pageAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements
    ) {
        return ResponseEntity.ok(
            this.syAuthenticationService.pageAll(page, elements)
        );
    }

    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<SyAuthenticationEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.syAuthenticationService.pageSortCol(
                page, elements, sortBy, sortDir
            )
        );
    }

    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<SyAuthenticationEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.syAuthenticationService.natIdRegister(idRegister)
        );
    }

    @GetMapping(value = "/query/cdName/{cdName}")
    public ResponseEntity<SyAuthenticationEntity> queryCdName(
        @PathVariable String cdName
    ) {
        return ResponseEntity.ok(
            this.syAuthenticationService.queryCdName(cdName)
        );
    }

    @PostMapping(value = "/post/save/multi")
    public ResponseEntity<List<SyAuthenticationEntity>> saveMulti(
        @RequestBody List<SyAuthenticationEntity> syAuthenticationEntities
    ) {
        List<SyAuthenticationEntity> savedEntities = new ArrayList<>();
        for (SyAuthenticationEntity entity : syAuthenticationEntities) {
            if (entity.getIdRegister() == null ||
                !this.syAuthenticationService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.syAuthenticationService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    @PostMapping(value = "/post/save/register")
    public ResponseEntity<SyAuthenticationEntity> saveRegister(
        @RequestBody SyAuthenticationEntity syAuthenticationEntity
    ) {
        if (syAuthenticationEntity.getIdRegister() == null ||
            !this.syAuthenticationService.existsById(
                syAuthenticationEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.syAuthenticationService.save(syAuthenticationEntity)
            );
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/update/register")
    public ResponseEntity<SyAuthenticationEntity> updateRegister(
        @RequestBody SyAuthenticationEntity syAuthenticationEntity
    ) {
        if (syAuthenticationEntity.getIdRegister() != null ||
            this.syAuthenticationService.existsById(
                syAuthenticationEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.syAuthenticationService.save(syAuthenticationEntity)
            );
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody SyAuthenticationDto syAuthenticationDto
    ) {
        if (this.syAuthenticationService.existsById(
                syAuthenticationDto.getIdRegister()
            )
        ) {
            this.syAuthenticationService.updateDto(syAuthenticationDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.syAuthenticationService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/allById/{ids}")
    public ResponseEntity<Void> deleteAllById(@PathVariable List<Integer> ids) {
        for (Integer id : ids) {
            if (!this.syAuthenticationService.existsById(id)) {
                return ResponseEntity.badRequest().build();
            }
        }

        this.syAuthenticationService.deleteAllById(ids);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.syAuthenticationService.existsById(idRegister)) {
                this.syAuthenticationService.deleteById(idRegister);
                message = "Registro Eliminado";
            }
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
