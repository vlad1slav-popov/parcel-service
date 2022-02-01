package com.api.parcelservice.security.jwt;



import com.api.parcelservice.dto.OnUserLogoutSuccessEvent;
import com.api.parcelservice.entity.RoleEntity;
import com.api.parcelservice.exception.JwtAuthenticationException;
import com.api.parcelservice.security.JwtUserDetailsService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long validInMilliSeconds;


    private final JwtUserDetailsService userDetailsService;
    private final LoggedOutJwtTokenCache loggedOutJwtTokenCache;


    public JwtTokenProvider(LoggedOutJwtTokenCache loggedOutJwtTokenCache,
                            JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.loggedOutJwtTokenCache = loggedOutJwtTokenCache;
    }


    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

//    public String createToken(String username, List<RoleEntity> roleEntities) {
//
//        Claims claims = Jwts.claims().setSubject(username);
//        claims.put("roles", getRoleNames(roleEntities));
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validInMilliSeconds);
//
//        return Jwts.builder()//
//                .setClaims(claims)//
//                .setIssuedAt(now)//
//                .setExpiration(validity)//
//                .signWith(SignatureAlgorithm.HS256, secret)//
//                .compact();
//    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(
                getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,
                "",
                userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        log.info("Token value: " + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            validateTokenIsNotForALoggedOutDevice(token);
            return true;
        } catch (JwtException | IllegalArgumentException | JwtAuthenticationException exception) {
//            logger.error(exception.getMessage());
            exception.getMessage();
        }
        return false;
    }

    private void validateTokenIsNotForALoggedOutDevice(String authToken) {
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache
                .getLogoutEventForToken(authToken);
        if (previouslyLoggedOutEvent != null) {
            String userEmail = previouslyLoggedOutEvent.getUserName();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already " +
                            "logged out user " +
                            "[%s] at [%s]. Please login again",
                    userEmail, logoutEventDate);
            throw new JwtAuthenticationException(errorMessage);
        }
    }

    private List<String> getRoleNames(List<RoleEntity> userRoleEntities) {
        List<String> result = new ArrayList<>();

        userRoleEntities.forEach(role -> {
            result.add(role.getName());
        });

        return result;
    }

}
