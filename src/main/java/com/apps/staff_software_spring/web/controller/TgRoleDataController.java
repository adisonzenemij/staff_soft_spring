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

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;
import com.apps.staff_software_spring.service.TgRoleDataService;
import com.apps.staff_software_spring.service.dto.TgRoleDataDto;

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

    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgRoleDataEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgRoleDataService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgRoleDataEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgRoleDataEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
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

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgRoleDataEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgRoleDataService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgRoleDataEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgRoleDataService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgRoleDataEntity>> pageAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.pageAll(page, elements)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
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



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgRoleDataEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdName/{cdName}")
    public ResponseEntity<TgRoleDataEntity> queryCdName(
        @PathVariable String cdName
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.queryCdName(cdName)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgRoleGroup/{tgRoleGroup}")
    public ResponseEntity<TgRoleDataEntity> queryTgRoleGroup(
        @PathVariable Integer tgRoleGroup
    ) {
        return ResponseEntity.ok(
            this.tgRoleDataService.queryTgRoleGroup(tgRoleGroup)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
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

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgRoleDataEntity> saveRegister(
        @RequestBody TgRoleDataEntity tgRoleDataEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgRoleDataEntity.getIdRegister() == null ||
            !this.tgRoleDataService.existsById(
                tgRoleDataEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleDataService.save(tgRoleDataEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgRoleDataEntity> updateRegister(
        @RequestBody TgRoleDataEntity tgRoleDataEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgRoleDataEntity.getIdRegister() != null ||
            this.tgRoleDataService.existsById(
                tgRoleDataEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleDataService.save(tgRoleDataEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
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



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgRoleDataService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
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

    // Eliminar varios registros especificos
    @DeleteMapping(value = "/delete/byIdAll/{ids}")
    public ResponseEntity<Void> deleteByIdAll(@PathVariable List<Integer> ids) {
        for (Integer id : ids) {
            if (!this.tgRoleDataService.existsById(id)) {
                this.tgRoleDataService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
