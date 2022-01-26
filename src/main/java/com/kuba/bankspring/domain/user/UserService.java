package com.kuba.bankspring.domain.user;

import com.kuba.bankspring.api.dto.response.UserView;
import com.kuba.bankspring.entity.Client;
import com.kuba.bankspring.entity.User;
import com.kuba.bankspring.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, List<Client> clients) {
        this.userRepository = userRepository;
    }

    public User createUser(String login, String password, String email, int pin) {
        if (userRepository.findByLogin(login).isPresent()) {
            throw new RuntimeException("user already exists");
        }

        validateLoginPasswordEmailAndPin(login, password, email, pin);

        return userRepository.save(new User(login, password, email, pin));
        //TODO dodać factory dla usera
    }

    public User getUserById(long id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<UserView> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserView(user.getId(), user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }

    private void validateLoginPasswordEmailAndPin(String login, String password, String email, Integer pin) {
        if (login == null)
            throw new RuntimeException("you don't pass login");
        else if (password == null)
            throw new RuntimeException("you don't pass password");
        else if (email == null)
            throw new RuntimeException("you don't pass email");
        else if  (pin == null)
                    throw new RuntimeException("you don't pass pin");

        if (login.length() < 5)
            throw new RuntimeException("login is to short");
        else if (password.length() < 5)
            throw new RuntimeException("password is to short");
        else if (pin.toString().length() < 4)
            throw new RuntimeException("pin is to short");
        else if (pin.toString().length() > 4)
            throw new RuntimeException("pin is to short");

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find())
            throw new RuntimeException("email is invalid");
    }
}