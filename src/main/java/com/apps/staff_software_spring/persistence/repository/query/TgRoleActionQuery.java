package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleActionEntity;

public interface TgRoleActionQuery extends ListCrudRepository<TgRoleActionEntity, Integer> {
    // Obtener resultados buscando por la columna cdAcronym
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRoleActionEntity trd" + " " +
            "WHERE trd.cdAcronym = :cd_acronym"
    )
    TgRoleActionEntity findByCdAcronym(@Param("cd_acronym") String cdAcronym);
    
    // Obtener resultados buscando por la columna cdName
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRoleActionEntity trd" + " " +
            "WHERE trd.cdName = :cd_name"
    )
    TgRoleActionEntity findByCdName(@Param("cd_name") String cdName);
    
    // Obtener resultados buscando por la columna tgRoleFunc
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRoleActionEntity trd" + " " +
            "WHERE trd.tgRoleFunc = :tg_role_func"
    )
    TgRoleActionEntity findByTgRoleFunc(@Param("tg_role_func") Integer tgRoleFunc);
}
