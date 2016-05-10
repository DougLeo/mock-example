package pl.sly.mock.example.service;

import pl.sly.mock.example.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAllUsers();

    UserEntity findUserById(Long userId);

    UserEntity findUserByName(String userName);

    UserEntity updateUser(UserEntity userEntity);
}
