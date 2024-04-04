package com.apps.staff_software_spring.persistence.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsernameAudit implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.isAuthenticated()) {
            return null;
        }

        System.out.println("Validar Data: " + authentication);

        String cdLogin = authentication.getPrincipal().toString();
        return Optional.of(cdLogin);
    }
}
