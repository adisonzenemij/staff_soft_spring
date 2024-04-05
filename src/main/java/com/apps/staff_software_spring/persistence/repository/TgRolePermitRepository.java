package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgRolePermitEntity;

public interface TgRolePermitRepository extends ListCrudRepository<TgRolePermitEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgRolePermitEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgRolePermitEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna tgRoleAction
    List<TgRolePermitEntity> findAllByOrderByTgRoleAction();

    // Buscar por la columna tgRoleAction
    List<TgRolePermitEntity> findAllByIdTgRoleAction(Integer tgRoleAction);



    // Ordenar por la columna tgRoleAuth
    List<TgRolePermitEntity> findAllByOrderByTgRoleAuth();

    // Buscar por la columna tgRoleAuth
    List<TgRolePermitEntity> findAllByIdTgRoleAuth(Integer tgRoleAuth);



    // Ordenar por la columna tgRoleData
    List<TgRolePermitEntity> findAllByOrderByTgRoleData();

    // Buscar por la columna tgRoleData
    List<TgRolePermitEntity> findAllByIdTgRoleData(Integer tgRoleData);

    

    // Buscar por fecha de creacion antes
    List<TgRolePermitEntity> findAllByAtCreatedDateAfter(LocalDateTime atCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgRolePermitEntity> findAllByAtModifiedDateAfter(LocalDateTime atModifiedDate);
}
