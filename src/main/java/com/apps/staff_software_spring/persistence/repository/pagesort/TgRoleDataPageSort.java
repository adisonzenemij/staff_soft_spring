package com.apps.staff_software_spring.persistence.repository.pagesort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

public interface TgRoleDataPageSort extends ListPagingAndSortingRepository<TgRoleDataEntity, Integer> {
    // Ordenar datos por columna y retornar n cantidad de registros
    Page<TgRoleDataEntity> findBy(Pageable pageable);
}
