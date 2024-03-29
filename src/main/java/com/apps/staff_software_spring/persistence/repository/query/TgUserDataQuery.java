package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;

public interface TgUserDataQuery extends ListCrudRepository<TgUserDataEntity, Integer> {
    // Obtener resultados buscando por la columna cdEmail
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgUserDataEntity trd" + " " +
            "WHERE trd.cdEmail = :cd_email"
    )
    TgUserDataEntity findByCdEmail(@Param("cd_email") String cdEmail);
    
    // Obtener resultados buscando por la columna cdLogin
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgUserDataEntity trd" + " " +
            "WHERE trd.cdLogin = :cd_login"
    )
    TgUserDataEntity findByCdLogin(@Param("cd_login") String cdLogin);
}
