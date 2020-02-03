package example.micronaut;

import example.account.BankAccount;
import example.account.BankAccountRepository;
import example.transaction.TransactionHistoryRepository;
import example.user.User;
import example.user.UserRepository;
import io.micronaut.context.annotation.Factory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Singleton;

import static java.util.Currency.getInstance;

/**
 * @author oozanyuksel
 */
@Factory
public class ApplicationConfig {

  public static final Set<User> INITIAL_USERS = Set.of(new User(1L, "Onur Ozan", "Yuksel"),
                                                        new User(2L, "User", "Two"),
                                                        new User(3L, "User", "Three"),
                                                        new User(4L, "User", "Four"));

  public static final List<BankAccount> INITIAL_BANK_ACCOUNTS =
      List.of(new BankAccount(1L, 1L, "My Euro Account", getInstance("EUR"),
                              BigDecimal.valueOf(100L), new Date(), new Date()),
              new BankAccount(2L, 1L, "My Dollar Account", getInstance("USD"),
                              BigDecimal.valueOf(300L), new Date(), new Date()),
              new BankAccount(3L, 1L, "My Pound Account", getInstance("GBP"),
                              BigDecimal.valueOf(0L), new Date(), new Date()),
              new BankAccount(4L, 2L, "Account 1", getInstance("GBP"),
                              BigDecimal.valueOf(100L), new Date(), new Date()),
              new BankAccount(5L, 3L, "Account 2", getInstance("EUR"),
                              BigDecimal.valueOf(100L), new Date(), new Date()),
              new BankAccount(6L, 3L, "Account 3", getInstance("GBP"),
                              BigDecimal.valueOf(100L), new Date(), new Date()),
              new BankAccount(7L, 4L, "Account 4", getInstance("EUR"),
                              BigDecimal.valueOf(30L), new Date(), new Date()));

  @Singleton
  TransactionHistoryRepository transactionHistoryRepository() {
    return new TransactionHistoryRepository(new ArrayList<>());
  }

  @Singleton
  UserRepository userRepository() {
    return new UserRepository(INITIAL_USERS);
  }

  @Singleton
  BankAccountRepository bankAccountRepository() {
    return new BankAccountRepository(INITIAL_BANK_ACCOUNTS);
  }
}
