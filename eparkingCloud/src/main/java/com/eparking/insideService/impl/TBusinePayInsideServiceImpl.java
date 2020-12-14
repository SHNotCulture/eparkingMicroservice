package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.insideService.TBusinePayInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
* @Description: TBusinePayInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/09 10:50
*/
@Component
public class TBusinePayInsideServiceImpl  implements TBusinePayInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TBusinePayInsideServiceImpl.class);



    /**
    *查询(分页)tBusinePay
    * @param tBusinePay
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TBusinePay> getTBusinePaybyPage(TBusinePay tBusinePay, Integer busineId, String payTimeBegin, String payTimeEnd, Integer page, Integer limit) {
        String str = "查询TBusinePay分页失败！";
        logger.info("查询TBusinePay分页失败！");
        return null;
    }

    /**
     * 查询tBusinePay
     * @param tBusinePay
     * @return
     */
    @Override
    public List<TBusinePay> getTBusinePay(TBusinePay tBusinePay, Integer busineId, String payTimeBegin, String payTimeEnd) {
        String str = "查询TBusinePay失败！";
        logger.info("查询TBusinePay失败！");
        return null;
    }

    /**
    * 更新tBusinePay
    * @param tBusinePay
    * @return
    */
    @Override
    public String UpdateTBusinePay(TBusinePay tBusinePay){
        String str = "更新TBusinePay失败！";
        logger.info("更新TBusinePay失败！");
        return str;
    }

    /**
    * 删除tBusinePay
    * @param tBusinePay
    * @return
    */
    @Override
    public String DeleteTBusinePay(TBusinePay tBusinePay){
        String str = "删除TBusinePay失败！";
        logger.info("删除TBusinePay失败！");
        return str;
    }

    @Override
    public void exportListforBusinePay(TBusinePay tBusinePay, Integer busineId, String payTimeBegin, String payTimeEnd, HttpServletResponse response) {

    }
}