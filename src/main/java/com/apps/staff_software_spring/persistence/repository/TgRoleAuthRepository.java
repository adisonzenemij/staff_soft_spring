package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;

public interface TgRoleAuthRepository extends ListCrudRepository<TgRoleAuthEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgRoleAuthEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgRoleAuthEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdName
    List<TgRoleAuthEntity> findAllByOrderByCdName();

    // Buscar por la columna cdName
    List<TgRoleAuthEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<TgRoleAuthEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Limitar la busqueda de registros por cdName
    TgRoleAuthEntity findFirstByCdName(String cdName);

    

    // Buscar por fecha de creacion antes
    List<TgRoleAuthEntity> findAllByAdCreatedDateAfter(LocalDateTime adCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgRoleAuthEntity> findAllByAdModifiedDateAfter(LocalDateTime adModifiedDate);
}
