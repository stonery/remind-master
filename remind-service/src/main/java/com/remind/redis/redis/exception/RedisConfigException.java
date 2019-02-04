package com.remind.redis.redis.exception;

/**
 * Redis配置异常
 * 
 * @author zhaofeng
 * @since 2018-05-16 19:43:02
 */
public class RedisConfigException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8959451096006473819L;

	public RedisConfigException() {
		super();
	}

	public RedisConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedisConfigException(String message) {
		super(message);
	}

	public RedisConfigException(Throwable cause) {
		super(cause);
	}

}
