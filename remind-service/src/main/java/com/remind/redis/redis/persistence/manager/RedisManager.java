package com.remind.redis.redis.persistence.manager;

import org.springframework.data.redis.core.RedisOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisManager {

	/**
	 * 新增一个字符串类型的值,key是键，value是值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean set(String key, String value);

	/**
	 * 设置变量值的过期时间
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Boolean set(String key, String value, long timeout, TimeUnit unit);

	/**
	 * 如果键不存在则新增,存在则不改变已经有的值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setIfAbsent(String key, String value);

	/**
	 * 设置map集合到redis
	 * 
	 * @param map
	 * @return
	 */
	public Boolean multiSet(Map<? extends String, ? extends String> map);

	/**
	 * 如果对应的map集合名称不存在，则添加，如果存在则不做修改
	 * 
	 * @param map
	 * @return
	 */
	public Boolean multiSetIfAbsent(Map<? extends String, ? extends String> map);

	/**
	 * 获取key键对应的值
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key);

	/**
	 * 获取原来key键对应的值并重新赋新值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Object getAndSet(String key, String value);

	/**
	 * 根据集合取出对应的value值
	 * 
	 * @param keys
	 * @return
	 */
	public List<String> multiGet(Collection<String> keys);

	/**
	 * 以增量的方式将long值存储在变量中
	 * 
	 * @param key
	 * @param delta
	 * @return
	 */
	public Long increment(String key, long delta);

	/**
	 * 以增量的方式将double值存储在变量中
	 * 
	 * @param key
	 * @param delta
	 * @return
	 */
	public Double increment(String key, double delta);

	/**
	 * 在原有的值基础上新增字符串到末尾
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Integer append(String key, String value);

	/**
	 * 截取key键对应值得字符串，从开始下标位置开始到结束下标的位置(包含结束下标)的字符串
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public String get(String key, long start, long end);

	/**
	 * 覆盖从指定位置开始的值
	 * 
	 * @param key
	 * @param value
	 * @param offset
	 * @return
	 */
	public Boolean set(String key, String value, long offset);

	/**
	 * 获取指定字符串的长度
	 * 
	 * @param key
	 * @return
	 */
	public Long size(String key);

	/**
	 * key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public Boolean setBit(String key, long offset, boolean value);

	/**
	 * 判断指定的位置ASCII码的bit位是否为1
	 * 
	 * @param key
	 * @param offset
	 * @return
	 */
	public Boolean getBit(String key, long offset);

	public RedisOperations<String, String> getOperations();

	/**
	 * 判断Key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean hasKey(String key);

	/**
	 * 批量删除KEY
	 * 
	 * @param key
	 * @return
	 */
	public Long delete(String... key);

	/**
	 * 删除KEY
	 * 
	 * @param key
	 * @return
	 */
	public Boolean delete(String key);
}
