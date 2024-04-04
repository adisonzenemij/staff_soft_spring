package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

public interface TgRoleDataQuery extends ListCrudRepository<TgRoleDataEntity, Integer> {
    // Obtener resultados buscando por la columna cdName
    @Query(
        nativeQuery = false,
        value = "SELECT tbcd FROM TgRoleDataEntity tbcd" + " " +
            "WHERE tbcd.cdName = :cd_name"
    )
    TgRoleDataEntity findByCdName(@Param("cd_name") String cdName);
    
    // Obtener resultados buscando por la columna tgRoleGroup
    @Query(
        nativeQuery = false,
        value = "SELECT tbcd FROM TgRoleDataEntity tbcd" + " " +
            "WHERE tbcd.tgRoleGroup = :tg_role_group"
    )
    TgRoleDataEntity findByTgRoleGroup(@Param("tg_role_group") Integer tgRoleGroup);
}
