package com.hao.laker.study.cache;

import org.springframework.stereotype.Component;

/**
 * Created by haojiahong on 17/3/6.
 */
@Component
public class JiaCacheManager {

    @JiaCache(key = "JIA_CACHE_{actId}_{cityId}")
    public JiaCacheData getDataFromCache(@JiaCacheKey(name = "actId") Integer actId,
                                         @JiaCacheKey(name = "cityId") Integer cityId) {
        JiaCacheData data = new JiaCacheData();
        System.out.println("我是从数据库中拿数据的方法");
        return data;
    }
}
