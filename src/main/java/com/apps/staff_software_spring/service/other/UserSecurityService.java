package com.apps.staff_software_spring.service.other;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.persistence.repository.TgUserDataRepository;

@Service
public class UserSecurityService implements UserDetailsService {
    private final TgUserDataRepository tgUserDataRepository;

    @Autowired
    public UserSecurityService(TgUserDataRepository tgUserDataRepository) {
        this.tgUserDataRepository = tgUserDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cdLogin) throws UsernameNotFoundException {
        TgUserDataEntity entityTgUserData = this.tgUserDataRepository.findFirstByCdLogin(cdLogin);
        if (entityTgUserData == null) { throw new UsernameNotFoundException("User " + cdLogin + " not found."); }

        System.out.println(entityTgUserData);
        
        Integer idRegister = entityTgUserData.getIdRegister();
        TgUserDataEntity tgUserDataEntity = this.tgUserDataRepository.findById(idRegister).orElseThrow(
            () -> new UsernameNotFoundException("User " + cdLogin + " not found.")
        );

        String[] roles = {"ADMIN"};

        return User.builder()
            .username(tgUserDataEntity.getCdLogin())
            .password(tgUserDataEntity.getCdPassword())
            //.roles("ADMIN")
            .authorities(this.grantedAuthorities(roles))
            .accountLocked(false)
            .disabled(false)
            .build();
    }

    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[] { "random_order" };
        }
        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            for (String authority: this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}
