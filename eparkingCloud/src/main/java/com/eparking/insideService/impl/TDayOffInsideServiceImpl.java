package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TDayOff;
import com.eparking.insideService.TDayOffInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TDayOffInsideServiceImpl implements TDayOffInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TDayOffInsideServiceImpl.class);

    @Override
    public List<TDayOff> getTHoliday(TDayOff tDayOff) {
        String str = "查询TDayOff失败！";
        logger.info("查询TDayOff失败！");
        return null;
    }

    @Override
    public PageBean<TDayOff> getTDayOffbyPage(TDayOff tDayOff, Integer page, Integer limit) {
        String str = "查询TDayOff分页失败！";
        logger.info("查询TDayOff分页失败！");
        return null;
    }

    @Override
    public String UpdateTDayOff(TDayOff tDayOff) {
        String str = "更新TDayOff失败！";
        logger.info("更新TDayOff失败！");
        return str;
    }

    @Override
    public String DeleteTDayOff(TDayOff tDayOff) {
        String str = "删除TDayOff失败！";
        logger.info("删除TDayOff失败！");
        return str;
    }
}
