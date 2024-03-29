package com.apps.staff_software_spring.persistence.audit;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgUserDataListener {
    private TgUserDataEntity currentValue;

    @PostLoad
    public void postLoad(TgUserDataEntity tgUserDataEntity) {
        System.out.println("POST LOAD");
        //this.currentValue = SerializationUtils.clone(tgUserDataEntity);
        this.currentValue = cloneEntity(tgUserDataEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgUserDataEntity tgUserDataEntity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE:" + " " + this.currentValue.toString());
        System.out.println("NEW VALUE:" + " " + tgUserDataEntity.toString());
    }

    @PreRemove
    public void onPreDelete(TgUserDataEntity tgUserDataEntity) {
        System.out.println(tgUserDataEntity.toString());
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
