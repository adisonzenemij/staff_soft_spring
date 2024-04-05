package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgUserEmailEntity;

public interface TgUserEmailRepository extends ListCrudRepository<TgUserEmailEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgUserEmailEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgUserEmailEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdExtra
    List<TgUserEmailEntity> findAllByOrderByCdExtra();

    // Buscar por la columna cdExtra
    List<TgUserEmailEntity> findAllByCdExtraIgnoreCase(String cdExtra);

    // Buscar por la columna cdExtra ignorando mayusculas y minusculas
    List<TgUserEmailEntity> findAllByCdExtraContainingIgnoreCase(String cdExtra);

    // Limitar la busqueda de registros por cdExtra
    TgUserEmailEntity findFirstByCdExtra(String cdExtra);



    // Ordenar por la columna tgUserData
    List<TgUserEmailEntity> findAllByOrderByTgUserData();

    // Buscar por la columna tgUserData
    List<TgUserEmailEntity> findAllByTgUserData(Integer tgUserData);

    

    // Buscar por fecha de creacion antes
    List<TgUserEmailEntity> findAllByAtCreatedDateAfter(LocalDateTime atCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgUserEmailEntity> findAllByAtModifiedDateAfter(LocalDateTime atModifiedDate);
}
