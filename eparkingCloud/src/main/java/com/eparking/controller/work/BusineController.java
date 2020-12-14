package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;
import com.common.entity.eparkingCloud.TBusineTicket;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.enums.ActionRspEnum;
import com.common.exception.ActionRspException;
import com.eparking.insideService.TBusinePayInsideService;
import com.eparking.insideService.TBusineTicketInsideService;
import com.eparking.service.BusinePayService;
import com.eparking.service.BusineService;
import com.eparking.service.ElectronicTbusineTicketService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 23:472018-7-25
 * @Modified By:
 */
@RestController
@RequestMapping(value = "busine")
public class BusineController {
    private static final Logger logger = LoggerFactory.getLogger(BusineController.class);
    @Autowired
    private BusineService busineService;
    @Autowired
    private BusinePayService businePayService;
    @Autowired
    private ElectronicTbusineTicketService electronicTbusineTicketService;
    @Autowired
    private TBusinePayInsideService tBusinePayInsideService;
    @Autowired
    private TBusineTicketInsideService tBusineTicketInsideService;

    /**
     * 查询商户信息
     *
     * @return
     */
    @PostMapping(value = "/getTBusinebyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询商户信息(分页)")
    public ControllerRsp getBusinebyPage(TBusine tBusine, Integer page, Integer limit) {
        TCompanyUser user = SessionUtil.getUser();
        tBusine.setCompanyId(user.getCompanyId());
        tBusine.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(busineService.getBusinebyPage(tBusine, page, limit));
    }

    /**
     * 查询商户信息
     *
     * @return
     */
    @PostMapping(value = "/getTBusine")
    @HttpLog(operationType = "0", modularTypeName = "查询商户信息")
    public ActionRsp getTBusine(TBusine tBusine) {
        TCompanyUser user = SessionUtil.getUser();
        tBusine.setCompanyId(user.getCompanyId());
        tBusine.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(busineService.getBusine(tBusine));
    }

    /**
     * 查询商户充值记录
     *
     * @return
     */
    @PostMapping(value = "/getBusinePaybyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询商户充值记录")
    public ControllerRsp getBusinePaybyPage(Integer busineId, String payTimeBegin, String payTimeEnd, Integer page, Integer limit) {
        TCompanyUser user = SessionUtil.getUser();
        TBusinePay tBusinePay = new TBusinePay();
        tBusinePay.setBusineId(busineId);
        tBusinePay.setCompanyId(user.getCompanyId());
        tBusinePay.setParkId(SessionUtil.getParkId());
//        logger.info(payTimeBegin+"  "+payTimeEnd);
        if (busineId == null) {
            busineId = 0;
        }
        if (payTimeBegin == null) {
            payTimeBegin = "";
        }
        if (payTimeEnd == null) {
            payTimeEnd = "";
        }
//        return ControllerRspUtil.Success(businePayService.getTBusinePaybyPage(tBusinePay,page,limit,beginTime,endTime));
        return ControllerRspUtil.Success(tBusinePayInsideService.getTBusinePaybyPage(tBusinePay, busineId, payTimeBegin, payTimeEnd, page, limit));

    }


    /**
     * 更新商户信息
     *
     * @param tBusine
     * @return
     */
    @PostMapping(value = "/updateBusine")
    @HttpLog(operationType = "1", modularTypeName = "更新商户信息")
    public ActionRsp UpdateBusine(TBusine tBusine, HttpServletRequest request) {
        String isAuto = request.getParameter("isAuto");
        if (isAuto != null) {
            tBusine.setIsAuto(1);
        } else {
            tBusine.setIsAuto(0);
        }
        TCompanyUser user = SessionUtil.getUser();
        if (tBusine.getIsfree() == null) {
            tBusine.setIsfree(0);
        }
        tBusine.setBusineType(0);
//        logger.info("chushijine: "+tBusine.getInitialAmount().toString());
        tBusine.setCompanyId(user.getCompanyId());
        tBusine.setParkId(SessionUtil.getParkId());
//        logger.info("bus: "+JsonUtil.beanToJson(tBusine));
        return ActionRspUtil.Success(busineService.UpdateBusine(tBusine));
    }

    /**
     * 修改商户密码
     *
     * @param password
     * @param newPassword
     * @param resetPassword
     * @return
     */
    @PostMapping(value = "/changePassword")
    @HttpLog(operationType = "1", modularTypeName = "修改商户密码")
    public ActionRsp changePassword(Integer id, String password, String newPassword, String resetPassword) {
        TCompanyUser nowUser = SessionUtil.getUser();
//        logger.info("nowUser:" + nowUser.toString());
        TBusine tBusine = new TBusine();
        tBusine.setId(id);
        TBusine tBusineChange;
        List<TBusine> tBusineList = busineService.getBusine(tBusine);
        if (tBusineList.size() > 0) {
//            tBusineChange = JsonUtil.jsonToBean(JsonUtil.beanToJson(tBusineList.get(0)), TBusine.class);
            tBusineChange = tBusineList.get(0);
            if (tBusineChange.getPassword().equals(MD5Util.MD5Encode(password))) {
                if (newPassword.equals(resetPassword)) {
                    tBusineChange.setPassword(MD5Util.MD5Encode(newPassword));
                    busineService.UpdateBusine(tBusineChange);
                } else {
                    throw new ActionRspException(ActionRspEnum.Reset_ERROR2);
                }

            } else {
                throw new ActionRspException(ActionRspEnum.Reset_ERROR1);
            }

        } else {
            throw new ActionRspException(ActionRspEnum.Reset_ERROR1);
        }

        return ActionRspUtil.Success("修改成功");
    }

    /**
     * 重置商户密码
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/resetPassword")
    @HttpLog(operationType = "1", modularTypeName = "重置商户密码")
    public ActionRsp resetPassword(Integer id) {
        TCompanyUser user = SessionUtil.getUser();
        TBusine tBusine = new TBusine();
        tBusine.setId(id);
        tBusine.setPassword(MD5Util.MD5Encode("123456"));
        tBusine.setCompanyId(user.getCompanyId());
        TBusine tBusineRes = busineService.selectByPrimaryKey(id);
        tBusine.setAccount(tBusineRes.getAccount());
        tBusine.setParkId(tBusineRes.getParkId());
        String result = busineService.UpdateBusine(tBusine);
        if (result.equals("更新成功")) {
            result = "密码重置成功,初始密码为123456,,请自行更改密码后使用！";
        }
        return ActionRspUtil.Success(result);
    }

    /**
     * 商户充值
     *
     * @param tBusinePay
     * @return
     */
    @PostMapping(value = "/busineRecharge")
    @HttpLog(operationType = "1", modularTypeName = "商户充值")
    public ActionRsp BusineRecharge(TBusinePay tBusinePay, String type) {
        TCompanyUser user = SessionUtil.getUser();
        tBusinePay.setCompanyId(user.getCompanyId());
        tBusinePay.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(busineService.BusineRecharge(tBusinePay, type));

    }

    /**
     * 删除商户信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteBusine")
    @HttpLog(operationType = "1", modularTypeName = "删除商户信息")
    public ActionRsp DeleteBusine(Integer id) {
        TBusine tBusine = new TBusine();
        tBusine.setId(id);
        return ActionRspUtil.Success(busineService.DeleteBusine(tBusine));
    }

    /**
     * 商户授权电子券
     *
     * @param
     * @return
     */
    @PostMapping(value = "/eleAuthorization")
    @HttpLog(operationType = "1", modularTypeName = "购买电子券")
    public ActionRsp eleAuthorization(TBusineTicket tBusineTicket) {
        return ActionRspUtil.Success(electronicTbusineTicketService.eleAuthorization(tBusineTicket));
    }

}
