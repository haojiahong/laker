package com.hao.laker.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;

/**
 * Created by haojiahong on 17/2/25.
 */
@Slf4j
public abstract class Job {
    @Value("${job.host}")
    private String jobHost;

    protected boolean checkHost() {
        try {
            // 获取IP地址
            String localIp = InetAddress.getLocalHost().getHostAddress();
            log.info("配置ip={}, 获取ip={}", jobHost, localIp);
            if (localIp.contains(jobHost)) {
                return true;
            }
        } catch (Exception e) {
            log.error("获取IP地址失败:", e);
            return false;
        }
        return false;
    }

    public void execute() {
//        if (!checkHost()) {
//            return;
//        }
        doBusiness();
    }

    abstract public void doBusiness();
}
