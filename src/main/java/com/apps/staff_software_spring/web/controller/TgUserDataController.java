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

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.service.TgUserDataService;
import com.apps.staff_software_spring.service.dto.TgUserDataDto;

@RestController
@RequestMapping(value = "/api/tgUserData")
public class TgUserDataController {
    private final TgUserDataService tgUserDataService;

    @Autowired
    public TgUserDataController(TgUserDataService tgUserDataService) {
        this.tgUserDataService = tgUserDataService;
    }
    
    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgUserDataEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgUserDataService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgUserDataEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgUserDataEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgUserDataEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgUserDataService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgUserDataEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgUserDataService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgUserDataEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgUserDataService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgUserDataEntity>> pageAll(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.pageAll(sheet, row)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgUserDataEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.pageSortCol(
                sheet, row, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgUserDataEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdEmail/{cdEmail}")
    public ResponseEntity<TgUserDataEntity> queryCdEmail(
        @PathVariable String cdEmail
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.queryCdEmail(cdEmail)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdLogin/{cdLogin}")
    public ResponseEntity<TgUserDataEntity> queryCdLogin(
        @PathVariable String cdLogin
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.queryCdLogin(cdLogin)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdPassword/{cdPassword}")
    public ResponseEntity<TgUserDataEntity> queryCdPassword(
        @PathVariable String cdPassword
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.queryCdPassword(cdPassword)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgRoleData/{tgRoleData}")
    public ResponseEntity<TgUserDataEntity> queryTgRoleData(
        @PathVariable Integer tgRoleData
    ) {
        return ResponseEntity.ok(
            this.tgUserDataService.queryTgRoleData(tgRoleData)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgUserDataEntity>> saveMulti(
        @RequestBody List<TgUserDataEntity> tgUserDataEntities
    ) {
        List<TgUserDataEntity> savedEntities = new ArrayList<>();
        for (TgUserDataEntity entity : tgUserDataEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgUserDataService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgUserDataService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgUserDataEntity> saveRegister(
        @RequestBody TgUserDataEntity tgUserDataEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgUserDataEntity.getIdRegister() == null ||
            !this.tgUserDataService.existsById(
                tgUserDataEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgUserDataService.save(tgUserDataEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgUserDataEntity> updateRegister(
        @RequestBody TgUserDataEntity tgUserDataEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgUserDataEntity.getIdRegister() != null ||
            this.tgUserDataService.existsById(
                tgUserDataEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgUserDataService.save(tgUserDataEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgUserDataDto tgUserDataDto
    ) {
        if (this.tgUserDataService.existsById(
                tgUserDataDto.getIdRegister()
            )
        ) {
            this.tgUserDataService.updateDto(tgUserDataDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgUserDataService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgUserDataService.existsById(idRegister)) {
                this.tgUserDataService.deleteById(idRegister);
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
            if (!this.tgUserDataService.existsById(id)) {
                this.tgUserDataService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
