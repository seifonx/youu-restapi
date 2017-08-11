package com.youu.youu.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youu.youu.manager.bean.TScenicdetails;
import com.youu.youu.manager.dao.TScenicdetailsMapper;
import com.youu.youu.restapi.service.ScenicDetailsService;

/**
 * 操作景点详情
 * @ClassName ScenicDetailsServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Seifon
 * @Date 2017年8月11日 下午2:37:27
 * @version 1.0.0
 */
@Service
public class ScenicDetailsServiceImpl implements ScenicDetailsService {

    @Autowired
    TScenicdetailsMapper scenicdetailsMapper;
    
    /**
     * 根据城市，查出所有景点详情
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @return
     */
    @Override
    public List<TScenicdetails> selectScenicsByCity(String city) {
        TScenicdetails scenicdetails = new TScenicdetails();
        scenicdetails.setCity(city);
        List<TScenicdetails> list = scenicdetailsMapper.select(scenicdetails);
        return list;
    }

    
}
