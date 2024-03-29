package com.apps.staff_software_spring.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditoryEntity {
    @Column(name = "at_created_date")
    @CreatedDate
    private LocalDateTime atCreatedDate;

    @Column(name = "at_modified_date")
    @LastModifiedDate
    private LocalDateTime atModifiedDate;
}
