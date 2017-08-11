package com.youu.youu.restapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.util.HttpClientUtil;

/**
 * 搜索相关处理器
 * @ClassName SearchController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Seifon
 * @Date 2017年8月11日 上午9:22:42
 * @version 1.0.0
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    
    /**
     * 根据传来的关键字，查询联想出来的关键字，并返回
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param key
     * @param before_key
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/key_guess",produces="text/html;charset=utf-8")
    public String keyGuess(@RequestParam("key")String key, @RequestParam("before_key")String before_key) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("wd", key);
        params.put("bs", before_key);
        params.put("json", "1");
        String url = "https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su";
        String httpGetRequest = HttpClientUtil.httpGetRequest(url, params);
        return httpGetRequest;
    }

}
