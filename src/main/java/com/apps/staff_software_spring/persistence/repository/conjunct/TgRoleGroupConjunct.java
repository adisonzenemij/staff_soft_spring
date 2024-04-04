package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleGroupEntity;
import com.apps.staff_software_spring.service.dto.TgRoleGroupDto;

public interface TgRoleGroupConjunct extends ListCrudRepository<TgRoleGroupEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_role_group"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_role_group" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgRoleGroupEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_group" + " " +
            "SET cd_name = :cdName" + " " +
            "WHERE id_register = :idRegister"
    )
    void updateData(
        @Param("idRegister") int idRegister,
        @Param("cdName") String cdName
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_group" + " " +
            "SET cd_name = :#{#newDto.cdName}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgRoleGroupDto newDto);
}
