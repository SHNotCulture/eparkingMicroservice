package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBlacklist;
import com.eparking.service.BlackListService;
import com.common.util.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "blackList")
public class BlackListController {
    private  static final Logger logger= LoggerFactory.getLogger(BlackListController.class);
    @Autowired
    private BlackListService blackListService;
    /**
     * 查询黑名单列表
     */
    @PostMapping(value = "/getBlackList")
    @HttpLog(operationType = "0",modularTypeName = "查询黑名单列表")
    public ControllerRsp getBlackList(TBlacklist tBlackList,Integer page, Integer limit){
        tBlackList.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(blackListService.getBlackListbyPage(tBlackList,page,limit));
    }
    /**
     * 编辑黑名单
     */
    @PostMapping(value = "/updateBlackList")
    @HttpLog(operationType = "1",modularTypeName = "编辑黑名单")
    public ActionRsp updateBlackList(TBlacklist tBlacklist){
        tBlacklist.setParkId(SessionUtil.getParkId());
        tBlacklist.setCreateTime(DateUtil.getCurDateTime());
//        tBlacklist.setCreatePerson(JSONObject.fromObject(request.getSession().getAttribute(Common.User).toString()).getString("userAccout"));
        tBlacklist.setCreatePerson(JSONObject.fromObject(SessionUtil.getUser()).getString("userAccout"));
        return ActionRspUtil.Success(blackListService.updateBlackList(tBlacklist));

    }
    /**
     * 删除黑名单
     */
    @PostMapping(value = "/deleteBlackList")
    @HttpLog(operationType = "1",modularTypeName = "删除黑名单")
    public ActionRsp deleteBlackList(TBlacklist tBlacklist){
        return ActionRspUtil.Success(blackListService.deleteBlackList(tBlacklist));
    }

}
