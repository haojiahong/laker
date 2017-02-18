package com.hao.laker.web.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by haojiahong on 17/2/18.
 */
@Getter
@Setter
public class ArticleVO {
    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String description;
    private Integer status;
    private String author;
    private Integer showCount;

    private String createTimeStr;
    private String updateTimeStr;
}
