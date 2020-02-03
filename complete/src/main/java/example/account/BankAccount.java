package example.account;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;

/**
 * @author oozanyuksel
 */
public class BankAccount {

  private Long id;

  private Long userId;

  private String name;

  private Currency currency;

  private BigDecimal balance;

  private Date createdAt;

  private Date lastUpdatedAt;

  public BankAccount() {
  }

  public BankAccount(Long id, Long userId, String name, Currency currency, BigDecimal balance, Date createdAt,
                     Date lastUpdatedAt) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.currency = currency;
    this.balance = balance;
    this.createdAt = createdAt;
    this.lastUpdatedAt = lastUpdatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getLastUpdatedAt() {
    return lastUpdatedAt;
  }

  public void setLastUpdatedAt(Date lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccount that = (BankAccount) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "BankAccount{" +
           "id=" + id +
           ", userId=" + userId +
           ", name='" + name + '\'' +
           ", currency=" + currency +
           ", balance=" + balance +
           ", createdAt=" + createdAt +
           ", lastUpdatedAt=" + lastUpdatedAt +
           '}';
  }
}
