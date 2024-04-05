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

import com.apps.staff_software_spring.persistence.entity.TgUserIpEntity;
import com.apps.staff_software_spring.service.TgUserIpService;
import com.apps.staff_software_spring.service.dto.TgUserIpDto;

@RestController
@RequestMapping(value = "/api/tgUserIp")
public class TgUserIpController {
    private final TgUserIpService tgUserIpService;

    @Autowired
    public TgUserIpController(TgUserIpService tgUserIpService) {
        this.tgUserIpService = tgUserIpService;
    }
    
    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgUserIpEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgUserIpService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgUserIpEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgUserIpEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgUserIpEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgUserIpService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgUserIpEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgUserIpService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgUserIpEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgUserIpService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgUserIpEntity>> pageAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.pageAll(page, elements)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgUserIpEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.pageSortCol(
                page, elements, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgUserIpEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdAddress/{cdAddress}")
    public ResponseEntity<TgUserIpEntity> queryCdAddress(
        @PathVariable String cdAddress
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.queryCdAddress(cdAddress)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgUserData/{tgUserData}")
    public ResponseEntity<TgUserIpEntity> queryTgUserData(
        @PathVariable Integer tgUserData
    ) {
        return ResponseEntity.ok(
            this.tgUserIpService.queryTgUserData(tgUserData)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgUserIpEntity>> saveMulti(
        @RequestBody List<TgUserIpEntity> tgUserIpEntities
    ) {
        List<TgUserIpEntity> savedEntities = new ArrayList<>();
        for (TgUserIpEntity entity : tgUserIpEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgUserIpService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgUserIpService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgUserIpEntity> saveRegister(
        @RequestBody TgUserIpEntity tgUserIpEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgUserIpEntity.getIdRegister() == null ||
            !this.tgUserIpService.existsById(
                tgUserIpEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgUserIpService.save(tgUserIpEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgUserIpEntity> updateRegister(
        @RequestBody TgUserIpEntity tgUserIpEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgUserIpEntity.getIdRegister() != null ||
            this.tgUserIpService.existsById(
                tgUserIpEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgUserIpService.save(tgUserIpEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgUserIpDto tgUserIpDto
    ) {
        if (this.tgUserIpService.existsById(
                tgUserIpDto.getIdRegister()
            )
        ) {
            this.tgUserIpService.updateDto(tgUserIpDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgUserIpService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgUserIpService.existsById(idRegister)) {
                this.tgUserIpService.deleteById(idRegister);
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
            if (!this.tgUserIpService.existsById(id)) {
                this.tgUserIpService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
