package com.apps.staff_software_spring.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;

public interface TgUserDataRepository extends ListCrudRepository<TgUserDataEntity, Integer> {
    
}
