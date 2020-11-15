package com.example.whr.config;

import com.example.whr.bean.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1.验证码过滤器，用来判断登录时验证码是否输入正确
 * 2.此过滤器要在用户名密码认证过滤器前执行
 */
@Component
public class VerifyCodeFilter extends GenericFilterBean {
    String defaultProcessUrl="/login";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        //遇到登录请求时就要做验证码判断
        if("POST".equalsIgnoreCase(req.getMethod()) && defaultProcessUrl.equals(req.getServletPath())){
            String code = req.getParameter("code");
            String index_code = (String)req.getSession().getAttribute("index_code");
            if(StringUtils.isEmpty(code)){
                throw new AuthenticationServiceException("验证码不能为空");
            }
            if(!index_code.toLowerCase().equals(code.toLowerCase())){
                throw new AuthenticationServiceException("验证码不正确，请重新输入！");
            }
            /*if(code == null || "".equals(code) || !index_code.toLowerCase().equals(code.toLowerCase())){
                //验证码未通过
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter writer = resp.getWriter();
                writer.println(new ObjectMapper().writeValueAsString(RespBean.error("验证码不正确，请重新输入！")));
                writer.flush();
                writer.close();
                return ;
            }*/
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
