package com.github.zrff.backend.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.zrff.backend.auth.model.User;
import com.github.zrff.backend.common.model.Message;
import com.github.zrff.backend.common.service.CommonListService;
import com.github.zrff.backend.util.HttpRequest;
import com.github.zrff.backend.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/dingtalk")
@PropertySource("classpath:/dingtalk.properties")
public class DingTalkController {

    @Value("${dingtalk.fetch_token_url}")
    private String fetch_token_url;
    @Value("${dingtalk.getuserinfo_url}")
    private String getuserinfo_url;
    @Value("${dingtalk.getuserdetails_url}")
    private String getuserdetails_url;

    @Value("${dingtalk.corpID}")
    private String corpID;

    @Value("${dingtalk.app.agentID}")
    private String agentIDs;
    @Value("${dingtalk.app.appkey}")
    private String appkeys;
    @Value("${dingtalk.app.appsecret}")
    private String appsecrets;
    private DingTalkAppInfo dingTalkAppInfo;

    String sqlNSBase = "com.github.zrff.backend.auth.controller.LoginController.";
    @Autowired
    CommonListService service;

    /**
     * 获取token
     * @return
     */
    public JSONObject getToken(){
        String params = "appkey=" + dingTalkAppInfo.getAppkey() + "&appsecret=" + dingTalkAppInfo.getAppsecret();
        String response = HttpRequest.sendGet(fetch_token_url,params);
        JSONObject msg = (JSONObject) JSONObject.parse(response);
        return msg;
    }

    /**
     * 获取用户信息
     * @param token 	调用企业接口凭证
     * @param auth_code      H5微应用免登授权码
     * @return
     */
    public JSONObject getUserID(String token, String auth_code){
        String params = "access_token=" + token + "&code=" + auth_code;
        String response = HttpRequest.sendGet(getuserinfo_url,params);
        JSONObject msg = (JSONObject) JSONObject.parse(response);
        return msg;
    }

    /**
     * 获取用户详细信息
     * @param userID  用户id
     * @param token   调用企业接口凭证
     * @return
     */
    public JSONObject getUserDetails(String token, String userID){
        String url = getuserdetails_url + "?access_token=" + token;
        String requestBody = "userid=" + userID;
        String response = HttpRequest.sendPost(url,requestBody);
        JSONObject msg = (JSONObject) JSONObject.parse(response);
        return msg;
    }

    /**
     * 检查用户是否存在于库
     * @param user
     * @return -1 不存在该用户 userID- 存在该用户
     */
    public int checkUserIfExist(User user){
        Map<String,Object> params = new HashMap<>();
        params.put("phoneNo",user.getPhoneNo());
        String sqlNS = sqlNSBase + "checkUserIfExist";
        int uid = service.getRow(params,sqlNS);
        return uid;
    }

    /**
     * 增加用户
     * @param user
     * @return 主键ID
     */
    public int addUser(User user){
        Map<String,Object> params = user.toMap();
        String sqlNS = sqlNSBase + "addUser";
        int uid = service.getRow(params,sqlNS);
        return uid;
    }


    @RequestMapping("/index")
    public String index(){
        return "/dingtalk/index.jsp";
    }


    @RequestMapping("/getCorpID")
    @ResponseBody
    public Object getCorpID(@RequestParam Map<String,Object> params) {
        return Message.createSuccessMessage(corpID);
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Object auth(@RequestParam(value = "auth_code")String auth_code, HttpServletRequest req) {
        String agentID = req.getParameter("agent_id");
        // 获取应用信息
        dingTalkAppInfo = DingTalkAppInfo.build(
                    agentID,
                    Arrays.asList(agentIDs.split(",")),
                    Arrays.asList(appkeys.split(",")),
                    Arrays.asList(appsecrets.split(","))
        );

        String errMsg = "";
        // 获取应用的token
        JSONObject tokenRep = getToken();
        String token = tokenRep.getInteger("errcode")==0? tokenRep.getString("access_token"):null;
        errMsg += tokenRep.getString("errmsg")+";";
        if(token == null) return Message.createFailureMessage(errMsg);

        // 获取userID
        JSONObject userIDRep = getUserID(token,auth_code);
        String userID = userIDRep.getInteger("errcode")==0? userIDRep.getString("userid"):null;
        errMsg += tokenRep.getString("errmsg")+";";
        if(userID == null) return Message.createFailureMessage(errMsg);

        // 获取用户详细信息
        JSONObject userRep = getUserDetails(token, userID);
        JSONObject user = userRep.getInteger("errcode")==0? userRep.getJSONObject("result"):null;
        errMsg += tokenRep.getString("errmsg")+";";
        if(user != null){ // 获取成功
            // 创建用户信息
            User u = User.parseDingTalkUserDetails(user);
            // 校验用户是否在库
            int uid = checkUserIfExist(u);
            if(uid<=0){ // 如果用户不存在，先添加
                uid = addUser(u);
            }
            u.setId(uid);
            // 加入会话
            new SessionUtil().saveUser(req,u);

            return Message.createSuccessMessage(errMsg);
        }

        return Message.createFailureMessage(errMsg);
    }

}
