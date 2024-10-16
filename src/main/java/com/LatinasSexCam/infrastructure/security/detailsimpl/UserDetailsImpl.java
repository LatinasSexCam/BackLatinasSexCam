package com.LatinasSexCam.infrastructure.security.detailsimpl;


import com.LatinasSexCam.domain.model.Role;
import com.LatinasSexCam.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@RequiredArgsConstructor
public class UserDetailsImpl implements UsersDetails {

    private final User user;

    @Override
    public String getNombre() {
        return user.getUser_name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole(){
        return user.getRole();
    }
    public Long getId(){
        return user.getId_user();
    }

}




