package com.apps.staff_software_spring.persistence.repository.conjunct;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;
import com.apps.staff_software_spring.service.dto.TgRoleFuncDto;

public interface TgRoleFuncConjunct extends ListCrudRepository<TgRoleFuncEntity, Integer> {
    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM tg_role_func"
    )
    int countAll();

    // Obtener el conteo de registros de la tabla
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM tg_role_func" + " " +
            "WHERE id_register = :idRegister"
    )
    List<TgRoleFuncEntity> findIdRegister(
        @Param("idRegister") String idRegister
    );

    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_func" + " " +
            "SET cd_acronym = :cdAcronym" + " " +
                ", cd_name = :cdName" + " " +
            "WHERE id_register = :idRegister"
    )
    void updateData(
        @Param("idRegister") int idRegister,
        @Param("cdAcronym") String cdAcronym,
        @Param("cdName") String cdName
    );

    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tg_role_func" + " " +
            "SET cd_acronym = :#{#newDto.cdAcronym}" + " " +
                ", cd_name = :#{#newDto.cdName}" + " " +
            "WHERE id_register = :#{#newDto.idRegister}"
    )
    void updateDto(@Param("newDto") TgRoleFuncDto newDto);
}
