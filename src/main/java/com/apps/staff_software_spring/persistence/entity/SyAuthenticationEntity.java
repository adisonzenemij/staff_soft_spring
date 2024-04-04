package com.apps.staff_software_spring.persistence.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.apps.staff_software_spring.persistence.audit.SyAuthenticationListener;

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
@Table(name = "sy_authentication")
@EntityListeners({AuditingEntityListener.class, SyAuthenticationListener.class})
@Getter
@Setter
@NoArgsConstructor
public class SyAuthenticationEntity extends AuditoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_register", nullable = false)
    private Integer idRegister;

    @Column(name = "cd_name", nullable = false, length = 255, unique = false)
    private String cdName;

    // Imprimir Atributos
    @Override
    public String toString() {
        return "SyAuthenticationEntity{" +
            "idRegister=" + idRegister +
            ", cdName='" + cdName + '\'' +
            '}';
    }
}
