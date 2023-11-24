package com.lian.pet.common.cache.redis.service;

import org.springframework.data.util.Pair;
import java.util.List;

/**
 * @Desc: Redis Service

 * @Time: 2022/1/24 15:28
 */
public interface RedisService {
    /**
     * 设置过期时间
     * @param key
     * @param ttl
     * @return
     * @throws RuntimeException
     */
    boolean expire(String key, long ttl) throws RuntimeException;

    /**
     * 获取过期时间
     * @param key
     * @return
     * @throws RuntimeException
     */
    long getExpire(String key) throws RuntimeException;

    /**
     * 有无key
     * @param key
     * @return
     * @throws RuntimeException
     */
    boolean hasKey(String key) throws RuntimeException;

    /**
     * 删除key
     * @param key
     * @return
     * @throws RuntimeException
     */
    boolean delKey(String key) throws RuntimeException;

    /**
     * 删除多个key
     * @param keys
     * @return
     * @throws RuntimeException
     */
    boolean delKeys(List<String> keys) throws RuntimeException;

    /**
     * get
     * @param key
     * @return
     * @throws RuntimeException
     */
    Object get(String key) throws RuntimeException;

    /**
     * set
     * @param key
     * @param value
     * @return
     * @throws RuntimeException
     */
    boolean set(String key, Object value) throws RuntimeException;

    /**
     * set 并设置过期时间
     * @param key
     * @param value
     * @param ttl 单位秒
     * @return
     * @throws RuntimeException
     */
    boolean set(String key, Object value, long ttl) throws RuntimeException;

    /**
     * 左插入
     * @param key
     * @param values
     * @return
     * @throws RuntimeException
     */
    long listLeftPush(String key, Object... values) throws RuntimeException;

    /**
     * 移除List中右侧key
     * @param key
     * @return
     * @throws RuntimeException
     */
    Object listRightPop(String key) throws RuntimeException;

    /**
     * TODO
     * @param keys
     * @return
     * @throws RuntimeException
     */
    List<Pair<String, String>> getList(List<String> keys) throws RuntimeException;

    /**
     * hPut
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    boolean hPut(String key, String hashKey, String value);

    /**
     * hGet
     * @param key
     * @param field
     * @return
     */
    Object hGet(String key, String field);

    /**
     * hDelete
     * @param key
     * @param fields
     * @return
     */
    long hDelete(String key, Object... fields);
}
