package com.apps.staff_software_spring.service.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;

@Service
public class JdbcExampleTemplate {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcExampleTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TgRoleDataEntity> getAll() {
        return this.jdbcTemplate.query(
            "SELECT * FROM tg_role_data",
            new BeanPropertyRowMapper<>(TgRoleDataEntity.class)
        );
    }
}
