package com.hao.laker.web.filter;

import org.springframework.web.context.ContextLoaderListener;

/**
 * Created by haojiahong on 16/7/26.
 */
public class CustomContextLoaderListener extends ContextLoaderListener {
    static {
        System.setProperty("dubbo.application.logger", "slf4j");
    }
}
