package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TTruckSpace;
import com.eparking.insideService.TTruckSpaceInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TTruckSpaceInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/15 11:22
*/
@Component
public class TTruckSpaceInsideServiceImpl  implements TTruckSpaceInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TTruckSpaceInsideServiceImpl.class);

    /**
    * 查询tTruckSpace
    * @param tTruckSpace
    * @return
    */
    @Override
    public List<TTruckSpace> getTTruckSpace(TTruckSpace tTruckSpace){
        String str = "查询TTruckSpace失败！";
        logger.info("查询TTruckSpace失败！");
        return null;
    }

    /**
    *查询(分页)tTruckSpace
    * @param tTruckSpace
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TTruckSpace> getTTruckSpacebyPage(TTruckSpace tTruckSpace, Integer page, Integer limit){
        String str = "查询TTruckSpace分页失败！";
        logger.info("查询TTruckSpace分页失败！");
        return null;
    }

    /**
    * 更新tTruckSpace
    * @param tTruckSpace
    * @return
    */
    @Override
    public String UpdateTTruckSpace(TTruckSpace tTruckSpace){
        String str = "更新TTruckSpace失败！";
        logger.info("更新TTruckSpace失败！");
        return str;
    }

    /**
    * 删除tTruckSpace
    * @param tTruckSpace
    * @return
    */
    @Override
    public String DeleteTTruckSpace(TTruckSpace tTruckSpace){
        String str = "删除TTruckSpace失败！";
        logger.info("删除TTruckSpace失败！");
        return str;
    }

    @Override
    public TTruckSpace getTTruckSpaceById(Integer id) {
        String str = "根据ID查找tTruckSpace失败！";
        logger.info("根据ID查找tTruckSpace失败！");
        return null;
    }
}