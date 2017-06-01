package UsersService.Security.TokenValidator;

/**
 * Created by Hare on 31.05.2017..
 */
public class LoginCredentials {
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
