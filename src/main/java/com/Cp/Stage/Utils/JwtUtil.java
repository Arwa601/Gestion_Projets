// package com.Capgemini.Stage.Utils;

// import java.sql.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import javax.crypto.SecretKey;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;

// @Component
// public class JwtUtil {
//     public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }

//     public java.util.Date extractExpiration(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }


//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     public Claims extractAllClaims(String token) {
//         return Jwts
//                 .parserBuilder()
//                 .setSigningKey(getSignKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
//     private Boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new java.util.Date());
//     }

//     public Boolean validateToken(String token, UserDetails userDetails) {
//         final String username = extractUsername(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }

//     public String generateToken(String userName){
//         Map<String,Object> claims=new HashMap<>();
//         return createToken(claims,userName);
//     }

//     private String createToken(Map<String, Object> claims, String userName) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(userName)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//                 .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//     }

//     private SecretKey getSignKey() {
//         byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//         return Keys.hmacShaKeyFor(keyBytes);
//     }



// }