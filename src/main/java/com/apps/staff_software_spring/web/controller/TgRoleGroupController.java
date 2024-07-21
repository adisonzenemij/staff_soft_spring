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

import com.apps.staff_software_spring.persistence.entity.TgRoleGroupEntity;
import com.apps.staff_software_spring.service.TgRoleGroupService;
import com.apps.staff_software_spring.service.dto.TgRoleGroupDto;

@RestController
@RequestMapping(value = "/api/tgRoleGroup")
public class TgRoleGroupController {
    private final TgRoleGroupService tgRoleGroupService;
    //private final TgRoleGroupEntity tgRoleGroupEntity;
    
    @Autowired
    public TgRoleGroupController(
        //TgRoleGroupEntity tgRoleGroupEntity,
        TgRoleGroupService tgRoleGroupService
    ) {
        //this.tgRoleGroupEntity = tgRoleGroupEntity;
        this.tgRoleGroupService = tgRoleGroupService;
    }

    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgRoleGroupEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgRoleGroupService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgRoleGroupEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleGroupService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgRoleGroupEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgRoleGroupService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgRoleGroupEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgRoleGroupService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgRoleGroupEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgRoleGroupService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgRoleGroupEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgRoleGroupService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgRoleGroupEntity>> pageAll(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row
    ) {
        return ResponseEntity.ok(
            this.tgRoleGroupService.pageAll(sheet, row)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgRoleGroupEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgRoleGroupService.pageSortCol(
                sheet, row, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgRoleGroupEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleGroupService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdName/{cdName}")
    public ResponseEntity<TgRoleGroupEntity> queryCdName(
        @PathVariable String cdName
    ) {
        return ResponseEntity.ok(
            this.tgRoleGroupService.queryCdName(cdName)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgRoleGroupEntity>> saveMulti(
        @RequestBody List<TgRoleGroupEntity> tgRoleGroupEntities
    ) {
        List<TgRoleGroupEntity> savedEntities = new ArrayList<>();
        for (TgRoleGroupEntity entity : tgRoleGroupEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgRoleGroupService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgRoleGroupService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgRoleGroupEntity> saveRegister(
        @RequestBody TgRoleGroupEntity tgRoleGroupEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgRoleGroupEntity.getIdRegister() == null ||
            !this.tgRoleGroupService.existsById(
                tgRoleGroupEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleGroupService.save(tgRoleGroupEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgRoleGroupEntity> updateRegister(
        @RequestBody TgRoleGroupEntity tgRoleGroupEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgRoleGroupEntity.getIdRegister() != null ||
            this.tgRoleGroupService.existsById(
                tgRoleGroupEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleGroupService.save(tgRoleGroupEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgRoleGroupDto tgRoleGroupDto
    ) {
        if (this.tgRoleGroupService.existsById(
                tgRoleGroupDto.getIdRegister()
            )
        ) {
            this.tgRoleGroupService.updateDto(tgRoleGroupDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgRoleGroupService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgRoleGroupService.existsById(idRegister)) {
                this.tgRoleGroupService.deleteById(idRegister);
                message = "Registro Eliminado";
            }
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Eliminar varios registros especificos
    @DeleteMapping(value = "/delete/byIdAll/{ids}")
    public ResponseEntity<Void> deleteByIdAll(@PathVariable List<Integer> ids) {
        for (Integer id : ids) {
            if (!this.tgRoleGroupService.existsById(id)) {
                this.tgRoleGroupService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
