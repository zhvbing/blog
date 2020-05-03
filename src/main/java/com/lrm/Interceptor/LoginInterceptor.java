package com.lrm.Interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {

        //判断是否存在名为“user”的HtppSession
        if(httpServletRequest.getSession().getAttribute("user")==null){
            httpServletResponse.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
