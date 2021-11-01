//package com.kuba.bankspring.domain.transaction;
//
//import com.kuba.bankspring.domain.account.AccountService;
//import com.kuba.bankspring.domain.user.UserService;
//import com.kuba.bankspring.entity.*;
//import com.kuba.bankspring.infrastructure.repository.AccountRepository;
//import com.kuba.bankspring.infrastructure.repository.TransferBetweenAccountsRepository;
//import com.kuba.bankspring.infrastructure.repository.TransferSelfRepository;
//import com.kuba.bankspring.infrastructure.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//class TransactionServiceTest {
//    @InjectMocks
//    private UserService userService;
//    @InjectMocks
//    private AccountService accountService;
//    @InjectMocks
//    private TransactionService transactionService;
//    @Mock
//    private TransferBetweenAccountsRepository transferBetweenAccountsRepository;
//    @Mock
//    private TransferSelfRepository transferSelfRepository;
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
//        Mockito.reset(transferBetweenAccountsRepository);
//        Mockito.reset(transferSelfRepository);
//        accountService = Mockito.spy(new AccountService(userService,accountRepository));
//        transactionService = Mockito.spy(new TransactionService(accountService,transferSelfRepository,
//                transferBetweenAccountsRepository));
//    }
//
//    @Test
//    void depositTestValuesShouldBeNotNullAndBalanceAmountShouldBe10() {
//        //given
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//        //when
//
//        Account account1 = transactionService.deposit(new TransferAmount(BigDecimal.TEN, CurrencyType.PLN)
//                , account.getAccountNumber(), account.getPin());
//        //then
//
//        Assertions.assertNotNull(account1);
//        Assertions.assertNotNull(account1.getAccountNumber());
//        Assertions.assertNotNull(account1.getBalance().getAmount());
//        Assertions.assertNotNull(account1.getBalance().getCurrencyType());
//        Assertions.assertEquals(BigDecimal.TEN, account1.getBalance().getAmount());
//    }
//
//    @Test
//    void makeDepositWithWrongAccountNumberShouldThrowException() {
//
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.deposit(new TransferAmount(BigDecimal.TEN, CurrencyType.EUR)
//                        , accountService.getAccountByAccountNumber("123").toString(),1234));
//
//        Assertions.assertEquals("account not found", runtimeException.getMessage());
//    }
//
//    @Test
//    void makeDepositWithWrongCurrencyShouldThrowException() {
//        //GIVEN
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.deposit(new TransferAmount(BigDecimal.TEN, CurrencyType.EUR)
//                        , account.getAccountNumber(),account.getPin()));
//
//        Assertions.assertEquals("different currency than account is unsupported yet", runtimeException.getMessage());
//    }
//
//    @Test
//    void makeDepositWithAmountZeroOrLessShouldThrowException() {
//        //GIVEN
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.deposit(new TransferAmount(BigDecimal.valueOf(-10), CurrencyType.EUR)
//                        , account.getAccountNumber(),account.getPin()));
//
//        Assertions.assertEquals("operation disallowed", runtimeException.getMessage());
//    }
//
//    @Test
//    void withdrawTestValuesShouldBeNotNullAndBalanceAmountShouldBeMinus10() {
//        //given
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//        //when
//
//        Account account1 = transactionService.withdraw(new TransferAmount(BigDecimal.TEN, CurrencyType.PLN)
//                , account.getAccountNumber(),account.getPin());
//
//        //then
//        Assertions.assertNotNull(account1);
//        Assertions.assertNotNull(account1.getAccountNumber());
//        Assertions.assertNotNull(account.getBalance().getAmount());
//        Assertions.assertNotNull(account.getBalance().getCurrencyType());
//        Assertions.assertEquals(BigDecimal.valueOf(-10L), account1.getBalance().getAmount());
//    }
//
//    @Test
//    void makeWithdrawWithWrongAccountNumberShouldThrowException() {
//
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.withdraw(new TransferAmount(BigDecimal.TEN, CurrencyType.EUR)
//                        , accountService.getAccountByAccountNumber("123").toString(),1234));
//
//        Assertions.assertEquals("account not found", runtimeException.getMessage());
//    }
//
//    @Test
//    void makeWithdrawWithWrongCurrencyShouldThrowException() {
//        //GIVEN
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.withdraw(new TransferAmount(BigDecimal.TEN, CurrencyType.EUR)
//                        , account.getAccountNumber(),account.getPin()));
//
//        Assertions.assertEquals("different currency than account is unsupported yet", runtimeException.getMessage());
//    }
//
//    @Test
//    void makeWithdrawWithAmountZeroOrLessShouldThrowException() {
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        //WHEN
//        //THEN
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.deposit(new TransferAmount(BigDecimal.valueOf(-10), CurrencyType.EUR)
//                        , account.getAccountNumber(),account.getPin()));
//
//        Assertions.assertEquals("operation disallowed", runtimeException.getMessage());
//    }
//
//    @Test
//    void transferTestShouldBeNotNull() {
//        //given
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        User user1 = new User();
//        user1.setId(2L);
//        user1.setEmail("asfSDSasf@asdd.pl");
//        user1.setPassword("pasword");
//        user1.setLogin("login");
//        Account account1 = new Account();
//        account1.setId(2L);
//        account1.setAccountNumber("123123098");
//        account1.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user1.getId())).thenReturn(java.util.Optional.of(user1));
//        when(accountRepository.getByAccountNumber(account1.getAccountNumber())).thenReturn(java.util.Optional.of(account1));
//        //when
//
//        Account account2 = transactionService.transfer(new TransferAmount(BigDecimal.TEN, CurrencyType.PLN)
//                , account.getAccountNumber(), account1.getAccountNumber(), account.getPin());
//
//        //then
//        Assertions.assertNotNull(account2);
//        Assertions.assertNotNull(account1);
//        Assertions.assertNotNull(account2.getAccountNumber());
//        Assertions.assertNotNull(account1.getAccountNumber());
//        Assertions.assertNotNull(account2.getBalance().getAmount());
//        Assertions.assertNotNull(account1.getBalance().getAmount());
//        Assertions.assertNotNull(account1.getAccountNumber());
//        Assertions.assertNotNull(account2.getAccountNumber());
//    }
//
//    @Test
//    void transferTestUserBalanceAmountShouldBeMinus10AndTargetBalanceAmountShouldBe10(){
//        //given
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        User user1 = new User();
//        user1.setId(2L);
//        user1.setEmail("asfSDSasf@asdd.pl");
//        user1.setPassword("pasword");
//        user1.setLogin("login");
//        Account account1 = new Account();
//        account1.setId(2L);
//        account1.setAccountNumber("123123098");
//        account1.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user1.getId())).thenReturn(java.util.Optional.of(user1));
//        when(accountRepository.getByAccountNumber(account1.getAccountNumber())).thenReturn(java.util.Optional.of(account1));
//        //when
//
//        Account account2 = transactionService.transfer(new TransferAmount(BigDecimal.TEN, CurrencyType.PLN)
//                ,account.getAccountNumber(), account1.getAccountNumber(),account.getPin());
//        //Then
//
//        Assertions.assertEquals(BigDecimal.valueOf(-10L), account2.getBalance().getAmount());
//        Assertions.assertEquals(BigDecimal.TEN, account1.getBalance().getAmount());
//    }
//
//    @Test
//    void makeTransferWithWrongUserAccountNumberShouldThrowException() {
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.transfer(new TransferAmount(BigDecimal.TEN, CurrencyType.EUR)
//                        ,accountService.getAccountByAccountNumber("123").toString()
//                        ,accountService.getAccountByAccountNumber(account.getAccountNumber()).toString(),account.getPin()));
//
//        Assertions.assertEquals("account not found", runtimeException.getMessage());
//    }
//
//    @Test
//    void makeTransferWithWrongTargetAccountNumberShouldThrowException() {
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.transfer(new TransferAmount(BigDecimal.TEN, CurrencyType.EUR)
//                        ,accountService.getAccountByAccountNumber(account.getAccountNumber()).toString()
//                        ,accountService.getAccountByAccountNumber("123").toString(),account.getPin()));
//
//        Assertions.assertEquals("account not found", runtimeException.getMessage());
//    }
//
//    @Test
//    void makeTransferWithZeroOrLessAmount() {
//        //given
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//
//        User user1 = new User();
//        user.setId(1L);
//        user.setEmail("asfasf@asdd.pl");
//        user.setPassword("pasword");
//        user.setLogin("login");
//        Account account1 = new Account();
//        account.setId(1L);
//        account.setAccountNumber("123123123");
//        account.setPin(1234);
//        account.setBalance(new Balance(BigDecimal.ZERO, CurrencyType.PLN));
//        when(userRepository.getById(user.getId())).thenReturn(java.util.Optional.of(user));
//        when(accountRepository.getByAccountNumber(account.getAccountNumber())).thenReturn(java.util.Optional.of(account));
//        //when
//        RuntimeException runtimeException = assertThrows(
//                RuntimeException.class,
//                () -> transactionService.transfer(new TransferAmount(BigDecimal.valueOf(-10), CurrencyType.EUR)
//                        , account.getAccountNumber(), account1.getAccountNumber(),account.getPin()));
//
//        //THEN
//        Assertions.assertEquals("operation disallowed", runtimeException.getMessage());
//    }
//}