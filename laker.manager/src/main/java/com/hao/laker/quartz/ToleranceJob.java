package com.hao.laker.quartz;

import com.alibaba.fastjson.JSON;
import com.hao.laker.bizenum.ToleranceStatusEnum;
import com.hao.laker.bo.ToleranceParameterBO;
import com.hao.laker.common.util.JsonUtil;
import com.hao.laker.common.util.SpringIocUtil;
import com.hao.laker.dao.ToleranceMapper;
import com.hao.laker.entity.ToleranceDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by haojiahong on 17/2/25.
 */
@Slf4j
@Service
public class ToleranceJob extends Job {

    @Autowired
    private ToleranceMapper toleranceMapper;

    @Override
    public void doBusiness() {
        List<ToleranceDO> toleranceDOList = toleranceMapper.selectByStatus(ToleranceStatusEnum.READY.getCode());
        if (CollectionUtils.isEmpty(toleranceDOList)) {
            return;
        }
        for (ToleranceDO toleranceDO : toleranceDOList) {
            this.handleTolerance(toleranceDO);
        }

    }

    private void handleTolerance(ToleranceDO toleranceDO) {
        log.info("开始容灾处理任务 toleranceDO={}", JSON.toJSON(toleranceDO));
        try {
            String className = toleranceDO.getToleranceClass();
            String methodName = toleranceDO.getToleranceMethod();
            String request = toleranceDO.getToleranceRequest();

            List<ToleranceParameterBO> list = JsonUtil.jsonStr2List(request, ToleranceParameterBO.class);
            if (StringUtils.isBlank(className) || StringUtils.isBlank(methodName)) {
                return;
            }

            Object bean = SpringIocUtil.getBean(className);
            if (bean == null) {
                log.error("容灾任务处理时未找到bean className={}", className);
                return;
            }
            Class<?>[] classes = new Class[list.size() + 1];
            Object[] params = new Object[list.size() + 1];

            classes[0] = ToleranceDO.class;
            params[0] = toleranceDO;

            int index = 1;
            for (ToleranceParameterBO paramBO : list) {
                classes[index] = Class.forName(paramBO.getType());
                params[index] = paramBO.getValue();
                index++;
            }
            Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, classes);
            ReflectionUtils.invokeMethod(method, bean, params);
        } catch (Exception e) {
            log.error("容灾任务处理发生异常", e);
        }

    }
}
