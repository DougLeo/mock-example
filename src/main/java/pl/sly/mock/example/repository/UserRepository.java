package pl.sly.mock.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sly.mock.example.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long userId);
    UserEntity findByUserName(String userName);
}