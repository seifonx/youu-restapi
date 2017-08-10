package com.youu.youu.restapi.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
    
    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

}
