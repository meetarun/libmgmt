package com.apus.arunlib.security;

import com.apus.arunlib.utils.LibMgmtConstants;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Arun Kumar Raju
 */
@Component
public class JwtTokenProvider {
    private static final long serialVersionUID = 9754784;
    private static final Logger logger = LogManager.getLogger(JwtTokenProvider.class);
    public String generateToken(String userName, Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        UUID uuid = UUID.randomUUID();
        String jwtToken = Jwts.builder()
                .setSubject(userName)
                .setId(uuid+"")
                .claim("roles",authorities)
                .setExpiration(new Date(System.currentTimeMillis() + LibMgmtConstants.LIB_TOKEN_EXP_TIME))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, LibMgmtConstants.LIB_TOKEN_SIGNING_KEY)
                .compact();
        return LibMgmtConstants.LIB_TOKEN_BEARER_PREFIX + " " + jwtToken;
    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(LibMgmtConstants.LIB_TOKEN_SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Validate the token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(LibMgmtConstants.LIB_TOKEN_SIGNING_KEY).parseClaimsJws(authToken);
            return true;
        }  catch (SignatureException ex) {
            logger.error("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT Claims string is Empty.");
        } catch (Exception ex) {
            logger.error("Invalid/Malformed JWT Token {}", ex.getMessage());
        }
        return false;
    }
}
