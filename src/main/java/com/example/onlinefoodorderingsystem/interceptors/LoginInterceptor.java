package com.example.onlinefoodorderingsystem.interceptors;

import com.example.onlinefoodorderingsystem.entity.Result;
import com.example.onlinefoodorderingsystem.utils.JwtUtil;
import com.example.onlinefoodorderingsystem.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token= request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            ThreadLocalUtil.setThreadLocal(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    ////    @Override
////    public boolean preHandle(HttpServletRequest request, final HttpServletResponse response, Object handler) throws Exception {
////        String token= request.getHeader("Authorization");
////        try {
////            Map<String, Object> claims = JwtUtil.parseToken(token);
////            return true;
////        } catch (Exception e) {
////            response.setStatus(401);
////            return false;
////        }
//
//
//    }
}
