package example.bank;

import example.account.BankAccount;
import example.account.BankAccountService;
import example.bank.exceptions.CurrencyNotMatchedException;
import example.bank.exceptions.InsufficientBalanceException;
import example.bank.exceptions.RecievingAccountNotFoundException;
import example.bank.exceptions.SendingAccountNotFoundException;
import example.transaction.Transaction;
import example.transaction.TransactionHistory;
import example.transaction.TransactionService;
import java.util.Currency;
import java.util.function.Supplier;
import javax.inject.Singleton;

import static example.transaction.TransactionHistory.Status.FAILED;
import static example.transaction.TransactionHistory.Status.SUCCESSFULL;

/**
 * @author oozanyuksel
 */
@Singleton
public class MoneyTransferService {

  private final BankAccountService bankAccountService;

  private final TransactionService transactionService;

  public MoneyTransferService(BankAccountService bankAccountService,
                              TransactionService transactionService) {
    this.bankAccountService = bankAccountService;
    this.transactionService = transactionService;
  }

  // @Transactional This method would be transactional on database
  public TransactionHistory doTransfer(Transaction transaction) {
    try {
      Long senderUserId = transaction.getSenderUserId();
      Long senderAccountId = transaction.getSenderAccountId();
      BankAccount senderAccount = bankAccountService.getBankAccount(senderUserId, senderAccountId)
                                                    .orElseThrow(getSenderExceptionSupplier(transaction));
      Long receiverUserId = transaction.getReceiverUserId();
      Long receiverAccountId = transaction.getReceiverAccountId();
      BankAccount receiverAccount = bankAccountService.getBankAccount(receiverUserId, receiverAccountId)
                                                      .orElseThrow(receiverExceptionSupplier(transaction));
      Currency currency = transaction.getCurrency();
      if (!senderAccount.getCurrency().equals(currency) || !receiverAccount.getCurrency().equals(currency)) {
        throw new CurrencyNotMatchedException(senderAccountId, receiverAccountId);
      }
      if (senderAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
        throw new InsufficientBalanceException(senderAccountId);
      }
      bankAccountService.doTransfer(senderAccount, receiverAccount, transaction.getAmount());
      return transactionService.addTransactionToHistory(transaction, SUCCESSFULL, "");
    } catch (RuntimeException ex) {
      return transactionService.addTransactionToHistory(transaction, FAILED, ex.getClass().getName());
    }
  }

  public Long getNewTransactionId() {
    return transactionService.getNewTransactionId();
  }

  private Supplier<SendingAccountNotFoundException> getSenderExceptionSupplier(Transaction transaction) {
    return () -> new SendingAccountNotFoundException(transaction.getSenderUserId(), transaction.getSenderAccountId());
  }

  private Supplier<RecievingAccountNotFoundException> receiverExceptionSupplier(Transaction transaction) {
    return () -> new RecievingAccountNotFoundException(transaction.getReceiverUserId(),
                                                       transaction.getReceiverAccountId());
  }
}
