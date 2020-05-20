package com.skpl.mailapp.config;

import com.skpl.mailapp.entity.Admin;
import com.skpl.mailapp.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author maple
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        Admin admin = (Admin) session.getAttribute("admin");
//        System.out.println("==========进入了拦截器==============");
//        if (user != null || admin != null) {
//            return true;
//        }
//        System.out.println("===========未登录===============");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

