//package com.kuba.bankspring.domain.account;
//
//import com.kuba.bankspring.domain.user.UserService;
//import com.kuba.bankspring.entity.Account;
//import com.kuba.bankspring.entity.AccountType;
//import com.kuba.bankspring.entity.CurrencyType;
//import com.kuba.bankspring.entity.User;
//import com.kuba.bankspring.infrastructure.repository.AccountRepository;
//import com.kuba.bankspring.infrastructure.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//
//class AccountServiceTest {
//    @InjectMocks
//    private UserService userService;
//    @InjectMocks
//    private AccountService accountService;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        Mockito.reset(userRepository);
//        Mockito.reset(accountRepository);
//        accountService = Mockito.spy(new AccountService(userService, accountRepository));
//    }
//
//    @Test
//    void createAccountShouldReturnAccountWithAccountNumberAndId() {
//        //GIVEN
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        long userId = getMaxId();
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//
//        User user = new User();
//        user.setId(userId);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//
//        when(userRepository.getById(userId)).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.saveAccount(any())).thenAnswer(invocation -> {
//            Account account = (Account) invocation.getArguments()[0];
//            return account;
//        });
//        //WHEN
//        Account account = accountService.createAccount(firstname, lastName, idCardNumber, userId, accountType,
//                currencyType, pin);
//
//        //THEN
//        Assertions.assertNotNull(account);
//        Assertions.assertNotNull(account.getId());
//        Assertions.assertTrue(account.getId() > 0);
//        Assertions.assertNotNull(account.getAccountNumber());
//        Assertions.assertTrue(account.getAccountNumber().length() > 0);
//        Assertions.assertNotNull(account.getPin());
//    }
//
//
//    @Test
//    void createAccountWithInvalidUserIdShouldThrowException() {
//        //GIVEN
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        long userId = 1L;
//
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        userId,
//                        accountType,
//                        currencyType,
//                        pin));
//
//        Assertions.assertEquals("user not found", runtimeException.getMessage());
//        System.out.println(runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithAlreadyExistsAccountTypeShouldThrowException() {
//        //GIVEN
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//
//        Account account = new Account();
//        account.setAccountType(accountType);
//
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getAccountsByUserId(user.getId())).thenReturn(Collections.singletonList(account));
//        //WHEN
//        //THEN
//
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin));
//
//        Assertions.assertEquals("provided account type already exists", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithNullLastnameShouldThrowException() {
//        //GIVEN
//        String firstname = "Jan";
//        String lastName = null;
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("you don't pass lastName", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithNullFirstnameShouldThrowException() {
//        //GIVEN
//        String firstname = null;
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("you don't pass firstName", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithNullIdCardNumberShouldThrowException() {
//        //GIVEN
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = null;
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("you don't pass idCardNumber", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithTooShortFirstnameShouldThrowException() {
//        String firstname = "J";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("firstname is to short", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithTooShortLastnameShouldThrowException() {
//        String firstname = "Jan";
//        String lastName = "K";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("lastname is to short", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithTooShortIdCardNumberShouldThrowException() {
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC1234567";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("ID Card Number length is to long", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithTooLongIdCardNumberShouldThrowException() {
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC12345";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 1234;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("ID Card Number length is to short", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithToLongPinShouldThrowException(){
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 12345;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("PIN is to long", runtimeException.getMessage());
//    }
//
//    @Test
//    void createAccountWithToShortPinShouldThrowException(){
//        String firstname = "Jan";
//        String lastName = "Kowalski";
//        String idCardNumber = "ABC123456";
//        AccountType accountType = AccountType.PERSONAL;
//        CurrencyType currencyType = CurrencyType.PLN;
//        Integer pin = 123;
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> accountService.createAccount(
//                        firstname,
//                        lastName,
//                        idCardNumber,
//                        user.getId(),
//                        accountType,
//                        currencyType,
//                        pin)
//        );
//        Assertions.assertEquals("PIN is to short", runtimeException.getMessage());
//    }
//
//    //HELPER METHODS
//
//    private long getMaxId() {
//        return userRepository.getAll().stream()
//                .map(User::getId)
//                .max(Long::compareTo)
//                .orElse(0L);
//    }
//}
