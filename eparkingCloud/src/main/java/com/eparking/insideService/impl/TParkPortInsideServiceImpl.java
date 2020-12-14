package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TParkPort;
import com.eparking.insideService.TParkPortInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TParkPortInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/29 15:05
*/
@Component
public class TParkPortInsideServiceImpl  implements TParkPortInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TParkPortInsideServiceImpl.class);

    /**
    * 查询tParkPort
    * @param tParkPort
    * @return
    */
    @Override
    public List<TParkPort> getTParkPort(TParkPort tParkPort){
        String str = "查询TParkPort失败！";
        logger.info("查询TParkPort失败！");
        return null;
    }

    /**
    *查询(分页)tParkPort
    * @param tParkPort
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TParkPort> getTParkPortbyPage(TParkPort tParkPort, Integer page, Integer limit){
        String str = "查询TParkPort分页失败！";
        logger.info("查询TParkPort分页失败！");
        return null;
    }

    /**
    * 更新tParkPort
    * @param tParkPort
    * @return
    */
    @Override
    public String UpdateTParkPort(TParkPort tParkPort){
        String str = "更新TParkPort失败！";
        logger.info("更新TParkPort失败！");
        return str;
    }

    /**
    * 删除tParkPort
    * @param tParkPort
    * @return
    */
    @Override
    public String DeleteTParkPort(TParkPort tParkPort){
        String str = "删除TParkPort失败！";
        logger.info("删除TParkPort失败！");
        return str;
    }

    @Override
    public List<TParkPort> getTParkPortOutPort(TParkPort tParkPort) {
        String str = "查询出口失败！";
        logger.info("查询出口失败！");
        return null;
    }

    @Override
    public List<TParkPort> getPortNameListForZtree(Integer parkId,String portType) {
        String str = "查询通道名称失败！";
        logger.info("查询通道名称失败！");
        return null;
    }
}