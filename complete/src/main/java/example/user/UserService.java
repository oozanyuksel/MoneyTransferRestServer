package example.user;

import java.util.Optional;
import javax.inject.Singleton;

/**
 * @author oozanyuksel
 */
@Singleton
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository userRepository) {
    this.repository = userRepository;
  }

  public Optional<User> getUserById(Long userId) {
    return repository.selectUserById(userId);
  }
}
