package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgUserDataAudit {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgUserDataAudit.class);
    private TgUserDataEntity currentValue;

    @PostLoad
    public void postLoad(TgUserDataEntity tgUserDataEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgUserDataEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgUserDataEntity tgUserDataEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgUserDataEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgUserDataEntity tgUserDataEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgUserDataEntity.toString());
        }
    }

    private TgUserDataEntity cloneEntity(TgUserDataEntity entity) {
        TgUserDataEntity clone = new TgUserDataEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdEmail(entity.getCdEmail());
        clone.setCdLogin(entity.getCdLogin());
        clone.setCdPassword(entity.getCdPassword());
        return clone;
    }
}
