package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBindingParking;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TBindingParkingService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description: TBindingParkingController类
* @author 谢轩然
* @date 2020/04/08 18:18
*/
@RestController
@RequestMapping("/tBindingParking")
public class TBindingParkingController {

    private  static final Logger logger= LoggerFactory.getLogger(TBindingParkingController.class);

    @Autowired
    private TBindingParkingService tBindingParkingService;

    /**
    * 查询TBindingParking信息
    * @paramtBindingParking
    * @return
    */
    @PostMapping(value = "/getTBindingParking")
    @HttpLog(operationType = "0",modularTypeName = "查询TBindingParking")
    public List<TBindingParking> getTBindingParking(@RequestBody TBindingParking tBindingParking, HttpServletRequest request){
    return tBindingParkingService.getTBindingParking(tBindingParking);
    }

    /**
    * 查询TBindingParking信息(分页)
    * @paramtBindingParking
    * @return
    */
    @PostMapping(value = "/getTBindingParkingbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TBindingParking(分页)")
    public PageBean<TBindingParking> getTBindingParkingbyPage(@RequestBody TBindingParking tBindingParking, HttpServletRequest request, Integer page, Integer limit){
    return tBindingParkingService.getTBindingParkingbyPage(tBindingParking,page,limit);
    }

    /**
    * 更新TBindingParking信息
    * @paramtBindingParking
    * @return
    */
    @PostMapping(value = "/updateTBindingParking")
    @HttpLog(operationType = "1",modularTypeName = "更新TBindingParking信息")
    public String UpdateTBindingParking(@RequestBody TBindingParking tBindingParking,HttpServletRequest request)
    {
        return tBindingParkingService. UpdateTBindingParking(tBindingParking);
    }

    /**
    * 删除TBindingParking信息
    * @param tBindingParking
    * @return
    */
    @PostMapping(value = "/deleteTBindingParking")
    @HttpLog(operationType = "1",modularTypeName = "删除TBindingParking信息")
    public String DeleteTBindingParking(@RequestBody TBindingParking tBindingParking){
    return tBindingParkingService.DeleteTBindingParking( tBindingParking);
    }
}