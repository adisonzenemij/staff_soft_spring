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

import com.apps.staff_software_spring.persistence.entity.TgUserEmailEntity;
import com.apps.staff_software_spring.service.TgUserEmailService;
import com.apps.staff_software_spring.service.dto.TgUserEmailDto;

@RestController
@RequestMapping(value = "/api/tgUserEmail")
public class TgUserEmailController {
    private final TgUserEmailService tgUserEmailService;

    @Autowired
    public TgUserEmailController(TgUserEmailService tgUserEmailService) {
        this.tgUserEmailService = tgUserEmailService;
    }
    
    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgUserEmailEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgUserEmailService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgUserEmailEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgUserEmailEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgUserEmailEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgUserEmailService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgUserEmailEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgUserEmailService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgUserEmailEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgUserEmailService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgUserEmailEntity>> pageAll(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.pageAll(sheet, row)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgUserEmailEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.pageSortCol(
                sheet, row, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgUserEmailEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdExtra/{cdExtra}")
    public ResponseEntity<TgUserEmailEntity> queryCdExtra(
        @PathVariable String cdExtra
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.queryCdExtra(cdExtra)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgUserData/{tgUserData}")
    public ResponseEntity<TgUserEmailEntity> queryTgUserData(
        @PathVariable Integer tgUserData
    ) {
        return ResponseEntity.ok(
            this.tgUserEmailService.queryTgUserData(tgUserData)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgUserEmailEntity>> saveMulti(
        @RequestBody List<TgUserEmailEntity> tgUserEmailEntities
    ) {
        List<TgUserEmailEntity> savedEntities = new ArrayList<>();
        for (TgUserEmailEntity entity : tgUserEmailEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgUserEmailService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgUserEmailService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgUserEmailEntity> saveRegister(
        @RequestBody TgUserEmailEntity tgUserEmailEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgUserEmailEntity.getIdRegister() == null ||
            !this.tgUserEmailService.existsById(
                tgUserEmailEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgUserEmailService.save(tgUserEmailEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgUserEmailEntity> updateRegister(
        @RequestBody TgUserEmailEntity tgUserEmailEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgUserEmailEntity.getIdRegister() != null ||
            this.tgUserEmailService.existsById(
                tgUserEmailEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgUserEmailService.save(tgUserEmailEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgUserEmailDto tgUserEmailDto
    ) {
        if (this.tgUserEmailService.existsById(
                tgUserEmailDto.getIdRegister()
            )
        ) {
            this.tgUserEmailService.updateDto(tgUserEmailDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgUserEmailService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgUserEmailService.existsById(idRegister)) {
                this.tgUserEmailService.deleteById(idRegister);
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
            if (!this.tgUserEmailService.existsById(id)) {
                this.tgUserEmailService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
