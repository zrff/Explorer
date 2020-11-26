package com.github.zrff.backend.test.controller;

import com.github.zrff.backend.common.dao.BaseDao;
import com.github.zrff.backend.common.service.CommonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    CommonListService service;

    @RequestMapping("/test")
    public String foo(@RequestParam Map<String,Object> params){
        String sqlNS = "com.github.zrff.backend.test.controller.TestController.test";
        int x = service.getRow(params,sqlNS);
        return "hello - " + x;
    }

}
