package pl.sly.mock.example.service.impl;

import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.entity.UserEntity;
import pl.sly.mock.example.repository.UserRepository;
import pl.sly.mock.example.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public UserEntity findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}