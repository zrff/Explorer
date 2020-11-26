package com.github.zrff.backend.auth.controller;

import com.github.zrff.backend.common.model.Message;
import com.github.zrff.backend.common.service.CommonListService;
import com.github.zrff.backend.auth.model.User;
import com.github.zrff.backend.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    @Qualifier("commonListService")
    CommonListService dao;

    String baseNamespace = LoginController.class.getName() + ".";

    @RequestMapping(value="/login")//,method= RequestMethod.POST
    public Object login(@RequestParam Map<String,Object> params, HttpServletRequest req, HttpServletResponse rep) throws IOException {
        String sqlNS = baseNamespace+"getUserByPhone";
        User user = dao.getRow(params, sqlNS);

        // 返回消息
        if(user != null && user.check(params)){
            // 会话信息记录
            SessionUtil sessionUtil = new SessionUtil();
            sessionUtil.saveUser(req,user);
            return Message.createSuccessMessage();
        }
        return Message.createUnAuthorityMessage();
    }

    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public Object logout(@RequestParam Map<String,Object> params, HttpServletRequest req, HttpServletResponse rep){
        // 会话信息清理
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.close(req);
        return Message.createLogoutMessage();
    }

}
