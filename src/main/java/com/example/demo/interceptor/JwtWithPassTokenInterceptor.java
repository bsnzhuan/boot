package com.example.demo.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.example.demo.anno.JwtPassToken;
import com.example.demo.tools.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

public class JwtWithPassTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("this is JwtWithPassTokenInterceptor,url:"+req.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(JwtPassToken.class)){
            JwtPassToken parseToken = method.getAnnotation(JwtPassToken.class);
            if(parseToken.required()){
                return true;
            }
        }
        String token = req.getHeader("authorization");
        if(token == null){
            throw new RuntimeException("沒有token");
        }
        Map<String, Claim> dataMap = JwtUtils.verifyToken(token.replace(JwtUtils.TOKEN_PREFIX,""));
        if(dataMap == null){
            throw new RuntimeException("token不合法");
        }
        //驗證dataMap數據 key:id serCode userName


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
