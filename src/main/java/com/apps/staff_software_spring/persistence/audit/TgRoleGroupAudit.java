package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgRoleGroupEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRoleGroupAudit {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgRoleGroupAudit.class);
    private TgRoleGroupEntity currentValue;

    @PostLoad
    public void postLoad(TgRoleGroupEntity tgRoleGroupEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgRoleGroupEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRoleGroupEntity tgRoleGroupEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgRoleGroupEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgRoleGroupEntity tgRoleGroupEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgRoleGroupEntity.toString());
        }
    }

    private TgRoleGroupEntity cloneEntity(TgRoleGroupEntity entity) {
        TgRoleGroupEntity clone = new TgRoleGroupEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdName(entity.getCdName());
        return clone;
    }
}
