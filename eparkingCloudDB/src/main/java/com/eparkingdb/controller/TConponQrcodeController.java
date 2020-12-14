package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TConponQrcode;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TConponQrcodeService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TConponQrcodeController类
* @author 谢轩然
* @date 2020/04/09 14:55
*/
@RestController
@RequestMapping("/tConponQrcode")
public class TConponQrcodeController {

    private  static final Logger logger= LoggerFactory.getLogger(TConponQrcodeController.class);

    @Autowired
    private TConponQrcodeService tConponQrcodeService;

    /**
    * 查询TConponQrcode信息
    * @paramtConponQrcode
    * @return
    */
    @PostMapping(value = "/getTConponQrcode")
    @HttpLog(operationType = "0",modularTypeName = "查询TConponQrcode")
    public List<TConponQrcode> getTConponQrcode(@RequestBody TConponQrcode tConponQrcode){
    return tConponQrcodeService.getTConponQrcode(tConponQrcode);
    }

    /**
    * 查询TConponQrcode信息(分页)
    * @paramtConponQrcode
    * @return
    */
    @PostMapping(value = "/getTConponQrcodebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TConponQrcode(分页)")
    public PageBean<TConponQrcode> getTConponQrcodebyPage(@RequestBody TConponQrcode tConponQrcode, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tConponQrcodeService.getTConponQrcodebyPage(tConponQrcode,page,limit);
    }

    /**
    * 更新TConponQrcode信息
    * @paramtConponQrcode
    * @return
    */
    @PostMapping(value = "/updateTConponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "更新TConponQrcode信息")
    public String UpdateTConponQrcode(@RequestBody TConponQrcode tConponQrcode)
    {
        return tConponQrcodeService.UpdateTConponQrcode(tConponQrcode);
    }

    /**
    * 删除TConponQrcode信息
    * @param tConponQrcode
    * @return
    */
    @PostMapping(value = "/deleteTConponQrcode")
    @HttpLog(operationType = "1",modularTypeName = "删除TConponQrcode信息")
    public String DeleteTConponQrcode(@RequestBody TConponQrcode tConponQrcode){
    return tConponQrcodeService.DeleteTConponQrcode(tConponQrcode);
    }

    @PostMapping(value = "/getPreciseTConponQrcode")
    @HttpLog(operationType = "0",modularTypeName = "根据优惠二维码名称精确查找")
    public List<TConponQrcode> getPreciseTConponQrcode(@RequestBody TConponQrcode tConponQrcode){
        return tConponQrcodeService.getPreciseTConponQrcode(tConponQrcode);
    }

/*    @PostMapping(value = "/tConponQrcode/existTConponQrcodeName")
    @HttpLog(operationType = "1",modularTypeName = "根据qrcodeName查询数据库")
    public void existTConponQrcodeName(@RequestBody TConponQrcode tConponQrcode){
     tConponQrcodeService.existTConponQrcodeName(tConponQrcode);
    }

    @PostMapping(value = "/tConponQrcode/lessDiscountAmount")
    @HttpLog(operationType = "1",modularTypeName = "当优惠类型不为时间时执行小于单笔最大消费金额判断")
    public void lessDiscountAmount(@RequestBody TConponQrcode tConponQrcode,@RequestParam("busineId") Integer busineId){
        tConponQrcodeService.lessDiscountAmount(tConponQrcode,busineId);
    }*/
}