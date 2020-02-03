package example.micronaut;

import example.account.BankAccount;
import example.account.BankAccountService;
import example.bank.MoneyTransferService;
import example.transaction.Transaction;
import example.transaction.TransactionHistory;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

/**
 * @author oozanyuksel
 */
@Validated
@Controller("/bank")
public class BankController {

  private final BankAccountService bankAccountService;

  private final MoneyTransferService moneyTransferService;

  public BankController(BankAccountService bankAccountService,
                        MoneyTransferService moneyTransferService) {
    this.bankAccountService = bankAccountService;
    this.moneyTransferService = moneyTransferService;
  }

  @Get("/accounts/user/{userId}")
  public List<BankAccount> getBankAccounts(@PathVariable Long userId) {
    return bankAccountService.getBankAccounts(userId);
  }

  @Post("/accounts/transfer")
  public TransactionHistory transfer(@Body MoneyTransferRequest request) {
    Long transactionId = moneyTransferService.getNewTransactionId();
    Transaction transaction = new Transaction(transactionId,
                                              request.getSenderUserId(),
                                              request.getSenderAccountId(),
                                              request.getReceiverUserId(),
                                              request.getReceiverAccountId(),
                                              new Date(),
                                              request.getCurrency(),
                                              request.getAmount(),
                                              request.getNote());
      return moneyTransferService.doTransfer(transaction);
  }
}
