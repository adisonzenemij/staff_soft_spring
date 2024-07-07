package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;

public interface TgRoleFuncRepository extends ListCrudRepository<TgRoleFuncEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgRoleFuncEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgRoleFuncEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdAcronym
    List<TgRoleFuncEntity> findAllByOrderByCdAcronym();

    // Buscar por la columna cdAcronym
    List<TgRoleFuncEntity> findAllByCdAcronymIgnoreCase(String cdAcronym);

    // Buscar por la columna cdAcronym ignorando mayusculas y minusculas
    List<TgRoleFuncEntity> findAllByCdAcronymContainingIgnoreCase(String cdAcronym);

    // Limitar la busqueda de registros por cdAcronym
    TgRoleFuncEntity findFirstByCdAcronym(String cdAcronym);



    // Ordenar por la columna cdName
    List<TgRoleFuncEntity> findAllByOrderByCdName();

    // Buscar por la columna cdName
    List<TgRoleFuncEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<TgRoleFuncEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Limitar la busqueda de registros por cdName
    TgRoleFuncEntity findFirstByCdName(String cdName);

    

    // Buscar por fecha de creacion antes
    List<TgRoleFuncEntity> findAllByAdCreatedDateAfter(LocalDateTime adCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgRoleFuncEntity> findAllByAdModifiedDateAfter(LocalDateTime adModifiedDate);
}
