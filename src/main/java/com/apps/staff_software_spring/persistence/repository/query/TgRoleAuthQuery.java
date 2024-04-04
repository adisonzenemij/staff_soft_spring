package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;

public interface TgRoleAuthQuery extends ListCrudRepository<TgRoleAuthEntity, Integer> {
    // Obtener resultados buscando por la columna cdName
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgRoleAuthEntity trd" + " " +
            "WHERE trd.cdName = :cd_name"
    )
    TgRoleAuthEntity findByCdName(@Param("cd_name") String cdName);
}
