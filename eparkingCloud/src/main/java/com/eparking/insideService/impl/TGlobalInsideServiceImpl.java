package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TGlobalInfo;
import com.eparking.insideService.TGlobalInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TGlobalInsideServiceImpl implements TGlobalInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TGlobalInsideServiceImpl.class);
    @Override
    public List<TGlobalInfo> getTGlobalInfo(TGlobalInfo tGlobalInfo) {
        String str = "查询TGlobalInfo失败！";
        logger.info("查询TGlobalInfo失败！");
        return null;
    }

    @Override
    public String UpdateTGlobalInfo(TGlobalInfo tGlobalInfo) {
        String str = "更新TGlobalInfo失败！";
        logger.info("更新TGlobalInfo失败！");
        return str;
    }

    @Override
    public String DeleteTGlobalInfo(TGlobalInfo tGlobalInfo) {
        String str = "删除TGlobalInfo失败！";
        logger.info("删除TGlobalInfo失败！");
        return str;
    }


}
