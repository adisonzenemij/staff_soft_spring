package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;

public interface TgUserDataRepository extends ListCrudRepository<TgUserDataEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgUserDataEntity> findAllByOrderByIdRegister();

    // Ordenar por la columna cdName
    List<TgUserDataEntity> findAllByOrderByCdName();

    // Buscar por la columna idRegister
    List<TgUserDataEntity> findAllByIdRegister(Integer idRegister);

    // Buscar por la columna cdName
    List<TgUserDataEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<TgUserDataEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Buscar por fecha de creacion antes
    List<TgUserDataEntity> findAllByAtCreatedDateAfter(LocalDateTime atCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgUserDataEntity> findAllByAtModifiedDateAfter(LocalDateTime atModifiedDate);

    // Limitar la busqueda de registros por cdName
    TgUserDataEntity findFirstByCdName(String cdName);
}
