package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.eparking.insideService.TMonthBindingCarInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TMonthBindingCarInsideServiceImpl implements TMonthBindingCarInsideService {
    private static final Logger logger = LoggerFactory.getLogger(TMonthBindingCarInsideServiceImpl.class);

    @Override
    public List<TMonthBindingCar> getTMonthBindingCar(TMonthBindingCar tMonthBindingCar) {
        String str = "查询tMonthBindingCar失败！";
        logger.info("查询tMonthBindingCar失败！");
        return null;
    }

    @Override
    public PageBean<TMonthBindingCar> getTMonthBindingCarbyPage(TMonthBindingCar tMonthBindingCar, Integer page, Integer limit) {
        String str = "查询tMonthBindingCar分页失败！";
        logger.info("查询tMonthBindingCar分页失败！");
        return null;
    }

    @Override
    public String UpdateTMonthBindingCar(TMonthBindingCar tMonthBindingCar) {
        String str = "更新tMonthBindingCar分页失败！";
        logger.info("更新tMonthBindingCar分页失败！");
        return str;
    }

    @Override
    public String DeleteTMonthBindingCar(TMonthBindingCar tMonthBindingCar) {
        String str = "删除tMonthBindingCar分页失败！";
        logger.info("删除tMonthBindingCar分页失败！");
        return str;
    }

    @Override
    public TMonthBindingCar getTMonthBindingCarById(Integer id) {
        String str = "根据ID查tMonthBindingCar信息失败！";
        logger.info("根据ID查tMonthBindingCar信息失败！");
        return null;
    }
}
