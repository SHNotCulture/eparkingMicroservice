package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusineTicket;
import com.common.entity.eparkingCloud.BusineCouponGetTicketInfo;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.CustomizeService;
import com.eparkingdb.service.TBusineTicketService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Description: TBusineTicketController类
* @author 谢轩然
* @date 2020/04/09 14:36
*/
@RestController
@RequestMapping("/tBusineTicket")
public class TBusineTicketController {

    private  static final Logger logger= LoggerFactory.getLogger(TBusineTicketController.class);

    @Autowired
    private TBusineTicketService tBusineTicketService;
    @Autowired
    private CustomizeService customizeService;

    /**
    * 查询TBusineTicket信息
    * @paramtBusineTicket
    * @return
    */
    @PostMapping(value = "/getTBusineTicket")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusineTicket")
    public List<TBusineTicket> getTBusineTicket(@RequestBody TBusineTicket tBusineTicket,@RequestParam("busineIdStr") String busineIdStr){
        List<String> busineIdChange = new ArrayList<>();
        if(busineIdStr!=""){
            busineIdChange = Arrays.asList(busineIdStr.split(","));
        }
        List<Integer> busineIds = new ArrayList<>();
        if(busineIdChange.size()>0 ){
            for(String id :busineIdChange){
                busineIds.add(Integer.valueOf(id));
            }
        }

    return tBusineTicketService.getTBusineTicket(tBusineTicket,busineIds);
    }

    /**
    * 查询TBusineTicket信息(分页)
    * @paramtBusineTicket
    * @return
    */
    @PostMapping(value = "/getTBusineTicketbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TBusineTicket(分页)")
    public PageBean<TBusineTicket> getTBusineTicketbyPage(@RequestBody TBusineTicket tBusineTicket, @RequestParam("parkId") Integer parkId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tBusineTicketService.getTBusineTicketbyPage(tBusineTicket,parkId,page,limit);
    }

    /**
    * 更新TBusineTicket信息
    * @paramtBusineTicket
    * @return
    */
    @PostMapping(value = "/updateTBusineTicket")
    @HttpLog(operationType = "1",modularTypeName = "更新TBusineTicket信息")
    public String UpdateTBusineTicket(@RequestBody TBusineTicket tBusineTicket)
    {
        return tBusineTicketService.UpdateTBusineTicket(tBusineTicket);
    }

    /**
    * 删除TBusineTicket信息
    * @param tBusineTicket
    * @return
    */
    @PostMapping(value = "/deleteTBusineTicket")
    @HttpLog(operationType = "1",modularTypeName = "删除TBusineTicket信息")
    public String DeleteTBusineTicket(@RequestBody TBusineTicket tBusineTicket){
    return tBusineTicketService.DeleteTBusineTicket(tBusineTicket);
    }

    /*查询商户拥有可用的电子券种类*/
    @PostMapping(value = "/getIdsByUsable")
    @HttpLog(operationType = "0",modularTypeName = "查询商户拥有可用的电子券种类")
    public List<Integer> getIdsByUsable(@RequestBody TBusineTicket tBusineTicket){
        return tBusineTicketService.getIdsByUsable(tBusineTicket);
    }

    /*商户电子券授权*/
    @PostMapping(value = "/eleAuthorization")
    @HttpLog(operationType = "1",modularTypeName = "商户电子券授权")
    public String eleAuthorization(@RequestBody TBusineTicket tBusineTicket){
        return tBusineTicketService.eleAuthorization(tBusineTicket);
    }

    /*回购电子券*/
    @PostMapping(value = "/buyBackBusineTicket")
    @HttpLog(operationType = "1",modularTypeName = "回购电子券")
    public String buyBackBusineTicket(@RequestBody TBusineTicket tBusineTicket){
        return tBusineTicketService.buyBackBusineTicket(tBusineTicket);
    }

    /*购买电子券*/
    @PostMapping(value = "/eleBuy")
    @HttpLog(operationType = "1",modularTypeName = "购买电子券")
    public String eleBuy(@RequestBody TBusineTicket tBusineTicket,@RequestParam("ticketNumNew") Integer ticketNumNew){
        return tBusineTicketService.eleBuy(tBusineTicket,ticketNumNew);
    }

    /*修改电子劵批次权限*/
    @PostMapping(value = "/changeIsPay")
    @HttpLog(operationType = "1",modularTypeName = "修改电子劵批次权限")
    public String changeIsPay(@RequestBody TBusineTicket tBusineTicket){
        return tBusineTicketService.changeIsPay(tBusineTicket);
    }

    //通过id查找电子券
    @PostMapping(value = "/getTBusineTicketByID")
    @HttpLog(operationType = "0",modularTypeName = "通过id查找电子券")
    public TBusineTicket getTBusineTicketByID(@RequestParam("id") Integer id){
        return tBusineTicketService.getTBusineTicketByID(id);
    }

    /**
     * 查询近效期的商户电子券
     * @param busineId
     * @param ticketId
     * @return
     */
    @PostMapping(value = "/getTBusineTicketExpireSoon")
    @HttpLog(operationType = "0", modularTypeName = "getTBusineTicketExpireSoon")
    public Integer getTBusineTicketExpireSoon(@RequestParam("busineId") Integer busineId,@RequestParam("ticketId") Integer ticketId) {
        return customizeService.getTBusineTicketExpireSoon(busineId,ticketId);
    }
    /**
     * 查询近效期的商户电子券
     * @param busineCouponGetTicketInfo
     * @return
     */
    @PostMapping(value = "/getTBusineTicketExpireSoonAPI")
    @HttpLog(operationType = "0", modularTypeName = "getTBusineTicketExpireSoonAPI")
    public Integer getTBusineTicketExpireSoonAPI(@RequestBody BusineCouponGetTicketInfo busineCouponGetTicketInfo) {
        return customizeService.getTBusineTicketExpireSoonAPI(busineCouponGetTicketInfo);
    }

    @PostMapping(value = "/test")
    @HttpLog(operationType = "0",modularTypeName = "cs ")
    public ActionRsp test(@RequestParam("name") String name){
        return ActionRspUtil.Success(name);
    }
}