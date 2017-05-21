package UsersService.Security;

import java.util.Optional;

import UsersService.Models.User;

public interface UserService {
    public Optional<User> getByUsername(String username);
}
