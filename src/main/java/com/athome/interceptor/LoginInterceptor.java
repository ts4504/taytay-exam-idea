package com.athome.interceptor;

import com.athome.util.JwtUtil;
import com.athome.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("Authorization");
            Map<String, Object> claim = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claim);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
