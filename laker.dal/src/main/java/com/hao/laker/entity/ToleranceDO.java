package com.hao.laker.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by haojiahong on 17/2/24.
 */
@Getter
@Setter
public class ToleranceDO {

    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private String toleranceClass;

    private String toleranceMethod;

    private String toleranceRequest;

    private Integer toleranceStatus;

    private Integer retryTimes;

}
