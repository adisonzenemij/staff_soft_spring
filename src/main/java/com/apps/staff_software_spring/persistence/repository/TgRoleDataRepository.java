package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

public interface TgRoleDataRepository extends ListCrudRepository<TgRoleDataEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgRoleDataEntity> findAllByOrderByIdRegister();

    // Ordenar por la columna cdName
    List<TgRoleDataEntity> findAllByOrderByCdName();

    // Buscar por la columna idRegister
    List<TgRoleDataEntity> findAllByIdRegister(Integer idRegister);

    // Buscar por la columna cdName
    List<TgRoleDataEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<TgRoleDataEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Buscar por fecha de creacion antes
    List<TgRoleDataEntity> findAllByazCreatedDateAfter(LocalDateTime azCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgRoleDataEntity> findAllByAzUpdatedAtAfter(LocalDateTime azUpdateAt);

    // Limitar la busqueda de registros por cdName
    TgRoleDataEntity findFirstByCdName(String cdName);
}
