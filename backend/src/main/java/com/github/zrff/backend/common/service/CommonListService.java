package com.github.zrff.backend.common.service;

import java.util.Map;

public interface CommonListService {
    //获取分页列表内容
    <T> T  getList(Map<String,Object> params,String sqlNamespaceList,String sqlNamespaceListSize,ListMapper<T> mapper);
    //获取不分页列表内容
    <T> T getListNoPagination(Map<String, Object> params, String sqlNamespace);
    //获取指定一条记录
    <T> T  getRow(Map<String,Object> params,String sqlNamespace);

    int insert(Map<String,Object> params,String sqlNamespace);
    int delete(Map<String,Object> params,String sqlNamespace);
    int update(Map<String,Object> params,String sqlNamespace);

}
