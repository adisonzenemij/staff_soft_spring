package com.apps.staff_software_spring.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgUserIpEntity;

public interface TgUserIpRepository extends ListCrudRepository<TgUserIpEntity, Integer> {
    // Ordenar por la columna idRegister
    List<TgUserIpEntity> findAllByOrderByIdRegister();

    // Buscar por la columna idRegister
    List<TgUserIpEntity> findAllByIdRegister(Integer idRegister);



    // Ordenar por la columna cdAddress
    List<TgUserIpEntity> findAllByOrderByCdAddress();

    // Buscar por la columna cdAddress
    List<TgUserIpEntity> findAllByCdAddressIgnoreCase(String cdAddress);

    // Buscar por la columna cdAddress ignorando mayusculas y minusculas
    List<TgUserIpEntity> findAllByCdAddressContainingIgnoreCase(String cdAddress);

    // Limitar la busqueda de registros por cdAddress
    TgUserIpEntity findFirstByCdAddress(String cdAddress);



    // Ordenar por la columna tgUserData
    List<TgUserIpEntity> findAllByOrderByTgUserData();

    // Buscar por la columna tgUserData
    List<TgUserIpEntity> findAllByTgUserData(Integer tgUserData);

    

    // Buscar por fecha de creacion antes
    List<TgUserIpEntity> findAllByAtCreatedDateAfter(LocalDateTime atCreatedDate);

    // Buscar por fecha de creacion antes
    List<TgUserIpEntity> findAllByAtModifiedDateAfter(LocalDateTime atModifiedDate);
}
