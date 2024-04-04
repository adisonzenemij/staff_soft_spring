package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.service.dto.TgUserDataDto;

public interface TgUserDataConjunct extends ListCrudRepository<TgUserDataEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_user_data"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_user_data" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgUserDataEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_user_data" + " " +
            "SET cd_email = :cdEmail" + " " +
                ", cd_login = :cdLogin" + " " +
                ", cd_password = :cdPassword" + " " +
                ", tg_role_data = :tgRoleData" + " " +
            "WHERE id_register = :idRegister"
    )
    void updateData(
        @Param("idRegister") int idRegister,
        @Param("cdEmail") String cdEmail,
        @Param("cdLogin") String cdLogin,
        @Param("cdPassword") String cdPassword,
        @Param("tgRoleData") String tgRoleData
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tg_user_data" + " " +
            "SET cd_email = :#{#newDto.cdEmail}" + " " +
                ", cd_login = :#{#newDto.cdLogin}" + " " +
                ", cd_password = :#{#newDto.cdPassword}" + " " +
                ", tg_role_data = :#{#newDto.tgRoleData}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgUserDataDto newDto);
}
