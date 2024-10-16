package com.LatinasSexCam.infrastructure;

import com.LatinasSexCam.domain.model.Role;
import com.LatinasSexCam.domain.ports.RoleRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.RoleEntity;
import com.LatinasSexCam.infrastructure.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Role findById(long id) {
        return toDomain(roleJpaRepository.findById(id));
    }

    @Override
    public void save(Role role) {
        roleJpaRepository.save(toEntity(role));
    }

    public Role toDomain(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        return Role.builder()
                .id_role(entity.getIdRole())
                .name(entity.getName())
                .build();
    }

    public RoleEntity toEntity(Role role) {
        if (role == null){
            return null;
        }
        return RoleEntity.builder()
                .idRole(role.getId_role())
                .name(role.getName())
                .build();
    }
}
