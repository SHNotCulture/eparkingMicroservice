package com.eparking.controller.work;


import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.TBusinePayInsideService;
import com.eparking.service.*;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 10:082018-10-10
 * @Modified By:
 */
@RestController
@RequestMapping(value = "Base")
public class BaseController {
    private  static final Logger logger= LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private ElectronPaymentService electronPaymentService;
    @Autowired
    private CashPaymentService cashPaymentService;
    @Autowired
    private ParkReportService parkReportService;
    @Autowired
    private ParkReportMonthService parkReportMonthService;
    @Autowired
    private ParkReportYearService parkReportYearService;
    @Autowired
    private BusinePayService businePayService;
    @Autowired
    private TruckSpaceService truckSpaceService;
    @Autowired
    private PresentCarService presentCarService;
    @Autowired
    private ParkingRecordService parkingRecordService;
    @Autowired
    private MonthlyCarService monthlyCarService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private CarPaymentService carPaymentService;
    @Autowired
    private PropertyHouseholdInfoService propertyHouseholdInfoService;
    @Autowired
    private TBusinePayInsideService tBusinePayInsideService;
    @Autowired
    private TCarownerPaymentService tCarownerPaymentService;
    @Autowired
    private TCarWalletReportService tCarWalletReportService;
    @Autowired
    private TChargeDetailsReportService tChargeDetailsReportService;

    /**
     * 导入车位信息
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/import")
    @HttpLog(operationType = "1",modularTypeName = "导入车位信息")
    public ActionRsp importCarPort(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        TCompanyUser user= SessionUtil.getUser();
        Integer companyID=user.getCompanyId();
        Integer parkID=SessionUtil.getParkId();
        return ActionRspUtil.Success(truckSpaceService.ImportTruckSpace(fileName,file,parkID,companyID));
    }
    /**
     * 导入登记车信息
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/importMonthlyCar")
    @HttpLog(operationType = "1",modularTypeName = "导入登记车信息")
    public ActionRsp importMonthlyCar(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
//        Integer parkID=SessionUtil.getParkId();
        Integer parkID = Integer.valueOf(request.getParameter("parkId"));
        String payRule = request.getParameter("payRule");
        String portId = request.getParameter("portId");
        return ActionRspUtil.Success(monthlyCarService.importMonthlyCar(fileName,file,parkID,payRule,portId));
    }

    /**
     * 导入住户信息
     * @param file
     * @param request
     * @return
     */
/*    @PostMapping("/importMonthlyCar")
    @HttpLog(operationType = "1",modularTypeName = "导入住户信息")
    public ActionRsp importHouseHoldInfo(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        Integer parkID=SessionUtil.getParkId();
        return ActionRspUtil.Success(propertyHouseholdInfoService.importHouseHoldInfo(fileName,file,parkID));
    }*/

    /**
     * 导出车位信息
     * @param tTruckSpace
     * @param request
     * @param response
     */
    @GetMapping("/exportListforTTruckSpace")
    @HttpLog(operationType = "0",modularTypeName = "导出车位信息")
    public void  exportListforTTruckSpace(TTruckSpace tTruckSpace, HttpServletRequest request, HttpServletResponse response){
        try {
            TCompanyUser user= SessionUtil.getUser();
            tTruckSpace.setCompanyId(user.getCompanyId());
            tTruckSpace.setParkId(SessionUtil.getParkId());
            truckSpaceService.exportList(tTruckSpace,"车位信息",response);
        } catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }
    /**
     *导出商户充值记录
     * @param beginTime
     * @param endTime
     * @param busineId
     * @param response
     */
    @GetMapping("/exportListforBusinePay")
    @HttpLog(operationType = "0",modularTypeName = "导出商户充值记录")
    public void  exportListforBusinePay(String beginTime,String endTime,Integer busineId, HttpServletResponse response){
        try {
            Integer parkId=SessionUtil.getParkId();
            TBusinePay tBusinePay=new TBusinePay();
            tBusinePay.setParkId(parkId);
            tBusinePay.setBusineId(busineId);
            if(beginTime==null){
                beginTime="";
            }
            if(endTime==null){
                endTime="";
            }
            businePayService.exportList(tBusinePay,busineId,beginTime,endTime,"商户充值记录",response);
//            tBusinePayInsideService.exportListforBusinePay(tBusinePay,busineId,beginTime,endTime,response);
        } catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }
    /**
     *导出电子对账单信息
     * @param beginTime
     * @param endTime
     * @param checkFlag
     * @param request
     * @param response
     */
    @GetMapping("/exportListforElectronic")
    @HttpLog(operationType = "0",modularTypeName = "导出电子对账单信息")
    public void  exportListforElectronic(String beginTime,String endTime,Integer checkFlag, HttpServletRequest request, HttpServletResponse response){
        try {
            Integer parkId=SessionUtil.getParkId();
            TElectronPayment tElectronPayment=new TElectronPayment();
            tElectronPayment.setParkId(parkId);
            tElectronPayment.setCheckFlag(checkFlag);
            electronPaymentService.exportList(beginTime,endTime,tElectronPayment,"电子对账单信息",response);
        } catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }
    /**
     *导出当班支付对账信息
     * @param beginTime
     * @param endTime
     * @param dutyPerson
     * @param request
     * @param response
     */
    @GetMapping("/exportListforCashPayment")
    @HttpLog(operationType = "0",modularTypeName = "导出当班支付对账信息")
    public void  exportListforCashPayment(String beginTime,String endTime,String dutyPerson, HttpServletRequest request, HttpServletResponse response){
        try {
            Integer parkId=SessionUtil.getParkId();
            TParkDuty tParkDuty=new TParkDuty();
            tParkDuty.setParkId(parkId);
            tParkDuty.setDutyPerson(dutyPerson);
            cashPaymentService.exportList(beginTime,endTime,tParkDuty,"当班支付对账信息",response);
        } catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    /**
     *
     * @param beginTime
     * @param endTime
     * @param request
     * @param response
     */
    @GetMapping("/exportListforCarownerPayment")
    @HttpLog(operationType = "0",modularTypeName = "导出车主钱包对账信息")
    public void  exportListforCarownerPayment(String beginTime,String endTime, HttpServletRequest request, HttpServletResponse response){
        try {
            Integer parkId=SessionUtil.getParkId();
            TCarWalletReport tCarWalletReport=new TCarWalletReport();
            tCarWalletReport.setParkId(parkId);
            tCarWalletReportService.exportList(beginTime,endTime,tCarWalletReport,"车主钱包对账信息",response);
        } catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    /**
     *导出财务报表信息
     * @param beginTime
     * @param endTime
     * @param type
     * @param request
     * @param response
     */
    @GetMapping("/exportListforParkReport")
    @HttpLog(operationType = "0",modularTypeName = "导出财务报表信息")
    public void  exportListforParkReport(String beginTime,String endTime,Integer type, HttpServletRequest request, HttpServletResponse response){
        try {
            Integer parkId=SessionUtil.getParkId();
            switch (type){
                case 0:
                    TParkReport tParkReport=new TParkReport();
                    tParkReport.setParkId(parkId);
                    parkReportService.exportList(beginTime,endTime,tParkReport,"财务日报表信息",response);
                    break;
                case 1:
                    TParkReportMonth tParkReportMonth=new TParkReportMonth();
                    tParkReportMonth.setParkId(parkId);
                    parkReportMonthService.exportList(beginTime,endTime,tParkReportMonth,"财务月报表信息",response);
                    break;
                case 2:
                    TParkReportYear tParkReportYear=new TParkReportYear();
                    tParkReportYear.setParkId(parkId);
                    parkReportYearService.exportList(beginTime,endTime,tParkReportYear,"财务年报表信息",response);
                    break;
                default:
                        break;
            }
        } catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListPresentCar")
    @HttpLog(operationType = "0",modularTypeName = "导出在场车辆信息")
    public void  exportListPresentCar(TParkInOut tParkInOut, HttpServletRequest request, HttpServletResponse response){
        try {
            TCompanyUser user= SessionUtil.getUser();
            String timeType=request.getParameter("timeType");
            String isUpdateCarplate = request.getParameter("isUpdateCarplate");
            String parkId = SessionUtil.getParkId().toString();
            if (timeType==null){
                timeType="all";
            }
//            logger.info("isUpdateCarplate: "+isUpdateCarplate);
            if (isUpdateCarplate.equals("on")){
                tParkInOut.setUpdatedincarplate("havaupdate");
            }else if(isUpdateCarplate.equals("off")){
                tParkInOut.setUpdatedincarplate(null);
            }
//            logger.info("switch: "+tParkInOut.getUpdatedincarplate());
            presentCarService.exportList(tParkInOut,timeType,"在场车辆",parkId,response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }
    @GetMapping("/exportListRecord")
    @HttpLog(operationType = "0",modularTypeName = "导出停车记录信息")
    public void  exportListRecord(TParkInOut tParkInOut, HttpServletRequest request, HttpServletResponse response){
        tParkInOut.setParkId(SessionUtil.getParkId());
        String isUpdateCarplate = request.getParameter("isUpdateCarplate");
        if (isUpdateCarplate!=null){
            tParkInOut.setUpdatedincarplate("havaupdate");
        }
        try {
            parkingRecordService.exportList(tParkInOut,"停车记录",response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportPayFeeRecord")
    @HttpLog(operationType = "0",modularTypeName = "导出缴费记录信息")
    public void  exportPayFeeRecord(TParkInOut tParkInOut, HttpServletRequest request, HttpServletResponse response){
        tParkInOut.setParkId(SessionUtil.getParkId());
        String outTimeBegin =(request.getParameter("outTimeBegin")==null)?"":(request.getParameter("outTimeBegin"));
        String outTimeEnd =(request.getParameter("outTimeEnd")==null)?"":(request.getParameter("outTimeEnd"));
        try {
            parkingRecordService.exportPayFeeRecord(tParkInOut,outTimeBegin,outTimeEnd,"缴费记录",response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListforChargeReport")
    @HttpLog(operationType = "0",modularTypeName = "导出分类统计报表")
    public void  exportListforChargeReport(String beginDate,String endDate, HttpServletRequest request, HttpServletResponse response){
        TChargeDetailsReport tChargeDetailsReport = new TChargeDetailsReport();
        tChargeDetailsReport.setParkId(SessionUtil.getParkId());
        if(beginDate==null){
            beginDate="";
        }
        if(endDate==null){
            endDate="";
        }
        try {
            tChargeDetailsReportService.exportList(tChargeDetailsReport,beginDate,endDate,"分类统计报表",response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListMonthly")
    @HttpLog(operationType = "0",modularTypeName = "导出登记车信息")
    public void  exportListMonthly(TParkCar tParkCar, HttpServletRequest request, HttpServletResponse response){
        tParkCar.setParkId(SessionUtil.getParkId());
        String endDateSelectStart = (request.getParameter("endDateSelectStart")==null)?"":(request.getParameter("endDateSelectStart"));
        String endDateSelectEnd = (request.getParameter("endDateSelectEnd")==null)?"":(request.getParameter("endDateSelectEnd"));
        try {
            monthlyCarService.exportList(tParkCar,endDateSelectStart,endDateSelectEnd,"登记车信息",response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListOwner")
    @HttpLog(operationType = "0",modularTypeName = "导出车主车位信息")
    public void  exportListOwner(TParkCar tParkCar, HttpServletRequest request, HttpServletResponse response){
        tParkCar.setParkId(SessionUtil.getParkId());
        tParkCar.setParkingSpace(4);
        try {
            monthlyCarService.exportList(tParkCar,"","","车主车位信息",response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListMonthlyDetails")
    @HttpLog(operationType = "0",modularTypeName = "导出登记车缴费记录")
    public void  exportListMonthlyDetails(TCarPayment tCarPayment,String beginData,String endData, HttpServletRequest request, HttpServletResponse response){
        tCarPayment.setParkId(SessionUtil.getParkId());
        tCarPayment.setCarplate(request.getParameter("carplate"));
        String payRule = request.getParameter("payRule");
        if(!payRule.equals("")){
            tCarPayment.setPayRule(Integer.valueOf(payRule));
        }

        try {
            carPaymentService.exportListMonthlyDetails(tCarPayment,"登记车缴费详情",response,beginData,endData);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListBlack")
    @HttpLog(operationType = "0",modularTypeName = "导出黑名单信息")
    public void  exportListBlack(HttpServletRequest request, HttpServletResponse response){
        try {
            TBlacklist tBlackList = new TBlacklist();
            tBlackList.setParkId(SessionUtil.getParkId());
            String carplate =  (request.getParameter("carplate")==null)?"":(request.getParameter("carplate"));
            tBlackList.setCarPlate(carplate);
            blackListService.exportList(tBlackList,"黑名单信息",response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }

    @GetMapping("/exportListWalletRechargeRecord")
    @HttpLog(operationType = "0",modularTypeName = "导出钱包充值记录")
    public void  exportListWalletRechargeRecord(TCarownerPayment tCarownerPayment, String title,String beginDate,String endDate, HttpServletResponse response){
        tCarownerPayment.setParkId(SessionUtil.getParkId());
        try {
            tCarownerPaymentService.exportList(tCarownerPayment,"钱包充值记录",beginDate,endDate,response);
        }
        catch (Exception e){
            logger.info("错误原因为："+e.toString());
            e.printStackTrace();
        }
    }
    @PostMapping(value = "/getParkingStateList")
    @HttpLog(operationType = "0",modularTypeName = "获取车位状态")
    public ActionRsp getParkingStateList(){
        return ActionRspUtil.Success(CommonUtil.parkingStateList);
    }
    @PostMapping(value = "/getParkingSpaceList")
    @HttpLog(operationType = "0",modularTypeName = "获取车位性质")
    public ActionRsp getParkingSpaceList(){
        return ActionRspUtil.Success(CommonUtil.parkingSpaceList);
    }
    @PostMapping(value = "/getBusineStatusList")
    @HttpLog(operationType = "0",modularTypeName = "获取商户状态")
    public ActionRsp getBusineStatusList(){
        return ActionRspUtil.Success(CommonUtil.busineStatusList);
    }
    @PostMapping(value = "/getBusineTypeList")
    @HttpLog(operationType = "0",modularTypeName = "获取商户类别")
    public ActionRsp getBusineTypeList(){
        return ActionRspUtil.Success(CommonUtil.busineTypeList);
    }
    @PostMapping(value = "/getCarTypeList")
    @HttpLog(operationType = "0",modularTypeName = "获取车辆类型")
    public ActionRsp getCarTypeList(){
        return ActionRspUtil.Success(CommonUtil.carTypeList);
    }
    @PostMapping(value = "/getIsHolidayUseList")
    @HttpLog(operationType = "0",modularTypeName = "获取假日规则")
    public ActionRsp getIsHolidayUseList(){
        return ActionRspUtil.Success(CommonUtil.isHolidayUseList);
    }
    @PostMapping(value = "/getH24RuleList")
    @HttpLog(operationType = "0",modularTypeName = "获取计时规则")
    public ActionRsp getH24RuleList(){
        return ActionRspUtil.Success(CommonUtil.h24RuleList);
    }
    @PostMapping(value = "/geth24CalModeList")
    @HttpLog(operationType = "0",modularTypeName = "获取24h后计费规则")
    public ActionRsp geth24CalModeList(){
        return ActionRspUtil.Success(CommonUtil.h24CalModeList);
    }
    @PostMapping(value = "/getIsMaxSectimeFeeByAddList")
    @HttpLog(operationType = "0",modularTypeName = "获取是否最大收费规则")
    public ActionRsp getIsMaxSectimeFeeByAddList(){
        return ActionRspUtil.Success(CommonUtil.isMaxSectimeFeeByAddList);
    }
    @PostMapping(value = "/getCarPayRuleList")
    @HttpLog(operationType = "0",modularTypeName = "获取月租车缴费规则")
    public ActionRsp getCarPayRuleList(){
        return ActionRspUtil.Success(CommonUtil.CarPayRuleList);
    }
    @PostMapping(value = "/getCompanyTypeList")
    @HttpLog(operationType = "0",modularTypeName = "获取物业类型")
    public ActionRsp getCompanyTypeList(){
        return ActionRspUtil.Success(CommonUtil.companyTypeList);
    }
    @PostMapping(value = "/getCompanyStatusList")
    @HttpLog(operationType = "0",modularTypeName = "获取物业状态")
    public ActionRsp getCompanyStatusList(){
        return ActionRspUtil.Success(CommonUtil.companyStatusList);
    }
    @PostMapping(value = "/getIsManyPreferentialList")
    @HttpLog(operationType = "0",modularTypeName = "获取代缴类型")
    public ActionRsp getIsManyPreferentialList(){
        return ActionRspUtil.Success(CommonUtil.IsManyPreferentialList);
    }
    @PostMapping(value = "/getFlagLists")
    @HttpLog(operationType = "0",modularTypeName = "获取电子报表确认标识")
    public ActionRsp getFlagLists(){
        return ActionRspUtil.Success(CommonUtil.FlagLists);
    }
    @PostMapping(value = "/getCarNatureLists")
    @HttpLog(operationType = "0",modularTypeName = "获取车辆类型")
    public ActionRsp getCarNatureLists(){
        return ActionRspUtil.Success(CommonUtil.carNature);
    }
    @PostMapping(value = "/getEPayType")
    @HttpLog(operationType = "0",modularTypeName = "获取电子支付（闪付）类型")
    public ActionRsp getEPayType(){
        return ActionRspUtil.Success(CommonUtil.ePayTypeList);
    }
    @PostMapping(value = "/getPaymentMethod")
    @HttpLog(operationType = "0",modularTypeName = "获取支付方式类型")
    public ActionRsp getPaymentMethod(){
        return ActionRspUtil.Success(CommonUtil.paymentMethodList);
    }
    @PostMapping(value = "/getAcquiringChannel")
    @HttpLog(operationType = "0",modularTypeName = "获取收单渠道类型")
    public ActionRsp getAcquiringChannel(){
        return ActionRspUtil.Success(CommonUtil.acquiringChannelList);
    }
    @PostMapping(value = "/getPaymentChannel")
    @HttpLog(operationType = "0",modularTypeName = "获取支付渠道类型")
    public ActionRsp getPaymentChannel(){
        return ActionRspUtil.Success(CommonUtil.paymentChannelList);
    }
}
