/**
 * 本类用于将useragent进行分类
 */
package com.github.zrff.backend.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MobileUserAgent {

    private static List<String> agents = new ArrayList();
    static {
        agents.add("phone");
        agents.add("pad");
        agents.add("pod");
        agents.add("iPhone");
        agents.add("iPod");
        agents.add("ios");
        agents.add("iPad");
        agents.add("Android");
        agents.add("Mobile");
        agents.add("BlackBerry");
        agents.add("IEMobuserAgentile");
        agents.add("MQQBrowser");
        agents.add("JUC");
        agents.add("Fennec");
        agents.add("wOSBrowser");
        agents.add("BrowserNG");
        agents.add("WebOS");
        agents.add("Symbian");
        agents.add("Windows Phone");
    }

    private MobileUserAgent(){}

    public static List<String> getAgents() {
        return agents;
    }
    public static String classifyAgent(String userAgent){
        String type = "pc";
        userAgent = userAgent.toUpperCase();
        for(String agent:agents){
            if(userAgent.indexOf(agent.toUpperCase())>-1){
                type = "mobile";
                break;
            }
        }
        return type;
    }
    public static String classifyAgent(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        return classifyAgent(userAgent);
    }
}
