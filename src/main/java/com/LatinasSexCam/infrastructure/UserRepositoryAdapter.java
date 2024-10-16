package com.LatinasSexCam.infrastructure;

import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.UserEntity;
import com.LatinasSexCam.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final RoleRepositoryAdapter roleRepositoryAdapter;
    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::toDomain)
                .orElse(null); // Devuelve null si no se encuentra el usuario
    }

    @Override
    public User findById(long id) {
        return userJpaRepository.findById(id)
                .map(this::toDomain)
                .orElse(null); // Devuelve null si no se encuentra el usuario
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(toEntity(user));
    }

    public User toDomain(UserEntity entity){
        return User.builder()
                .id_user(entity.getIdUser())
                .user_name(entity.getUserName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nationality(entity.getNationality())
                .gender(entity.getGender())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .role(roleRepositoryAdapter.toDomain(entity.getRole()))
                .build();
    }
    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .idUser(user.getId_user())
                .userName(user.getUser_name())
                .email(user.getEmail())
                .password(user.getPassword())
                .nationality(user.getNationality())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .role(roleRepositoryAdapter.toEntity(user.getRole())) // el mapeo de roles también debe estar aquí
                .build();
    }


}
