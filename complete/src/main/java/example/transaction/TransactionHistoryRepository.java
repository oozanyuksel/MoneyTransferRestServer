package example.transaction;

import example.transaction.TransactionHistory.Status;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author oozanyuksel
 */
public class TransactionHistoryRepository {

  private List<TransactionHistory> transactionHistoryDb;

  public TransactionHistoryRepository(List<TransactionHistory> initialHistory) {
    this.transactionHistoryDb = initialHistory;
  }

  public List<TransactionHistory> getSentTransactionsByUserId(Long accountId) {
    return transactionHistoryDb.stream()
                               .filter(history -> accountId.equals(history.getTransaction().getSenderAccountId()))
                               .collect(toList());
  }

  public List<TransactionHistory> getRecievedTransactionsByUserId(Long accountId) {
    return transactionHistoryDb.stream()
                               .filter(
                                   transaction -> accountId.equals(transaction.getTransaction().getReceiverAccountId()))
                               .collect(toList());
  }

  public List<TransactionHistory> getTransactionsByDate(Date startDate, Date finishDate) {
    return transactionHistoryDb.stream()
                               .filter(getInDateIntervalPredicate(startDate, finishDate))
                               .collect(Collectors.toList());
  }

  private Predicate<TransactionHistory> getInDateIntervalPredicate(Date startDate, Date finishDate) {
    return transaction -> {
      Date transactionDate = transaction.getTransaction().getTransactionDate();
      return transactionDate.after(startDate) && transactionDate.before(finishDate);
    };
  }

  public TransactionHistory insert(Transaction transaction, Status status, String detail) {
    Long id = (long) (Math.random() * 10_000_000L);  // This line is to simulate generated id
    TransactionHistory transactionHistory = new TransactionHistory(id, transaction, status, new Date(6), detail);
    transactionHistoryDb.add(transactionHistory);
    return transactionHistory;
  }
}
