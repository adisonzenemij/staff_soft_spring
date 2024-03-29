package com.apps.staff_software_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.persistence.repository.TgUserDataRepository;

@Service
public class TgUserDataService {
    private final TgUserDataRepository tgUserDataRepository;

    @Autowired
    public TgUserDataService(TgUserDataRepository tgUserDataRepository) {
        this.tgUserDataRepository = tgUserDataRepository;
    }
    
    public List<TgUserDataEntity> getAll() {
        return this.tgUserDataRepository.findAll();
    }
    
    public List<TgUserDataEntity> getAllLazy() {
        List<TgUserDataEntity> tgUserDataEntitys = this.tgUserDataRepository.findAll();
        tgUserDataEntitys.forEach(
            data -> System.out.println(
                data.getTgRoleData().getCdName()
            )
        );
        return tgUserDataEntitys;
    }
}
