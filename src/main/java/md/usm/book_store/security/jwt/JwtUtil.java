package md.usm.book_store.security.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.usm.book_store.models.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET = "b17fd75924958d3062adc3b0e5b366332cb8b5addb92ccc0165364b3e0d739dbbb86e1562af4d9f64fd766cf5a045524071863fe34077f2505889d8e1d5b74dc";
    private static final long EXPIRATION_TIME = 3600000; // 1h

    public static String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUser_id());
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole_id());


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Object getUserAttributeFromToken(String token, String userAttribute) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .build()
                .parseSignedClaims(token)
                .getBody().get(userAttribute);
    }

    public static Cookie createJwtCookie(String jwtToken) {
        // Create a new Cookie object to store the JWT token
        Cookie jwtCookie = new Cookie("jwtToken", jwtToken);

        // Set the cookie properties
        jwtCookie.setHttpOnly(true); // Prevent JavaScript access
        jwtCookie.setSecure(true); // Ensure it's sent over HTTPS
        jwtCookie.setPath("/"); // Available for the whole application
        jwtCookie.setMaxAge(60 * 60); // Cookie expiration time in seconds (1 h)
        return jwtCookie;
    }

    public static String extractJwtFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void clearJwtCookie(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("jwtToken", null); // Create a new cookie with a null value
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // Setting Max-Age to 0 deletes the cookie

        response.addCookie(jwtCookie);
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException ex) {
            return false;
        }
    }
}
