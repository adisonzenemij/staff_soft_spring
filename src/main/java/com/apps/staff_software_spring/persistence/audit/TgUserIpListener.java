package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgUserIpEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgUserIpListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgUserIpListener.class);
    private TgUserIpEntity currentValue;

    @PostLoad
    public void postLoad(TgUserIpEntity tgUserIpEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgUserIpEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgUserIpEntity tgUserIpEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgUserIpEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgUserIpEntity tgUserIpEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgUserIpEntity.toString());
        }
    }

    private TgUserIpEntity cloneEntity(TgUserIpEntity entity) {
        TgUserIpEntity clone = new TgUserIpEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdAddress(entity.getCdAddress());
        clone.setTgUserData(entity.getTgUserData());
        return clone;
    }
}
