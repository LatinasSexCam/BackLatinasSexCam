package com.LatinasSexCam.repository;

import com.LatinasSexCam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(long id);
}
