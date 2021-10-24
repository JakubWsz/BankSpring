package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client saveClient(Client client);
    long getClientIdByFirstNameLastNameIdCardNumber(String firstName, String lastName,String idCardNumber);
    Client getClientByClientId(long id);
}
