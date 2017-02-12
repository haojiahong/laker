package com.hao.laker.common.exception;

import java.io.Serializable;

/**
 * Created by haojiahong on 2017/2/12.
 */
public interface ServiceErrors extends Serializable {

    String getCode();

    String getMessage();
}
