package com.hao.laker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * freemarker测试类
 * Created by haojiahong on 17/2/14.
 */
@Controller
@RequestMapping("/")
public class FreemarkerDemoController {

    @RequestMapping("main")
    public String testDemo(Model model) {
        model.addAttribute("name", "haojiahong");
        return "index";
    }
}
