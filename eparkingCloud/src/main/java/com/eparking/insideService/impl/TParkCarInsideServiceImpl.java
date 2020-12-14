package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparking.insideService.TParkCarInsideService;
import com.common.util.ActionRspUtil;
import com.common.entity.PageBean;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TParkCarInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/14 15:43
*/
@Component
public class TParkCarInsideServiceImpl  implements TParkCarInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TParkCarInsideServiceImpl.class);

    /**
    * 查询tParkCar
    * @param tParkCar
    * @return
    */
    @Override
    public List<TParkCar> getTParkCar(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd){
        String str = "查询TParkCar失败！";
        logger.info("查询TParkCar失败！");
        return null;
    }

    /**
    *查询(分页)tParkCar
    * @param tParkCar
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TParkCar> getTParkCarbyPage(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd, Integer page, Integer limit){
        String str = "查询TParkCar分页失败！";
        logger.info("查询TParkCar分页失败！");
        return null;
    }

    @Override
    public List<TParkCar> getOwnerCar(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd) {
        String str = "查询OwnnerCar失败！";
        logger.info("查询OwnnerCar失败！");
        return null;
    }

    @Override
    public PageBean<TParkCar> getOwnnerCarbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
        String str = "查询OwnnerCar分页失败！";
        logger.info("查询OwnnerCar分页失败！");
        return null;
    }

    /**
    * 更新tParkCar
    * @param tParkCar
    * @return
    */
    @Override
    public String UpdateTParkCar(TParkCar tParkCar){
        String str = "更新TParkCar失败！";
        logger.info("更新TParkCar失败！");
        return null;
    }

    /**
    * 删除tParkCar
    * @param tParkCar
    * @return
    */
    @Override
    public String DeleteTParkCar(TParkCar tParkCar){
        String str = "删除TParkCar失败！";
        logger.info("删除TParkCar失败！");
        return str;
    }

    @Override
    public TParkCar getTParkCarById(Integer id) {
        String str = "查询TParkCar失败！";
        logger.info("查询TParkCar失败！");
        return null;
    }

    @Override
    public ActionRsp recharge(String carInfo) {
        String str = "月租充值失败！";
        logger.info("月租充值失败！");
        return ActionRspUtil.Error(1,str);
    }

    @Override
    public List<TParkCar> getCloseParkCar(String closeType, String payRule, String parkid) {
        String str = "查询接近到期的月租车失败！";
        logger.info("查询接近到期的月租车失败！");
        return null;
    }

    @Override
    public PageBean<TParkCar> getCloseParkCarbyPage(String closeType, String payRule, String parkid, Integer page, Integer limit) {
        String str = "查询接近到期的月租车分页失败！";
        logger.info("查询接近到期的月租车分页失败！");
        return null;
    }



    @Override
    public JSONObject getNeedPay(Integer payStandard, Integer payCount, TParkCar tParkCar) {
        String str = "查询应收费失败！";
        logger.info("查询应收费失败！");
        return null;
    }

    @Override
    public String Resetport(String portids, String ids) {
        String str = "重置通道权限失败！";
        logger.info("重置通道权限失败！");
        return str;
    }

    @Override
    public ActionRsp correct(String carInfo) {
        String str = "冲正失败！";
        logger.info("冲正失败！");
        return ActionRspUtil.Error(1,str);
    }

    @Override
    public ActionRsp refund(String carInfo) {
        String str = "删除失败！";
        logger.info("删除失败！");
        return ActionRspUtil.Error(1,str);
    }

    @Override
    public Integer insertTParkCarMap(TParkCar tParkCar) {
        String str = "插入TParkCar表失败！";
        logger.info("插入TParkCar表失败！");
        return 1;
    }

    @Override
    public List<TParkCar> whetherTParkCar(Integer parkId, String carPlate,String endDateSelectStart,String endDateSelectEnd) {
        String str = "查询有效月租车失败！";
        logger.info("查询有效月租车失败！");
        return null;
    }

    @Override
    public Integer getPresentCarNum(String parkIDlist) {
        String str = "getPresentCarNum失败！";
        logger.info("getPresentCarNum失败！");
        return null;
    }

    @Override
    public List<TParkCar> getTParkCarForStatistics(TParkCar tParkCar, String date) {
        String str = "财务报表统计登记车失败！";
        logger.info("财务报表统计登记车失败！");
        return null;
    }

    @Override
    public PageBean<TParkCar> getTParkCarFuzzybyPage(TParkCar tParkCar,String parkCarIdList, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
        String str = "模糊查询TParkCar分页失败！";
        logger.info("模糊查询TParkCar分页失败！");
        return null;
    }

}