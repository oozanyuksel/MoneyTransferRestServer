package example.bank.exceptions;

/**
 * @author oozanyuksel
 */
public class InsufficientBalanceException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "Sending account doesn't have enough balance for trnasaction. "
                                                 + "AccountId: %d";

  public InsufficientBalanceException(Long accountId) {
    super(String.format(MESSAGE_TEMPLATE, accountId));
  }
}
