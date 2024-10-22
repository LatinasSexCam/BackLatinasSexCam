package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.User;

public interface UserRepositoryPort {
    User findByEmail(String email);
    User findByUserName(String userName);
    User findById(long id);
    void save(User user);
}
