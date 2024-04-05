package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleActionEntity;

public interface TgRoleActionRepository extends ListCrudRepository<TgRoleActionEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgRoleActionEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgRoleActionEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdAcronym
    List<TgRoleActionEntity> findAllByOrderByCdAcronym();

    // Buscar por la columna cdAcronym
    List<TgRoleActionEntity> findAllByCdAcronymIgnoreCase(String cdAcronym);

    // Buscar por la columna cdAcronym ignorando mayusculas y minusculas
    List<TgRoleActionEntity> findAllByCdAcronymContainingIgnoreCase(String cdAcronym);

    // Limitar la busqueda de registros por cdAcronym
    TgRoleActionEntity findFirstByCdAcronym(String cdAcronym);



    // Ordenar por la columna cdName
    List<TgRoleActionEntity> findAllByOrderByCdName();

    // Buscar por la columna cdName
    List<TgRoleActionEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<TgRoleActionEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Limitar la busqueda de registros por cdName
    TgRoleActionEntity findFirstByCdName(String cdName);



    // Ordenar por la columna tgRoleFunc
    List<TgRoleActionEntity> findAllByOrderByTgRoleFunc();

    // Buscar por la columna tgRoleFunc
    List<TgRoleActionEntity> findAllByTgRoleFunc_IdRegister(Integer tgRoleFunc);

    

    // Buscar por fecha de creacion antes
    List<TgRoleActionEntity> findAllByAtCreatedDateAfter(LocalDateTime atCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgRoleActionEntity> findAllByAtModifiedDateAfter(LocalDateTime atModifiedDate);
}
