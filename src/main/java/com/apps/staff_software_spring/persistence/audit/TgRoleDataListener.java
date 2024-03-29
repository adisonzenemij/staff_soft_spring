package com.apps.staff_software_spring.persistence.audit;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class TgRoleDataListener {
    private TgRoleDataEntity currentValue;

    @PostLoad
    public void postLoad(TgRoleDataEntity tgRoleDataEntity) {
        System.out.println("POST LOAD");
        //this.currentValue = SerializationUtils.clone(tgRoleDataEntity);
        this.currentValue = cloneEntity(tgRoleDataEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(TgRoleDataEntity tgRoleDataEntity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE:" + " " + this.currentValue.toString());
        System.out.println("NEW VALUE:" + " " + tgRoleDataEntity.toString());
    }

    @PreRemove
    public void onPreDelete(TgRoleDataEntity tgRoleDataEntity) {
        System.out.println(tgRoleDataEntity.toString());
    }

    private TgRoleDataEntity cloneEntity(TgRoleDataEntity entity) {
        TgRoleDataEntity clone = new TgRoleDataEntity();
        clone.setIdRegister(entity.getIdRegister());
        clone.setCdName(entity.getCdName());
        return clone;
    }
}
