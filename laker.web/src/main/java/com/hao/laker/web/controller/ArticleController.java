package com.hao.laker.web.controller;

import com.hao.laker.bo.ArticleBO;
import com.hao.laker.common.util.BdUtil;
import com.hao.laker.common.entity.Result;
import com.hao.laker.service.ArticleService;
import com.hao.laker.web.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 文章
 * Created by haojiahong on 17/2/18.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    @ResponseBody
    public Result<List<ArticleVO>> getList() {
        List<ArticleBO> articleBOList = articleService.getAllArticleList();
        List<ArticleVO> articleVOList = BdUtil.bo2do4List(articleBOList, ArticleVO.class);
        return Result.wrapSuccessfulResult(articleVOList);
    }

    @RequestMapping("")
    public Result<ArticleVO> getById(@RequestParam("id") Integer id) {
        ArticleBO articleBO = articleService.selectByPrimaryKey(id);
        ArticleVO articleVO = BdUtil.bo2do(articleBO, ArticleVO.class);
        return Result.wrapSuccessfulResult(articleVO);
    }

}
