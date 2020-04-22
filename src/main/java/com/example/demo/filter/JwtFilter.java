package com.example.demo.filter;


import com.auth0.jwt.interfaces.Claim;
import com.example.demo.tools.JwtUtils;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "JwtFilter",urlPatterns = "/test/*")
public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //获取header中的token
        String token = req.getParameter("authorization");

        if ("OPTIONS".equals(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, resp);
        }else{
            if (token == null) {
                resp.getWriter().write("没有token！");
                return;
            }

            Map<String, Claim> userData = JwtUtils.verifyToken(token);
            if (userData == null) {
                resp.getWriter().write("token不合法！");
                return;
            }
            Integer id = userData.get("id").asInt();
            String userCode = userData.get("userCode").asString();
            String userName = userData.get("userName").asString();
            //拦截器 拿到用户信息，放到request中
            req.setAttribute("id", id);
            req.setAttribute("usercode", userCode);
            req.setAttribute("userName", userName);
            chain.doFilter(req, resp);
        }


    }
}
