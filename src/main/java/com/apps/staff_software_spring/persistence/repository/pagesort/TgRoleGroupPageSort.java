package com.apps.staff_software_spring.persistence.repository.pagesort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleGroupEntity;

public interface TgRoleGroupPageSort extends ListPagingAndSortingRepository<TgRoleGroupEntity, Integer> {
    // Ordenar datos por columna y retornar n cantidad de registros
    Page<TgRoleGroupEntity> findBy(Pageable pageable);
}
