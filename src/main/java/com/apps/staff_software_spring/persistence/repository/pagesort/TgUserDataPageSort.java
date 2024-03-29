package com.apps.staff_software_spring.persistence.repository.pagesort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;

public interface TgUserDataPageSort extends ListPagingAndSortingRepository<TgUserDataEntity, Integer> {
    // Ordenar datos por columna y retornar n cantidad de registros
    Page<TgUserDataEntity> findBy(Pageable pageable);
}
