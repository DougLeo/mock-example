package pl.sly.mock.example.utils;

import pl.sly.mock.example.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class FactoryTestUtils {

    public static UserEntity createUserModel(Long userId, String userName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setUserName(userName);

        return userEntity;
    }

    public static List<UserEntity> createUserModelList(int size) {
        List<UserEntity> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Long userId = Long.valueOf(i + 1);
            String userName = "testUserName" + userId.intValue();
            result.add(createUserModel(userId, userName));
        }

        return result;
    }
}
