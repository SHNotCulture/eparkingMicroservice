package com.eparking.insideService.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.insideService.TBusineInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TBusineInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/08 18:35
*/
@Component
public class TBusineInsideServiceImpl  implements TBusineInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TBusineInsideServiceImpl.class);

    /**
    * 查询tBusine
    * @param tBusine
    * @return
    */
    @Override
    public List<TBusine> getTBusine(TBusine tBusine){
        String str = "查询TBusine失败！";
        logger.info("查询TBusine失败！");
        return null;
    }

    /**
    *查询(分页)tBusine
    * @param tBusine
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TBusine> getTBusinebyPage(TBusine tBusine, Integer page, Integer limit){
        String str = "查询TBusine分页失败！";
        logger.info("查询TBusine分页失败！");
        return null;
    }

    /**
    * 更新tBusine
    * @param tBusine
    * @return
    */
    @Override
    public String UpdateTBusine(TBusine tBusine){
        String str = "更新TBusine失败！";
        logger.info("更新TBusine失败！");
        return str;
    }

    /**
    * 删除tBusine
    * @param tBusine
    * @return
    */
    @Override
    public String DeleteTBusine(TBusine tBusine){
        String str = "删除TBusine失败！";
        logger.info("删除TBusine失败！");
        return str;
    }

    @Override
    public String BusineRecharge(TBusinePay tBusinePay, String type) {
        String str = "商户充值失败！";
        logger.info("商户充值失败！");
        return str;
    }

    @Override
    public TBusine selectByAccount(String account) {
        String str = "商户账号查询失败！";
        logger.info("商户账号查询失败！");
        return null;
    }

    @Override
    public TBusine selectById(Integer id) {
        String str = "根据ID商户账号查询失败！";
        logger.info("根据ID商户账号查询失败！");
        return null;
    }
}