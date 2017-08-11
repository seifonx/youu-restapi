package com.youu.youu.restapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.util.HttpClientUtil;

/**
 * 搜索相关处理器
 * @ClassName SearchController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Seifon
 * @Date 2017年8月11日 上午9:22:42
 * @version 1.0.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    
    @ResponseBody
    @RequestMapping("/key_guess/{key}_{before_key}.html")
    public String keyGuess(@PathVariable("key")String key, @PathVariable("before_key")String before_key) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("wd", "阿里巴巴");
        params.put("bs", "阿里");
        params.put("json", "1");
        String url = "https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su";
        String httpGetRequest = HttpClientUtil.httpGetRequest(url , params);
        System.out.println(httpGetRequest);
        return "";
    }

}
