package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleGroupEntity;

public interface TgRoleGroupRepository extends ListCrudRepository<TgRoleGroupEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgRoleGroupEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgRoleGroupEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdName
    List<TgRoleGroupEntity> findAllByOrderByCdName();

    // Buscar por la columna cdName
    List<TgRoleGroupEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<TgRoleGroupEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Limitar la busqueda de registros por cdName
    TgRoleGroupEntity findFirstByCdName(String cdName);

    

    // Buscar por fecha de creacion antes
    List<TgRoleGroupEntity> findAllByAtCreatedDateAfter(LocalDateTime atCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgRoleGroupEntity> findAllByAtModifiedDateAfter(LocalDateTime atModifiedDate);
}
