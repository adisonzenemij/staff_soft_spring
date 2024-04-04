package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;

public interface TgRoleFuncQuery extends ListCrudRepository<TgRoleFuncEntity, Integer> {
    // Obtener resultados buscando por la columna cdAcronym
    @Query(
        nativeQuery = false,
        value = "SELECT tbcd FROM TgRoleFuncEntity tbcd" + " " +
            "WHERE tbcd.cdAcronym = :cd_acronym"
    )
    TgRoleFuncEntity findByCdAcronym(@Param("cd_acronym") String cdAcronym);
    
    // Obtener resultados buscando por la columna cdName
    @Query(
        nativeQuery = false,
        value = "SELECT tbcd FROM TgRoleFuncEntity tbcd" + " " +
            "WHERE tbcd.cdName = :cd_name"
    )
    TgRoleFuncEntity findByCdName(@Param("cd_name") String cdName);
}
