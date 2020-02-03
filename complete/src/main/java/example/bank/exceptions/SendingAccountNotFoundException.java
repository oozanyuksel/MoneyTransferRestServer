package example.bank.exceptions;

/**
 * @author oozanyuksel
 */
public class SendingAccountNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "An error occurred while getting sending account. "
                                                 + "UserId: %d, AccountId: %d";

  public SendingAccountNotFoundException(Long senderUserId, Long senderAccountId) {
    super(String.format(MESSAGE_TEMPLATE, senderUserId, senderAccountId));
  }
}
