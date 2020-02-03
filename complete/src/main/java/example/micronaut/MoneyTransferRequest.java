package example.micronaut;

import java.math.BigDecimal;
import java.util.Currency;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author oozanyuksel
 */
public class MoneyTransferRequest {

  @NotNull
  @Positive
  private Long senderUserId;

  @NotNull
  @Positive
  private Long senderAccountId;

  @NotNull
  @Positive
  private Long receiverUserId;

  @NotNull
  @Positive
  private Long receiverAccountId;

  @NotNull
  private Currency currency;

  @NotNull
  private BigDecimal amount;

  private String note;

  public MoneyTransferRequest() {
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
    return "MoneyTransferRequest{" +
           "senderUserId=" + senderUserId +
           ", senderAccountId=" + senderAccountId +
           ", receiverUserId=" + receiverUserId +
           ", receiverAccountId=" + receiverAccountId +
           ", currency=" + currency +
           ", amount=" + amount +
           ", note='" + note + '\'' +
           '}';
  }
}
