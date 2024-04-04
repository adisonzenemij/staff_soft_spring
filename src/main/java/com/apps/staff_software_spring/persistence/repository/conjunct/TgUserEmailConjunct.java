package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgUserEmailEntity;
import com.apps.staff_software_spring.service.dto.TgUserEmailDto;

public interface TgUserEmailConjunct extends ListCrudRepository<TgUserEmailEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_user_email"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_user_email" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgUserEmailEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_user_email" + " " +
            "SET cd_extra = :cdExtra" + " " +
                ", tg_user_data = :tgUserData" + " " +
            "WHERE id_register = :idRegister"
    )
    void updateData(
        @Param("idRegister") int idRegister,
        @Param("cdExtra") String cdExtra,
        @Param("tgUserData") int tgUserData
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tg_user_email" + " " +
            "SET cd_extra = :#{#newDto.cdExtra}" + " " +
                ", tg_user_data = :#{#newDto.tgUserData}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgUserEmailDto newDto);
}
