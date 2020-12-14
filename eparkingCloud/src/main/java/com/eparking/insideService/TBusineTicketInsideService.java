package com.eparking.insideService;

import com.common.entity.eparkingCloud.TBusineTicket;
import com.eparking.insideService.impl.TBusineTicketInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
* @Description: TBusineTicketInsideService接口
* @author 谢轩然
* @date 2020/04/15 11:39
*/
@FeignClient(value = "eparkingCloudData",fallback = TBusineTicketInsideServiceImpl.class)
public interface TBusineTicketInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tBusineTicket
    * @param tBusineTicket
    * @return
    */
    @PostMapping(value = client+"/tBusineTicket/getTBusineTicket")
    List<TBusineTicket> getTBusineTicket(@RequestBody TBusineTicket tBusineTicket, @RequestParam("busineIdStr") String busineIdStr);

    /**
    *查询(分页)tBusineTicket
    * @param tBusineTicket
    * @param page
    * @param limit
    * @returngetTBusineTicketbyPage
    */
    @PostMapping(value = client+"/tBusineTicket/getTBusineTicketbyPage")
    PageBean<TBusineTicket> getTBusineTicketbyPage(@RequestBody TBusineTicket tBusineTicket, @RequestParam("parkId") Integer parkId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tBusineTicket
    * @param tBusineTicket
    * @return
    */
    @PostMapping(value = client+"/tBusineTicket/updateTBusineTicket")
    String UpdateTBusineTicket(@RequestBody TBusineTicket tBusineTicket);

    /**
    * 删除tBusineTicket
    * @param tBusineTicket
    * @return
    */
    @PostMapping(value = client+"/tBusineTicket/deleteTBusineTicket")
    String DeleteTBusineTicket(@RequestBody TBusineTicket tBusineTicket);


    /*查询商户拥有可用的电子券种类*/
    @PostMapping(value = client+"/tBusineTicket/getIdsByUsable")
    List<Integer> getIdsByUsable(@RequestBody TBusineTicket tBusineTicket);

    /*根据电子券id查询最接近过期的电子券批次号*/
    @PostMapping(value = client+"/tBusineTicket/getTBusineTicketExpireSoon")
    Integer getTBusineTicketExpireSoon(@RequestParam("busineId") Integer busineId, @RequestParam("ticketId") Integer ticketId);

    /*授权电子券*/
    @PostMapping(value = client+"/tBusineTicket/eleAuthorization")
    String eleAuthorization(@RequestBody TBusineTicket tBusineTicket);

    /*回购电子券*/
    @PostMapping(value = client+"/tBusineTicket/buyBackBusineTicket")
    String buyBackBusineTicket(@RequestBody TBusineTicket tBusineTicket);

    /*购买电子券*/
    @PostMapping(value = client+"/tBusineTicket/eleBuy")
    String eleBuy(@RequestBody TBusineTicket tBusineTicket, @RequestParam("ticketNumNew") Integer ticketNumNew);

    /*修改电子劵批次权限*/
    @PostMapping(value = client+"/tBusineTicket/changeIsPay")
    String changeIsPay(@RequestBody TBusineTicket tBusineTicket);

    @PostMapping(value = client+"/tBusineTicket/getTBusineTicketByID")
    TBusineTicket getTBusineTicketByID(@RequestParam("id") Integer id);
}