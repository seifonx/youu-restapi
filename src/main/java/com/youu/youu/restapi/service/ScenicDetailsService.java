package com.youu.youu.restapi.service;

import java.util.List;

import com.youu.youu.manager.bean.TScenicdetails;

/**
 * 操作景点详情
 * @ClassName ScenicDetailsService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Seifon
 * @Date 2017年8月11日 下午2:33:59
 * @version 1.0.0
 */
public interface ScenicDetailsService {

    /**
     * 根据城市，查出所有景点详情
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @return
     */
    public List<TScenicdetails> selectScenicsByCity(String city);
}
