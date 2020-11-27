package com.github.zrff.backend.lab.service;

import com.github.zrff.backend.lab.model.BasicInfo;
import com.github.zrff.backend.lab.model.FinanceRecord;

import java.util.List;
import java.util.Map;

public interface FinanceInfo {
    BasicInfo getBasic(Map<String,Object> params);
    List<FinanceRecord> getList(Map<String,Object> params);
    int addRecord(Map<String,Object> params);
}
