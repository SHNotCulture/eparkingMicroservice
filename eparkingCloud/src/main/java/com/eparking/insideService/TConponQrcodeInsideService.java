package com.eparking.insideService;

import com.common.entity.eparkingCloud.TConponQrcode;
import com.eparking.insideService.impl.TConponQrcodeInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TConponQrcodeInsideService接口
* @author 谢轩然
* @date 2020/04/15 16:48
*/
@FeignClient(value = "eparkingCloudData",fallback = TConponQrcodeInsideServiceImpl.class)
public interface TConponQrcodeInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tConponQrcode
    * @param tConponQrcode
    * @return
    */
    @PostMapping(value = client+"/tConponQrcode/getTConponQrcode")
    List<TConponQrcode> getTConponQrcode(@RequestBody TConponQrcode tConponQrcode);

    /**
    *查询(分页)tConponQrcode
    * @param tConponQrcode
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tConponQrcode/getTConponQrcodebyPage")
    PageBean<TConponQrcode> getTConponQrcodebyPage(@RequestBody TConponQrcode tConponQrcode, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tConponQrcode
    * @param tConponQrcode
    * @return
    */
    @PostMapping(value = client+"/tConponQrcode/updateTConponQrcode")
    String UpdateTConponQrcode(@RequestBody TConponQrcode tConponQrcode);

    /**
    * 删除tConponQrcode
    * @param tConponQrcode
    * @return
    */
    @PostMapping(value = client+"/tConponQrcode/deleteTConponQrcode")
    String DeleteTConponQrcode(@RequestBody TConponQrcode tConponQrcode);

/*    @PostMapping(value = client+"/tConponQrcode/existTConponQrcodeName")
    void existTConponQrcodeName(@RequestBody TConponQrcode tConponQrcode);

    @PostMapping(value = client+"/tConponQrcode/lessDiscountAmount")
    void lessDiscountAmount(@RequestBody TConponQrcode tConponQrcode,@RequestParam("busineId") Integer busineId);*/

    @PostMapping(value = client+"/tConponQrcode/getPreciseTConponQrcode")
    List<TConponQrcode> getPreciseTConponQrcode(@RequestBody TConponQrcode tConponQrcode);
}