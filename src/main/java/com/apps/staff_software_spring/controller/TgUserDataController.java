package com.apps.staff_software_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.service.TgUserDataService;

@RestController
public class TgUserDataController {
    private final TgUserDataService tgUserDataService;

    @Autowired
    public TgUserDataController(TgUserDataService tgUserDataService) {
        this.tgUserDataService = tgUserDataService;
    }
    
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<TgUserDataEntity>> getAll() {
        return ResponseEntity.ok(
            this.tgUserDataService.getAll()
        );
    }
}
