package com.github.zrff.backend.common.controller;

import com.github.zrff.backend.common.service.CommonListService;
import com.github.zrff.backend.common.service.ListMapper;
import com.github.zrff.backend.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonListController {
    @Autowired
    @Qualifier("commonListService")
    CommonListService commonListService;

    @Autowired
    SessionUtil sessionUtil;

    /**
     * 分页列表接口
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public Object getList(@RequestParam Map<String,Object> params, HttpServletRequest request){
        //增加用户信息
        params.put("userCode", sessionUtil.getUserCode(request));

        String sqlNamespaceList = (String) params.get("sqlNamespaceList");
        String sqlNamespaceListSize = (String) params.get("sqlNamespaceListSize");
        return commonListService.getList(params, sqlNamespaceList, sqlNamespaceListSize, (ListMapper<HashMap>) (list, listSize) -> {
            HashMap<String,Object> res = new HashMap<>();
            res.put("code",0);
            res.put("data",list);
            res.put("recordsTotal",listSize);
            res.put("recordsFiltered",listSize);
            return res;
        });
    }

    @RequestMapping("/getListNoPagination")
    @ResponseBody
    public Object getListNoPagination(@RequestParam Map<String,Object> params, HttpServletRequest request){
        //增加用户信息
        params.put("userCode", sessionUtil.getUserCode(request));
        String sqlNamespace = (String) params.get("sqlNamespace");
        Map<String,Object> res = new HashMap<>();
        try {
            List<Object> data = commonListService.getListNoPagination(params,sqlNamespace);
            res.put("data",data);
            res.put("code",0);
            res.put("recordsTotal",data.size());
            res.put("recordsFiltered",data.size());
            res.put("msg","数据获取成功！");
        }catch (Exception e){
            res.put("code",1);
            res.put("msg","数据获取失败#"+e.getMessage());
        }
        return res;
    }

    @RequestMapping("/getRow")
    @ResponseBody
    public Object getRow(@RequestParam Map<String,Object> params, HttpServletRequest request){
        //增加用户信息
        params.put("userCode", sessionUtil.getUserCode(request));

        Map<String,Object> message = new HashMap<>();
        String sqlNamespace = (String) params.get("sqlNamespace");
        Object obj = commonListService.getRow(params,sqlNamespace);
        message.put("data",obj);
        if(obj!=null){
            message.put("code",0);
            message.put("message","SUCCESS");
        }else {
            message.put("code",-1);
            message.put("message","无数据");
        }
        return message;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(@RequestParam Map<String,Object> params, HttpServletRequest request){
        //增加用户信息
        params.put("userCode", sessionUtil.getUserCode(request));

        Map<String,Object> message = new HashMap<>();
        String sqlNamespace = (String) params.get("sqlNamespace");
        int x = commonListService.insert(params,sqlNamespace);
        if(x>0){
            message.put("data",x);
            message.put("code",0);
        }else{
            message.put("data",x);
            message.put("message","操作失败！");
            message.put("code",-1);
        }

        return message;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Map<String,Object> params, HttpServletRequest request){
        //增加用户信息
        params.put("userCode", sessionUtil.getUserCode(request));

        Map<String,Object> message = new HashMap<>();
        String sqlNamespace = (String) params.get("sqlNamespace");
        int x = commonListService.delete(params,sqlNamespace);
        if(x>0){
            message.put("data",x);
            message.put("code",0);
        }else{
            message.put("data",x);
            message.put("message","操作失败！");
            message.put("code",-1);
        }
        return message;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestParam Map<String,Object> params, HttpServletRequest request){
        //增加用户信息
        params.put("userCode", sessionUtil.getUserCode(request));

        Map<String,Object> message = new HashMap<>();
        String sqlNamespace = (String) params.get("sqlNamespace");
        int x = commonListService.update(params,sqlNamespace);
        if(x>0){
            message.put("data",x);
            message.put("code",0);
        }else{
            message.put("data",x);
            message.put("message","操作失败！");
            message.put("code",-1);
        }
        return message;
    }

}