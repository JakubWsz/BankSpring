package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
   Optional<Client> getClientByFirstNameAndLastName(String firstName, String lastName);
}
