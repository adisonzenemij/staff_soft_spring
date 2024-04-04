package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRolePermitEntity;

public interface TgRolePermitQuery extends ListCrudRepository<TgRolePermitEntity, Integer> {
    // Obtener resultados buscando por la columna tgRoleAction
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRolePermitEntity trd" + " " +
            "WHERE trd.tgRoleAction = :tg_role_action"
    )
    TgRolePermitEntity findByTgRoleAction(@Param("tg_role_action") Integer tgRoleAction);
    
    // Obtener resultados buscando por la columna tgRoleAuth
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRolePermitEntity trd" + " " +
            "WHERE trd.tgRoleAuth = :tg_role_auth"
    )
    TgRolePermitEntity findByTgRoleAuth(@Param("tg_role_auth") Integer tgRoleAuth);
    
    // Obtener resultados buscando por la columna tgRoleData
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRolePermitEntity trd" + " " +
            "WHERE trd.tgRoleData = :tg_role_data"
    )
    TgRolePermitEntity findByTgRoleData(@Param("tg_role_data") Integer tgRoleData);
}
