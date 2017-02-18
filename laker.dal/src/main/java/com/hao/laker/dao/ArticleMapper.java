package com.hao.laker.dao;

import com.hao.laker.dao.common.MyBatisRepository;
import com.hao.laker.entity.ArticleDO;

import java.util.List;

/**
 * Created by haojiahong on 2017/2/18.
 */
@MyBatisRepository
public interface ArticleMapper {

    /**
     * 根据主键id获取文章
     *
     * @param id
     * @return
     */
    ArticleDO selectByPrimaryKey(Integer id);

    /**
     * 获取所有有效状态的文章
     *
     * @return
     */
    List<ArticleDO> getAllArticleList();
}
