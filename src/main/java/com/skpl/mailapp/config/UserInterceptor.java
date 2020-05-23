package com.skpl.mailapp.config;

import com.alibaba.fastjson.JSONObject;
import com.skpl.mailapp.entity.Admin;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.SpringUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Date;

/**
 * @author maple
 */
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        // 用户请求
        if(request.getHeader("jwt") != null) {
            String userMail;
            try {
                userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
                // 判断jwt是否正确
                UserService userService = (UserService) SpringUtil.getBean("userService");
                User user = userService.queryByEmail(userMail);
                if(user != null ) {
                    user.setU_time(new Date());
                    userService.update(user);
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("jwt错误，可能是伪造的");
            }
        }
        // 可能是管理员请求
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("adminName"));
        if(session.getAttribute("adminName") != null) {
            flag = true;
        }
        if(!flag) {
            System.out.println("请求被拦截");
            JSONObject res = new JSONObject();
            res.put("status","error");
            res.put("error","您没有登录");
            response.getWriter().write(res.toJSONString());
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

