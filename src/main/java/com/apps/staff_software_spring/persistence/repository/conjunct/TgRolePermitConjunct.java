package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRolePermitEntity;
import com.apps.staff_software_spring.service.dto.TgRolePermitDto;

public interface TgRolePermitConjunct extends ListCrudRepository<TgRolePermitEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_role_permit"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_role_permit" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgRolePermitEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_permit" + " " +
            "SET tg_role_action = :tgRoleAction" + " " +
                ", tg_role_auth = :tgRoleAuth" + " " +
                ", tg_role_data = :tgRoleData" + " " +
            "WHERE id_register = :idRegister"
    )
    void updateData(
        @Param("idRegister") int idRegister,
        @Param("tgRoleAction") int tgRoleAction,
        @Param("tgRoleAuth") int tgRoleAuth,
        @Param("tgRoleData") int tgRoleData
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_permit" + " " +
            "SET tg_role_action = :#{#newDto.tgRoleAction}" + " " +
                ", tg_role_auth = :#{#newDto.tgRoleAuth}" + " " +
                ", tg_role_data = :#{#newDto.tgRoleData}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgRolePermitDto newDto);
}
