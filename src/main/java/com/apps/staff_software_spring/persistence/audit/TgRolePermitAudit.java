package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgRolePermitEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRolePermitAudit {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgRolePermitAudit.class);
    private TgRolePermitEntity currentValue;

    @PostLoad
    public void postLoad(TgRolePermitEntity tgRolePermitEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgRolePermitEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRolePermitEntity tgRolePermitEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgRolePermitEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgRolePermitEntity tgRolePermitEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgRolePermitEntity.toString());
        }
    }

    private TgRolePermitEntity cloneEntity(TgRolePermitEntity entity) {
        TgRolePermitEntity clone = new TgRolePermitEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setTgRoleAction(entity.getTgRoleAction());
        clone.setTgRoleAuth(entity.getTgRoleAuth());
        clone.setTgRoleData(entity.getTgRoleData());
        return clone;
    }
}
