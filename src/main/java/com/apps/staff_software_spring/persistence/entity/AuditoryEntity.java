package com.apps.staff_software_spring.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditoryEntity {
    // ad: Auditoria - af: Otros Campos
    @Column(name = "ad_created_date", nullable = true, unique = false)
    @CreatedDate
    private LocalDateTime adCreatedDate;

    @Column(name = "ad_created_by", nullable = true, length = 255, unique = false)
    @CreatedBy
    private String adCreatedBy;

    @Column(name = "ad_modified_date", nullable = true, unique = false)
    @LastModifiedDate
    private LocalDateTime adModifiedDate;

    @Column(name = "ad_modified_by", nullable = true, length = 255, unique = false)
    @LastModifiedBy
    private String adModifiedBy;

    @Column(name = "af_description", nullable = true, length = 255, unique = false)
    private String afDescription;
}
