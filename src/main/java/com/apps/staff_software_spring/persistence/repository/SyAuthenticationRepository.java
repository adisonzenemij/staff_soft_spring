package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.SyAuthenticationEntity;

public interface SyAuthenticationRepository extends ListCrudRepository<SyAuthenticationEntity, Integer> {
    // Ordenar por la columna idRegister
    List<SyAuthenticationEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<SyAuthenticationEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdName
    List<SyAuthenticationEntity> findAllByOrderByCdName();

    // Buscar por la columna cdName
    List<SyAuthenticationEntity> findAllByCdNameIgnoreCase(String cdName);

    // Buscar por la columna cdName ignorando mayusculas y minusculas
    List<SyAuthenticationEntity> findAllByCdNameContainingIgnoreCase(String cdName);

    // Limitar la busqueda de registros por cdName
    SyAuthenticationEntity findFirstByCdName(String cdName);

    

    // Buscar por fecha de creacion antes
    List<SyAuthenticationEntity> findAllByAdCreatedDateAfter(LocalDateTime adCreatedDate);

    // Buscar por fecha de creacion antes
    List<SyAuthenticationEntity> findAllByAdModifiedDateAfter(LocalDateTime adModifiedDate);
}
