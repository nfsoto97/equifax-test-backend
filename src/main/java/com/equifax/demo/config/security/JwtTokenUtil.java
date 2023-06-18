package com.equifax.demo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.equifax.demo.login.model.User;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
	
	@Value("${security.jwt.secret}")
	private String SECRET_KEY;
	
	public String generateAccessToken(User user) {

        // Construir la clave secreta a partir de la cadena SECRET_KEY
        byte[] apiKeySecretBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        Key secretKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
		return Jwts.builder()
				.setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
				.setIssuer("CodeJava")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(secretKey)
				.compact();

	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expired", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
		} catch (MalformedJwtException ex) {
			LOGGER.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		} 
		
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	private Claims parseClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .build()
				.parseClaimsJws(token)
				.getBody();
	}
}





// {
//     private static final String SECRET_KEY = "D50380A090CEDB9C9A4301E96639E9302C4C0A3B0157028BD4FC8FA7C3A38FE0";
//     private static final long EXPIRATION_TIME = 86400000; // 24 horas

//     public String generateToken(String username) {
//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

//         // Construir la clave secreta a partir de la cadena SECRET_KEY
//         byte[] apiKeySecretBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
//         Key secretKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(now)
//                 .setExpiration(expiryDate)
//                 .signWith(secretKey)
//                 .compact();
//     }

//     public String getUsernameFromToken(String token) {
//         Claims claims = Jwts.parserBuilder()
//                 .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();

//         return claims.getSubject();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                     .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }