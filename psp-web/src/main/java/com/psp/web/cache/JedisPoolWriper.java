package com.psp.web.cache;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWriper {
	private static Logger logger = Logger.getLogger(JedisPoolWriper.class);
	private static JedisPool jedisPool = null;

	public static JedisPool getJedisPool() {
		return jedisPool;
	}

	public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host, final int port, final int timeout,
			final String password) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
			logger.info("jedisPool:" + jedisPool);
		} catch (Exception e) {
			logger.error("Jedis Pool init failed:", e);
		}
	}
}
