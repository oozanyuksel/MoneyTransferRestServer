package example.bank.exceptions;

/**
 * @author oozanyuksel
 */
public class CurrencyNotMatchedException extends IllegalArgumentException {

  private static final String MESSAGE_TEMPLATE = "Account currencies not matched exception."
                                                 + "SenderAccountId: %d, ReceiverAccountId: %d";

  public CurrencyNotMatchedException(Long senderAccountId, Long receiverAccountId) {
    super(String.format(MESSAGE_TEMPLATE, senderAccountId, receiverAccountId));
  }
}
