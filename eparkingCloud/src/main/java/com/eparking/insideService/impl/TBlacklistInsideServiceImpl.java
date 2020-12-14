package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TBlacklist;
import com.eparking.insideService.TBlacklistInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TBlacklistInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/14 16:29
*/
@Component
public class TBlacklistInsideServiceImpl  implements TBlacklistInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TBlacklistInsideServiceImpl.class);

    /**
    * 查询tBlacklist
    * @param tBlacklist
    * @return
    */
    @Override
    public List<TBlacklist> getTBlacklist(TBlacklist tBlacklist){
        String str = "查询TBlacklist失败！";
        logger.info("查询TBlacklist失败！");
        return null;
    }

    /**
    *查询(分页)tBlacklist
    * @param tBlacklist
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TBlacklist> getTBlacklistbyPage(TBlacklist tBlacklist, Integer page, Integer limit){
        String str = "查询TBlacklist分页失败！";
        logger.info("查询TBlacklist分页失败！");
        return null;
    }

    /**
    * 更新tBlacklist
    * @param tBlacklist
    * @return
    */
    @Override
    public String UpdateTBlacklist(TBlacklist tBlacklist){
        String str = "更新TBlacklist失败！";
        logger.info("更新TBlacklist失败！");
        return str;
    }

    /**
    * 删除tBlacklist
    * @param tBlacklist
    * @return
    */
    @Override
    public String DeleteTBlacklist(TBlacklist tBlacklist){
        String str = "删除TBlacklist失败！";
        logger.info("删除TBlacklist失败！");
        return str;
    }

    @Override
    public TBlacklist getTBlacklistByID(Integer id) {
        String str = "通过id查询TBlacklist信息！";
        logger.info("通过id查询TBlacklist信息！");
        return null;
    }

}