package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean isUserExists(String login);
    User saveUser(User user);
    Optional<User> getById(long id);
    Optional<User> getByLoginAndPassword(String login, String password);
    List<User> getAll();
}