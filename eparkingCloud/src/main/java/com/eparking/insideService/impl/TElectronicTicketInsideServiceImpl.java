package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TElectronicTicket;
import com.eparking.insideService.TElectronicTicketInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TElectronicTicketInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/09 15:01
*/
@Component
public class TElectronicTicketInsideServiceImpl  implements TElectronicTicketInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TElectronicTicketInsideServiceImpl.class);

    /**
    * 查询tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    @Override
    public List<TElectronicTicket> getTElectronicTicket(TElectronicTicket tElectronicTicket){
        String str = "查询TElectronicTicket失败！";
        logger.info("查询TElectronicTicket失败！");
        return null;
    }

    @Override
    public List<TElectronicTicket> FindElectronicTicketByIds(String ids) {
        String str = "查询FindElectronicTicketByIds失败！";
        logger.info("查询FindElectronicTicketByIds失败！");
        return null;
    }

    /**
    *查询(分页)tElectronicTicket
    * @param tElectronicTicket
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TElectronicTicket> getTElectronicTicketbyPage(TElectronicTicket tElectronicTicket, Integer page, Integer limit){
        String str = "查询TElectronicTicket分页失败！";
        logger.info("查询TElectronicTicket分页失败！");
        return null;
    }

    /**
    * 更新tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    @Override
    public String UpdateTElectronicTicket(TElectronicTicket tElectronicTicket){
        String str = "更新TElectronicTicket失败！";
        logger.info("更新TElectronicTicket失败！");
        return str;
    }

    /**
    * 删除tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    @Override
    public String DeleteTElectronicTicket(TElectronicTicket tElectronicTicket){
        String str = "删除TElectronicTicket失败！";
        logger.info("删除TElectronicTicket失败！");
        return str;
    }

    @Override
    public TElectronicTicket getElectronicTicketByPrimaryKey(Integer id) {
        String str = "查询TElectronicTicket失败！";
        logger.info("查询TElectronicTicket失败！");
        return null;
    }

    @Override
    public PageBean<TElectronicTicket> geteTicketListbyPageAndIds(String ids, Integer page, Integer limit) {
        String str = "查询TElectronicTicket分页失败！";
        logger.info("查询TElectronicTicket分页失败！");
        return null;
    }


}