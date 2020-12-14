package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TIpc;
import com.eparking.insideService.TIpcInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TIpcInsideService接口熔断器
* @author 谢轩然
* @date 2020/07/13 18:31
*/
@Component
public class TIpcInsideServiceImpl  implements TIpcInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TIpcInsideServiceImpl.class);

    /**
    * 查询tIpc
    * @param tIpc
    * @return
    */
    @Override
    public List<TIpc> getTIpc(TIpc tIpc){
        String str = "查询TIpc失败！";
        logger.info("查询TIpc失败！");
        return null;
    }

    /**
    *查询(分页)tIpc
    * @param tIpc
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TIpc> getTIpcbyPage(TIpc tIpc, Integer page, Integer limit){
        String str = "查询TIpc分页失败！";
        logger.info("查询TIpc分页失败！");
        return null;
    }

    /**
    * 更新tIpc
    * @param tIpc
    * @return
    */
    @Override
    public String UpdateTIpc(TIpc tIpc){
        String str = "更新TIpc失败！";
        logger.info("更新TIpc失败！");
        return str;
    }

    /**
    * 删除tIpc
    * @param tIpc
    * @return
    */
    @Override
    public String DeleteTIpc(TIpc tIpc){
        String str = "删除TIpc失败！";
        logger.info("删除TIpc失败！");
        return str;
    }
}