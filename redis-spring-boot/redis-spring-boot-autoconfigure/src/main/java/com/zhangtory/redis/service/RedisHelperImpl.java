package com.zhangtory.redis.service;

import com.alibaba.fastjson.JSON;
import com.zhangtory.core.exception.RedisException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.zhangtory.redis.contant.RedisTimeConstant.DEFAULT_TIMEOUT;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: redis辅助工具
 */
public class RedisHelperImpl implements RedisHelper {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisHelperImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String REDIS_KEY_NULL_ERROR = "redis_key_is_null";

    private static final String REDIS_KEY_OR_VALUE_NULL_ERROR = "redis_key_or_value_is_null";

    /**
     * 获取value
     */
    @Override
    public <T> T get(String key, Class<T> classType) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : JSON.parseObject(value.toString(), classType);
    }

    @Override
    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : value.toString();
    }

    /**
     * 设置value, 键、值、过期时间均不能为空
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间，单位：秒
     */
    @Override
    public void set(String key, Object value, long expire) {
        if (StringUtils.isBlank(key) || value == null) {
            throw new RedisException(REDIS_KEY_OR_VALUE_NULL_ERROR);
        }
        if (expire <= 0) {
            expire = DEFAULT_TIMEOUT;
        }
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value), expire + getRandomSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public void setExpire(String key, long expire) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        redisTemplate.expire(key, expire + getRandomSeconds(), TimeUnit.SECONDS);
    }

    /**
     * key是否已存在
     *
     * @param key 键
     * @return 是否存在
     */
    @Override
    public boolean hasKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }

    /**
     * 获取整个数组
     *
     * @param key       redis键
     * @param classType 返回类型
     * @return 返回数组
     */
    @Override
    public <T> List<T> queryList(String key, Class<T> classType) {
        List<T> result = new ArrayList<>();
        List<Object> objs = redisTemplate.opsForList().range(key, 0, -1);
        if (CollectionUtils.isEmpty(objs)) {
            return result;
        }
        for (Object obj : objs) {
            result.add(JSON.parseObject(obj.toString(), classType));
        }
        return result;
    }

    /**
     * 添加元素到list中
     *
     * @param key        键
     * @param collection 集合
     * @return 是否成功
     */
    @Override
    public boolean addAllToList(String key, Collection collection) {
        if (StringUtils.isBlank(key) || collection == null) {
            throw new RedisException(REDIS_KEY_OR_VALUE_NULL_ERROR);
        }
        for (Object obj : collection) {
            redisTemplate.opsForList().rightPush(key, JSON.toJSONString(obj));
        }
        return true;
    }

    @Override
    public boolean addAllToList(String key, Collection collection, long expire) {
        if (StringUtils.isBlank(key) || collection == null) {
            throw new RedisException(REDIS_KEY_OR_VALUE_NULL_ERROR);
        }
        if (expire <= 0) {
            expire = DEFAULT_TIMEOUT;
        }
        for (Object obj : collection) {
            redisTemplate.opsForList().rightPush(key, JSON.toJSONString(obj));
        }
        redisTemplate.expire(key, expire + getRandomSeconds(), TimeUnit.SECONDS);
        return true;
    }

    @Override
    public boolean addToList(String key, Object obj) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        Long result = redisTemplate.opsForList().rightPush(key, JSON.toJSONString(obj));
        return result != null && result > 0;
    }

    @Override
    public long countList(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        Long size = redisTemplate.opsForList().size(key);
        return size == null ? 0 : size;
    }

    /**
     * set集合添加元素
     *
     * @param key     键
     * @param element 要添加的元素
     * @return 是否成功
     */
    @Override
    public boolean addToSet(String key, Object element) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        Long result = redisTemplate.opsForSet().add(key, JSON.toJSONString(element));
        return result != null && result > 0;
    }

    @Override
    public boolean addAllToSet(String key, Collection collection) {
        if (StringUtils.isBlank(key) || collection == null) {
            throw new RedisException(REDIS_KEY_OR_VALUE_NULL_ERROR);
        }
        for (Object obj : collection) {
            redisTemplate.opsForSet().add(key, JSON.toJSONString(obj));
        }
        return true;
    }

    @Override
    public long countSet(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        Long size = redisTemplate.opsForSet().size(key);
        return size == null ? 0 : size;
    }

    /**
     * element是否是key对应的set中的元素
     *
     * @param key     键
     * @param element 判断的元素
     * @return 是否在set中
     */
    @Override
    public boolean isMemberInSet(String key, Object element) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Boolean isMember = redisTemplate.opsForSet().isMember(key, element);
        return isMember != null && isMember;
    }

    @Override
    public <T> Set<T> querySet(String key, Class<T> classType) {
        Set<T> result = new HashSet<>();
        if (StringUtils.isBlank(key)) {
            return result;
        }
        Set<Object> members = redisTemplate.opsForSet().members(key);
        if (CollectionUtils.isEmpty(members)) {
            return result;
        }
        for (Object member : members) {
            result.add(JSON.parseObject(member.toString(), classType));
        }
        return result;
    }

    /**
     * 获取整个hashmap
     *
     * @param key 键
     * @return hashmap
     */
    @Override
    public Map<Object, Object> entries(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public boolean addInMap(String mapKey, Object key, Object val) {
        if (StringUtils.isBlank(mapKey)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        redisTemplate.opsForHash().put(mapKey, key, JSON.toJSONString(val));
        return true;
    }

    @Override
    public boolean addInMap(String mapKey, Object key, Object val, long expire) {
        boolean isAddMapSuccess = addInMap(mapKey, key, val);
        Boolean isExpireSuccess = false;
        if (isAddMapSuccess) {
            isExpireSuccess = redisTemplate.expire(mapKey, expire + getRandomSeconds(), TimeUnit.SECONDS);
            if (isExpireSuccess == null) {
                isExpireSuccess = false;
            }
        }
        return isAddMapSuccess && isExpireSuccess;
    }

    /**
     * 获取hashmap中的指定值
     *
     * @param key     键
     * @param hashKey hash键
     * @return hash值
     */
    @Override
    public Object get(String key, Object hashKey) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public <T> T getHashValue(String mapKey, Object key, Class<T> classType) {
        if (StringUtils.isBlank(mapKey)) {
            return null;
        }
        Object obj = redisTemplate.opsForHash().get(mapKey, key);
        return obj == null ? null : JSON.parseObject(obj.toString(), classType);
    }

    @Override
    public boolean hasKey(String mapKey, Object key) {
        return redisTemplate.opsForHash().hasKey(mapKey, key);
    }

    /**
     * 根据key和member获取对应的score
     *
     * @param key    键
     * @param member 成员
     * @return 分数
     */
    @Override
    public Double score(String key, Object member) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return redisTemplate.opsForZSet().score(key, member);
    }

    /**
     * 减一并返回
     *
     * @param key 键
     * @return 减一后的值
     */
    @Override
    public Long decrement(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        return redisTemplate.opsForValue().decrement(key);
    }

    @Override
    public Long increment(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 删除 指定的值
     *
     * @param key 键
     * @return 是否删除成功
     */
    @Override
    public boolean delete(String key) {
        Boolean success = redisTemplate.delete(key);
        return success != null && success;
    }

    @Override
    public boolean deleteFromMap(String mapKey, Object key) {
        if (StringUtils.isBlank(mapKey)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        Long count = redisTemplate.opsForHash().delete(mapKey, key);
        return count > 0;
    }

    @Override
    public long incrementMap(String mapKey, Object key, long value) {
        return redisTemplate.opsForHash().increment(mapKey, key, value);
    }

    @Override
    public long incrementMapByOne(String mapKey, Object key) {
        return incrementMap(mapKey, key, 1);
    }

    @Override
    public boolean setNx(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null) {
            throw new RedisException(REDIS_KEY_OR_VALUE_NULL_ERROR);
        }
        Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, JSON.toJSONString(value));
        return isSuccess != null && isSuccess;
    }

    @Override
    public <T> T getAndSet(String key, Object value, Class<T> returnType) {
        Object oldVal = redisTemplate.opsForValue().getAndSet(key, JSON.toJSONString(value));
        return oldVal == null ? null : JSON.parseObject(oldVal.toString(), returnType);
    }

    @Override
    public long ttl(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RedisException(REDIS_KEY_NULL_ERROR);
        }
        Long ttl = redisTemplate.getExpire(key);
        return ttl == null ? 0 : ttl;
    }

    /**
     * 获取随机时间，防止缓存雪崩
     * 随机10秒到180秒
     * @return
     */
    private long getRandomSeconds() {
        return RandomUtils.nextLong(10, 180);
    }

}
