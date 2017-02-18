package com.hao.laker.web.controller;

import com.hao.laker.bo.ArticleBO;
import com.hao.laker.common.util.BdUtil;
import com.hao.laker.service.ArticleService;
import com.hao.laker.web.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * freemarker测试类
 * Created by haojiahong on 17/2/14.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/")
    public String index(Model model) {
        List<ArticleBO> articleBOList = articleService.getAllArticleList();
        List<ArticleVO> articleVOList = BdUtil.bo2do4List(articleBOList, ArticleVO.class);
        model.addAttribute("dataList", articleVOList);
        return "index";
    }


    @RequestMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("name", "haojiahong");
        return "index";
    }

    @RequestMapping("/about")
    public String getAbout() {
        return "about";
    }

    @RequestMapping("/contact")
    public String getContact() {
        return "contact";
    }

}
