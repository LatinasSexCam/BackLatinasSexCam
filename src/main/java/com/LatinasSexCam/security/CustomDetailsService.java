package com.LatinasSexCam.security;

import com.LatinasSexCam.repository.UserRepository;
import com.LatinasSexCam.model.User;
import com.LatinasSexCam.security.detailsimpl.UserDetailsImpl;
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
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        if (user != null){
            return new UserDetailsImpl(user);
        }

        throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
    }

    private User findUserByEmail(String email){return userRepository.findByEmail(email);}
}
