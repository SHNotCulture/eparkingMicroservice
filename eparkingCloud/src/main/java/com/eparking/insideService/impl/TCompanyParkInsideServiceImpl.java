package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.eparking.insideService.TCompanyParkInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TCompanyParkInsideServiceImpl implements TCompanyParkInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TCompanyParkInsideServiceImpl.class);

    @Override
    public List<TCompanyPark> getTCompanyPark(TCompanyPark tCompanyPark) {
        String str = "查询TCompanyPark失败！";
        logger.info("查询TCompanyPark失败！");
        return null;
    }

    @Override
    public PageBean<TCompanyPark> getTCompanyParkbyPage(TCompanyPark tCompanyPark, Integer page, Integer limit) {
        String str = "查询TCompanyPark分页失败！";
        logger.info("查询TCompanyPark分页失败！");
        return null;
    }

    @Override
    public String UpdateTCompanyPark(TCompanyPark tCompanyPark) {
        String str = "更新TCompanyPark失败！";
        logger.info("更新TCompanyPark失败！");
        return str;
    }

    @Override
    public String DeleteTCompanyPark(TCompanyPark tCompanyPark) {
        String str = "删除TCompanyPark失败！";
        logger.info("删除TCompanyPark失败！");
        return str;
    }

    @Override
    public TCompanyPark getTCompanyParkByID(TCompanyPark tCompanyPark) {
        String str = "查询TCompanyPark失败！";
        logger.info("查询TCompanyPark失败！");
        return null;
    }

    @Override
    public List<TCompanyPark> getCarParkbyIDIn(String ids) {
        String str = "根据id字符串查询TCompanyPark失败！";
        logger.info("根据id字符串查询TCompanyPark失败！");
        return null;
    }
}
