package com.hao.laker.study.proxyaop;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by haojiahong on 17/3/7.
 */
public class Hacker implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("i am a hacker,before");
        methodProxy.invokeSuper(o, objects);
        System.out.println("i am a hacker,after");
        return null;
    }
}
