package com.LatinasSexCam.infrastructure.adapters;

import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.infrastructure.adapters.RoleRepositoryAdapter;
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
                .orElse(null);
    }

    @Override
    public User findByUserName(String userName) {
        return userJpaRepository.findByUserName(userName)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public User findById(long id) {
        return userJpaRepository.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(toEntity(user));
    }

    public User toDomain(UserEntity entity){
        return User.builder()
                .idUser(entity.getIdUser())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nationality(entity.getNationality())
                .gender(entity.getGender())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .profilePhoto(entity.getProfilePhoto())
                .role(roleRepositoryAdapter.toDomain(entity.getRole()))
                .build();
    }
    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .idUser(user.getIdUser())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .nationality(user.getNationality())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .profilePhoto(user.getProfilePhoto())
                .role(roleRepositoryAdapter.toEntity(user.getRole()))
                .build();
    }


}
