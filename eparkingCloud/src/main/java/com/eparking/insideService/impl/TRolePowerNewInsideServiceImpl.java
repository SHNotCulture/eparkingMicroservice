package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TRolePowerNew;
import com.eparking.insideService.TRolePowerNewInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TRolePowerNewInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/15 15:20
*/
@Component
public class TRolePowerNewInsideServiceImpl  implements TRolePowerNewInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TRolePowerNewInsideServiceImpl.class);

    /**
    * 查询tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    @Override
    public List<TRolePowerNew> getTRolePowerNew(TRolePowerNew tRolePowerNew){
        String str = "查询TRolePowerNew失败！";
        logger.info("查询TRolePowerNew失败！");
        return null;
    }

    /**
    *查询(分页)tRolePowerNew
    * @param tRolePowerNew
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TRolePowerNew> getTRolePowerNewbyPage(TRolePowerNew tRolePowerNew, Integer page, Integer limit){
        String str = "查询TRolePowerNew分页失败！";
        logger.info("查询TRolePowerNew分页失败！");
        return null;
    }

    /**
    * 更新tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    @Override
    public String UpdateTRolePowerNew(TRolePowerNew tRolePowerNew){
        String str = "更新TRolePowerNew失败！";
        logger.info("更新TRolePowerNew失败！");
        return str;
    }

    /**
    * 删除tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    @Override
    public String DeleteTRolePowerNew(TRolePowerNew tRolePowerNew){
        String str = "删除TRolePowerNew失败！";
        logger.info("删除TRolePowerNew失败！");
        return str;
    }

    @Override
    public List<TRolePowerNew> getRolePowerById(TRolePowerNew TRolePowerNew) {
        String str = "通过ID查询角色信息失败！";
        logger.info("通过ID查询角色信息失败！");
        return null;
    }

    @Override
    public TRolePowerNew selectByPrimaryKey(Integer id) {
        String str = "通过ID查询角色信息失败！";
        logger.info("通过ID查询角色信息失败！");
        return null;
    }


}