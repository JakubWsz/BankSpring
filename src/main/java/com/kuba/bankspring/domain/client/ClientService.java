package com.kuba.bankspring.domain.client;

import com.kuba.bankspring.entity.Client;
import com.kuba.bankspring.entity.User;
import com.kuba.bankspring.infrastructure.repository.ClientRepository;
import com.kuba.bankspring.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public Client createClient(String login, String password, String firstName, String lastName) {

        validateFirstnameLastname(firstName, lastName);

        Client client = new Client(firstName,lastName, IdCardNumberCreator(),getUser(login,password));

       return saveClient(client);
    }

    private User getUser(String login, String password) {
        return userRepository.getUserByLoginAndPassword(login, password)
                .orElseThrow(() -> new RuntimeException("There is no client with passed login or password"));
    }

    private void validateFirstnameLastname(String firstName, String lastName) {
        if (firstName == null)
            throw new RuntimeException("you don't pass firstName");
        else if (lastName == null)
            throw new RuntimeException("you don't pass lastName");
    }

    private Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    private String IdCardNumberCreator(){
        Integer variant = UUID.randomUUID().variant();
        Integer version = UUID.randomUUID().version();

        StringBuilder strB = new StringBuilder("0022");
        strB.append(variant);
        strB.append(version);
        return strB.toString();
    }
}
