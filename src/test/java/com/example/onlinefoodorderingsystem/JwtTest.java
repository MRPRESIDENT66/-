package com.example.onlinefoodorderingsystem;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.onlinefoodorderingsystem.utils.JwtUtil;
import com.example.onlinefoodorderingsystem.utils.ThreadLocalUtil;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.onlinefoodorderingsystem.utils.JwtUtil.parseToken;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        Integer id=1;
        String userIdStr = String.valueOf(id);
        claims.put("id",userIdStr);
        claims.put("username","chenpu");
        String token=JwtUtil.genToken(claims);
//        String token= JWT.create()
//                .withClaim("user",claims)
//                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*7))
//                .sign(Algorithm.HMAC256("mancity"));
        System.out.println(token);

    }

    @Test
    public void testDecode(){
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOiIxIiwidXNlcm5hbWUiOiJjaGVucHUifSwiZXhwIjoxNzIwNjM5MTU5fQ.MX6rtiD_ucU3MKpha42AB6J4wCwBxzEKSoYsyBEbIWw";
//        JWTVerifier JWTVerifier=JWT.require(Algorithm.HMAC256("mancity")).build();
//        DecodedJWT decoderJWT = JWTVerifier.verify(token);
//        Map<String, Claim> claims = decoderJWT.getClaims();
        Map<String, Object> claims= JwtUtil.parseToken(token);
        System.out.println(claims.get("id"));
        System.out.println(claims.get("username"));

    }

    @Test
    public void getid(){
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiVXNlcklkIjoxLCJ1c2VybmFtZSI6ImNoZW5wdSJ9LCJleHAiOjE3MjA2MzUzNDl9.kwG96TOiWO1E3gWwq_-Q-JbbP9CMvhem7OuAZ7eeXro";
        Map<String, Object> claims = parseToken(token);
        String username=(String)claims.get("username");
        String userid=(String)claims.get("id");
        System.out.println(username);
        System.out.println(userid);

    }
    }


