package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompany;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCompanyService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TCompanyController类
* @author 谢轩然
* @date 2020/04/09 14:42
*/
@RestController
@RequestMapping("/tCompany")
public class TCompanyController {

    private  static final Logger logger= LoggerFactory.getLogger(TCompanyController.class);

    @Autowired
    private TCompanyService tCompanyService;

    /**
    * 查询TCompany信息
    * @paramtCompany
    * @return
    */
    @PostMapping(value = "/getTCompany")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompany")
    public List<TCompany> getTCompany(@RequestBody TCompany tCompany){
    return tCompanyService.getTCompany(tCompany);
    }

    /**
    * 查询TCompany信息(分页)
    * @paramtCompany
    * @return
    */
    @PostMapping(value = "/getTCompanybyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompany(分页)")
    public PageBean<TCompany> getTCompanybyPage(@RequestBody TCompany tCompany, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tCompanyService.getTCompanybyPage(tCompany,page,limit);
    }

    /**
    * 更新TCompany信息
    * @paramtCompany
    * @return
    */
    @PostMapping(value = "/updateTCompany")
    @HttpLog(operationType = "1",modularTypeName = "更新TCompany信息")
    public String UpdateTCompany(@RequestBody TCompany tCompany)
    {
        return tCompanyService.UpdateTCompany(tCompany);
    }

    /**
    * 删除TCompany信息
    * @param tCompany
    * @return
    */
    @PostMapping(value = "/deleteTCompany")
    @HttpLog(operationType = "1",modularTypeName = "删除TCompany信息")
    public String DeleteTCompany(@RequestBody TCompany tCompany){
    return tCompanyService.DeleteTCompany(tCompany);
    }

    /**
     * 根据id查询tCompany
     * @param id
     * @return
     */
    @PostMapping(value = "/getTCompanyById")
    @HttpLog(operationType = "0",modularTypeName = "根据id查询tCompany")
    public TCompany getTCompanyById(@RequestParam("id") Integer id){
        return tCompanyService.getTCompanyByID(id);
    }

    /**
     * 查询是否存在重复物业信息
     * @paramtCompany
     * @return
     */
    @PostMapping(value = "/getCompanyDistnict")
    @HttpLog(operationType = "0",modularTypeName = "查询是否存在重复物业信息")
    public List<TCompany> getCompanyDistnict(@RequestBody TCompany tCompany){
        return tCompanyService.getCompanyDistnict(tCompany);
    }
}