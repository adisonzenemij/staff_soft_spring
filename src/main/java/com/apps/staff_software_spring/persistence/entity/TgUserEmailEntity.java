package com.apps.staff_software_spring.persistence.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tg_user_email")
@EntityListeners({ AuditingEntityListener.class })
@Getter
@Setter
@NoArgsConstructor
public class TgUserEmailEntity extends AuditoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_register", nullable = true)
    private Integer idRegister;

    @Column(name = "cd_extra", nullable = false, length = 255, unique = true)
    private String cdExtra;

    //@ManyToOne(fecth = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "tg_user_data",
        referencedColumnName = "id_register",
        insertable = false,
        updatable = false
    )
    //@JsonIgnore
    @OrderBy("id_register ASC")
    //@OrderBy("id_register DESC")
    private TgUserDataEntity tgUserData;

    // Imprimir Atributos
    @Override
    public String toString() {
        return "TgUserEmailEntity{" +
            "idRegister=" + idRegister +
            ", cdExtra='" + cdExtra + '\'' +
            ", tgUserData='" + tgUserData + '\'' +
            '}';
    }
}
