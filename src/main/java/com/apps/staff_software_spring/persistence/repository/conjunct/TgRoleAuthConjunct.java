package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;
import com.apps.staff_software_spring.service.dto.TgRoleAuthDto;

public interface TgRoleAuthConjunct extends ListCrudRepository<TgRoleAuthEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_role_auth"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_role_auth" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgRoleAuthEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_auth" + " " +
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
        value = "UPDATE tg_role_auth" + " " +
            "SET cd_name = :#{#newDto.cdName}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgRoleAuthDto newDto);
}
