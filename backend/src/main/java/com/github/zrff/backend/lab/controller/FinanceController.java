package com.github.zrff.backend.lab.controller;

import com.github.zrff.backend.common.model.Message;
import com.github.zrff.backend.lab.model.BasicInfo;
import com.github.zrff.backend.lab.model.FinanceRecord;
import com.github.zrff.backend.lab.service.FinanceInfo;
import com.github.zrff.backend.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lab")
public class FinanceController {

    @Autowired
    FinanceInfo financeInfo;

    @RequestMapping("index")
    String index(){
        return "/lab/index.jsp";
    }

    @RequestMapping("/getBasic")
    @ResponseBody
    public Object getBasic(@RequestParam Map<String,Object> params, HttpServletRequest req){
        BasicInfo res = financeInfo.getBasic(params);
        return res!=null? Message.createSuccessMessage(res)
                : Message.createFailureMessage();
    }

    @RequestMapping("/getFinanceList")
    @ResponseBody
    public Object getList(@RequestParam Map<String,Object> params, HttpServletRequest req){
        List<FinanceRecord> records = financeInfo.getList(params);
        Map<String,Object> res = new HashMap<>();
        res.put("totalCount",records.size());
        res.put("rows",records);
        return Message.createSuccessMessage(res);
    }


    @RequestMapping("/addRecord")
    @ResponseBody
    public Object addRecord(@RequestParam Map<String,Object> params, HttpServletRequest req){
        params.put("userID",new SessionUtil().getUserCode(req));
        int res = financeInfo.addRecord(params);
        return res>0? Message.createSuccessMessage(res)
                : Message.createFailureMessage(res);
    }
}
