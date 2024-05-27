package com.apps.staff_software_spring.service.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class SqlFileLoaderService {
    private final JdbcTemplate jdbcTemplate;

    public SqlFileLoaderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void loadSqlFiles() {
        try {
            ClassPathResource[] resources = {
                // Ejecutar eliminaciones en orden descendente
                new ClassPathResource("sql/delete/technology/tg_user_ip.sql"),
                new ClassPathResource("sql/delete/technology/tg_user_email.sql"),
                new ClassPathResource("sql/delete/technology/tg_user_data.sql"),
                new ClassPathResource("sql/delete/technology/tg_role_permit.sql"),
                new ClassPathResource("sql/delete/technology/tg_role_auth.sql"),
                new ClassPathResource("sql/delete/technology/tg_role_action.sql"),
                new ClassPathResource("sql/delete/technology/tg_role_func.sql"),
                new ClassPathResource("sql/delete/technology/tg_role_data.sql"),
                new ClassPathResource("sql/delete/technology/tg_role_group.sql"),
                // Ejecutar eliminaciones en orden descendente
                new ClassPathResource("sql/delete/system/sy_authentication.sql"),

                
                // Ejecutar alteraciones en orden descendente
                new ClassPathResource("sql/alter/technology/tg_user_ip.sql"),
                new ClassPathResource("sql/alter/technology/tg_user_email.sql"),
                new ClassPathResource("sql/alter/technology/tg_user_data.sql"),
                new ClassPathResource("sql/alter/technology/tg_role_permit.sql"),
                new ClassPathResource("sql/alter/technology/tg_role_auth.sql"),
                new ClassPathResource("sql/alter/technology/tg_role_action.sql"),
                new ClassPathResource("sql/alter/technology/tg_role_func.sql"),
                new ClassPathResource("sql/alter/technology/tg_role_data.sql"),
                new ClassPathResource("sql/alter/technology/tg_role_group.sql"),
                // Ejecutar alteraciones en orden descendente
                new ClassPathResource("sql/alter/system/sy_authentication.sql"),


                // Ejecutar inserciones en orden ascendente
                new ClassPathResource("sql/insert/system/sy_authentication.sql"),
                // Ejecutar inserciones en orden ascendente
                new ClassPathResource("sql/insert/technology/tg_role_group.sql"),
                new ClassPathResource("sql/insert/technology/tg_role_data.sql"),
                new ClassPathResource("sql/insert/technology/tg_role_func.sql"),
                new ClassPathResource("sql/insert/technology/tg_role_action.sql"),
                new ClassPathResource("sql/insert/technology/tg_role_auth.sql"),
                new ClassPathResource("sql/insert/technology/tg_role_permit.sql"),
                new ClassPathResource("sql/insert/technology/tg_user_data.sql"),
                new ClassPathResource("sql/insert/technology/tg_user_email.sql"),
                new ClassPathResource("sql/insert/technology/tg_user_ip.sql"),
            };

            for (ClassPathResource resource : resources) {
                if (!resource.exists()) {
                    throw new IOException("File not found: " + resource.getPath());
                }

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                    String sql = reader.lines().collect(Collectors.joining("\n"));
                    jdbcTemplate.execute(sql);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading SQL files", e);
        }
    }

}
