package example.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;

/**
 * @author oozanyuksel
 */
@Singleton
public class BankAccountService {

  private final BankAccountRepository repository;

  public BankAccountService(BankAccountRepository bankAccountRepository) {
    this.repository = bankAccountRepository;
  }

  public List<BankAccount> getBankAccounts(Long userId) {
    return repository.selectByUserId(userId);
  }

  public Optional<BankAccount> getBankAccount(Long userId, Long accountId) {
    return repository.select(userId, accountId);
  }

  public void doTransfer(BankAccount sender, BankAccount receiver, BigDecimal value) {
    repository.transferBalance(sender, receiver, value);
  }
}
