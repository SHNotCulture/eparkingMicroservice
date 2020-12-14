package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TModuleListNew;
import com.eparking.insideService.TModuleListNewInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TModuleListNewInsideServiceImpl implements TModuleListNewInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TModuleListNewInsideServiceImpl.class);


    @Override
    public List<TModuleListNew> getModuleListNewForLayuiMenu(List<Integer> tModuleListNew) {
        String str = "按条件查找权限信息失败！";
        logger.info("按条件查找权限信息失败！");
        return null;
    }

    @Override
    public List<TModuleListNew> getModuleList(TModuleListNew tModuleListNew) {
        String str = "查询全部页面信息失败！";
        logger.info("查询全部页面信息失败！");
        return null;
    }

    @Override
    public List<TModuleListNew> getModuleListForZtree(TModuleListNew tModuleListNew) {
        String str = "查询全部页面信息Ztree失败！";
        logger.info("查询全部页面信息Ztree失败！");
        return null;
    }

    @Override
    public List<TModuleListNew> getModuleListNewForSecond(Integer pid, List<Integer> tModuleListNew) {
        String str = "根据用户权限查询二级菜单失败！";
        logger.info("根据用户权限查询二级菜单失败！");
        return null;
    }
}
