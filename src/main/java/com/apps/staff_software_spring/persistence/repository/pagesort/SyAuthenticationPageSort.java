package com.apps.staff_software_spring.persistence.repository.pagesort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.apps.staff_software_spring.persistence.entity.SyAuthenticationEntity;

public interface SyAuthenticationPageSort extends ListPagingAndSortingRepository<SyAuthenticationEntity, Integer> {
    // Ordenar datos por columna y retornar n cantidad de registros
    Page<SyAuthenticationEntity> findBy(Pageable pageable);
}
