package com.apps.staff_software_spring.service.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtDto {
    private String token;
}