package com.zhangtory.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: redis辅助工具
 */
public interface RedisHelper {

    /**
     * 获取value
     * @param key 键
     * @param classType 返回的对象类型
     * @return 返回value
     */
    <T> T get(String key, Class<T> classType);

    /**
     * 获取value
     * @param key 键
     * @return 返回value
     */
    String get(String key);

    /**
     * 设置value, 键、值、过期时间均不能为空
     * @param key 键
     * @param value 值
     * @param expire 过期时间，单位：秒
     */
    void set(String key, Object value, long expire);

    /**
     * 设置过期时间
     * @param key 键
     * @param expire 过期时间，单位:秒
     */
    void setExpire(String key, long expire);

    /**
     * key是否已存在
     * @param key 键
     * @return 是否存在
     */
    boolean hasKey(String key);

    /**
     * 获取整个数组
     * @param key redis键
     * @param classType 返回类型
     * @return 返回数组
     */
    <T> List<T> queryList(String key, Class<T> classType);

    /**
     * 添加元素到list中
     * @param key 键
     * @param collection 集合
     * @return 是否成功
     */
    boolean addAllToList(String key, Collection collection);

    /**
     * 添加元素到list中,并设置过期时间
     * @param key 键
     * @param collection 集合
     * @param expire 过期时间
     * @return 是否成功
     */
    boolean addAllToList(String key, Collection collection, long expire);

    /**
     * 添加元素到list中
     * @param key 键
     * @param obj 元素
     * @return 是否成功
     */
    boolean addToList(String key, Object obj);

    /**
     * 获取list集合的数量
     * @param key 键
     * @return list集合数量
     */
    long countList(String key);

    /**
     * set集合添加元素
     * @param key 键
     * @param element 要添加的元素
     * @return 是否成功
     */
    boolean addToSet(String key, Object element);

    /**
     * 添加元素到set中
     * @param key 键
     * @param collection 集合
     * @return 是否成功
     */
    boolean addAllToSet(String key, Collection collection);

    /**
     * element是否是key对应的set中的元素
     * @param key 键
     * @param element 判断的元素
     * @return 是否在set中
     */
    boolean isMemberInSet(String key, Object element);

    /**
     * 获取set集合
     * @param key 键
     * @return set集合
     */
    <T> Set<T> querySet(String key, Class<T> classType);

    /**
     * 获取set集合的数量
     * @param key 键
     * @return 数量
     */
    long countSet(String key);

    /**
     * 获取整个hashmap
     * @param key 键
     * @return hashmap
     */
    Map<Object, Object> entries(String key);

    /**
     * 添加map元素
     * @param mapKey map key
     * @param key map value key
     * @param val map value val
     * @return 是否成功
     */
    boolean addInMap(String mapKey, Object key, Object val);

    /**
     * 添加map元素，同时设置过期时间
     * @param mapKey map key
     * @param key map value key
     * @param val map value val
     * @param expire 过期秒数
     * @return 是否成功
     */
    boolean addInMap(String mapKey, Object key, Object val, long expire);

    /**
     * 获取hashmap中的指定值
     * @param key 键
     * @param hashKey hash键
     * @return hash值
     */
    Object get(String key, Object hashKey);

    /**
     * 获取hashmap中指定key的value
     * @param mapKey hashmap的key
     * @param key hashmap value的key
     * @param classType 返回的类型
     * @return 返回hashmap value的值
     */
    <T> T getHashValue(String mapKey, Object key, Class<T> classType);

    /**
     * 判断hashmap中是否有该键
     * @param mapKey hashmap的key
     * @param key hashmap value的key
     * @return 是否存在
     */
    boolean hasKey(String mapKey, Object key);

    /**
     * 根据key和member获取对应的score
     * @param key 键
     * @param member 成员
     * @return 分数
     */
    Double score(String key, Object member);

    /**
     * 减一并返回
     * @param key 键
     * @return 减一后的值
     */
    Long decrement(String key);

    /**
     * 加一并返回
     * @param key 键
     * @return 加一后的值
     */
    Long increment(String key);

    /**
     * 删除 指定的值
     * @param key 键
     * @return 是否删除成功
     */
    boolean delete(String key);

    /**
     * 删除hashmap中的一对KV
     * @param mapKey map key
     * @param key map value key
     * @return 是否删除成功
     */
    boolean deleteFromMap(String mapKey, Object key);

    /**
     * 对hashmap中的值累加
     * @param mapKey map key
     * @param key map value key
     * @param value 累加值
     * @return 累加后的值
     */
    long incrementMap(String mapKey, Object key, long value);

    /**
     * 对hashmap中的值累加1
     * @param mapKey map key
     * @param key map value key
     * @return 累加后的值
     */
    long incrementMapByOne(String mapKey, Object key);

    /**
     * 设置key和value，若已存在则返回false，设置操作失效
     * @param key 键
     * @param value 值
     * @return 是否设置成功
     */
    boolean setNx(String key, Object value);

    /**
     * 获取值的同时设置新的值
     * @param key 健
     * @param value 新的值
     * @param returnType 旧的值的类型
     * @return 旧的值
     */
    <T> T getAndSet(String key, Object value, Class<T> returnType);

    /**
     * 返回距离过期key还剩余的秒数
     * @param key cache key
     * @return 距离过期key还剩余的秒数
     */
    long ttl(String key);

}
