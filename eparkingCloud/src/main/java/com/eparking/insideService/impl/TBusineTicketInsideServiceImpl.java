package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TBusineTicket;
import com.eparking.insideService.TBusineTicketInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TBusineTicketInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/15 11:39
*/
@Component
public class TBusineTicketInsideServiceImpl  implements TBusineTicketInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TBusineTicketInsideServiceImpl.class);

    /**
    * 查询tBusineTicket
    * @param tBusineTicket
    * @return
    */
    @Override
    public List<TBusineTicket> getTBusineTicket(TBusineTicket tBusineTicket, String busineIdStr){
        String str = "查询TBusineTicket失败！";
        logger.info("查询TBusineTicket失败！");
        return null;
    }

    /**
    *查询(分页)tBusineTicket
    * @param tBusineTicket
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TBusineTicket> getTBusineTicketbyPage(TBusineTicket tBusineTicket, Integer parkId, Integer page, Integer limit){
        String str = "查询TBusineTicket分页失败！";
        logger.info("查询TBusineTicket分页失败！");
        return null;
    }


    /**
    * 更新tBusineTicket
    * @param tBusineTicket
    * @return
    */
    @Override
    public String UpdateTBusineTicket(TBusineTicket tBusineTicket){
        String str = "更新TBusineTicket失败！";
        logger.info("更新TBusineTicket失败！");
        return str;
    }

    /**
    * 删除tBusineTicket
    * @param tBusineTicket
    * @return
    */
    @Override
    public String DeleteTBusineTicket(TBusineTicket tBusineTicket){
        String str = "删除TBusineTicket失败！";
        logger.info("删除TBusineTicket失败！");
        return str;
    }


    @Override
    public List<Integer> getIdsByUsable(TBusineTicket tBusineTicket) {
        String str = "查询商户拥有可用的电子券种类失败！";
        logger.info("查询商户拥有可用的电子券种类失败！");
        return null;
    }

    @Override
    public Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId) {
        String str = "根据电子券id查询最接近过期的电子券批次号失败！";
        logger.info("根据电子券id查询最接近过期的电子券批次号失败！");
        return null;
    }

    @Override
    public String eleAuthorization(TBusineTicket tBusineTicket) {
        String str = "授权电子券失败！";
        logger.info("授权电子券失败！");
        return str;
    }

    @Override
    public String buyBackBusineTicket(TBusineTicket tBusineTicket) {
        String str = "回购电子券失败！";
        logger.info("回购电子券失败！");
        return str;
    }

/*    @Override
    public ActionRsp buyBackBusineTicket(TBusineTicket tBusineTicket) {
        String str = "回购电子券失败！";
        logger.info("回购电子券失败！");
        return ActionRspUtil.Error(1,str);
    }*/

    @Override
    public String eleBuy(TBusineTicket tBusineTicket, Integer ticketNumNew) {
        String str = "购买电子券失败！";
        logger.info("购买电子券失败！");
        return str;
    }

    @Override
    public String changeIsPay(TBusineTicket tBusineTicket) {
        String str = "修改电子劵批次权限失败！";
        logger.info("修改电子劵批次权限失败！");
        return str;
    }

    @Override
    public TBusineTicket getTBusineTicketByID(Integer id) {
        String str = "根据id查询电子券失败！";
        logger.info("根据id查询电子券失败！");
        return null;
    }
}