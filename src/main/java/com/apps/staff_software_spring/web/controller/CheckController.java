package com.apps.staff_software_spring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pub/check")
public class CheckController {
    
    @GetMapping(value = "/verify")
    public Object checkAvailability() {
        return new ApiResponse("Service Backend Avaible", true);
    }

    // Clase auxiliar para la respuesta JSON
    public static class ApiResponse {
        private String message;
        private boolean status;

        public ApiResponse(String message, boolean status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
