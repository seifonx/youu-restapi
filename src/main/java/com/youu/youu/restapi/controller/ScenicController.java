package com.youu.youu.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youu.youu.manager.bean.TScenicdetails;
import com.youu.youu.restapi.bean.ScwReturn;
import com.youu.youu.restapi.service.ScenicDetailsService;

/**
 * 景点详情相关处理器
 * @ClassName ScenicController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Seifon
 * @Date 2017年8月11日 下午7:48:39
 * @version 1.0.0
 */
@RestController
@RequestMapping("/scenics_details")
public class ScenicController {
    
    @Autowired
    ScenicDetailsService scenicDetailsService;
    
    /**
     * 根据城市名，进行景点推荐
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @return
     */
    @RequestMapping("/recommend")
    public ScwReturn<List<TScenicdetails>> recommendScenics(@RequestParam("city")String city) {
        List<TScenicdetails> scenicsByCity = scenicDetailsService.selectScenicsByCity(city);
        if (scenicsByCity.size() > 0) return ScwReturn.success("推荐成功", scenicsByCity, null);
        return ScwReturn.fail("推荐失败", null, null);
    }
}
