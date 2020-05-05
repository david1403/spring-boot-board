package com.david.springbootboard.interceptor;

import com.david.springbootboard.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------preHandle of LoginInterceptor---------------");

        HttpSession session = request.getSession();
        if (session.getAttribute("Member") != null) {
            session.removeAttribute("Member");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        Object member = modelAndView.getModel().get("Member");
        if (member != null) {
            session.setAttribute("Member", member);
        }
    }
}
