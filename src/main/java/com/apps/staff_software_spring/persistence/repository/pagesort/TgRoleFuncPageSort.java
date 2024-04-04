package com.apps.staff_software_spring.persistence.repository.pagesort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;

public interface TgRoleFuncPageSort extends ListPagingAndSortingRepository<TgRoleFuncEntity, Integer> {
    // Ordenar datos por columna y retornar n cantidad de registros
    Page<TgRoleFuncEntity> findBy(Pageable pageable);
}
