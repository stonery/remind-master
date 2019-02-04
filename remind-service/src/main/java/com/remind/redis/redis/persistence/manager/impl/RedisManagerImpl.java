package com.remind.redis.redis.persistence.manager.impl;


import com.remind.redis.redis.persistence.manager.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component("redisManager")
public class RedisManagerImpl implements RedisManager {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private ValueOperations<String, String> opsForValue;

	@PostConstruct
	public void initMethod() {
		this.opsForValue = redisTemplate.opsForValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#set(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean set(String key, String value) {
		try {
			opsForValue.set(key, value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#set(java.lang.String,
	 * java.lang.String, long, java.util.concurrent.TimeUnit)
	 */
	@Override
	public Boolean set(String key, String value, long timeout, TimeUnit unit) {
		try {
			opsForValue.set(key, value, timeout, unit);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#setIfAbsent(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Boolean setIfAbsent(String key, String value) {
		return opsForValue.setIfAbsent(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#multiSet(java.util.Map)
	 */
	@Override
	public Boolean multiSet(Map<? extends String, ? extends String> map) {
		try {
			opsForValue.multiSet(map);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#multiSetIfAbsent(java.util
	 * .Map)
	 */
	@Override
	public Boolean multiSetIfAbsent(Map<? extends String, ? extends String> map) {
		return opsForValue.multiSetIfAbsent(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		return opsForValue.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#getAndSet(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Object getAndSet(String key, String value) {
		return opsForValue.getAndSet(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#multiGet(java.util.
	 * Collection)
	 */
	@Override
	public List<String> multiGet(Collection<String> keys) {
		return opsForValue.multiGet(keys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#increment(java.lang.
	 * String, long)
	 */
	@Override
	public Long increment(String key, long delta) {
		return opsForValue.increment(key, delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#increment(java.lang.
	 * String, double)
	 */
	@Override
	public Double increment(String key, double delta) {
		return opsForValue.increment(key, delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#append(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Integer append(String key, String value) {
		return opsForValue.append(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#get(java.lang.String,
	 * long, long)
	 */
	@Override
	public String get(String key, long start, long end) {
		return opsForValue.get(key, start, end);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#set(java.lang.String,
	 * java.lang.String, long)
	 */
	@Override
	public Boolean set(String key, String value, long offset) {
		try {
			opsForValue.set(key, value, offset);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#size(java.lang.String)
	 */
	@Override
	public Long size(String key) {
		return opsForValue.size(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#setBit(java.lang.String,
	 * long, boolean)
	 */
	@Override
	public Boolean setBit(String key, long offset, boolean value) {
		return opsForValue.setBit(key, offset, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#getBit(java.lang.String,
	 * long)
	 */
	@Override
	public Boolean getBit(String key, long offset) {
		return opsForValue.getBit(key, offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianqu.common.persistence.manager.RedisManager#getOperations()
	 */
	@Override
	public RedisOperations<String, String> getOperations() {
		return opsForValue.getOperations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#hasKey(java.lang.String)
	 */
	@Override
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#delete(java.lang.String[])
	 */
	@Override
	public Long delete(String... key) {
		List<String> keyList = Arrays.asList(key);
		return redisTemplate.delete(keyList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianqu.common.persistence.manager.RedisManager#delete(java.lang.String)
	 */
	@Override
	public Boolean delete(String key) {
		return redisTemplate.delete(key);
	}

}
