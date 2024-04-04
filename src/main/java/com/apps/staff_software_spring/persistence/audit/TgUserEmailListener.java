package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgUserEmailEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgUserEmailListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgUserEmailListener.class);
    private TgUserEmailEntity currentValue;

    @PostLoad
    public void postLoad(TgUserEmailEntity tgUserEmailEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgUserEmailEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgUserEmailEntity tgUserEmailEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgUserEmailEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgUserEmailEntity tgUserEmailEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgUserEmailEntity.toString());
        }
    }

    private TgUserEmailEntity cloneEntity(TgUserEmailEntity entity) {
        TgUserEmailEntity clone = new TgUserEmailEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdExtra(entity.getCdExtra());
        clone.setTgUserData(entity.getTgUserData());
        return clone;
    }
}
