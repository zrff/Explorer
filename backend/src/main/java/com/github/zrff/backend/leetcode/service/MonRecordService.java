package com.github.zrff.backend.leetcode.service;

import java.util.Map;

public interface MonRecordService {
    int addRecord(Map<String,Object> params);
    Map getMonRecord(Map<String,Object> params);
}
