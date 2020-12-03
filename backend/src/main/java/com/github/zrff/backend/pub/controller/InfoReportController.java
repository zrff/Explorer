package com.github.zrff.backend.pub.controller;

import com.github.zrff.backend.common.controller.CommonListController;
import com.github.zrff.backend.common.model.Message;
import com.github.zrff.backend.common.service.CommonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/pub/reportInfo")
public class InfoReportController extends CommonListController {

    String sqlBase = InfoReportController.class.getName() + ".";

    @Autowired
    @Qualifier("commonListService")
    CommonListService commonListService;

    @RequestMapping("/report")
    public Object report(@RequestParam Map<String,Object> params, HttpServletRequest req){
        String sqlNS = sqlBase + "count";
        int num = commonListService.getRow(params,sqlNS);

        int res = 0;
        if(num>0){
            sqlNS = sqlBase + "update";
            res = commonListService.update(params,sqlNS);
        }else{
            sqlNS = sqlBase + "add";
            res = commonListService.insert(params,sqlNS);
        }

        return Message.createSuccessMessage(res);
    }

    @RequestMapping("/summary")
    public Object summary(@RequestParam Map<String,Object> params, HttpServletRequest req){
        String sqlNamespace = sqlBase + "summary";
        params.put("sqlNamespace",sqlNamespace);
        Object res = commonListService.getListNoPagination(params,sqlNamespace);
        if(res==null) res = new ArrayList<>();
        return Message.createSuccessMessage(res);
    }
}
