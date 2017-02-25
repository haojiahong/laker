package com.hao.laker.bizenum;

import lombok.Getter;

/**
 * Created by haojiahong on 17/2/24.
 */
public enum ToleranceStatusEnum {
    READY(0, "未处理"),
    DONE(1, "已处理"),
    EXPIRED(2, "过期"),;

    @Getter
    private Integer code;
    @Getter
    private String desc;

    ToleranceStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
