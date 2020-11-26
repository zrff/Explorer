package com.github.zrff.backend.leetcode.controller;

import com.github.zrff.backend.common.model.Message;
import com.github.zrff.backend.leetcode.service.MonRecordService;
import com.github.zrff.backend.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/leetcode")
public class LeetcodeController {

    @Autowired
    MonRecordService monRecordService;

    @RequestMapping("index")
    String index(){
        return "/leetcode/index.jsp";
    }

    @RequestMapping("add")
    @ResponseBody
    Object addRecord(@RequestParam Map<String,Object> params, HttpServletRequest req){
        params.put("userID",new SessionUtil().getUserCode(req));
        int res = monRecordService.addRecord(params);
        return res>0?Message.createSuccessMessage(res)
                :Message.createFailureMessage();
    }

    @RequestMapping("fetch")
    @ResponseBody
    Object getMonRecord(@RequestParam Map<String,Object> params, HttpServletRequest req){
        params.put("userID",new SessionUtil().getUserCode(req));
        Map<String,Object> data = monRecordService.getMonRecord(params);
        return Message.createSuccessMessage(data);
    }

}
