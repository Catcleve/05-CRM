package com.Hwang.crm.base.interceptor;

import com.Hwang.crm.base.exception.CrmException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrmInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler);
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        Object user = request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return false;
        }

        return true;
    }
}
