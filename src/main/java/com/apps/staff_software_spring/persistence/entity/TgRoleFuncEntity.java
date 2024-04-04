package com.apps.staff_software_spring.persistence.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tg_role_func")
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
public class TgRoleFuncEntity extends AuditoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_register", nullable = true)
    private Integer idRegister;

    @Column(name = "cd_acronym", nullable = false, length = 255, unique = false)
    private String cdAcronym;

    @Column(name = "cd_name", nullable = false, length = 255, unique = false)
    private String cdName;

    // Imprimir Atributos
    @Override
    public String toString() {
        return "TgRoleFuncEntity{" +
            "idRegister=" + idRegister +
            ", cdAcronym='" + cdAcronym + '\'' +
            ", cdName='" + cdName + '\'' +
            '}';
    }
}
