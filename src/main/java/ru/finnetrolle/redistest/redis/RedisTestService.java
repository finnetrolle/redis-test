package ru.finnetrolle.redistest.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisTestService {

    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    public void save(String key, String value) {
        listOps.leftPush(key, value);
    }

    public String load(String key) {
        return listOps.leftPop(key);
    }

}
