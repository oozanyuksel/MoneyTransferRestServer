package example.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author oozanyuksel
 */
public class BankAccountRepository {

  // userId -> BankAccounts Map
  private Map<Long, List<BankAccount>> accountDb;

  public BankAccountRepository(List<BankAccount> initialBankAccounts) {
    this.accountDb = initialBankAccounts.stream().collect(Collectors.groupingBy(BankAccount::getUserId));
  }

  public List<BankAccount> selectByUserId(Long userId) {
    return accountDb.get(userId);
  }

  public Optional<BankAccount> select(Long userId, Long accountId) {
    if (isNull(accountDb.get(userId))) {
      return Optional.empty();
    }
    return accountDb.get(userId).stream().filter(bankAccount -> accountId.equals(bankAccount.getId())).findFirst();
  }

  // @Transactional
  public void transferBalance(BankAccount sender, BankAccount receiver, BigDecimal value) {
    Date now = new Date();
    subtractBalance(sender, value, now);
    addBalance(receiver, value, now);
  }

  private void addBalance(BankAccount account, BigDecimal value, Date date) {
    BankAccount bankAccount = accountDb.get(account.getUserId())
                                       .stream()
                                       .filter(account::equals)
                                       .findFirst()
                                       .orElseThrow(RuntimeException::new);
    bankAccount.setBalance(bankAccount.getBalance().add(value));
    bankAccount.setLastUpdatedAt(date);
  }

  private void subtractBalance(BankAccount account, BigDecimal value, Date date) {
    BankAccount bankAccount = accountDb.get(account.getUserId())
                                       .stream()
                                       .filter(account::equals)
                                       .findFirst()
                                       .orElseThrow(RuntimeException::new);
    bankAccount.setBalance(bankAccount.getBalance().subtract(value));
    bankAccount.setLastUpdatedAt(date);
  }
}
