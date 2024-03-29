package com.apps.staff_software_spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;
import com.apps.staff_software_spring.service.TgRoleDataService;
import com.apps.staff_software_spring.service.dto.TgRoleDataDto;
import com.apps.staff_software_spring.util.RandomUtil;

@RestController
@RequestMapping(value = "/api/tgRoleData")
public class TgRoleDataController {
    private final TgRoleDataService tgRoleDataService;
    //private final TgRoleDataEntity tgRoleDataEntity;
    
    @Autowired
    public TgRoleDataController(
        //TgRoleDataEntity tgRoleDataEntity,
        TgRoleDataService tgRoleDataService
    ) {
        //this.tgRoleDataEntity = tgRoleDataEntity;
        this.tgRoleDataService = tgRoleDataService;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgRoleDataEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgRoleDataService.getAll()
        );
    }

    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgRoleDataEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.getById(idRegister)
        );
    }

    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgRoleDataEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.getOrderByColumn(column)
        );
    }

    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgRoleDataEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgRoleDataService.getSearchData(columnName, columnValue)
        );
    }

    @GetMapping(value = "/get/today")
    public ResponseEntity<List<TgRoleDataEntity>> getToday() {
        return ResponseEntity.ok(
            this.tgRoleDataService.getToday()
        );
    }

    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgRoleDataEntity>> pageAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.pageAll(page, elements)
        );
    }

    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgRoleDataEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.pageSortCol(
                page, elements, sortBy, sortDir
            )
        );
    }

    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgRoleDataEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.natIdRegister(idRegister)
        );
    }

    @GetMapping(value = "/query/cdName/{cdName}")
    public ResponseEntity<TgRoleDataEntity> queryCdName(
        @PathVariable String cdName
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.queryCdName(cdName)
        );
    }

    @PostMapping(value = "/post/save/multi")
    public ResponseEntity<List<TgRoleDataEntity>> saveMulti(
        @RequestBody List<TgRoleDataEntity> tgRoleDataEntities
    ) {
        List<TgRoleDataEntity> savedEntities = new ArrayList<>();
        for (TgRoleDataEntity entity : tgRoleDataEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgRoleDataService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgRoleDataService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    @PostMapping(value = "/post/save/random/{count}")
    public ResponseEntity<Void> saveRandom(@PathVariable int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            TgRoleDataEntity tgRoleDataEntity = new TgRoleDataEntity();
            String randomName = RandomUtil.randomName(random);
            tgRoleDataEntity.setCdName(randomName);
            this.tgRoleDataService.save(tgRoleDataEntity);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/post/save/register")
    public ResponseEntity<TgRoleDataEntity> saveRegister(
        @RequestBody TgRoleDataEntity tgRoleDataEntity
    ) {
        if (tgRoleDataEntity.getIdRegister() == null ||
            !this.tgRoleDataService.existsById(
                tgRoleDataEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleDataService.save(tgRoleDataEntity)
            );
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/update/register")
    public ResponseEntity<TgRoleDataEntity> updateRegister(
        @RequestBody TgRoleDataEntity tgRoleDataEntity
    ) {
        if (tgRoleDataEntity.getIdRegister() != null ||
            this.tgRoleDataService.existsById(
                tgRoleDataEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleDataService.save(tgRoleDataEntity)
            );
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgRoleDataDto tgRoleDataDto
    ) {
        if (this.tgRoleDataService.existsById(
                tgRoleDataDto.getIdRegister()
            )
        ) {
            this.tgRoleDataService.updateDto(tgRoleDataDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgRoleDataService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/allById/{ids}")
    public ResponseEntity<Void> deleteAllById(@PathVariable List<Integer> ids) {
        for (Integer id : ids) {
            if (!this.tgRoleDataService.existsById(id)) {
                return ResponseEntity.badRequest().build();
            }
        }

        this.tgRoleDataService.deleteAllById(ids);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgRoleDataService.existsById(idRegister)) {
                this.tgRoleDataService.deleteById(idRegister);
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
