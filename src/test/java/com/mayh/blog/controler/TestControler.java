package com.mayh.blog.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TestControler {
    @RequestMapping("/test")
    public String test() {
        return "admin/test";
    }

    @GetMapping("/toindex")
    public String index() {
        return "admin/index";
    }
}
