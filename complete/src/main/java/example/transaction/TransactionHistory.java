package example.transaction;

import java.util.Date;

/**
 * @author oozanyuksel
 */
public class TransactionHistory {

  private Long id;

  private Transaction transaction;

  private Status status;

  private String detail;

  private Date createdAt;

  public TransactionHistory() {
  }

  @SuppressWarnings("checkstyle:MagicNumber")
  public TransactionHistory(Long id, Transaction transaction, Status status, Date createdAt, String detail) {
    this.id = id;
    this.transaction = transaction;
    this.status = status;
    this.createdAt = createdAt;
    this.detail = detail;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public enum Status {
    FAILED,
    SUCCESSFULL
  }
}
