package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.eparking.service.TCarownerPaymentService;
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
* @Description: TCarownerPaymentController类
* @author 谢轩然
* @date 2020/09/11 16:29
*/
@RestController
@RequestMapping("tCarownerPayment")
public class TCarownerPaymentController {

    private  static final Logger logger= LoggerFactory.getLogger(TCarownerPaymentController.class);

    @Autowired
    private TCarownerPaymentService tCarownerPaymentService;

    /**
    * 查询TCarownerPayment信息
    * @paramtCarownerPayment
    * @return
    */
    @PostMapping(value = "/getTCarownerPayment")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarownerPayment")
    public ActionRsp getTCarownerPayment(TCarownerPayment tCarownerPayment, HttpServletRequest request){
        tCarownerPayment.setParkId(SessionUtil.getParkId());
        String beginDate = (request.getParameter("beginDate")==null)?"":(request.getParameter("beginDate"));
        String endDate = (request.getParameter("endDate")==null)?"":(request.getParameter("endDate"));
    return ActionRspUtil.Success(tCarownerPaymentService.getTCarownerPayment(tCarownerPayment,beginDate,endDate));
    }

    /**
    * 查询TCarownerPayment信息(分页)
    * @paramtCarownerPayment
    * @return
    */
    @PostMapping(value = "/getTCarownerPaymentbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarownerPayment(分页)")
    public ControllerRsp getTCarownerPaymentbyPage(TCarownerPaymentCus tCarownerPaymentCus, Integer page, Integer limit, HttpServletRequest request){
        tCarownerPaymentCus.setParkId(SessionUtil.getParkId());
/*        String beginDate = (request.getParameter("beginDate")==null)?"":(request.getParameter("beginDate"));
        String endDate = (request.getParameter("endDate")==null)?"":(request.getParameter("endDate"));*/
        String valueType = (request.getParameter("valueType")==null)?"":(request.getParameter("valueType"));
        String priceMin = "";
        String priceMax= "";
        if(request.getParameter("priceMin")!=null && !request.getParameter("priceMin").equals("")){
            priceMin=request.getParameter("priceMin");
        }
        if(request.getParameter("priceMax")!=null && !request.getParameter("priceMax").equals("")){
            priceMax=request.getParameter("priceMax");
        }
        //初始化查询
        switch (valueType){
            case "1":
                //应付
                if(!priceMin.equals("")){
                    tCarownerPaymentCus.setNeedPayMin(Double.valueOf(priceMin));
                }
                if(!priceMax.equals("")){
                    tCarownerPaymentCus.setNeedPayMax(Double.valueOf(priceMax));
                }

                break;
            case "2":
                //实付
                if(!priceMin.equals("")){
                    tCarownerPaymentCus.setActualPayMin(Double.valueOf(priceMin));
                }
                if(!priceMax.equals("")){
                    tCarownerPaymentCus.setActualPayMax(Double.valueOf(priceMax));
                }
                break;
            case "3":
                //钱包余额
                    if(priceMin.contains(".")){
                        tCarownerPaymentCus.setWalletBalanceMin(priceMin);
                    }else{
                        tCarownerPaymentCus.setWalletBalanceMin(priceMin+".0");
                    }

                    if(priceMax.contains(".")){
                        tCarownerPaymentCus.setWalletBalanceMax(priceMax);
                    }else{
                        tCarownerPaymentCus.setWalletBalanceMax(priceMax+".0");
                    }

                break;
            default:
                if(!priceMin.equals("")){
                    tCarownerPaymentCus.setNeedPayMin(Double.valueOf(priceMin));
                    tCarownerPaymentCus.setActualPayMin(Double.valueOf(priceMin));
                    if(priceMin.contains(".")){
                        tCarownerPaymentCus.setWalletBalanceMin(priceMin);
                    }else{
                        tCarownerPaymentCus.setWalletBalanceMin(priceMin+".0");
                    }
                }
                if(!priceMax.equals("")){
                    tCarownerPaymentCus.setNeedPayMax(Double.valueOf(priceMax));
                    tCarownerPaymentCus.setActualPayMax(Double.valueOf(priceMax));
                    if(priceMax.contains(".")){
                        tCarownerPaymentCus.setWalletBalanceMax(priceMax);
                    }else{
                        tCarownerPaymentCus.setWalletBalanceMax(priceMax+".0");
                    }
                }
                break;
        }
    return ControllerRspUtil.Success(tCarownerPaymentService.getTCarownerPaymentbyPage(tCarownerPaymentCus,page,limit));
    }

    /**
    * 更新TCarownerPayment信息
    * @paramtCarownerPayment
    * @return
    */
    @PostMapping(value = "/updateTCarownerPayment")
    @HttpLog(operationType = "1",modularTypeName = "更新TCarownerPayment信息")
    public ActionRsp UpdateTCarownerPayment(TCarownerPayment tCarownerPayment)
    {
        return ActionRspUtil.Success(tCarownerPaymentService.UpdateTCarownerPayment(tCarownerPayment));
    }

    /**
    * 删除TCarownerPayment信息
    * @param tCarownerPayment
    * @return
    */
    @PostMapping(value = "/deleteTCarownerPayment")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarownerPayment信息")
    public ActionRsp DeleteTCarownerPayment(TCarownerPayment tCarownerPayment){
    return ActionRspUtil.Success(tCarownerPaymentService.DeleteTCarownerPayment(tCarownerPayment));
    }

}