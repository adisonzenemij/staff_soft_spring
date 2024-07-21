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

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;
import com.apps.staff_software_spring.service.TgRoleFuncService;
import com.apps.staff_software_spring.service.dto.TgRoleFuncDto;

@RestController
@RequestMapping(value = "/api/tgRoleFunc")
public class TgRoleFuncController {
    private final TgRoleFuncService tgRoleFuncService;
    //private final TgRoleFuncEntity tgRoleFuncEntity;
    
    @Autowired
    public TgRoleFuncController(
        //TgRoleFuncEntity tgRoleFuncEntity,
        TgRoleFuncService tgRoleFuncService
    ) {
        //this.tgRoleFuncEntity = tgRoleFuncEntity;
        this.tgRoleFuncService = tgRoleFuncService;
    }

    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgRoleFuncEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgRoleFuncService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgRoleFuncEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgRoleFuncEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgRoleFuncEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgRoleFuncService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgRoleFuncEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgRoleFuncService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgRoleFuncEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgRoleFuncService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgRoleFuncEntity>> pageAll(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.pageAll(sheet, row)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgRoleFuncEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.pageSortCol(
                sheet, row, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgRoleFuncEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdAcronym/{cdAcronym}")
    public ResponseEntity<TgRoleFuncEntity> queryCdAcronym(
        @PathVariable String cdAcronym
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.queryCdAcronym(cdAcronym)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdName/{cdName}")
    public ResponseEntity<TgRoleFuncEntity> queryCdName(
        @PathVariable String cdName
    ) {
        return ResponseEntity.ok(
            this.tgRoleFuncService.queryCdName(cdName)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgRoleFuncEntity>> saveMulti(
        @RequestBody List<TgRoleFuncEntity> tgRoleFuncEntities
    ) {
        List<TgRoleFuncEntity> savedEntities = new ArrayList<>();
        for (TgRoleFuncEntity entity : tgRoleFuncEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgRoleFuncService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgRoleFuncService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgRoleFuncEntity> saveRegister(
        @RequestBody TgRoleFuncEntity tgRoleFuncEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgRoleFuncEntity.getIdRegister() == null ||
            !this.tgRoleFuncService.existsById(
                tgRoleFuncEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleFuncService.save(tgRoleFuncEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgRoleFuncEntity> updateRegister(
        @RequestBody TgRoleFuncEntity tgRoleFuncEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgRoleFuncEntity.getIdRegister() != null ||
            this.tgRoleFuncService.existsById(
                tgRoleFuncEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleFuncService.save(tgRoleFuncEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgRoleFuncDto tgRoleFuncDto
    ) {
        if (this.tgRoleFuncService.existsById(
                tgRoleFuncDto.getIdRegister()
            )
        ) {
            this.tgRoleFuncService.updateDto(tgRoleFuncDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgRoleFuncService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgRoleFuncService.existsById(idRegister)) {
                this.tgRoleFuncService.deleteById(idRegister);
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
            if (!this.tgRoleFuncService.existsById(id)) {
                this.tgRoleFuncService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
