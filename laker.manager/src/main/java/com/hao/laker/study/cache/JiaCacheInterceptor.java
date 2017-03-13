package com.hao.laker.study.cache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * Created by haojiahong on 17/3/6.
 */
@Slf4j
public class JiaCacheInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        Annotation[][] annotations = method.getParameterAnnotations();

        Map<String, Object> map = getParamValueByAnnotation(annotations, args, JiaCacheKey.class);

        Class<?> returnClazz = method.getReturnType();
        JiaCache jiaCache = method.getAnnotation(JiaCache.class);
        String key = jiaCache.key();
        key = initKey(key, map);

        Object objResult = null;

//        String result = "我是从缓存中获取到的值,根据key=" + key;
        String result = null;
        if (StringUtils.isBlank(result)) {
            objResult = invocation.proceed();
            String dataBaseResult = "我是从数据库中获取到的值。";
            System.out.println("将数据库中获取到的值set到缓存中");
        } else {
            objResult = null;
        }
        return objResult;
    }

    private String initKey(String key, Map<String, Object> map) {

        return key;
    }

    private Map<String, Object> getParamValueByAnnotation(Annotation[][] annotations, Object[] params, Class<?> clazz) {
        Map<String, Object> result = Maps.newHashMap();//String 做map的键值不是太好。
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (clazz.isInstance(annotations[i][j])) {
                    result.put(((JiaCacheKey) annotations[i][j]).name(), params[i]);
                    break;
                }
            }
        }
        return result;
    }


}
