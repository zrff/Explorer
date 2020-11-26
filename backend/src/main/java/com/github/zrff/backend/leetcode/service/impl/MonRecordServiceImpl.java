package com.github.zrff.backend.leetcode.service.impl;

import com.github.zrff.backend.common.dao.BaseDao;
import com.github.zrff.backend.leetcode.model.Record;
import com.github.zrff.backend.leetcode.service.MonRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("monRecordServiceImpl")
public class MonRecordServiceImpl implements MonRecordService {

    @Autowired
    @Qualifier("baseDao")
    private BaseDao baseDao;

    String baseNamespace = MonRecordServiceImpl.class.getName() + ".";

    @Override
    public int addRecord(Map<String, Object> params) {
        String sqlNS = baseNamespace + "addRecord";
        int x = baseDao.insert(sqlNS,params);
        return x;
    }

    @Override
    public Map getMonRecord(Map<String, Object> params) {
        // 获取总数
        String sqlNS1 = baseNamespace + "getSum";
        int sum = baseDao.selectOne(sqlNS1,params);
        // 获取每月数
        String sqlNS2 = baseNamespace + "getList";
        List<Record> records = baseDao.selectList(sqlNS2,params);
        // 获取本月数
        int thisMon = records.get(records.size()-1).getNum();
        // 计算同比
        List<String> dates = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        List<Double> rates = new ArrayList<>();
        for(int i=0;i<records.size();i++){
            Record record = records.get(i);
            dates.add(record.getMon());
            nums.add(record.getNum());

            double rate = 0;
            if(i > 0){
                int x1 = nums.get(i-1), x2 = nums.get(i);
                if(x1!=0){
                    rate = (x2-x1)*100/x1; // bug: (x2-x1)/x1*100
                }else rate = x2!=0?x2:0;
            }
            rates.add(rate);
        }
        // 计算本月的同比
        double thisRate = rates.get(rates.size()-1);

        // 构造数据
        Map<String,Object> data = new HashMap<>();
        data.put("sum",sum);
        data.put("thisMon",thisMon);
        data.put("thisRate",thisRate);
        data.put("dates",dates);
        data.put("nums",nums);
        data.put("rates",rates);

        return data;
    }
}
