package com.apps.staff_software_spring.service.dto;

import lombok.Data;

@Data
public class TgUserDataDto {
    private int idRegister;
    private String cdEmail;
    private String cdLogin;
    private String cdPassword;
}
