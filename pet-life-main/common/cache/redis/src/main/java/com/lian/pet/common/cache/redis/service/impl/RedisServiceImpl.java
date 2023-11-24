package com.lian.pet.common.cache.redis.service.impl;

import com.lian.pet.common.cache.redis.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Desc:

 * @Time: 2022/1/24 15:28
 */
@Slf4j
@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean expire(String key, long ttl) throws RuntimeException {
        Boolean ret = redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        if (Objects.isNull(ret)) {
            log.error("redis template expire return null!");
            throw new RuntimeException("redis template expire return null!");
        }

        return ret;
    }

    @Override
    public long getExpire(String key) throws RuntimeException {
        Long ret = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (Objects.isNull(ret)) {
            log.error("redis template get expire return null!");
            throw new RuntimeException("redis template get expire return null!");
        }

        return ret;
    }

    @Override
    public boolean hasKey(String key) throws RuntimeException {
        Boolean ret = redisTemplate.hasKey(key);
        if (Objects.isNull(ret)) {
            log.error("redis template has key return null!");
            throw new RuntimeException("redis template has key return null!");
        }
        return ret;
    }

    @Override
    public boolean delKey(String key) throws RuntimeException {
        return delKeys(Collections.singletonList(key));
    }

    @Override
    public boolean delKeys(List<String> keys) throws RuntimeException {
        Long ret = redisTemplate.delete(keys);
        if (Objects.isNull(ret)) {
            log.error("redis template del keys return null!");
            throw new RuntimeException("redis template del keys return null!");
        }

        if (ret != keys.size()) {
            // 可能部分key已删除或者已过期，这里不抛出异常
            log.warn("redis template del keys exception: some of keys may not deleted!");
            return false;
        }

        return true;
    }

    @Override
    public Object get(String key) throws RuntimeException {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) throws RuntimeException {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public boolean set(String key, Object value, long ttl) throws RuntimeException {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public long listLeftPush(String key, Object... values) throws RuntimeException {
        Long ret = redisTemplate.opsForList().leftPushAll(key, values);

        if (Objects.isNull(ret)) {
            log.error("redis template list left push keys return null!");
            throw new RuntimeException("redis template list left push keys return null!");
        }

        return ret;
    }

    @Override
    public Object listRightPop(String key) throws RuntimeException {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public List<Pair<String, String>> getList(List<String> keys) throws RuntimeException {
        // FIXME: 2021/10/29 测试下是否正确
        List<Object> results = redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (String s: keys) {
                    connection.get(s.getBytes(StandardCharsets.UTF_8));
                }
                return null;
            }
        });
        List<Pair<String, String>> lists = new ArrayList<>(results.size());
        for (int i = 0; i < results.size(); i++) {
            lists.add(Pair.of(keys.get(i), (String) results.get(i)));
        }
        return lists;
    }

    @Override
    public boolean hPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return true;
    }

    @Override
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public long hDelete(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }
}
