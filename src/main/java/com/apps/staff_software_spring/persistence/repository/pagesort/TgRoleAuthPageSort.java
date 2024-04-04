package com.apps.staff_software_spring.persistence.repository.pagesort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;

public interface TgRoleAuthPageSort extends ListPagingAndSortingRepository<TgRoleAuthEntity, Integer> {
    // Ordenar datos por columna y retornar n cantidad de registros
    Page<TgRoleAuthEntity> findBy(Pageable pageable);
}
