package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TRolePowerNew;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TRolePowerNewService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* @Description: TRolePowerNewController类
* @author 谢轩然
* @date 2020/05/15 15:20
*/
@RestController
@RequestMapping("/tRolePowerNew")
public class TRolePowerNewController {

    private  static final Logger logger= LoggerFactory.getLogger(TRolePowerNewController.class);

    @Autowired
    private TRolePowerNewService tRolePowerNewService;

    /**
    * 查询TRolePowerNew信息
    * @paramtRolePowerNew
    * @return
    */
    @PostMapping(value = "/getTRolePowerNew")
    @HttpLog(operationType = "0",modularTypeName = "查询TRolePowerNew")
    public List<TRolePowerNew> getTRolePowerNew(@RequestBody TRolePowerNew tRolePowerNew){
    return tRolePowerNewService.getTRolePowerNew(tRolePowerNew);
    }

    /**
    * 查询TRolePowerNew信息(分页)
    * @paramtRolePowerNew
    * @return
    */
    @PostMapping(value = "/getTRolePowerNewbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TRolePowerNew(分页)")
    public PageBean<TRolePowerNew> getTRolePowerNewbyPage(@RequestBody TRolePowerNew tRolePowerNew, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tRolePowerNewService.getTRolePowerNewbyPage(tRolePowerNew,page,limit);
    }

    /**
    * 更新TRolePowerNew信息
    * @paramtRolePowerNew
    * @return
    */
    @PostMapping(value = "/updateTRolePowerNew")
    @HttpLog(operationType = "1",modularTypeName = "更新TRolePowerNew信息")
    public String UpdateTRolePowerNew(@RequestBody TRolePowerNew tRolePowerNew)
    {
        return tRolePowerNewService.UpdateTRolePowerNew(tRolePowerNew);
    }

    /**
    * 删除TRolePowerNew信息
    * @param tRolePowerNew
    * @return
    */
    @PostMapping(value = "/deleteTRolePowerNew")
    @HttpLog(operationType = "1",modularTypeName = "删除TRolePowerNew信息")
    public String DeleteTRolePowerNew(@RequestBody TRolePowerNew tRolePowerNew){
    return tRolePowerNewService.DeleteTRolePowerNew(tRolePowerNew);
    }

    /**
     * 通过ID查询角色信息
     * @paramtRolePowerNew
     * @return
     */
    @PostMapping(value = "/getRolePowerById")
    @HttpLog(operationType = "0",modularTypeName = "通过ID查询角色信息")
    public List<TRolePowerNew> getRolePowerById(@RequestBody TRolePowerNew tRolePowerNew){
        return tRolePowerNewService.getRolePowerById(tRolePowerNew);
    }

    /**
     * 通过ID查询角色信息
     * @paramtRolePowerNew
     * @return
     */
    @PostMapping(value = "/selectByPrimaryKey")
    @HttpLog(operationType = "0",modularTypeName = "通过ID查询角色信息")
    public TRolePowerNew selectByPrimaryKey(@RequestParam("id") Integer id){
        return tRolePowerNewService.getTRolePowerNewByID(id);
    }
}