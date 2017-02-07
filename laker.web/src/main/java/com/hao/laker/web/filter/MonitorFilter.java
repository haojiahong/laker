package com.hao.laker.web.filter;

import com.hao.laker.web.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 这个类打在了logback日志文件中
 * Created by haojiahong on 16/7/20.
 */
public class MonitorFilter implements Filter {
    static Logger log = LoggerFactory.getLogger(MonitorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        long start = System.currentTimeMillis();
        boolean hasExp = true;
        try {
            chain.doFilter(request, response);
            hasExp = false;
        } finally {
            log.info("IP:" + IpUtil.getClientIP(req) + " " + (System.currentTimeMillis() - start) + "ms " + (hasExp ? "has Exception " : "OK ")
                    + ((HttpServletRequest) request).getRequestURI());
        }

    }

    @Override
    public void destroy() {

    }
}
