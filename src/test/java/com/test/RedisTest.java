package com.test;

import com.Hwang.crm.base.util.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RedisTest {

    @Test
    public void test01(){

        Jedis jedis = RedisUtil.getJedis();
        jedis.select(0);
        jedis.set("name", "xiaolulu");
        String age = jedis.get("age");
        System.out.println("age = " + age);

    }

    @Test
    public void test02(){

        List<User> users = new ArrayList<>();
        Random random = new Random();

        String name;

        String password;
        for (int i = 0; i < 10; i++) {
            int i1 = random.nextInt(10000) + 20000;
            int i2 = random.nextInt(10000) + 20000;
            int i3 = random.nextInt(99999) + 99999;
            password = String.valueOf(i3);
            name = (char) i1 + "" + (char) i2;
            User user = new User();
            user.setName(name);
            user.setLoginPwd(password);
            users.add(user);
        }
        Jedis jedis = RedisUtil.getJedis();
        RedisUtil.inRedis(users,5,"user:",jedis);
    }
}
