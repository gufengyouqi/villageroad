package com.villageroad.storage.redis;

import jakarta.annotation.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    public Optional<String> get(String key) {
        return Optional.ofNullable(stringRedisTemplate.opsForValue().get(key));
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void increment(String key, long value) {
        stringRedisTemplate.opsForValue().increment(key, value);
    }

    public void set(String key, String value, long ttl) {
        stringRedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    public void setEx(String key, String value, long ttl) {
        stringRedisTemplate.opsForValue().setIfPresent(key, value, ttl, TimeUnit.SECONDS);
    }

    public void setNx(String key, String value, long ttl) {
        stringRedisTemplate.opsForValue().setIfAbsent(key, value, ttl, TimeUnit.SECONDS);
    }

    public void expire(String key, long ttl) {
        stringRedisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    public List<String> lRange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    public Long lPush(String key, String... values) {
        return stringRedisTemplate.opsForList().leftPushAll(key, values);
    }

    public Long rPush(String key, String... values) {
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }

    public String lPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    public String rPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    public Long lLen(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    public Long sAdd(String key, String... values) {
        return stringRedisTemplate.opsForSet().add(key, values);
    }

    public List<String> sPop(String key, long count) {
        return stringRedisTemplate.opsForSet().pop(key, count);
    }

    public Long sRem(String key, String... values) {
        Object[] objects = Arrays.stream(values).toArray();
        return stringRedisTemplate.opsForSet().remove(key, objects);
    }

    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    public void expireAt(String key, Date date) {
        stringRedisTemplate.expireAt(key, date);
    }

    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public RedisConnectionFactory getConnection() {
        return redisTemplate.getConnectionFactory();
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public void put(String key, Object mapKey, Object mapValue) {
        stringRedisTemplate.opsForHash().put(key, mapKey, mapValue);
    }

    public void deleteMap(String key, Object mapKey) {
        stringRedisTemplate.opsForHash().delete(key, mapKey);
    }

    public Optional<Object> getMap(String key, Object mapKey) {
        return Optional.ofNullable(stringRedisTemplate.opsForHash().get(key, mapKey));
    }

    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }


}
