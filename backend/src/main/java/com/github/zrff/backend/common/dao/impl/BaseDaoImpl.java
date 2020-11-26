package com.github.zrff.backend.common.dao.impl;

import com.github.zrff.backend.common.dao.BaseDao;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by huqian on 2018/1/25.
 */
@Repository
@Qualifier("baseDao")
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public BaseDao setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        return this;
    }

    @Override
    public <T> T selectOne(String statement) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectOne(statement);
        }finally {
            session.close();
        }
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectOne(statement,parameter);
        }finally {
            session.close();
        }
    }

    @Override
    public <E> List<E> selectList(String statement) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectList(statement);
        }finally {
            session.close();
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectList(statement,parameter);
        }finally {
            session.close();
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectList(statement,parameter,rowBounds);
        }finally {
            session.close();
        }
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectMap(statement,mapKey);
        }finally {
            session.close();
        }
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectMap(statement,parameter,mapKey);
        }finally {
            session.close();
        }
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectMap(statement,parameter,mapKey,rowBounds);
        }finally {
            session.close();
        }
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectCursor(statement);
        }finally {
            session.close();
        }
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectCursor(statement,parameter);
        }finally {
            session.close();
        }
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            return session.selectCursor(statement,parameter,rowBounds);
        }finally {
            session.close();
        }
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        SqlSession session = sqlSessionFactory.openSession();
        session.select(statement,parameter,handler);
        session.close();
    }

    @Override
    public void select(String statement, ResultHandler handler) {
        SqlSession session = sqlSessionFactory.openSession();
        session.select(statement,handler);
        session.close();
    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        SqlSession session = sqlSessionFactory.openSession();
        session.select(statement,parameter,rowBounds,handler);
        session.close();
    }

    @Override
    public int insert(String statement) {
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            return session.insert(statement);
        }finally {
            session.close();
        }
    }

    @Override
    public int insert(String statement, Object parameter) {
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            return session.insert(statement,parameter);
        }finally {
            session.close();
        }
    }

    @Override
    public int update(String statement) {
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            return session.update(statement);
        }finally {
            session.close();
        }
    }

    @Override
    public int update(String statement, Object parameter) {
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            return session.update(statement,parameter);
        }finally {
            session.close();
        }
    }

    @Override
    public int delete(String statement) {
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            return session.delete(statement);
        }finally {
            session.close();
        }
    }

    @Override
    public int delete(String statement, Object parameter) {
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();
            return session.delete(statement,parameter);
        }finally {
            session.close();
        }
    }

}