package com.kuba.bankspring.api.rest;

import com.kuba.bankspring.api.dto.request.CreateUserRequest;
import com.kuba.bankspring.domain.user.UserService;
import com.kuba.bankspring.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(
                createUserRequest.getLogin(),
                createUserRequest.getPassword(),
                createUserRequest.getEmail(),
                createUserRequest.getPin()
        );
    }
}
