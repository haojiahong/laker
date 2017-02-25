package com.hao.laker.manager.tolerance;

import com.hao.laker.entity.ToleranceDO;

/**
 * 分布式方法容灾处理接口
 * Created by haojiahong on 17/2/24.
 */
public interface ToleranceManager {

    void handleToleranceSuccess(ToleranceDO tolerance);

    void handleToleranceFail(ToleranceDO toleranceDO, String className, String methodName, String s);
}
