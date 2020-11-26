package com.github.zrff.backend.auth.model;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int id;
    private String nickName;
    private String phoneNo;
    private String password;
    private String createTime;
    private String updateTime;
    private String dingtalkID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDingtalkID() {
        return dingtalkID;
    }

    public void setDingtalkID(String dingtalkID) {
        this.dingtalkID = dingtalkID;
    }

    public boolean check(Map<String, Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        return this.phoneNo.equals(username) && this.password.equals(password);
    }

    public static User parseDingTalkUserDetails(JSONObject dingtalkUser){
        User user = new User();
        user.setId(-1);
        user.setNickName(dingtalkUser.getString("name"));
        user.setPhoneNo(dingtalkUser.getString("mobile"));
        user.setDingtalkID(dingtalkUser.getString("userid"));
        return user;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> data = new HashMap<>();
        data.put("nickName",getNickName());
        data.put("phoneNo",getPhoneNo());
        data.put("dingtalkID",getDingtalkID());
        return data;
    }
}
