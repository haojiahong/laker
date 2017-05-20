package com.hao.laker.study.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Created by haojiahong on 17/5/19.
 */
public class RedissonManager {

    public static RedissonClient getRedisson() {
        // 默认连接地址 127.0.0.1:6379
        Config config = new Config();
        config.useSingleServer().setAddress("121.199.28.244:6379").setPassword("xZ7ScgiMV1Acv3h4").setDatabase(3);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }


}
