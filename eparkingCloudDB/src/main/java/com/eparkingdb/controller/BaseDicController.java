package com.eparkingdb.controller;

import com.common.annotation.HttpLog;
import com.common.entity.eparkingCloud.TDicOuttype;
import com.common.entity.eparkingCloud.TDicPrepayType;
import com.common.entity.eparkingCloud.TParkPort;
import com.eparkingdb.service.BaseDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/BaseDic")
public class BaseDicController {
    private  static final Logger logger= LoggerFactory.getLogger(BaseDicController.class);
    @Autowired
    private BaseDicService baseDicService;

    @PostMapping(value = "/getPortName")
    @HttpLog(operationType = "0",modularTypeName = "查询通道名称")
    public List<TParkPort> selectParkPort(@RequestParam("portType") String portType, @RequestParam("parkId") Integer parkId){
        return baseDicService.selectParkPort(portType,parkId);
    }

    @PostMapping(value = "/selectOutType")
    @HttpLog(operationType = "0",modularTypeName = "查询出场方式")
    public List<TDicOuttype> selectOutType(){
        return baseDicService.selectOutType();
    }

    @PostMapping(value = "/selectgetPayType")
    @HttpLog(operationType = "0",modularTypeName = "查询支付方式")
    public List<TDicPrepayType> selectgetPayType(){
        return baseDicService.selectgetPayType();
    }
}
