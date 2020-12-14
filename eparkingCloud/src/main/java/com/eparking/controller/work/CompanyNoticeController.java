package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyNotice;
import com.eparking.insideService.TCompanyNoticeInsideService;
import com.eparking.service.CompanyNoticeService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyNotice")
public class CompanyNoticeController {
    private  static final Logger logger= LoggerFactory.getLogger(CompanyNoticeController.class);

    @Autowired
    private CompanyNoticeService companyNoticeService;
    @Autowired
    private TCompanyNoticeInsideService tCompanyNoticeInsideService;

    @PostMapping(value = "/getCompanyNoticeList")
    @HttpLog(operationType = "0",modularTypeName = "查询物业公告列表")
    public ControllerRsp getCompanyNoticeList(TCompanyNotice tCompanyNotice,Integer page, Integer limit){
        tCompanyNotice.setCompanyId(SessionUtil.getCompany().getId());
        tCompanyNotice.setParkId(SessionUtil.getParkId());
//        return ControllerRspUtil.Success(companyNoticeService.getCompanyNoticebyPage(tCompanyNotice,page,limit));
        return tCompanyNoticeInsideService.getTCompanyNoticebyPage(tCompanyNotice,page,limit);
    }


    @PostMapping(value = "/updateCompanyNoticeList")
    @HttpLog(operationType = "1",modularTypeName = "编辑公告信息")
    public ActionRsp updateCompanyNoticeList(TCompanyNotice tCompanyNotice){
        tCompanyNotice.setCompanyId(SessionUtil.getCompany().getId());
        tCompanyNotice.setMemberId("");
        tCompanyNotice.setParkId(SessionUtil.getParkId());
        tCompanyNotice.setUpdateTime(DateUtil.getCurDateTime());
        tCompanyNotice.setCreateTime(DateUtil.getCurDateTime());
        tCompanyNotice.setUserId(SessionUtil.getUser().getId());
//        return ActionRspUtil.Success(companyNoticeService.updateCompanyNotice(tCompanyNotice));
        return tCompanyNoticeInsideService.UpdateTCompanyNotice(tCompanyNotice);
    }


    @PostMapping(value = "/postNotice")
    @HttpLog(operationType = "1",modularTypeName = "推送公告信息")
    public ActionRsp postNotice(String memberIds, String noticeIds){
        System.out.println("memberIds: "+memberIds+"noticeIds: "+noticeIds);
        String[] memberId = memberIds.split(",");
        for(int i=0;i<memberId.length;i++){
            for(String a:noticeIds.split(",")){
    //
            }
        }

        return ActionRspUtil.Success();
    }
}
