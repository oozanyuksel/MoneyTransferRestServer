package example.transaction;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * @author oozanyuksel
 */
public class Transaction {

  private Long id;

  private Long senderUserId;

  private Long senderAccountId;

  private Long receiverUserId;

  private Long receiverAccountId;

  private Date transactionDate;

  private Currency currency;

  private BigDecimal amount;

  private String note;

  public Transaction() {
  }

  public Transaction(Long id, Long senderUserId, Long senderAccountId, Long receiverUserId, Long receiverAccountId,
                     Date transactionDate, Currency currency, BigDecimal amount, String note) {
    this.id = id;
    this.senderUserId = senderUserId;
    this.senderAccountId = senderAccountId;
    this.receiverUserId = receiverUserId;
    this.receiverAccountId = receiverAccountId;
    this.transactionDate = transactionDate;
    this.currency = currency;
    this.amount = amount;
    this.note = note;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSenderUserId() {
    return senderUserId;
  }

  public void setSenderUserId(Long senderUserId) {
    this.senderUserId = senderUserId;
  }

  public Long getSenderAccountId() {
    return senderAccountId;
  }

  public void setSenderAccountId(Long senderAccountId) {
    this.senderAccountId = senderAccountId;
  }

  public Long getReceiverUserId() {
    return receiverUserId;
  }

  public void setReceiverUserId(Long receiverUserId) {
    this.receiverUserId = receiverUserId;
  }

  public Long getReceiverAccountId() {
    return receiverAccountId;
  }

  public void setReceiverAccountId(Long receiverAccountId) {
    this.receiverAccountId = receiverAccountId;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "Transaction{" +
           "id=" + id +
           ", senderUserId=" + senderUserId +
           ", senderAccountId=" + senderAccountId +
           ", receiverUserId=" + receiverUserId +
           ", receiverAccountId=" + receiverAccountId +
           ", transactionDate=" + transactionDate +
           ", currency=" + currency +
           ", amount=" + amount +
           ", note='" + note + '\'' +
           '}';
  }
}
