package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

public interface TgRoleDataQuery extends ListCrudRepository<TgRoleDataEntity, Integer> {
    // Obtener resultados buscando por la columna cdName
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRoleDataEntity trd" + " " +
            "WHERE trd.cdName = :cd_name"
    )
    TgRoleDataEntity findByCdName(@Param("cd_name") String cdName);
}
