package com.github.zrff.backend.common.service.impl;

import com.github.zrff.backend.common.dao.BaseDao;
import com.github.zrff.backend.common.service.CommonListService;
import com.github.zrff.backend.common.service.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能特点：
 * 1. 列表展示 getList
 * 2. 去指定一行记录 getRow
 * 3. 编辑或增加记录 editOrAdd
 * 4. 更改状态 changeStatus
 */
@Service
@Qualifier("commonListService")
public class CommonListServiceImpl<T> implements CommonListService {

    @Autowired
    @Qualifier("baseDao")
    private BaseDao baseDao;

    @Override
    public <T> T getList(Map<String, Object> params, String sqlNamespaceList, String sqlNamespaceListSize, ListMapper<T> mapper) {
        Map<String, Object> res = new HashMap<>();
        List<Object> list = baseDao.selectList(sqlNamespaceList,params);
        Object listSize = baseDao.selectOne(sqlNamespaceListSize,params);
        return mapper.map(list,listSize);
    }

    @Override
    public <T> T getListNoPagination(Map<String, Object> params, String sqlNamespace) {
        List<Object> obj = baseDao.selectList(sqlNamespace,params);
        return (T) obj;
    }

    @Override
    public <T> T getRow(Map<String, Object> params, String sqlNamespace) {
        return baseDao.selectOne(sqlNamespace,params);
    }

    @Override
    public int insert(Map<String, Object> params, String sqlNamespace) {
        return baseDao.insert(sqlNamespace,params);
    }

    @Override
    public int delete(Map<String, Object> params, String sqlNamespace) {
        return baseDao.delete(sqlNamespace,params);
    }

    @Override
    public int update(Map<String, Object> params, String sqlNamespace) {
        return baseDao.update(sqlNamespace,params);
    }


}
