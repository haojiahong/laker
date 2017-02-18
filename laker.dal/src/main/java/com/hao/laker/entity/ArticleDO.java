package com.hao.laker.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by haojiahong on 2017/2/18.
 */
@Getter
@Setter
public class ArticleDO {
    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String description;
    private Integer status;
    private String author;
    private Date createTime;
    private Date updateTime;
    private int showCount;

}
