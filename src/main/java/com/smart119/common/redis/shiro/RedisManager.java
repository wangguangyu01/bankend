package com.smart119.common.redis.shiro;

/**
 * @author smart119 1992lcg@163.com
 * @version V1.0
 */

import java.util.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key, value);
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
     * @param pattern
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



    /**
     * 向redis的set集合添加元素
     * @param key
     *  @param value
     * @return
     */
    public boolean setAddElement(String key, String value) {
        Jedis jedis = this.getResource();
        try {
            long scardNew  = jedis.sadd(key, value);
            if (scardNew > 0 ) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }


    /**
     * 向redis的set集合添加元素
     * @param key
     * @param valueList
     * @return
     */
    public boolean setAddElement(String key, List<String> valueList) {
        Jedis jedis = this.getResource();
        try {
            int count = 0;
            for (String value: valueList) {
                jedis.sadd(key, value);
                count ++;
            }
            if (count == valueList.size() ) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }



    /**
     * 向redis的set集合移除元素
     * @param key
     * @param value
     * @return
     */
    public boolean setRemoveElement(String key, String value) {
        Jedis jedis = this.getResource();
        try {
            long scardNew = 0;
            if (!jedis.exists(key)) {
                return true;
            } else {
                long scard = jedis.scard(key);
                if (scard > 0) {
                    scardNew = jedis.srem(key, value);
                    if (scard > scardNew) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }


    /**
     * 根据key键获取所有的set集合中的元素
     * @param key
     * @return
     */
    public Set<String> getSetAllElement(String key) {
        Jedis jedis = this.getResource();
        Set<String> set = new HashSet<>();
        try {
            long scardNew = 0;
            if (!jedis.exists(key)) {
                return  set;
            } else {
                set = jedis.smembers(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return set;
    }


    /**
     * 设置hash值
     * @param key
     * @param hash
     * @return
     */
    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hmset(key, hash);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }



    /**
     * 操作hash结构保存单个key
     */
    public Long hSet(String key, String field, String value) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hset(key, field, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 删除hashMap的字段
     * @param key
     * @param fields
     * @return
     */
    public Long hdel(String key, String... fields) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hdel(key, fields);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 获取map里面的数据所有值
     * @param key
     * @return
     */
    public Map<String,String> hgetAll(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hgetAll(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key和field获取map里面的value值
     * @param key
     * @return
     */
    public String hget(String key, String field) {
        Jedis jedis = this.getResource();
        try {
            return jedis.hget(key,field);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public Long lpush(String key, String value) {
        Jedis jedis = this.getResource();
        try {
           return jedis.lpush(key, value);

        } catch (Exception e) {
            log.info("redis lpush插入失败，{}", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }


    /**
     * 移除列表元素
     * @param key
     * @param count
     *        count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     *        count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     *        count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param value
     * @return
     */
    public Long lrem(String key, long count, String value) {
        Jedis jedis = this.getResource();
        try {
            return jedis.lrem(key, count, value);

        } catch (Exception e) {
            log.info("redis lrem插入失败，{}", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }


    /**
     * 查询列表指定的开始位置到结束位置的元素
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public List<String> lrange(String key, long start,  long stop) {
        Jedis jedis = this.getResource();
        try {
            return jedis.lrange(key, start, stop);

        } catch (Exception e) {
            log.info("redis lrange插入失败，{}", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }


    /**
     * 查询列表所有元素
     * @param key
     * @return
     */
    public List<String> lrange(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.lrange(key, 0, -1);

        } catch (Exception e) {
            log.info("redis lrange插入失败，{}", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }


}
