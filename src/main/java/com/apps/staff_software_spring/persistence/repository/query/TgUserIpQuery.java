package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgUserIpEntity;

public interface TgUserIpQuery extends ListCrudRepository<TgUserIpEntity, Integer> {
    // Obtener resultados buscando por la columna cdAddress
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgUserIpEntity trd" + " " +
            "WHERE trd.cdAddress = :cd_address"
    )
    TgUserIpEntity findByCdAddress(@Param("cd_address") String cdAddress);
    
    // Obtener resultados buscando por la columna tgUserData
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgUserIpEntity trd" + " " +
            "WHERE trd.tgUserData = :tg_user_data"
    )
    TgUserIpEntity findByTgUserData(@Param("tg_user_data") Integer tgUserData);
}
