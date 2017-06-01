package UsersService.Security.TokenGenerator;

/**
 * Created by Hare on 31.05.2017..
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateAuthentication {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "NekiRandomTajniKljuc12345";

    static public void addAuthentication(HttpServletRequest request, HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //res.(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", JWT);
        tokenMap.put("username", username);
        tokenMap.put("id", username);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getWriter(), tokenMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clearAuthenticationAttributes(request);
    }

//    static Authentication getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            // parse the token.
//            String user = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody()
//                    .getSubject();
//
//            return user != null ?
//                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
//                    null;
//        }
//        return null;
//    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     *
     */
    static private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}

