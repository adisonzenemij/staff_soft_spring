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

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;
import com.apps.staff_software_spring.service.TgRoleAuthService;
import com.apps.staff_software_spring.service.dto.TgRoleAuthDto;

@RestController
@RequestMapping(value = "/api/tgRoleAuth")
public class TgRoleAuthController {
    private final TgRoleAuthService tgRoleAuthService;
    //private final TgRoleAuthEntity tgRoleAuthEntity;
    
    @Autowired
    public TgRoleAuthController(
        //TgRoleAuthEntity tgRoleAuthEntity,
        TgRoleAuthService tgRoleAuthService
    ) {
        //this.tgRoleAuthEntity = tgRoleAuthEntity;
        this.tgRoleAuthService = tgRoleAuthService;
    }

    // Obtener todos los registros
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgRoleAuthEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgRoleAuthService.getAll()
        );
    }

    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/get/byId/{idRegister}")
    public ResponseEntity<TgRoleAuthEntity> getById(
        @PathVariable int idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleAuthService.getById(idRegister)
        );
    }

    // Path Varabile - Ordenar los registros por columna
    @GetMapping(value = "/get/orderBy/{column}")
    public ResponseEntity<List<TgRoleAuthEntity>> getOrderByColumn(
        @PathVariable String column
    ) {
        return ResponseEntity.ok(
            this.tgRoleAuthService.getOrderByColumn(column)
        );
    }

    // Request Body - Obtener registros segun busqueda
    @GetMapping(value = "/get/search/data")
    public ResponseEntity<List<TgRoleAuthEntity>> getSearchData(
        @RequestBody Map<String, String> searchData
    ) {
        String columnName = searchData.keySet().iterator().next();
        String columnValue = searchData.get(columnName);

        return ResponseEntity.ok(
            this.tgRoleAuthService.getSearchData(columnName, columnValue)
        );
    }

    // Obtener registros con fecha de creacion actual
    @GetMapping(value = "/get/at/date/cr")
    public ResponseEntity<List<TgRoleAuthEntity>> getAtDateCreate() {
        return ResponseEntity.ok(
            this.tgRoleAuthService.getAtDateCreate()
        );
    }

    // Obtener registros con fecha de actualizacion actual
    @GetMapping(value = "/get/at/date/up")
    public ResponseEntity<List<TgRoleAuthEntity>> getAtDateUpdate() {
        return ResponseEntity.ok(
            this.tgRoleAuthService.getAtDateUpdate()
        );
    }



    // Obtener todos los registros con paginacion
    @GetMapping(value = "/page/all")
    public ResponseEntity<Page<TgRoleAuthEntity>> pageAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements
    ) {
        return ResponseEntity.ok(
            this.tgRoleAuthService.pageAll(page, elements)
        );
    }

    // Obtener todos los registros con paginacion y ordenacion
    @GetMapping(value = "/page/sort")
    public ResponseEntity<Page<TgRoleAuthEntity>> pageSortCol(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements,
        @RequestParam(defaultValue = "idRegister") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(
            this.tgRoleAuthService.pageSortCol(
                page, elements, sortBy, sortDir
            )
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/nat/idRegister/{idRegister}")
    public ResponseEntity<List<TgRoleAuthEntity>> natIdRegister(
        @PathVariable String idRegister
    ) {
        return ResponseEntity.ok(
            this.tgRoleAuthService.natIdRegister(idRegister)
        );
    }



    // Path Variable - Obtener un registro especifico
    @GetMapping(value = "/query/cdName/{cdName}")
    public ResponseEntity<TgRoleAuthEntity> queryCdName(
        @PathVariable String cdName
    ) {
        return ResponseEntity.ok(
            this.tgRoleAuthService.queryCdName(cdName)
        );
    }



    // Request Body - Almacenar varios registros
    @PostMapping(value = "/insert/multi")
    public ResponseEntity<List<TgRoleAuthEntity>> saveMulti(
        @RequestBody List<TgRoleAuthEntity> tgRoleAuthEntities
    ) {
        List<TgRoleAuthEntity> savedEntities = new ArrayList<>();
        for (TgRoleAuthEntity entity : tgRoleAuthEntities) {
            if (entity.getIdRegister() == null ||
                !this.tgRoleAuthService.existsById(
                    entity.getIdRegister()
                )
            ) {
                savedEntities.add(this.tgRoleAuthService.save(entity));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(savedEntities);
    }

    // Request Body - Almacenar un registro
    @PostMapping(value = "/insert/register")
    public ResponseEntity<TgRoleAuthEntity> saveRegister(
        @RequestBody TgRoleAuthEntity tgRoleAuthEntity
    ) {
        // Si el id del registro es nulo o si el registro no existe
        if (tgRoleAuthEntity.getIdRegister() == null ||
            !this.tgRoleAuthService.existsById(
                tgRoleAuthEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleAuthService.save(tgRoleAuthEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }



    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/register")
    public ResponseEntity<TgRoleAuthEntity> updateRegister(
        @RequestBody TgRoleAuthEntity tgRoleAuthEntity
    ) {
        // Si el id del registro no es nulo o si el registro existe
        if (tgRoleAuthEntity.getIdRegister() != null ||
            this.tgRoleAuthService.existsById(
                tgRoleAuthEntity.getIdRegister()
            )
        ) {
            return ResponseEntity.ok(
                this.tgRoleAuthService.save(tgRoleAuthEntity)
            );
        }
        // No se procese la peticion a construir
        return ResponseEntity.badRequest().build();
    }

    // Request Body - Actualizar un registro
    @PutMapping(value = "/update/dto")
    public ResponseEntity<Void> updateDto(
        @RequestBody TgRoleAuthDto tgRoleAuthDto
    ) {
        if (this.tgRoleAuthService.existsById(
                tgRoleAuthDto.getIdRegister()
            )
        ) {
            this.tgRoleAuthService.updateDto(tgRoleAuthDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



    // Eliminar todos los registros
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Void> deleteAll() {
        this.tgRoleAuthService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Eliminar un registros especifico
    @DeleteMapping(value = "/delete/byId/{idRegister}")
    public ResponseEntity<?> deleteById(@PathVariable int idRegister) {
        Map<String, String> response = new HashMap<>();
        try {
            var message = "Registro Inexistente";
            if (this.tgRoleAuthService.existsById(idRegister)) {
                this.tgRoleAuthService.deleteById(idRegister);
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
            if (!this.tgRoleAuthService.existsById(id)) {
                this.tgRoleAuthService.deleteByIdAll(ids);
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
