package com.eparkingdb.controller;

import com.common.entity.ActionRsp;
import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TElectronicTicket;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TElectronicTicketService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Description: TElectronicTicketController类
* @author 谢轩然
* @date 2020/04/09 15:01
*/
@RestController
@RequestMapping("/tElectronicTicket")
public class TElectronicTicketController {

    private  static final Logger logger= LoggerFactory.getLogger(TElectronicTicketController.class);

    @Autowired
    private TElectronicTicketService tElectronicTicketService;

    /**
    * 查询TElectronicTicket信息
    * @paramtElectronicTicket
    * @return
    */
    @PostMapping(value = "/getTElectronicTicket")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronicTicket")
    public List<TElectronicTicket> getTElectronicTicket(@RequestBody TElectronicTicket tElectronicTicket){
        return tElectronicTicketService.getTElectronicTicket(tElectronicTicket);
    }

    /**
    * 查询TElectronicTicket信息(分页)
    * @paramtElectronicTicket
    * @return
    */
    @PostMapping(value = "/getTElectronicTicketbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronicTicket(分页)")
    public PageBean<TElectronicTicket> getTElectronicTicketbyPage(@RequestBody TElectronicTicket tElectronicTicket, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return tElectronicTicketService.getTElectronicTicketbyPage(tElectronicTicket,page,limit);
    }

    /**
    * 更新TElectronicTicket信息
    * @paramtElectronicTicket
    * @return
    */
    @PostMapping(value = "/updateTElectronicTicket")
    @HttpLog(operationType = "1",modularTypeName = "更新TElectronicTicket信息")
    public String UpdateTElectronicTicket(@RequestBody TElectronicTicket tElectronicTicket)
    {
//        tElectronicTicket.setCompanyId(SessionUtil.getCompany().getId());
        return tElectronicTicketService. UpdateTElectronicTicket(tElectronicTicket);
    }

    /**
    * 删除TElectronicTicket信息
    * @param tElectronicTicket
    * @return
    */
    @PostMapping(value = "/deleteTElectronicTicket")
    @HttpLog(operationType = "1",modularTypeName = "删除TElectronicTicket信息")
    public String DeleteTElectronicTicket(@RequestBody TElectronicTicket tElectronicTicket){
    return tElectronicTicketService.DeleteTElectronicTicket( tElectronicTicket);
    }

    /**
     * 查询TElectronicTicket信息
     * @paramtElectronicTicket
     * @return
     */
    @PostMapping(value = "/getElectronicTicketByPrimaryKey")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronicTicket")
    public TElectronicTicket getTElectronicTicketByID(@RequestParam("id") Integer id){
        return tElectronicTicketService.getTElectronicTicketByID(id);
    }

    /**
     * 查询TElectronicTicket信息(分页)
     * @paramtElectronicTicket
     * @return
     */
    @PostMapping(value = "/geteTicketListbyPageAndIds")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronicTicket(分页)")
    public PageBean<TElectronicTicket> geteTicketListbyPageAndIds(@RequestParam("ids") String ids,@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        List<String> idStrList = new ArrayList<>();
        if(ids!=""){
            idStrList = Arrays.asList(ids.split(","));
        }
        List<Integer> idList = new ArrayList<>();
        if(idStrList.size()>0){
            for(String id :idStrList){
                idList.add(Integer.valueOf(id));
            }
        }

        return tElectronicTicketService.geteTicketListbyPageAndIds(idList,page,limit);
    }
    /**
     * 查询TElectronicTicket信息(分页)
     * @paramtElectronicTicket
     * @return
     */
    @PostMapping(value = "/FindElectronicTicketByIds")
    @HttpLog(operationType = "0",modularTypeName = "查询TElectronicTicket(分页)")
    public  List<TElectronicTicket> FindElectronicTicketByIds(@RequestParam("ids")String ids){
        List<String> idStrList = new ArrayList<>();
        if(ids!=""){
            idStrList = Arrays.asList(ids.split(","));
        }
        List<Integer> idList = new ArrayList<>();
        if(idStrList.size()>0){
            for(String id :idStrList){
                idList.add(Integer.valueOf(id));
            }
        }
        return tElectronicTicketService.FindElectronicTicketByIds(idList);
    }

    @PostMapping(value = "/test")
    @HttpLog(operationType = "0",modularTypeName = "测试")
    public ActionRsp test(String name){
        return ActionRspUtil.Success(name);
    }
}