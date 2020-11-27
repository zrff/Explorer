package com.github.zrff.backend.auth.controller;

import java.util.List;

public class DingTalkAppInfo {
    private String agentID;
    private String appkey;
    private String appsecret;

    public DingTalkAppInfo(String agentID,String appkey,String appsecret){
        this.agentID = agentID;
        this.appkey = appkey;
        this.appsecret = appsecret;
    }

    public static DingTalkAppInfo build(String agentID, List<String> agentIDs, List<String> appkeys, List<String> appsecrets){
        // 错误参数
        if(agentIDs.size()<1||appkeys.size()<1||appsecrets.size()<1) return null;

        DingTalkAppInfo info = new DingTalkAppInfo(agentIDs.get(0),appkeys.get(0),appsecrets.get(0));
        if(agentID == null|| "".equals(agentID)) return info;

        for(int i=0;i<agentIDs.size();i++){
            if(agentIDs.get(i).equals(agentID)){
                info.setAgentID(agentIDs.get(i));
                info.setAppkey(appkeys.get(i));
                info.setAppsecret(appsecrets.get(i));
            }
        }

        return info;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
