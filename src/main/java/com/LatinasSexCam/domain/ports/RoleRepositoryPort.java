package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Role;

public interface RoleRepositoryPort {

    Role findById(long id);
    void save(Role role);
}
