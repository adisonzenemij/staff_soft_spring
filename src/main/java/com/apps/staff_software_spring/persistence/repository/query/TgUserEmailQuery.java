package com.apps.staff_software_spring.persistence.repository.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgUserEmailEntity;

public interface TgUserEmailQuery extends ListCrudRepository<TgUserEmailEntity, Integer> {
    // Obtener resultados buscando por la columna cdExtra
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgUserEmailEntity trd" + " " +
            "WHERE trd.cdExtra = :cd_extra"
    )
    TgUserEmailEntity findByCdExtra(@Param("cd_extra") String cdExtra);
    
    // Obtener resultados buscando por la columna tgUserData
    @Query(
        nativeQuery = false,
        value = "SELECT trd FROM TgUserEmailEntity trd" + " " +
            "WHERE trd.tgUserData = :tg_user_data"
    )
    TgUserEmailEntity findByTgUserData(@Param("tg_user_data") Integer tgUserData);
}
