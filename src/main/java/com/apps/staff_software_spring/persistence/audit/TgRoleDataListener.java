package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRoleDataListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgRoleDataListener.class);
    private TgRoleDataEntity currentValue;

    @PostLoad
    public void postLoad(TgRoleDataEntity tgRoleDataEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgRoleDataEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRoleDataEntity tgRoleDataEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgRoleDataEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgRoleDataEntity tgRoleDataEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgRoleDataEntity.toString());
        }
    }

    private TgRoleDataEntity cloneEntity(TgRoleDataEntity entity) {
        TgRoleDataEntity clone = new TgRoleDataEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdName(entity.getCdName());
        return clone;
    }
}
