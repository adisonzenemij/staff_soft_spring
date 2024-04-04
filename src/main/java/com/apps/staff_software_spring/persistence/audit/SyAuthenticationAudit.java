package com.apps.staff_software_spring.persistence.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.staff_software_spring.persistence.entity.SyAuthenticationEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class SyAuthenticationAudit {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyAuthenticationAudit.class);
    private SyAuthenticationEntity currentValue;

    @PostLoad
    public void postLoad(SyAuthenticationEntity syAuthenticationEntity) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("POST LOAD"); }
        this.currentValue = cloneEntity(syAuthenticationEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(SyAuthenticationEntity syAuthenticationEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("POST PERSIST OR UPDATE");
            LOGGER.info("OLD VALUE: {}", this.currentValue);
            LOGGER.info("NEW VALUE: {}", syAuthenticationEntity);
        }
    }

    @PreRemove
    public void onPreDelete(SyAuthenticationEntity syAuthenticationEntity) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(syAuthenticationEntity.toString());
        }
    }

    private SyAuthenticationEntity cloneEntity(SyAuthenticationEntity entity) {
        SyAuthenticationEntity clone = new SyAuthenticationEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdName(entity.getCdName());
        return clone;
    }
}
