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

    @SuppressWarnings({ "resource", "null" })
    @PostConstruct
    public void loadSqlFiles() {
        try {
            ClassPathResource[] resources = {
                new ClassPathResource("sql/table/sy_authentication.sql"),
                new ClassPathResource("sql/table/tg_role_data.sql"),
                new ClassPathResource("sql/table/tg_user_data.sql"),
                new ClassPathResource("sql/table/tg_user_ip.sql"),
            };

            for (ClassPathResource resource : resources) {
                String sql = new BufferedReader(
                    new InputStreamReader(
                        resource.getInputStream()
                    ))
                    .lines()
                    .collect(Collectors.joining("\n"));
                jdbcTemplate.execute(sql);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: Loading SQL Files", e);
        }
    }
}
