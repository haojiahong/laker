package com.hao.laker.manager.other;

import com.alibaba.fastjson.JSON;
import com.hao.laker.entity.ToleranceDO;
import com.hao.laker.interceptor.Tolerance;
import org.springframework.stereotype.Component;

/**
 * Created by haojiahong on 17/2/24.
 */
@Component("testToleranceManager")
public class TestToleranceManagerImpl implements TestToleranceManager {

    @Override
    @Tolerance
    public void canToleranceException(ToleranceDO toleranceDO, Integer userId, String json) {
        json = JSON.toJSONString(json);
        if (userId == 2565) {
            throw new RuntimeException("2565员工报错了");
        }
        System.out.println("成功运行，未发生异常：" + json);
    }
}
