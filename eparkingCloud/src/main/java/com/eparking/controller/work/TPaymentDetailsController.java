package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TPaymentDetails;
import com.eparking.service.TPaymentDetailsService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: TPaymentDetailsController类
* @author 谢轩然
* @date 2020/10/13 17:31
*/
@RestController
@RequestMapping("/tPaymentDetails")
public class TPaymentDetailsController {

    private  static final Logger logger= LoggerFactory.getLogger(TPaymentDetailsController.class);

    @Autowired
    private TPaymentDetailsService tPaymentDetailsService;

    /**
    * 查询TPaymentDetails信息
    * @paramtPaymentDetails
    * @return
    */
    @PostMapping(value = "/getTPaymentDetails")
    @HttpLog(operationType = "0",modularTypeName = "查询TPaymentDetails")
    public ActionRsp getTPaymentDetails(TPaymentDetails tPaymentDetails){
        tPaymentDetails.setParkId(SessionUtil.getParkId());
    return ActionRspUtil.Success(tPaymentDetailsService.getTPaymentDetails(tPaymentDetails));
    }

    /**
    * 查询TPaymentDetails信息(分页)
    * @paramtPaymentDetails
    * @return
    */
    @PostMapping(value = "/getTPaymentDetailsbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TPaymentDetails(分页)")
    public ControllerRsp getTPaymentDetailsbyPage(TPaymentDetails tPaymentDetails, Integer page, Integer limit,HttpServletRequest request){
        tPaymentDetails.setParkId(SessionUtil.getParkId());
        tPaymentDetails.setOrderId((request.getParameter("orderId")==null)?"":(request.getParameter("orderId")));
    return ControllerRspUtil.Success(tPaymentDetailsService.getTPaymentDetailsbyPage(tPaymentDetails,page,limit));
    }

    /**
    * 更新TPaymentDetails信息
    * @paramtPaymentDetails
    * @return
    */
    @PostMapping(value = "/updateTPaymentDetails")
    @HttpLog(operationType = "1",modularTypeName = "更新TPaymentDetails信息")
    public ActionRsp UpdateTPaymentDetails(TPaymentDetails tPaymentDetails)
    {
        return ActionRspUtil.Success(tPaymentDetailsService.UpdateTPaymentDetails(tPaymentDetails));
    }

    /**
    * 删除TPaymentDetails信息
    * @param tPaymentDetails
    * @return
    */
    @PostMapping(value = "/deleteTPaymentDetails")
    @HttpLog(operationType = "1",modularTypeName = "删除TPaymentDetails信息")
    public ActionRsp DeleteTPaymentDetails(TPaymentDetails tPaymentDetails){
    return ActionRspUtil.Success(tPaymentDetailsService.DeleteTPaymentDetails(tPaymentDetails));
    }
}