package com.github.zrff.backend.util;

import com.github.zrff.backend.auth.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SessionUtil {

    // 使用种的key
    private final String userKey = "user";


    @Value("${spring.profiles.active}")
    private String active;


    /**
     * 存储用户信息
     * @param req
     * @return
     */
    public HttpSession saveUser(HttpServletRequest req, User user){
        // 存储用户信息
        HttpSession session = req.getSession(true);
        session.setAttribute(userKey,user);
        req.setAttribute("userID",user.getId());
        return session;
    }

    /**
     * 获取用户信息
     * @param req
     * @return USER Object in normal , or return NULL when user hasn't login.
     */
    public User getUser(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        Object o = session.getAttribute(userKey);
        if(o!=null && o instanceof User)
            return (User) o;
        return null;
    }

    /**
     * 清除用户信息
     */
    public void close(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session != null){
            Object o = session.getAttribute(userKey);
            if(o!=null)
                session.removeAttribute(userKey);
        }
    }


    /**
     * 获取用户ID
     * @param req
     * @return
     */
    public String getUserCode(HttpServletRequest req){
        User u = getUser(req);
        return u!=null? ""+u.getId(): "-1";
    }
    public String getDevUserCode(HttpServletRequest req){
        User u = getDevUser();
        return u.getId()+"";
    }


    /**
     * 此处用于开发人员获取用户信息！
     * 只能用于本类
     * @return
     */
    public final User getDevUser(){
        User user = new User();
        user.setId(1);
        user.setPhoneNo("13177436107");
        return user;
    }

}
