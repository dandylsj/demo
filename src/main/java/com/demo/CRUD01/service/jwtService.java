package com.demo.CRUD01.service;

import com.demo.CRUD01.dto.JwtMemberPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class jwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    @PostConstruct
    void init() {
        byte[] decodedKeyByteList = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKeyByteList);
    }

    public String createToken(JwtMemberPayload jwtMemberPayload) {
        Date now = new Date();
        // 1시간
        long expirationMillis = 3600000;
        Date expiration = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .issuer("jwt.basic.com")
                .claim("memberId",jwtMemberPayload.getMemberId())
                .claim("email", jwtMemberPayload.getEmail())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }


    /**
     * 토큰 검증
     *
     */
    public MemberAuthInfo vaildateToken(String encodedToken) {
        if (encodedToken != null && encodedToken.startsWith("Bearer ")) {
            encodedToken = encodedToken.substring(7);
        }
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(encodedToken);

            Claims claims = jws.getPayload();

            Long memberId = claims.get("memberId", Long.class);
            String email = claims.get("email", String.class);

            MemberAuthInfo memberAuthInfo = new MemberAuthInfo(memberId, email);
            return memberAuthInfo;
        } catch (Exception e) {
            throw new RuntimeException("토큰 검증 실패", e);
        }
    }

}
