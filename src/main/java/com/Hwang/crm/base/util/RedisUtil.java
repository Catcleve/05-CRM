package com.Hwang.crm.base.util;

import cn.hutool.core.bean.BeanUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisUtil {

    public static Jedis getJedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(100);
        JedisPool jedisPool =
                new JedisPool(config, "192.168.232.129", 6379, 1000, "123456");
        return jedisPool.getResource();
    }

    //    把从数据库获取的对象数据放入redis中

    /**
     * @param t         要放入的对象集合
     * @param index     redis数据库下标
     * @param paramName 参数名字
     * @param jedis     jedis对象
     * @param <T>       对象类型
     */
    public static <T> void inRedis(List<T> t, int index, String paramName, Jedis jedis) {
        jedis.select(index);

//        集合要遍历转为map
        for (T t1 : t) {
            Map<String, Object> tMap = BeanUtil.beanToMap(t1);
            HashMap<String, String> redisMap = new HashMap<>();
            tMap.forEach((s, o) -> redisMap.put(s, o + ""));
            jedis.hmset(paramName + jedis.incr("index"), redisMap);
        }

    }
}
