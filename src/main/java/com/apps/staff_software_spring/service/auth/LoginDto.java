package com.apps.staff_software_spring.service.auth;

import lombok.Data;

@Data
public class LoginDto {
    private String cdLogin;
    private String cdPassword;
}
