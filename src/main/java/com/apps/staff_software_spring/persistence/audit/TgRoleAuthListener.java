package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRoleAuthListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgRoleAuthListener.class);
    private TgRoleAuthEntity currentValue;

    @PostLoad
    public void postLoad(TgRoleAuthEntity tgRoleAuthEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgRoleAuthEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRoleAuthEntity tgRoleAuthEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgRoleAuthEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgRoleAuthEntity tgRoleAuthEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgRoleAuthEntity.toString());
        }
    }

    private TgRoleAuthEntity cloneEntity(TgRoleAuthEntity entity) {
        TgRoleAuthEntity clone = new TgRoleAuthEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdName(entity.getCdName());
        return clone;
    }
}
