package com.hao.laker.interceptor;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.hao.laker.bo.ToleranceParameterBO;
import com.hao.laker.common.util.JsonUtil;
import com.hao.laker.entity.ToleranceDO;
import com.hao.laker.manager.tolerance.ToleranceManager;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by YangFalcom on 16/8/3.
 */
@Slf4j
public class ToleranceInterceptor implements MethodInterceptor {

    @Autowired
    private ToleranceManager toleranceManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            Object obj = invocation.proceed();
            handleToleranceSuccess(invocation);
            return obj;
        } catch (Exception e) {
            handleToleranceFail(invocation);
            return null;
        }
    }

    private void handleToleranceSuccess(MethodInvocation invocation) {
        Object[] args = invocation.getArguments();
        toleranceManager.handleToleranceSuccess(args[0] instanceof ToleranceDO ? (ToleranceDO) args[0] : null);
    }

    private void handleToleranceFail(MethodInvocation invocation) {
        Method method = invocation.getMethod();

        String className = method.getDeclaringClass().getSimpleName();
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        String methodName = method.getName();

        log.error("容灾拦截器处理调用失败。class:{}, method:{}, args:{}", className, methodName, JSONArray.toJSONString(invocation.getArguments()));

        Class<?>[] classes = method.getParameterTypes();
        Object[] args = invocation.getArguments();

        List<ToleranceParameterBO> list = Lists.newArrayList();
        for (int i = 0; i < classes.length; i++) {
            if ("com.hao.laker.entity.ToleranceDO".equals(classes[i].getName())) {
                continue;
            }

            Class<?> cls = classes[i];
            Object arg = args[i];

            ToleranceParameterBO paramBO = new ToleranceParameterBO();
            paramBO.setType(cls.getName());
            paramBO.setValue(arg);
            list.add(paramBO);
        }

        toleranceManager.handleToleranceFail(args[0] instanceof ToleranceDO ? (ToleranceDO) args[0] : null,
                className, methodName, JsonUtil.list2JsonStr(list));
    }
}
