package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TConponQrcode;
import com.eparking.insideService.TConponQrcodeInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TConponQrcodeInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/15 16:48
*/
@Component
public class TConponQrcodeInsideServiceImpl  implements TConponQrcodeInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TConponQrcodeInsideServiceImpl.class);

    /**
    * 查询tConponQrcode
    * @param tConponQrcode
    * @return
    */
    @Override
    public List<TConponQrcode> getTConponQrcode(TConponQrcode tConponQrcode){
        String str = "查询TConponQrcode失败！";
        logger.info("查询TConponQrcode失败！");
        return null;
    }

    /**
    *查询(分页)tConponQrcode
    * @param tConponQrcode
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TConponQrcode> getTConponQrcodebyPage(TConponQrcode tConponQrcode, Integer page, Integer limit){
        String str = "查询TConponQrcode分页失败！";
        logger.info("查询TConponQrcode分页失败！");
        return null;
    }

    /**
    * 更新tConponQrcode
    * @param tConponQrcode
    * @return
    */
    @Override
    public String UpdateTConponQrcode(TConponQrcode tConponQrcode){
        String str = "更新TConponQrcode失败！";
        logger.info("更新TConponQrcode失败！");
        return str;
    }

    /**
    * 删除tConponQrcode
    * @param tConponQrcode
    * @return
    */
    @Override
    public String DeleteTConponQrcode(TConponQrcode tConponQrcode){
        String str = "删除TConponQrcode失败！";
        logger.info("删除TConponQrcode失败！");
        return str;
    }


/*    @Override
    public void existTConponQrcodeName(TConponQrcode tConponQrcode) {
    }

    @Override
    public void lessDiscountAmount(TConponQrcode tConponQrcode, Integer busineId) {
    }*/

    @Override
    public List<TConponQrcode> getPreciseTConponQrcode(TConponQrcode tConponQrcode) {
        String str = "根据优惠二维码名称精确查找TConponQrcode失败！";
        logger.info("根据优惠二维码名称精确查找TConponQrcode失败！");
        return null;
    }
}