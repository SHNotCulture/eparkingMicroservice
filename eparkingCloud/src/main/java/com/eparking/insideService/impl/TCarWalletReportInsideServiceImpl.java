package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TCarWalletReport;
import com.eparking.insideService.TCarWalletReportInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TCarWalletReportInsideService接口熔断器
* @author 李书瀚
* @date 2020/10/26 23:19
*/
@Component
public class TCarWalletReportInsideServiceImpl implements TCarWalletReportInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCarWalletReportInsideServiceImpl.class);

    /**
    * 查询tCarWalletReport
    * @param tCarWalletReport
    * @return
    */
    @Override
    public List<TCarWalletReport> getTCarWalletReport(TCarWalletReport tCarWalletReport,String beginTime,String endTime){
        String str = "查询TCarWalletReport失败！";
        logger.info("查询TCarWalletReport失败！");
        return null;
    }

    /**
    *查询tCarWalletReport(分页)
    * @param tCarWalletReport
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TCarWalletReport> getTCarWalletReportbyPage(TCarWalletReport tCarWalletReport,String beginTime,String endTime,Integer page, Integer limit){
        String str = "查询TCarWalletReport分页失败！";
        logger.info("查询TCarWalletReport分页失败！");
        return null;
    }

    /**
    * 更新tCarWalletReport
    * @param tCarWalletReport
    * @return
    */
    @Override
    public String  UpdateTCarWalletReport(TCarWalletReport tCarWalletReport){
        String str = "更新TCarWalletReport失败！";
        logger.info("更新TCarWalletReport失败！");
        return null;
    }

    /**
    * 删除tCarWalletReport
    * @param tCarWalletReport
    * @return
    */
    @Override
    public String DeleteTCarWalletReport(TCarWalletReport tCarWalletReport){
        String str = "删除TCarWalletReport失败！";
        logger.info("删除TCarWalletReport失败！");
        return null;
    }
}