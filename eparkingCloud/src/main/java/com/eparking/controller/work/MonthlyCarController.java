package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TCarPayment;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparking.insideService.TCarPayRuleInsideService;
import com.eparking.insideService.TParkCarInsideService;
import com.eparking.service.CarPayRuleService;
import com.eparking.service.MemberService;
import com.eparking.service.MonthlyCarService;
import com.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName MonthlyCarController
 * @Author jin
 * @Date 2018/9/21 13:15
 **/
@RestController
@RequestMapping(value = "monthlyCar")
public class MonthlyCarController {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyCarController.class);
    @Autowired
    private MonthlyCarService monthlyCarService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CarPayRuleService carPayRuleService;
    @Autowired
    private TParkCarInsideService tParkCarInsideService;
    @Autowired
    private TCarPayRuleInsideService tCarPayRuleInsideService;


    @PostMapping(value = "/getMonthlyCar")
    @HttpLog(operationType = "0", modularTypeName = "查询登记车信息")
    public ControllerRsp getMonthlyCar(TParkCar tParkCar, Integer page, Integer limit, HttpServletRequest request) {
        tParkCar.setParkId(SessionUtil.getParkId());
        String endDateSelectStart = (request.getParameter("endDateSelectStart")==null)?"":(request.getParameter("endDateSelectStart"));
        String endDateSelectEnd = (request.getParameter("endDateSelectEnd")==null)?"":(request.getParameter("endDateSelectEnd"));
        return ControllerRspUtil.Success(monthlyCarService.getTParkCarlistbyPage(tParkCar,endDateSelectStart,endDateSelectEnd, page, limit));
//        return tParkCarInsideService.getTParkCarbyPage(tParkCar,page,limit);
    }

    @PostMapping(value = "/getOwnnerCarbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询车主车位信息")
    public ControllerRsp getOwnnerCarbyPage(TParkCar tParkCar, Integer page, Integer limit,HttpServletRequest request) {
        tParkCar.setParkId(SessionUtil.getParkId());
        tParkCar.setParkingSpace(4);
        String endDateSelectStart = (request.getParameter("endDateSelectStart")==null)?"":(request.getParameter("endDateSelectStart"));
        String endDateSelectEnd = (request.getParameter("endDateSelectEnd")==null)?"":(request.getParameter("endDateSelectEnd"));
        return ControllerRspUtil.Success(monthlyCarService.getOwnnerCarbyPage(tParkCar,endDateSelectStart,endDateSelectEnd, page, limit));
//        return tParkCarInsideService.getTParkCarbyPage(tParkCar,page,limit);
    }

    @PostMapping(value = "/updateMonthlyCar")
    @HttpLog(operationType = "1", modularTypeName = "更新登记车信息")
    public ActionRsp updateMonthlyCar(TParkCar tParkCar, HttpServletRequest request) {
        TCompanyUser user = SessionUtil.getUser();
        if (tParkCar.getIsOnlinePayment() == null) {
            tParkCar.setIsOnlinePayment(0);
        }
        if (tParkCar.getStatus() == null) {
            tParkCar.setStatus((short) 1);
        }
/*        if (tParkCar.getStopTime() == null) {
            tParkCar.setStopTime(DateUtil.getCurDate());
        }*/
        //更新车牌号在绑定车牌操作里修改
//        tParkCar.setCarPlate(null);
        tParkCar.setCompanyId(user.getCompanyId());
        tParkCar.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        tParkCar.setIsDelet((short) 0);
        tParkCar.setCarType(1);
        TParkCar tParkCarNew = monthlyCarService.UpdateTParkCar(tParkCar);
//        TParkCar tParkCarNew = (TParkCar) tParkCarInsideService.UpdateTParkCar(tParkCar).getResult();
        return ActionRspUtil.Success(tParkCarNew);

    }

    @PostMapping("/voucherSave")
    @HttpLog(operationType = "1", modularTypeName = "登记车充值")
    public ActionRsp voucherSave(TCarPayment carPayment, HttpServletResponse response, HttpServletRequest request) {
        try {
            Integer parkCarId = Integer.valueOf(request.getParameter("parkCarId"));
            TCompanyUser user = SessionUtil.getUser();
            carPayment.setCompanyId(user.getCompanyId());
            carPayment.setParkId(SessionUtil.getParkId());
            carPayment.setPayType((short) 1);
            carPayment.setRechargeNo(Calendar.getInstance().getTimeInMillis() + carPayment.getParkId().toString());
            carPayment.setOperator(user.getUserAccout());
            carPayment.setBeginDate(carPayment.getEndDate());
            carPayment.setEndDate(request.getParameter("endDatenew"));
            TParkCar tParkCarnew = monthlyCarService.getTParkCarById(parkCarId);
//            TParkCar tParkCarnew = JsonUtil.jsonToBean(tParkCarInsideService.getTParkCarById(parkCarId).getResult().toString(),TParkCar.class) ;
            Map carInfo = new HashMap();
            carInfo.put("carPayment", JsonUtil.beanToJson(carPayment));
            carInfo.put("tParkCarnew", JsonUtil.beanToJson(tParkCarnew));
//            logger.info(JsonUtil.beanToJson(tParkCarnew));
//            String error = tParkCarInsideService.recharge(JsonUtil.mapToJson(carInfo)).getResult().toString();
            String error = monthlyCarService.recharge(carPayment, tParkCarnew);
            tParkCarnew.setBeginDate(carPayment.getBeginDate());
            tParkCarnew.setEndDate(carPayment.getEndDate());
            tParkCarnew.setRenewalTime(DateUtil.getCurDateTime());
            monthlyCarService.UpdateTParkCar(tParkCarnew);
//            tParkCarInsideService.UpdateTParkCar(tParkCarnew);
            response.setContentType("application/json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionRspUtil.Success("充值成功");
    }

    /**
     * 计算充值金额
     */
    @PostMapping(value = "/calRule")
    @HttpLog(operationType = "1", modularTypeName = "计算金额")
    public ActionRsp calRule(Integer payStandard, Integer payRule, Integer payCount, String beginDate, Integer parkingNo, HttpServletResponse response) throws IOException {
        String result = "{\"result\":\"success\"}";
        try {
            TCarPayRule carPayRule = carPayRuleService.getCarPayRuleByid(payRule);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bigengDate = sdf.parse(beginDate);
            Double needPay = 0.0;//应收费
            String endDate = null;//截止日期
            Calendar calendar = Calendar.getInstance();

            if (carPayRule != null) {
                //年缴
                if (CommonUtil.CAR_PAY_RULE_TYPE_Y.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getYearFee();
                    //endDate = sdf.format(StringUtil3.getBeforeDay(bigengDate, 365 * payCount));
                    calendar.setTime(StringUtil3.getBeforeDay(bigengDate, 365 * payCount));
                    //季度
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_Q.equals(payStandard)) {
                    Long needPay1 = (parkingNo * payCount * carPayRule.getSeasonFee());
                    needPay = needPay1.doubleValue();
                    //endDate = sdf.format(StringUtil3.getBeforeMonth(bigengDate, 3 * payCount));
                    calendar.setTime(StringUtil3.getBeforeMonth(bigengDate, 3 * payCount));
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_M.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getMonthFee();
                    //endDate = sdf.format(StringUtil3.getBeforeMonth(bigengDate, payCount));
                    calendar.setTime(StringUtil3.getBeforeMonth(bigengDate, payCount));
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_D.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getDayFee();
                    //endDate = sdf.format(StringUtil3.getBeforeDay(bigengDate, payCount));
                    calendar.setTime(StringUtil3.getBeforeDay(bigengDate, payCount));
                }
            }

            //calendar.add(calendar.DATE, -1);
            endDate = sdf.format(calendar.getTime());
            result = "{\"result\":\"success\",\"needPay\":\"" + needPay + "\",\"endDate\":\"" + endDate + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            result = "{\"result\":\"" + e.getMessage() + "\"}";
        }
        return ActionRspUtil.Success(result);
    }

    /**
     * 查找接近到期的登记车信息
     */
    @PostMapping(value = "/getCloseParkCarbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询接近到期的登记车")
    public ControllerRsp getCloseParkCarbyPage(String closeType, String payRule, Integer page, Integer limit) {
        String parkid = SessionUtil.getParkId().toString();
        if (closeType == null) {
            closeType = "";
        }
        if (payRule == null) {
            payRule = "";
        }
        return ControllerRspUtil.Success(monthlyCarService.getCloseParkCarbyPage(closeType, payRule, parkid, page, limit));
//        return tParkCarInsideService.getCloseParkCar(closeType,payRule,parkid,page,limit);
    }

    /**
     * 计算批量充值的金额
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/vouchersBatchjs")
    @HttpLog(operationType = "0", modularTypeName = "批量充值计算金额")
    public ActionRsp vouchersBatchjs(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        Double needPayTotal = 0.0;
        String ids = request.getParameter("ids");
        Integer payStandard = Integer.valueOf(request.getParameter("payStandard"));
        Integer payCount = Integer.valueOf(request.getParameter("payCount"));
        String[] strs = ids.split(",");
        for (int i = 0, len = strs.length; i < len; i++) {
//            TParkCar tParkCar=JsonUtil.jsonToBean(tParkCarInsideService.getTParkCarById(Integer.valueOf(strs[i])).getResult().toString(),TParkCar.class);
            JSONObject msgPay = monthlyCarService.getNeedPay(payStandard, payCount, monthlyCarService.getTParkCarById(Integer.valueOf(strs[i].toString())));
//            JSONObject msgPay = JsonUtil.jsonToBean(tParkCarInsideService.getNeedPay(payStandard,payCount,tParkCar).getResult().toString(),JSONObject.class);
            needPayTotal += Double.valueOf(msgPay.getString("needPay"));
            array.add(msgPay);
        }
        jsonObject.put("dataresult", array.toString());
        jsonObject.put("needPayTotal", needPayTotal);
//        System.out.println(jsonObject.toString());
        return ActionRspUtil.Success(jsonObject);
    }

    /**
     * 批量充值
     */
    @PostMapping(value = "/vouchersBatchSave")
    @HttpLog(operationType = "1", modularTypeName = "批量充值")
    public ActionRsp vouchersBatchSave(String ids, Integer payStandard, Integer payCount, HttpServletRequest request) {
        String[] idList = ids.split(",");
        Double actualPay = Double.valueOf(request.getParameter("actualPay"));
        for (int i = 0; i < idList.length; i++) {
            TCarPayment carPayment = new TCarPayment();
            String id = idList[i];
            //根据ID查询登记车信息
//            TParkCar tParkCarf = JsonUtil.jsonToBean(tParkCarInsideService.getTParkCarById(Integer.valueOf(id)).getResult().toString(), TParkCar.class);
            TParkCar tParkCarf = monthlyCarService.getTParkCarById(Integer.valueOf(id));
            //计算金额
//            JSONObject msgPay = JsonUtil.jsonToBean(tParkCarInsideService.getNeedPay(payStandard, payCount, tParkCarf).getResult().toString(), JSONObject.class);
            JSONObject msgPay = monthlyCarService.getNeedPay(payStandard, payCount, tParkCarf);
            carPayment.setNeedPay(Double.valueOf(msgPay.get("needPay").toString()));
            carPayment.setActualPay(actualPay);
            carPayment.setCarplate(tParkCarf.getCarPlate());
            carPayment.setPayRule(tParkCarf.getPayRule());
            carPayment.setEndDate(msgPay.get("endDate").toString());
            carPayment.setBeginDate(tParkCarf.getEndDate());
            try {
                TCompanyUser user = SessionUtil.getUser();
                carPayment.setCompanyId(user.getCompanyId());
                carPayment.setParkId(SessionUtil.getParkId());
                carPayment.setPayType((short) 1);
                carPayment.setRechargeNo(Calendar.getInstance().getTimeInMillis() + carPayment.getParkId().toString());
                carPayment.setOperator(user.getUserName());
                TParkCar tParkCar = new TParkCar();
                tParkCar.setParkId(carPayment.getParkId());
                tParkCar.setCarPlate(carPayment.getCarplate());
                String error = monthlyCarService.recharge(carPayment, monthlyCarService.getTParkCarlist(tParkCar,"","").get(0));
//                Map carInfo = new HashMap();
//                carInfo.put("carPayment", JsonUtil.beanToJson(carPayment));
//                carInfo.put("tParkCarnew", JsonUtil.beanToJson(((List<TParkCar>) tParkCarInsideService.getTParkCar(tParkCar).getResult()).get(0)));
//                carInfo.put("tParkCarnew", monthlyCarService.getTParkCarlist(tParkCar).get(0));
//                String error = tParkCarInsideService.recharge(JsonUtil.mapToJson(carInfo)).getResult().toString();
//                TParkCar tParkCarnew = monthlyCarService.getTParkCarlist(tParkCar).get(0);
               /* TParkCar tParkCarnew = ((List<TParkCar>)tParkCarInsideService.getTParkCar(tParkCar).getResult()).get(0);
                tParkCarnew.setBeginDate(carPayment.getBeginDate());
                tParkCarnew.setEndDate(carPayment.getEndDate());
                tParkCarnew.setRenewalTime(DateUtil.getCurDateTime());
//                monthlyCarService.UpdateTParkCar(tParkCarnew);
                tParkCarInsideService.UpdateTParkCar(tParkCarnew);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ActionRspUtil.Success("充值成功");
    }

    @PostMapping(value = "/Resetport")
    @HttpLog(operationType = "1", modularTypeName = "重置通道权限")
    public ActionRsp Resetport(HttpServletRequest request) {
        String idsport = request.getParameter("idsport");
        String portids = request.getParameter("portids");
        portids = portids.substring(2, portids.length());
        return ActionRspUtil.Success(monthlyCarService.Resetport(portids, idsport));
//        return tParkCarInsideService.Resetport(portids, idsport);
    }

    @PostMapping(value = "/correct")
    @HttpLog(operationType = "1", modularTypeName = "登记车冲正")
    public ActionRsp correct(TParkCar tParkCar, Double needPay, Short payType, String remark) {
      /*  Map carInfo = new HashMap();
        carInfo.put("tCarPayment", JsonUtil.beanToJson(tCarPayment));
        carInfo.put("tParkCar", JsonUtil.beanToJson(tParkCar));*/
        return ActionRspUtil.Success(monthlyCarService.correct(tParkCar, needPay, payType, remark));
//        return tParkCarInsideService.correct(JsonUtil.mapToJson(carInfo));
    }

    @PostMapping(value = "/refund")
    @HttpLog(operationType = "1", modularTypeName = "登记车删除")
    public ActionRsp refund(HttpServletRequest request, TParkCar parkCar, TCarPayment carPayment) {
        String isRefund = request.getParameter("isRefund");
/*        Map carInfo = new HashMap();
        carInfo.put("tCarPayment", JsonUtil.beanToJson(carPayment));
        carInfo.put("tParkCar", JsonUtil.beanToJson(parkCar));
        carInfo.put("isRefund", isRefund);*/
        return ActionRspUtil.Success(monthlyCarService.refund(parkCar, carPayment, isRefund));
//        return tParkCarInsideService.refund(JsonUtil.mapToJson(carInfo));
    }

    @PostMapping(value = "/monthlyPrepayment")
    @HttpLog(operationType = "1", modularTypeName = "登记车预缴")
    public String monthlyPrepayment(Integer parkId, String carPlate, Double needPay, Double actualPay, Integer ePayType, String beginDate, String endDate, Integer monthNum, Integer payRule, Integer companyId) {
        return monthlyCarService.monthlyPrepayment(parkId, carPlate, needPay, actualPay, ePayType, beginDate, endDate, monthNum, payRule, companyId);
    }

    @PostMapping(value = "/monthlyWalletRecharge")
    @HttpLog(operationType = "1", modularTypeName = "登记车钱包充值")
    public ActionRsp monthlyWalletRecharge(Integer id, Double needPay, Double actualPay, String remark, HttpServletRequest request) {
        String rechargeType = request.getParameter("rechargeType");
        return ActionRspUtil.Success(monthlyCarService.monthlyWalletRecharge(id, needPay, actualPay, remark ,rechargeType));
    }

    @PostMapping(value = "/getTParkCarByCarplate")
    @HttpLog(operationType = "0", modularTypeName = "新增车牌验证已存在")
    public ActionRsp getTParkCarByCarplate(String carplate) {
        Integer parkId = SessionUtil.getParkId();
        return ActionRspUtil.Success(monthlyCarService.getTParkCarByCarplate(parkId, carplate));
    }

    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        List<TParkCar> tParkCarList = new ArrayList<>();
        //07年的 不兼容之前
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

        //获取行数
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            TParkCar tParkCar = new TParkCar();
            if (row.getCell(0) != null){
//                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                tParkCar.setCarPlate(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(1) != null){
//                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                if(row.getCell(1).getStringCellValue()=="小车"){
                    tParkCar.setCarType(1);
                }else if(row.getCell(1).getStringCellValue()=="大车"){
                    tParkCar.setCarType(2);
                }else if(row.getCell(1).getStringCellValue()=="地库小车"){
                    tParkCar.setCarType(3);
                }
            }
            if (row.getCell(2) != null){
//                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                tParkCar.setParkingNo((int)row.getCell(2).getNumericCellValue());
            }
            if (row.getCell(3) != null){
                if(row.getCell(3).getStringCellValue()=="机械车位"){
                    tParkCar.setParkingSpace(1);
                }else if(row.getCell(3).getStringCellValue()=="子母车位"){
                    tParkCar.setParkingSpace(2);
                }else if(row.getCell(3).getStringCellValue()=="普通车位"){
                    tParkCar.setParkingSpace(3);
                }else if(row.getCell(3).getStringCellValue()=="车主车位"){
                    tParkCar.setParkingSpace(4);
                }
            }
            if (row.getCell(4) != null){
                tParkCar.setCarSeat(row.getCell(4).getStringCellValue());
            }
            if (row.getCell(5) != null){
                tParkCar.setBeginDate(row.getCell(5).getStringCellValue());
            }
            if (row.getCell(6) != null){
//                row.getCell(6).setCellType(XSSFCell.CELL_TYPE_STRING);
                tParkCar.setEndDate(row.getCell(6).getStringCellValue());
            }
            if (row.getCell(7) != null){
//                row.getCell(7).setCellType(XSSFCell.CELL_TYPE_STRING);
                tParkCar.setRealname(row.getCell(7).getStringCellValue());
            }
            if (row.getCell(8) != null){
                tParkCar.setPhone(row.getCell(8).getStringCellValue());
            }
            if (row.getCell(9) != null){
                tParkCar.setAddress(row.getCell(9).getStringCellValue());
            }
            tParkCar.setStatus((short)2);
            tParkCarList.set(i,tParkCar);
        }
        for(int i=1;i<=tParkCarList.size();i++){
            monthlyCarService.UpdateTParkCar(tParkCarList.get(i));
        }
    }

    /**
     * 模糊查询TParkCar信息(分页)
     *
     * @return
     * @paramtParkCar
     */
    @PostMapping(value = "/getTParkCarFuzzybyPage")
    @HttpLog(operationType = "0", modularTypeName = "模糊查询TParkCar(分页)")
    public ControllerRsp getTParkCarFuzzybyPage(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd,Integer page,Integer limit) {
        tParkCar.setParkId(SessionUtil.getParkId());
        String endDateSelectStartFuzzy ="";
        String endDateSelectEndFuzzy = "";
        if(endDateSelectStart==null){
            endDateSelectStartFuzzy ="";
        }else{
            endDateSelectStartFuzzy=endDateSelectStart;
        }
        if(endDateSelectEnd==null){
            endDateSelectEndFuzzy ="";
        }else{
            endDateSelectEndFuzzy=endDateSelectEnd;
        }
/*        String endDateSelectStartFuzzy = (request.getParameter("endDateSelectStart")==null)?"":(request.getParameter("endDateSelectStart"));
        String endDateSelectEndFuzzy = (request.getParameter("endDateSelectEnd")==null)?"":(request.getParameter("endDateSelectEnd"));*/
        return ControllerRspUtil.Success(monthlyCarService.getTParkCarFuzzybyPage(tParkCar,endDateSelectStartFuzzy,endDateSelectEndFuzzy, page, limit));
    }
}
