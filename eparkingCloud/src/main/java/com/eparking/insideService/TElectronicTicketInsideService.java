package com.eparking.insideService;

import com.common.entity.eparkingCloud.TElectronicTicket;
import com.eparking.insideService.impl.TElectronicTicketInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TElectronicTicketInsideService接口
* @author 谢轩然
* @date 2020/04/09 15:01
*/
@FeignClient(value = "eparkingCloudData",fallback = TElectronicTicketInsideServiceImpl.class)
public interface TElectronicTicketInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    @PostMapping(value = client+"/tElectronicTicket/getTElectronicTicket")
    List<TElectronicTicket> getTElectronicTicket(@RequestBody TElectronicTicket tElectronicTicket);

    @PostMapping(value = client+"/tElectronicTicket/FindElectronicTicketByIds")
    List<TElectronicTicket> FindElectronicTicketByIds(@RequestParam("ids") String ids);
    /**
    *查询(分页)tElectronicTicket
    * @param tElectronicTicket
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tElectronicTicket/getTElectronicTicketbyPage")
    PageBean<TElectronicTicket> getTElectronicTicketbyPage(@RequestBody TElectronicTicket tElectronicTicket, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    @PostMapping(value = client+"/tElectronicTicket/updateTElectronicTicket")
    String UpdateTElectronicTicket(@RequestBody TElectronicTicket tElectronicTicket);

    /**
    * 删除tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    @PostMapping(value = client+"/tElectronicTicket/deleteTElectronicTicket")
    String DeleteTElectronicTicket(@RequestBody TElectronicTicket tElectronicTicket);

    @PostMapping(value = client+"/tElectronicTicket/getElectronicTicketByPrimaryKey")
    TElectronicTicket getElectronicTicketByPrimaryKey(@RequestParam("id") Integer id);

    @PostMapping(value = client+"/tElectronicTicket/geteTicketListbyPageAndIds")
    PageBean<TElectronicTicket> geteTicketListbyPageAndIds(@RequestParam("ids") String ids, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}