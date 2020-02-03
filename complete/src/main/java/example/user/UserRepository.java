package example.user;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author oozanyuksel
 */
public class UserRepository {

  private Map<Long, User> userDb;

  public UserRepository(Set<User> initialUsers) {
    this.userDb = initialUsers.stream().collect(Collectors.toMap(User::getId, Function.identity()));
  }

  public Optional<User> selectUserById(Long userId) {
    return Optional.ofNullable(userDb.get(userId));
  }
}
