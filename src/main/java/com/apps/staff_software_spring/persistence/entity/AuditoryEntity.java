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
    @Column(name = "at_created_date", nullable = true, unique = false)
    @CreatedDate
    private LocalDateTime atCreatedDate;

    @Column(name = "at_modified_date", nullable = true, unique = false)
    @LastModifiedDate
    private LocalDateTime atModifiedDate;

    @Column(name = "cd_description", nullable = true, length = 255, unique = false)
    private String cdDescription;

    @Column(name = "at_created_by", nullable = true, length = 255, unique = false)
    @CreatedBy
    private String atCreatedBy;

    @Column(name = "at_modified_by", nullable = true, length = 255, unique = false)
    @LastModifiedBy
    private String atModifiedBy;
}
