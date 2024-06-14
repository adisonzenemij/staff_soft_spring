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
@Table(name = "tg_geo_country")
@EntityListeners({ AuditingEntityListener.class })
@Getter
@Setter
@NoArgsConstructor
public class TgGeoCountryEntity extends AuditoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_register", nullable = false)
    private Integer idRegister;

    @Column(name = "cd_name", nullable = false, length = 15, unique = false)
    private String cdName;

    @Column(name = "cd_number", nullable = false, length = 15, unique = false)
    private String cdNumber;

    @Column(name = "cd_value", nullable = false, length = 15, unique = false)
    private String cdValue;

    // Imprimir Atributos
    @Override
    public String toString() {
        return "TgGeoCountryEntity{" +
            "idRegister=" + idRegister +
            ", cdName='" + cdName + '\'' +
            ", cdNumber='" + cdNumber + '\'' +
            ", cdValue='" + cdValue + '\'' +
            '}';
    }

}
