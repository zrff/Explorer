package com.github.zrff.backend.lab.service.impl;

import com.github.zrff.backend.common.dao.BaseDao;
import com.github.zrff.backend.lab.model.BasicInfo;
import com.github.zrff.backend.lab.model.FinanceRecord;
import com.github.zrff.backend.lab.service.FinanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("financeInfoImpl")
public class FinanceInfoImpl implements FinanceInfo {
    @Autowired
    @Qualifier("baseDao")
    private BaseDao baseDao;

    String baseNamespace = FinanceInfoImpl.class.getName() + ".";

    @Override
    public BasicInfo getBasic(Map<String, Object> params){
        String sqlNS = baseNamespace + "getBasic";
        return baseDao.selectOne(sqlNS,params);
    }

    @Override
    public List<FinanceRecord> getList(Map<String, Object> params){
        String sqlNS = baseNamespace + "getList";
        List<FinanceRecord> records = baseDao.selectList(sqlNS,params);
        return records!=null?records:new ArrayList<>();
    }

    @Override
    public int addRecord(Map<String, Object> params) {
        String sqlNS = baseNamespace + "addRecord";
        return baseDao.insert(sqlNS,params);
    }

}
