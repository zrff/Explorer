package com.github.zrff.backend.common.dao;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by huqian on 2018/1/25.
 */
public interface BaseDao{
    /**
     * Retrieve a single row mapped from the statement key
     * @param <T> the returned object type
     * @param statement
     * @return Mapped object
     */
    <T> T selectOne(String statement);

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * @param <T> the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement);

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * Retrieve a list of mapped objects from the statement key and parameter,
     * within the specified row bounds.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds  Bounds to limit object retrieval
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * Eg. Return a of Map[Integer,Author] for selectMap("selectAuthors","id")
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param mapKey The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    <K, V> Map<K, V> selectMap(String statement, String mapKey);

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey The property to use as key for each value in the list.
     * @param rowBounds  Bounds to limit object retrieval
     * @return Map containing key pair data.
     */
    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);

    /**
     * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
     * @param <T> the returned cursor element type.
     * @param statement Unique identifier matching the statement to use.
     * @return Cursor of mapped objects
     */
    <T> Cursor<T> selectCursor(String statement);

    /**
     * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
     * @param <T> the returned cursor element type.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Cursor of mapped objects
     */
    <T> Cursor<T> selectCursor(String statement, Object parameter);

    /**
     * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
     * @param <T> the returned cursor element type.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds  Bounds to limit object retrieval
     * @return Cursor of mapped objects
     */
    <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds);

    /**
     * Retrieve a single row mapped from the statement key and parameter
     * using a {@code ResultHandler}.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param handler ResultHandler that will handle each retrieved row
     */
    void select(String statement, Object parameter, ResultHandler handler);

    /**
     * Retrieve a single row mapped from the statement
     * using a {@code ResultHandler}.
     * @param statement Unique identifier matching the statement to use.
     * @param handler ResultHandler that will handle each retrieved row
     */
    void select(String statement, ResultHandler handler);

    /**
     * Retrieve a single row mapped from the statement key and parameter
     * using a {@code ResultHandler} and {@code RowBounds}
     * @param statement Unique identifier matching the statement to use.
     * @param rowBounds RowBound instance to limit the query results
     * @param handler ResultHandler that will handle each retrieved row
     */
    void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler);

    /**
     * Execute an insert statement.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the insert.
     */
    int insert(String statement);

    /**
     * Execute an insert statement with the given parameter object. Any generated
     * autoincrement values or selectKey entries will modify the given parameter
     * object properties. Only the number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    int insert(String statement, Object parameter);

    /**
     * Execute an update statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the update.
     */
    int update(String statement);

    /**
     * Execute an update statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    int update(String statement, Object parameter);

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the delete.
     */
    int delete(String statement);

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete.
     */
    int delete(String statement, Object parameter);
}