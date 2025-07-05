package com.pm.authservice.Util;
import javax.crypto.SecretKey;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.security.SignatureException; // ✅ Correct
import java.util.*;

@Component
public class jwtUtil {

    private Key secretKey;

    public jwtUtil(@Value(("${jwt.secret}")) String secret) {
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email , String role){
        return Jwts.builder().subject(email)
                             .claim("role",role)
                             .issuedAt(new  Date())
                             .expiration(new  Date(System.currentTimeMillis() + 1000*60*60*10))
                             .signWith(secretKey).compact();
    }


    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT Signature");
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }
}