package com.LatinasSexCam.infrastructure.security;

import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.infrastructure.security.detailsimpl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        if (user != null){
            return new UserDetailsImpl(user);
        }

        throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
    }

    private User findUserByEmail(String email){return userRepositoryPort.findByEmail(email);}
}
