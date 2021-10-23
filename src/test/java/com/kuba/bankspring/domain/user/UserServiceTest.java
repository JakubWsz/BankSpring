package com.kuba.bankspring.domain.user;

import com.kuba.bankspring.entity.User;
import com.kuba.bankspring.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(userRepository);
    }

    @Test
    void createUserShouldReturnUserWithId() {
        //GIVEN
        String login = "login";
        String password = "password";
        String email = "email@df.pl";
        long maxId = getMaxId();
        User user = new User(maxId +1,login,password,email);
        //WHEN
        when(userService.createUser(login,password,email)).thenReturn(user);
        //THEN
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(login,user.getLogin());
    }

    @Test
    void createUserWithTakenLoginShouldThrowException () {
        //GIVEN
        String login = "login";
        String password = "password";
        String email = "email@df.pl";
        when(userRepository.isUserExists(login)).thenReturn(true);
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("user already exists",runtimeException.getMessage());
    }

    @Test
    void createUserWithNullLoginShouldThrowException() {
        //GIVEN
        String login = null;
        String password = "password";
        String email = "email@df.pl";
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("you don't pass login",runtimeException.getMessage());
    }

    @Test
    void createUserWithNullPasswordShouldThrowException() {
        //GIVEN
        String login = "login";
        String password = null;
        String email = "email@df.pl";
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("you don't pass password",runtimeException.getMessage());
    }
    @Test
    void createUserWithNullEmailShouldThrowException() {
        //GIVEN
        String login = "login";
        String password = "password";
        String email = null;
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("you don't pass email",runtimeException.getMessage());
    }

    @Test
    void createUserWithTooShortLoginShouldThrowException() {
        //GIVEN
        String login = "lo";
        String password = "password";
        String email ="email@df.pl" ;
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("login is to short",runtimeException.getMessage());
    }
    @Test
    void createUserWithTooShortPasswordShouldThrowException() {
        //GIVEN
        String login = "login";
        String password = "rd";
        String email ="email@df.pl" ;
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("password is to short",runtimeException.getMessage());
    }
    @Test
    void createUserWithInvalidEmailShouldThrowException() {
        //GIVEN
        String login = "login";
        String password = "password";
        String email ="email@df." ;
        //WHEN
        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(login, password, email)
        );
        Assertions.assertEquals("email is invalid",runtimeException.getMessage());
    }

    private long getMaxId() {
        return userRepository.getAll().stream()
                .map(User::getId)
                .max(Long::compareTo)
                .orElse(0L);
    }
}