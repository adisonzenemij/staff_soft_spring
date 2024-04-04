package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRoleFuncListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TgRoleFuncListener.class);
    private TgRoleFuncEntity currentValue;

    @PostLoad
    public void postLoad(TgRoleFuncEntity tgRoleFuncEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(tgRoleFuncEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRoleFuncEntity tgRoleFuncEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", tgRoleFuncEntity);
        }
    }

    @PreRemove
    public void onPreDelete(TgRoleFuncEntity tgRoleFuncEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(tgRoleFuncEntity.toString());
        }
    }

    private TgRoleFuncEntity cloneEntity(TgRoleFuncEntity entity) {
        TgRoleFuncEntity clone = new TgRoleFuncEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdAcronym(entity.getCdAcronym());
        clone.setCdName(entity.getCdName());
        return clone;
    }
}
