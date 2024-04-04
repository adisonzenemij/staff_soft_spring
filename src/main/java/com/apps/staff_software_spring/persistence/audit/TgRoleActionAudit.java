package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgRoleActionEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRoleActionAudit {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgRoleActionAudit.class);
    private TgRoleActionEntity currentValue;

    @PostLoad
    public void postLoad(TgRoleActionEntity tgRoleActionEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgRoleActionEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRoleActionEntity tgRoleActionEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgRoleActionEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgRoleActionEntity tgRoleActionEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgRoleActionEntity.toString());
        }
    }

    private TgRoleActionEntity cloneEntity(TgRoleActionEntity entity) {
        TgRoleActionEntity clone = new TgRoleActionEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdAcronym(entity.getCdAcronym());
        clone.setCdName(entity.getCdName());
        clone.setTgRoleFunc(entity.getTgRoleFunc());
        return clone;
    }
}
