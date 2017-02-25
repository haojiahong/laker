package com.hao.laker.manager.other;

import com.hao.laker.entity.ToleranceDO;

/**
 * Created by haojiahong on 17/2/24.
 */
public interface TestToleranceManager {

    /**
     * 能够容灾此方法吗
     *
     * @param userId
     * @param json
     */
    void canToleranceException(ToleranceDO toleranceDO, Integer userId, String json);
}
