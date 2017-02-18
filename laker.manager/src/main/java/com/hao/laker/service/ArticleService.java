package com.hao.laker.service;

import com.hao.laker.bo.ArticleBO;

import java.util.List;

/**
 * Created by haojiahong on 17/2/18.
 */
public interface ArticleService {

    ArticleBO selectByPrimaryKey(Integer id);

    List<ArticleBO> getAllArticleList();

}
