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

import com.apps.staff_software_spring.persistence.entity.TgRolePermitEntity;
import com.apps.staff_software_spring.service.TgRolePermitService;
import com.apps.staff_software_spring.service.dto.TgRolePermitDto;

@RestController
@RequestMapping(value = "/api/tgRolePermit")
public class TgRolePermitController {
    private final TgRolePermitService tgRolePermitService;
    //private final TgRolePermitEntity tgRolePermitEntity;
    
    @Autowired
    public TgRolePermitController(
        //TgRolePermitEntity tgRolePermitEntity,
        TgRolePermitService tgRolePermitService
    ) {
        //this.tgRolePermitEntity = tgRolePermitEntity;
        this.tgRolePermitService = tgRolePermitService;
    }

    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgRolePermitEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgRolePermitService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgRolePermitEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgRolePermitEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgRolePermitEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgRolePermitService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgRolePermitEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgRolePermitService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgRolePermitEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgRolePermitService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgRolePermitEntity>> pageAll(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.pageAll(sheet, row)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgRolePermitEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int sheet,
        @RequestParam(defaultValue = "10") int row,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.pageSortCol(
                sheet, row, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgRolePermitEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgRoleAction/{tgRoleAction}")
    public ResponseEntity<TgRolePermitEntity> queryTgRoleAction(
        @PathVariable Integer gRoleAction
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.queryTgRoleAction(gRoleAction)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgRoleAuth/{tgRoleAuth}")
    public ResponseEntity<TgRolePermitEntity> queryTgRoleAuth(
        @PathVariable Integer gRoleAuth
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.queryTgRoleAuth(gRoleAuth)
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/tgRoleData/{tgRoleData}")
    public ResponseEntity<TgRolePermitEntity> queryTgRoleData(
        @PathVariable Integer gRoleData
    ) {
        return ResponseEntity.ok(
            this.tgRolePermitService.queryTgRoleData(gRoleData)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgRolePermitEntity>> saveMulti(
        @RequestBody List<TgRolePermitEntity> tgRolePermitEntities
    ) {
        List<TgRolePermitEntity> savedEntities = new ArrayList<>();
        for (TgRolePermitEntity entity : tgRolePermitEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgRolePermitService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgRolePermitService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgRolePermitEntity> saveRegister(
        @RequestBody TgRolePermitEntity tgRolePermitEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgRolePermitEntity.getIdRegister() == null ||
            !this.tgRolePermitService.existsById(
                tgRolePermitEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRolePermitService.save(tgRolePermitEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgRolePermitEntity> updateRegister(
        @RequestBody TgRolePermitEntity tgRolePermitEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgRolePermitEntity.getIdRegister() != null ||
            this.tgRolePermitService.existsById(
                tgRolePermitEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRolePermitService.save(tgRolePermitEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgRolePermitDto tgRolePermitDto
    ) {
        if (this.tgRolePermitService.existsById(
                tgRolePermitDto.getIdRegister()
            )
        ) {
            this.tgRolePermitService.updateDto(tgRolePermitDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgRolePermitService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgRolePermitService.existsById(idRegister)) {
                this.tgRolePermitService.deleteById(idRegister);
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
            if (!this.tgRolePermitService.existsById(id)) {
                this.tgRolePermitService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
