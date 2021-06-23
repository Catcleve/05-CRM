package com.Hwang.crm.base.interceptor;

import com.Hwang.crm.base.exception.CrmException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrmInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath("/123");
        Object user = request.getSession().getAttribute("user");

        if (user == null) {
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/");
            return false;
        }

        return true;
    }
}
