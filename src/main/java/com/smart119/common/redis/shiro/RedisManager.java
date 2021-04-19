package com.smart119.common.redis.shiro;

/**
 * @author smart119 1992lcg@163.com
 * @version V1.0
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 *
 */
@Data
public class RedisManager {

    /**
     * 主节点
     */
    @Value("${spring.redis.sentinel.master:#{null}}")
    private String master;


    @Value("${spring.redis.host:#{null}}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port = 6379;

    @Value("${security.shiro.globalSessionTimeout:1800}")
    private int expire = 0;

    @Value("${spring.redis.timeout:2000ms}")
    private String timeoutMs;

    @Value("${spring.redis.password:#{null}}")
    private String password;

    @Value("${spring.redis.sentinel.password:#{null}}")
    private String sentinelPassword;
    /**
     * 节点
     */
    @Value("${spring.redis.sentinel.nodes:#{null}}")
    private String nodes;

    private static JedisPool jedisPool;

    private static JedisSentinelPool sentinelPool;
    /**
     * 是否是哨兵模式
     */
    private static boolean sentinel = false;

    /**
     * 初始化方法
     */
    public void init() {

        int timeout = Integer.valueOf(timeoutMs.replace("ms", ""));

        if (StringUtils.isNotBlank(master)) {
            //哨兵模式
            Set<String> hostSet = new HashSet<>();
            List<String> strings = Arrays.asList(nodes.split(","));
            hostSet.addAll(strings);
            GenericObjectPoolConfig config = new GenericObjectPoolConfig();

            if (StringUtils.isNotBlank(sentinelPassword)) {
                sentinelPool = new JedisSentinelPool(master, hostSet, config, timeout,
                    sentinelPassword);
            } else {
                sentinelPool = new JedisSentinelPool(master, hostSet, config, timeout);
            }
            sentinel = true;
        } else {
            //单点模式
            if (Objects.isNull(jedisPool)) {

                JedisPoolConfig config = new JedisPoolConfig();
                if (StringUtils.isNotBlank(password)) {
                    jedisPool = new JedisPool(config, host, port, timeout, password);
                } else {
                    jedisPool = new JedisPool(config, host, port, timeout);
                }
            }
        }
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = this.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }
    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = this.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }
    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String set(String key, String value, int expire) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return key;
    }

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = this.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * del
     *
     * @param key
     */
    public void del(String key) {
        Jedis jedis = this.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = this.getResource();
        try {
            jedis.flushDB();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = this.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param regex
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = this.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }

    /**
     * 获取Jedis对象
     * @return
     */
    public Jedis getResource() {

        Jedis result;
        if (sentinel) {
            result = sentinelPool.getResource();
        } else {
            result = jedisPool.getResource();
        }
        return result;
    }
    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exist(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.exists(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    /**
     * 设置值并设置过期时间
     */
    public String setex(String key, int seconds, String value) {
        Jedis jedis = this.getResource();
        try {
            return jedis.setex(key, seconds, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 给key数自动+1
     * @param key
     * @return
     */
    public Long incr(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.incr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
