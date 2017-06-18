package TransactionsService.Security.Model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;
    private final Integer id;


    private UserContext(String username, List<GrantedAuthority> authorities, Integer id) {
        this.username = username;
        this.authorities = authorities;
        this.id = id;
    }
    
    public static UserContext create(String username, List<GrantedAuthority> authorities, Integer id) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities, id);
    }


    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
