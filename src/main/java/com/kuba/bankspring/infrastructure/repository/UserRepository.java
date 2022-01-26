package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> getUserById(long id);
    Optional<User> getUserByLoginAndPassword(String login, String password);
    Optional<User> getUserByEmail(String email);
}