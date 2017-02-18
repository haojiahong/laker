package com.hao.laker.service.impl;

import com.hao.laker.bo.ArticleBO;
import com.hao.laker.common.util.BdUtil;
import com.hao.laker.dao.ArticleMapper;
import com.hao.laker.entity.ArticleDO;
import com.hao.laker.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haojiahong on 17/2/18.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleBO selectByPrimaryKey(Integer id) {
        ArticleDO articleDO = articleMapper.selectByPrimaryKey(id);
        ArticleBO articleBO = BdUtil.do2bo(articleDO, ArticleBO.class);
        return articleBO;
    }

    @Override
    public List<ArticleBO> getAllArticleList() {
        List<ArticleDO> articleDOList = articleMapper.getAllArticleList();
        List<ArticleBO> articleBOList = BdUtil.bo2do4List(articleDOList, ArticleBO.class);
        return articleBOList;
    }
}
