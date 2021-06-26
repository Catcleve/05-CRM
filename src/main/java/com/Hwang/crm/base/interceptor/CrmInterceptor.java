package com.Hwang.crm.base.interceptor;

import com.Hwang.crm.base.exception.CrmException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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


            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('" + request.getContextPath() + "/index.jsp','_top')");
            out.println("</script>");
            out.println("</html>");
            return false;

            /*
            * 直接使用下面会有在子页面跳转的问题
             request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/");
            return false;
            */
        }
        return true;
    }
}
