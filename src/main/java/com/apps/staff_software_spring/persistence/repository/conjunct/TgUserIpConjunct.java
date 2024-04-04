package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgUserIpEntity;
import com.apps.staff_software_spring.service.dto.TgUserIpDto;

public interface TgUserIpConjunct extends ListCrudRepository<TgUserIpEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_user_ip"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_user_ip" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgUserIpEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_user_ip" + " " +
            "SET cd_address = :cdAddress" + " " +
                ", tg_user_data = :tgUserData" + " " +
            "WHERE id_register = :idRegister"
    )
    void updateData(
        @Param("idRegister") int idRegister,
        @Param("cdAddress") String cdAddress,
        @Param("tgUserData") int tgUserData
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tg_user_ip" + " " +
            "SET cd_address = :#{#newDto.cdAddress}" + " " +
                ", tg_user_data = :#{#newDto.tgUserData}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgUserIpDto newDto);
}
