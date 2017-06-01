package PaymentsService.Security.TokenValidator;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.emptyList;

/**
 * Created by Hare on 01.06.2017..
 */
public class ValidateAuthentication {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "NekiRandomTajniKljuc12345";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "X-Authorization";
    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
