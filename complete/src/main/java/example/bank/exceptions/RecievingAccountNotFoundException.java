package example.bank.exceptions;

/**
 * @author oozanyuksel
 */
public class RecievingAccountNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "An error occurred while getting recieving account. "
                                                 + "UserId: %d, AccountId: %d";

  public RecievingAccountNotFoundException(Long receiverUserId, Long recieverAccountId) {
    super(String.format(MESSAGE_TEMPLATE, receiverUserId, recieverAccountId));
  }
}
