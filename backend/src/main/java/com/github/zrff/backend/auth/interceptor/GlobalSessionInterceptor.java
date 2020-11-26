package com.github.zrff.backend.auth.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.github.zrff.backend.common.model.Message;
import com.github.zrff.backend.util.SessionUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class GlobalSessionInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUtil sessionUtil = new SessionUtil();
        if(sessionUtil.getUser(request) != null) {
            return super.preHandle(request, response, handler);
        }

        response.sendRedirect("/");
        return false;
    }
}
