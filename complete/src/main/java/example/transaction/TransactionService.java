package example.transaction;

import javax.inject.Singleton;

/**
 * @author oozanyuksel
 */
@Singleton
public class TransactionService {

  private final TransactionHistoryRepository repository;

  public TransactionService(TransactionHistoryRepository repository) {
    this.repository = repository;
  }

  public TransactionHistory addTransactionToHistory(Transaction transaction, TransactionHistory.Status status,
                                                    String detail) {
    return repository.insert(transaction, status, detail);
  }

  public Long getNewTransactionId() {
    // This should be autoIncremental
    return (long) (Math.random() * 10_000_000L);  // This line is to simulate generated
  }
}
