package com.hao.laker.study.proxyaop;

import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by haojiahong on 17/3/7.
 */
public class JiaCglibProxyTest {

    public static void main(String[] args) {
        Programmer programmer = new Programmer();
        Hacker hacker = new Hacker();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(programmer.getClass());
        enhancer.setCallback(hacker);
        Programmer proxy = (Programmer) enhancer.create();
        proxy.code();
        System.out.println(proxy.getClass());
//        ProxyUtils.generateClassFile(proxy.getClass(), "ProgrammerProxy");

    }

}
