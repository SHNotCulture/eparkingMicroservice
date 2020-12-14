package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompany;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.insideService.TCompanyInsideService;
import com.eparking.service.CompanyService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lishuhan
 * @Description:物业公司Controller
 * @Date Create in 16:542018-7-16
 * @Modified By:
 */
@RestController
@RequestMapping(value = "comPany")
public class CompanyController {
    private  static final Logger logger= LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TCompanyInsideService tCompanyInsideService;

    /**
     * 查询全部物业信息
     * @return
     */
    @PostMapping(value = "/getComPany")
    @HttpLog(operationType = "0",modularTypeName = "查询全部物业信息")
    public ActionRsp getCompany(TCompany tCompany){
        TCompanyUser user= SessionUtil.getUser();
        if(user.getCompanyId()!=0){
            tCompany.setId(user.getCompanyId());
        }

//        return ActionRspUtil.Success(tCompanyInsideService.getTCompany(tCompany));
        return ActionRspUtil.Success(companyService.getCompany(tCompany));
    }
    /**
     * 查询全部物业信息(分页)
     * @return
     */
    @PostMapping(value = "/getCompanybyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询全部物业信息(分页)")
    public ControllerRsp getCompanybyPage(TCompany tCompany, Integer page, Integer limit){
        TCompanyUser user= SessionUtil.getUser();
        //tCompany.setId(user.getCompanyId());
//        return tCompanyInsideService.getTCompanybyPage(tCompany,page,limit);
        return ControllerRspUtil.Success(companyService.getCompanybyPage(tCompany, page, limit));
    }
    /**
     * 通过ID查询物业信息
     * @return
     */
    @PostMapping(value = "/getComPanybyId")
    @HttpLog(operationType = "0",modularTypeName = "通过ID查询物业信息")
    public ActionRsp getComPanybyId(HttpServletRequest request){
        TCompany tCompany= SessionUtil.getCompany();
        //tCompany.setId(user.getCompanyId());
//        return ActionRspUtil.Success(tCompanyInsideService.getTCompanyById(tCompany.getId()));
        return ActionRspUtil.Success(companyService.getCompanyById(tCompany.getId()));
    }
    /**
     * 更新物业信息
     * @param tCompany
     * @return
     */
    @PostMapping(value = "/updateComPany")
    @HttpLog(operationType = "1",modularTypeName = "更新物业信息")
    public ActionRsp UpdateComPany(TCompany tCompany)
    {
        TCompanyUser user= SessionUtil.getUser();
        if(tCompany.getAutoRenew()==null){
            tCompany.setAutoRenew(0);
        }
        if(tCompany.getStatus()==null){
            tCompany.setStatus(0);
        }
        //tCompany.setId(user.getCompanyId());
//        return ActionRspUtil.Success(tCompanyInsideService.UpdateTCompany(tCompany));
        return ActionRspUtil.Success(companyService.UpdateComPany(tCompany));
    }

    /**
     * 删除物业信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteComPany")
    @HttpLog(operationType = "1",modularTypeName = "删除物业信息")
    public ActionRsp DeleteComPany(Integer id){
        TCompany tCompany = new TCompany();
        tCompany.setId(id);
//        return ActionRspUtil.Success(tCompanyInsideService.DeleteTCompany(tCompany));
        return ActionRspUtil.Success(companyService.DeleteComPany(tCompany));
    }
}
