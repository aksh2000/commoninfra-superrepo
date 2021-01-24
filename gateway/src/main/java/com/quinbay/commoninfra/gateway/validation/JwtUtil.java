package com.quinbay.commoninfra.gateway.validation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    // DO NOT CHANGE THE SECRET KEY HERE AS IT SHOULD SAME AS IT IS IN AUTHENTICATION SERVER
    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim( token, Claims::getSubject );
    }

    public String extractPayload(String token){
        return Jwts.parser().setSigningKey( SECRET_KEY ).parseClaimsJws( token ).getBody().get( "userroles" ).toString();
    }

    public Date extractExpiration(String token) {
        return extractClaim( token, Claims::getExpiration );
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims( token );
        return claimsResolver.apply( claims );
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey( SECRET_KEY ).parseClaimsJws( token ).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration( token ).before( new Date() );
    }

    public String generateToken(UserDetails userDetails, List<String> userRoles) {
        Map<String, Object> claims = new HashMap<>();
        return createToken( claims, userDetails.getUsername(), userRoles.toString() );
    }

    private String createToken(Map<String, Object> claims, String subject, String payload) {

        return Jwts.builder().setClaims( claims ).setSubject( subject ).setIssuedAt( new Date( System.currentTimeMillis() ) )
                .setPayload( payload )
                .setExpiration( new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 36 ) )
                .signWith( SignatureAlgorithm.HS256, SECRET_KEY ).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername( token );
        return (username.equals( userDetails.getUsername() ) && !isTokenExpired( token ));
    }

}

