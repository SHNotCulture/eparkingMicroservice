package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkPort;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TParkPortService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TParkPortController类
* @author 谢轩然
* @date 2020/05/29 15:05
*/
@RestController
@RequestMapping("/tParkPort")
public class TParkPortController {

    private  static final Logger logger= LoggerFactory.getLogger(TParkPortController.class);

    @Autowired
    private TParkPortService tParkPortService;

    /**
    * 查询TParkPort信息
    * @paramtParkPort
    * @return
    */
    @PostMapping(value = "/getTParkPort")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkPort")
    public List<TParkPort> getTParkPort(@RequestBody TParkPort tParkPort){
    return tParkPortService.getTParkPort(tParkPort);
    }

    /**
    * 查询TParkPort信息(分页)
    * @paramtParkPort
    * @return
    */
    @PostMapping(value = "/getTParkPortbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TParkPort(分页)")
    public PageBean<TParkPort> getTParkPortbyPage(@RequestBody TParkPort tParkPort, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tParkPortService.getTParkPortbyPage(tParkPort,page,limit);
    }

    /**
    * 更新TParkPort信息
    * @paramtParkPort
    * @return
    */
    @PostMapping(value = "/updateTParkPort")
    @HttpLog(operationType = "1",modularTypeName = "更新TParkPort信息")
    public String UpdateTParkPort(@RequestBody TParkPort tParkPort)
    {
        return tParkPortService.UpdateTParkPort(tParkPort);
    }

    /**
    * 删除TParkPort信息
    * @param tParkPort
    * @return
    */
    @PostMapping(value = "/deleteTParkPort")
    @HttpLog(operationType = "1",modularTypeName = "删除TParkPort信息")
    public String DeleteTParkPort(@RequestBody TParkPort tParkPort){
    return tParkPortService.DeleteTParkPort(tParkPort);
    }

    /**
     * 查询出口的TParkPort信息
     * @param tParkPort
     * @return
     */
    @PostMapping(value = "/getTParkPortOutPort")
    @HttpLog(operationType = "0",modularTypeName = "查询出口的TParkPort信息")
    public List<TParkPort> getTParkPortOutPort(@RequestBody TParkPort tParkPort){
        return tParkPortService.getTParkPortOutPort(tParkPort);
    }


    @PostMapping(value = "/getTParkPortByParkIdAndPortId")
    @HttpLog(operationType = "0",modularTypeName = "查询出口的TParkPort信息")
    public TParkPort getTParkPortByParkIdAndPortId(@RequestParam Integer parkId,@RequestParam Integer portId){
        return tParkPortService.getTParkPortByParkIdAndPortId(parkId,portId);
    }

    /**
     * 查询通道名称
     * @param portType
     * @return
     */
    @PostMapping(value = "/getPortNameListForZtree")
    @HttpLog(operationType = "0",modularTypeName = "查询通道名称")
    public List<TParkPort> getPortNameListForZtree(@RequestParam("parkId") Integer parkId,@RequestParam("portType") String portType){
        return tParkPortService.getPortNameListForZtree(parkId,portType);
    }
}