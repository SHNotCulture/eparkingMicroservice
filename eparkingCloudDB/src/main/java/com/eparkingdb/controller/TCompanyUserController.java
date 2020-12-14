package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCompanyUserService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TCompanyUserController类
* @author 李书瀚
* @date 2020/04/08 17:24
*/
@RestController
@RequestMapping("/tCompanyUser")
public class TCompanyUserController {

    private  static final Logger logger= LoggerFactory.getLogger(TCompanyUserController.class);

    @Autowired
    private TCompanyUserService tCompanyUserService;

    /**
    * 查询TCompanyUser信息
    * @paramtCompanyUser
    * @return
    */
    @PostMapping(value = "/getTCompanyUser")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyUser")
    public List<TCompanyUser> getTCompanyUser(@RequestBody TCompanyUser tCompanyUser){
    return tCompanyUserService.getTCompanyUser(tCompanyUser);
    }

    /**
    * 查询TCompanyUser信息(分页)
    * @paramtCompanyUser
    * @return
    */
    @PostMapping(value = "/getTCompanyUserbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyUser(分页)")
    public PageBean<TCompanyUser> getTCompanyUserbyPage(@RequestBody TCompanyUser tCompanyUser, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tCompanyUserService.getTCompanyUserbyPage(tCompanyUser,page,limit);
    }

    /**
    * 更新TCompanyUser信息
    * @paramtCompanyUser
    * @return
    */
    @PostMapping(value = "/updateTCompanyUser")
    @HttpLog(operationType = "1",modularTypeName = "更新TCompanyUser信息")
    public String UpdateTCompanyUser(@RequestBody TCompanyUser tCompanyUser)
    {
        return tCompanyUserService.UpdateTCompanyUser(tCompanyUser);
    }

    /**
    * 删除TCompanyUser信息
    * @param tCompanyUser
    * @return
    */
    @PostMapping(value = "/deleteTCompanyUser")
    @HttpLog(operationType = "1",modularTypeName = "删除TCompanyUser信息")
    public String DeleteTCompanyUser(@RequestBody TCompanyUser tCompanyUser){
    return tCompanyUserService.DeleteTCompanyUser( tCompanyUser);
    }

    @PostMapping(value = "/CheckPassword")
    @HttpLog(operationType = "1",modularTypeName = "核对登录用户信息")
    public TCompanyUser CheckPassword(@RequestBody TCompanyUser tCompanyUser){
        return tCompanyUserService.CheckPassword(tCompanyUser);
    }

    @PostMapping(value = "/getTCompanyUserByID")
    @HttpLog(operationType = "0",modularTypeName = "根据id查询登录用户信息")
    public TCompanyUser getTCompanyUserByID(@RequestParam("id") Integer id){
        return tCompanyUserService.getTCompanyUserByID(id);
    }
}