package example.micronaut;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.account.BankAccount;
import example.transaction.TransactionHistory;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static example.micronaut.ApplicationConfig.INITIAL_BANK_ACCOUNTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest // <1>
public class BankControllerTest {

  private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Inject
  @Client("/")
  RxHttpClient client; // <2>

  @Test
  public void testGetBankAccount() {
    HttpRequest<String> request = HttpRequest.GET("/bank/accounts/user/1"); // <3>
    String body = client.toBlocking().retrieve(request);

    try {
      BankAccount[] bankAccounts = new ObjectMapper().readValue(body, BankAccount[].class);
      List<BankAccount> actual = Stream.of(bankAccounts).collect(Collectors.toList());
      List<BankAccount> expected = INITIAL_BANK_ACCOUNTS.stream()
                                                        .filter(bankAccount -> (bankAccount.getUserId().equals(1L)))
                                                        .collect(Collectors.toList());
      Assertions.assertIterableEquals(expected, actual);
    } catch (IOException ignored) {
      fail();
    }
  }

  @Test
  @SuppressWarnings("checkstyle:MagicNumber")
  public void testTransferBankAccountSuccess() {
    MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();
    moneyTransferRequest.setSenderUserId(3L);
    moneyTransferRequest.setSenderAccountId(6L);
    moneyTransferRequest.setReceiverUserId(1L);
    moneyTransferRequest.setReceiverAccountId(3L);
    moneyTransferRequest.setCurrency(Currency.getInstance("GBP"));
    moneyTransferRequest.setAmount(BigDecimal.valueOf(20L));
    moneyTransferRequest.setNote("Pocket Money");

    try {
      String req = OBJECT_MAPPER.writeValueAsString(moneyTransferRequest);
      HttpRequest<String> request = HttpRequest.POST("/bank/accounts/transfer", req); // <3>
      String body = client.toBlocking().retrieve(request);
      TransactionHistory transactionHistory = OBJECT_MAPPER.readValue(body, TransactionHistory.class);
      assertEquals(TransactionHistory.Status.SUCCESSFULL, transactionHistory.getStatus());
    } catch (IOException ignored) {
      fail();
    }
  }

  @Test
  @SuppressWarnings("checkstyle:MagicNumber")
  public void testTransferBankAccountFailCurrencyNotMatched() {
    MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();
    moneyTransferRequest.setSenderUserId(3L);
    moneyTransferRequest.setSenderAccountId(6L);
    moneyTransferRequest.setReceiverUserId(1L);
    moneyTransferRequest.setReceiverAccountId(3L);
    moneyTransferRequest.setCurrency(Currency.getInstance("USD"));
    moneyTransferRequest.setAmount(BigDecimal.valueOf(20L));
    moneyTransferRequest.setNote("Pocket Money");

    try {
      String req = OBJECT_MAPPER.writeValueAsString(moneyTransferRequest);
      HttpRequest<String> request = HttpRequest.POST("/bank/accounts/transfer", req); // <3>
      String body = client.toBlocking().retrieve(request);
      TransactionHistory transactionHistory = OBJECT_MAPPER.readValue(body, TransactionHistory.class);
      assertEquals(TransactionHistory.Status.FAILED, transactionHistory.getStatus());
      assertEquals("example.bank.exceptions.CurrencyNotMatchedException", transactionHistory.getDetail());
    } catch (IOException ignored) {
      fail();
    }
  }

  @Test
  @SuppressWarnings("checkstyle:MagicNumber")
  public void testTransferBankAccountFailInsufficientBalance() {
    MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();
    moneyTransferRequest.setSenderUserId(3L);
    moneyTransferRequest.setSenderAccountId(6L);
    moneyTransferRequest.setReceiverUserId(1L);
    moneyTransferRequest.setReceiverAccountId(3L);
    moneyTransferRequest.setCurrency(Currency.getInstance("GBP"));
    moneyTransferRequest.setAmount(BigDecimal.valueOf(120L));
    moneyTransferRequest.setNote("Pocket Money");

    try {
      String req = OBJECT_MAPPER.writeValueAsString(moneyTransferRequest);
      HttpRequest<String> request = HttpRequest.POST("/bank/accounts/transfer", req); // <3>
      String body = client.toBlocking().retrieve(request);
      TransactionHistory transactionHistory = OBJECT_MAPPER.readValue(body, TransactionHistory.class);
      assertEquals(TransactionHistory.Status.FAILED, transactionHistory.getStatus());
      assertEquals("example.bank.exceptions.InsufficientBalanceException", transactionHistory.getDetail());
    } catch (IOException ignored) {
      fail();
    }
  }

  @Test
  @SuppressWarnings("checkstyle:MagicNumber")
  public void testTransferBankAccountFailRecieverNotFound() {
    MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();
    moneyTransferRequest.setSenderUserId(3L);
    moneyTransferRequest.setSenderAccountId(6L);
    moneyTransferRequest.setReceiverUserId(9L);
    moneyTransferRequest.setReceiverAccountId(3L);
    moneyTransferRequest.setCurrency(Currency.getInstance("GBP"));
    moneyTransferRequest.setAmount(BigDecimal.valueOf(20L));
    moneyTransferRequest.setNote("Pocket Money");

    try {
      String req = OBJECT_MAPPER.writeValueAsString(moneyTransferRequest);
      HttpRequest<String> request = HttpRequest.POST("/bank/accounts/transfer", req); // <3>
      String body = client.toBlocking().retrieve(request);
      TransactionHistory transactionHistory = OBJECT_MAPPER.readValue(body, TransactionHistory.class);
      assertEquals(TransactionHistory.Status.FAILED, transactionHistory.getStatus());
      assertEquals("example.bank.exceptions.RecievingAccountNotFoundException", transactionHistory.getDetail());
    } catch (IOException ignored) {
      fail();
    }
  }

  @Test
  @SuppressWarnings("checkstyle:MagicNumber")
  public void testTransferBankAccountFailSenderNotFound() {
    MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();
    moneyTransferRequest.setSenderUserId(9L);
    moneyTransferRequest.setSenderAccountId(6L);
    moneyTransferRequest.setReceiverUserId(1L);
    moneyTransferRequest.setReceiverAccountId(3L);
    moneyTransferRequest.setCurrency(Currency.getInstance("GBP"));
    moneyTransferRequest.setAmount(BigDecimal.valueOf(20L));
    moneyTransferRequest.setNote("Pocket Money");

    try {
      String req = OBJECT_MAPPER.writeValueAsString(moneyTransferRequest);
      HttpRequest<String> request = HttpRequest.POST("/bank/accounts/transfer", req); // <3>
      String body = client.toBlocking().retrieve(request);
      TransactionHistory transactionHistory = OBJECT_MAPPER.readValue(body, TransactionHistory.class);
      assertEquals(TransactionHistory.Status.FAILED, transactionHistory.getStatus());
      assertEquals("example.bank.exceptions.SendingAccountNotFoundException", transactionHistory.getDetail());
    } catch (IOException ignored) {
      fail();
    }
  }
}
