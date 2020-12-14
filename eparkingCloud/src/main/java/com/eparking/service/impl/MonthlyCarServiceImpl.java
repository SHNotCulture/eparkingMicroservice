package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparking.enums.ActionRspEnum;
import com.eparking.exception.ActionRspException;
import com.eparking.insideService.*;
import com.eparking.service.CarPayRuleService;
import com.eparking.service.CarPaymentService;
import com.eparking.service.MonthBindingCarService;
import com.eparking.service.MonthlyCarService;
import com.common.util.*;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONObject;*/

/**
 * @ClassName MonthlyCarServiceImpl
 * @Author jin
 * @Date 2018/9/21 13:20
 **/
@Service
public class MonthlyCarServiceImpl implements MonthlyCarService {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyCarServiceImpl.class);

    @Autowired
    private CarPayRuleService carPayRuleService;
    @Autowired
    private TParkCarInsideService tParkCarInsideService;
    @Autowired
    private TCarPaymentInsideService tCarPaymentInsideService;
    @Autowired
    private TCarPayRuleInsideService tCarPayRuleInsideService;
    @Autowired
    private TParkPortInsideService tParkPortInsideService;
    @Autowired
    private CustomizeInsideService customizeInsideService;
    @Autowired
    private MonthBindingCarService monthBindingCarService;
    @Autowired
    private CarPaymentService carPaymentService;
    @Autowired
    private TCarownerPaymentInsideService tCarownerPaymentInsideService;


    /**
     * 设置查询条件
     * @param tParkCar
     * @return
     */
/*    private TParkCarCriteria setCriteria(TParkCar tParkCar){
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        if (tParkCar!=null){
            TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
            if(tParkCar.getParkId()!=null){
                criteria.andParkIdEqualTo(tParkCar.getParkId());
            }
            if (tParkCar.getStatus()!=null){
                criteria.andStatusEqualTo(tParkCar.getStatus());
            }
            if (tParkCar.getParkingSpace()!=null){
                criteria.andParkingSpaceEqualTo(tParkCar.getParkingSpace());
            }
            if (tParkCar.getPayRule()!=null){
                criteria.andPayRuleEqualTo(tParkCar.getPayRule());
            }
            if (tParkCar.getParkCode()!=null && tParkCar.getParkCode().length()>0){
                criteria.andParkCodeLike("%"+tParkCar.getParkCode()+"%");
            }
            if (tParkCar.getCarPlate()!=null && tParkCar.getCarPlate().length()>0){
                criteria.andCarPlateLike("%"+tParkCar.getCarPlate()+"%");
            }
            if (tParkCar.getStopTime()!=null && tParkCar.getStopTime().length()>0 && tParkCar.getLockTime()!=null && tParkCar.getLockTime().length()>0){
                criteria.andStopTimeBetween(tParkCar.getStopTime(),tParkCar.getLockTime());
            }if (tParkCar.getStopTime()!=null && tParkCar.getStopTime().length()>0 && (tParkCar.getLockTime()==null || tParkCar.getLockTime().length()==0)){
                criteria.andStopTimeGreaterThanOrEqualTo(tParkCar.getStopTime());
            }if ((tParkCar.getStopTime()==null || tParkCar.getStopTime().length()==0) && tParkCar.getLockTime()!=null && tParkCar.getLockTime().length()>0){
                criteria.andStopTimeLessThanOrEqualTo(tParkCar.getLockTime());
            }
        }
        return  tParkCarCriteria;
    }*/
    /**
     * 获取数据总量
     * @param tParkCar
     * @return
     */
/*    private Integer getCount(TParkCar tParkCar){
        Integer total =(int)tParkCarMapper.countByExample(setCriteria(tParkCar));
        return total;
    }*/

    /**
     * 获取月租车信息(分页)
     *
     * @param tParkCar
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TParkCar> getTParkCarlistbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TParkCar> tParkCarList=getTParkCarlist(tParkCar);
        Integer countNums =getCount(tParkCar);
        PageBean<TParkCar> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkCarList);
        return pageData;*/
        if(tParkCar.getCarPlate()!=null && !tParkCar.getCarPlate().equals("")){
            TMonthBindingCar tMonthBindingCarSel = new TMonthBindingCar();
            tMonthBindingCarSel.setParkId(tParkCar.getParkId());
            tMonthBindingCarSel.setCarPlate(tParkCar.getCarPlate());
            List<TMonthBindingCar> tMonthBindingCarList = monthBindingCarService.getMonthBindingCar(tMonthBindingCarSel);
            if(tMonthBindingCarList.size()>0){
                tParkCar.setId(tMonthBindingCarList.get(0).getParkCarId());
            }else{
                tParkCar.setId(0);
            }
            tParkCar.setCarPlate(null);
//            System.out.println(JsonUtil.beanToJson(tParkCar));
        }
        return tParkCarInsideService.getTParkCarbyPage(tParkCar, endDateSelectStart, endDateSelectEnd, page, limit);
    }

    /**
     * 获取月租车信息
     *
     * @param tParkCar
     * @return
     */
    @Override
    public List<TParkCar> getTParkCarlist(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd) {
//        List<TParkCar> tParkCarList = tParkCarMapper.selectByExample(setCriteria(tParkCar));
        List<TParkCar> tParkCarList = tParkCarInsideService.getTParkCar(tParkCar, endDateSelectStart, endDateSelectEnd);
        return tParkCarList;
    }

    /**
     * 查询框体中的数量
     *
     * @return
     */
    @Override
    public TCustomize getCarNum(String parkid) {
/*        TCustomize tCustomize = customizeMapper.selectMonthlyNum(parkid);
        return tCustomize;*/
        return customizeInsideService.getCarNum(parkid);
    }

    @Override
    @Transactional
    public TParkCar UpdateTParkCar(TParkCar tParkCar) {
//        logger.info(JsonUtil.beanToJson(tParkCar));
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
        String msg;
        if (tParkCar.getId() != null) {
//            TParkCar tParkCarPar = tParkCarMapper.selectByPrimaryKey(tParkCar.getId());
            TParkCar tParkCarPar = tParkCarInsideService.getTParkCarById(tParkCar.getId());
            //修改了状态值
            if (!tParkCarPar.getStatus().equals(tParkCar.getStatus())) {
                //状态值发生变化，并且改变值为暂停
                if (tParkCar.getStatus() == 2) {
                    tParkCar.setStopTime(DateUtil.getCurDateYMR());
                }
                //状态值发生变化，并且改变值为启用
                if (tParkCar.getStatus() == 1) {
                    tParkCar.setStopTime("");
                    //修改月租截止日期加上暂停的天数
/*                    String stopTime = tParkCarPar.getStopTime();
                    Date format1 = null;
                    try {
                        format1 = DateUtil.parse(stopTime,DateUtil.g_SimpleDateFormat_I);
                        String longDate = DateUtil.format(format1,DateUtil.g_SimpleDateFormat_I);
                        Integer days = DateUtil.nowBetweenStartTime(longDate);
                        String endData = DateUtil.changeDate(DateUtil.getStartDateInterval(tParkCarPar.getEndDate(), days * 24 * 60));
                        tParkCar.setEndDate(endData);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/

                }
            }

            TMonthBindingCar tMonthBindingCar = new TMonthBindingCar();
/*            String carplate = tParkCar.getCarPlate();
            carplate.replaceAll("，",",");
            for(String carplateOne:carplate.split(",")){
                carplateOne.trim();
                tMonthBindingCar.setCarPlate(carplateOne);
                tMonthBindingCar.setParkCarId(tParkCar.getId());
                if (monthBindingCarService.getMonthBindingCar(tMonthBindingCar).get(0).getParkCarId()!=tParkCar.getId()){
                    throw new ActionRspException(ActionRspEnum.CarplateExist_ERROR);
                }
            }*/
            tMonthBindingCar.setParkCarId(tParkCar.getId());
            List<TMonthBindingCar> tMonthBindingCarList = monthBindingCarService.getMonthBindingCar(tMonthBindingCar);
            Integer bindingNum=0;
            if(tMonthBindingCarList!=null){
                bindingNum = tMonthBindingCarList.size();
                if(bindingNum>1){
                    tParkCar.setBindingName("多车绑定");
                }else{
                    tParkCar.setBindingName("");
                }
            }
            tParkCar.setBindingNum(bindingNum);
            //若绑定车数量大于3编辑保存操作不下发绑定车信息
            if(bindingNum<=3){
                 for(TMonthBindingCar tMonthBindingCarSyn:tMonthBindingCarList){
                     monthBindingCarService.updateMonthBindingCar(tMonthBindingCarSyn);
                 }
             }
/*            if(tParkCar.getCarPlate()!=null && !tParkCar.getCarPlate().equals("")){
                String[] carplateArray = tParkCar.getCarPlate().split(",");
                if(carplateArray.length>1){
                    tParkCar.setBindingName("多车绑定");
                }
                tParkCar.setBindingNum(carplateArray.length);
            }*/
//           tParkCarMapper.updateByPrimaryKey(tParkCar);
             tParkCarInsideService.UpdateTParkCar(tParkCar);
        } else {
            String carplateStr = tParkCar.getCarPlate();
            carplateStr.replaceAll("，",",");
            for(String carplateOne:carplateStr.split(",")){
                carplateOne.trim();
                //检查车牌合法性，如果合法查询绑定表判断车牌是否唯一
                if(StringUtil.checkCarplate(carplateOne)==true){
                    TMonthBindingCar tMonthBindingCarCheck = new TMonthBindingCar();
                    tMonthBindingCarCheck.setCarPlate(carplateOne);
                    tMonthBindingCarCheck.setParkId(tParkCar.getParkId());
                    if(!monthBindingCarService.checkCarplateOnlyOne(tMonthBindingCarCheck)){
                        throw new ActionRspException(ActionRspEnum.CarplateExist_ERROR);
                    }
                }else{
                    throw new ActionRspException(ActionRspEnum.CarplateCheck_ERROR);
                }
            }
/*            List<TParkCar> tParkCarList = tParkCarInsideService.getTParkCarByCarplate(tParkCar.getParkId(),tParkCar.getCarPlate());
            if(tParkCarList.size()>0){
                String oldCarplate = tParkCarList.get(0).getCarPlate();
                if(oldCarplate!=null && oldCarplate!="" && oldCarplate==tParkCar.getCarPlate()){
                    throw new ActionRspException(ActionRspEnum.CarplateExist_ERROR);
                }
            }*/
            TCarPayRule tCarPayRule = new TCarPayRule();
            tCarPayRule.setId(tParkCar.getPayRule());
            List<TCarPayRule> tCarPayRuleList = tCarPayRuleInsideService.getTCarPayRule(tCarPayRule);
            tParkCar.setPayruleidstr(tCarPayRuleList.get(0).getRuleName());
            //目前member无用，默认值给0
            tParkCar.setMemberId(0);
            //新增月租车，默认起止时间为当前，截止时间为前一天
            tParkCar.setBeginDate(DateUtil.getCurDateYMR());
            tParkCar.setEndDate(DateUtil.getPredate());
            tParkCar.setStopTime("");
//            tParkCarMapper.insert(tParkCar);
            String tParkCarUpdate = tParkCarInsideService.UpdateTParkCar(tParkCar);
            if(tParkCarUpdate.equals("新建成功")){
                TParkCar tParkCarNew = new TParkCar();
                if(tParkCar.getParkingSpace()==4){
                    tParkCarNew = tParkCarInsideService.getOwnerCar(tParkCar,"","").get(0);
                }else{
                    tParkCarNew = tParkCarInsideService.getTParkCar(tParkCar,"","").get(0);
                }
                for(String carplate:carplateStr.split(",")){
                    TMonthBindingCar tMonthBindingCar = new TMonthBindingCar();
                    tMonthBindingCar.setParkCarId(tParkCarNew.getId());
                    tMonthBindingCar.setParkId(tParkCarNew.getParkId());
                    tMonthBindingCar.setCarPlate(carplate.trim());
                    tMonthBindingCar.setCarType(tParkCarNew.getCarType());
                    tMonthBindingCar.setCreateTime(DateUtil.getCurDateTime());
                    tMonthBindingCar.setUserId(SessionUtil.getUser().getId());
                    tMonthBindingCar.setUpdateTime(DateUtil.getCurDateTime());
                    monthBindingCarService.updateMonthBindingCar(tMonthBindingCar);
                }
                //更新绑定车车辆数
                TMonthBindingCar tMonthBindingCarSel = new TMonthBindingCar();
                tMonthBindingCarSel.setParkId(tParkCarNew.getParkId());
                tMonthBindingCarSel.setParkCarId(tParkCarNew.getId());
                List<TMonthBindingCar> tMonthBindingCarResultList = monthBindingCarService.getMonthBindingCar(tMonthBindingCarSel);
                if(tMonthBindingCarResultList.size()>0){
                    tParkCarNew.setBindingNum(tMonthBindingCarResultList.size());
                    tParkCarInsideService.UpdateTParkCar(tParkCarNew);
                }
                tParkCar.setId(tParkCarNew.getId());
            }else{
                throw new ActionRspException(ActionRspEnum.CarplateCheck_ERROR);
            }
        }
        TParkCar tParkCarSelect = new TParkCar();
        tParkCarSelect.setId(tParkCar.getId());
        TParkCar tParkCarNew = new TParkCar();
/*        if(tParkCar.getParkingSpace()==4){
            tParkCarNew = tParkCarInsideService.getOwnerCar(tParkCarSelect, "", "").get(0);
        }else{
            tParkCarNew = getTParkCarlist(tParkCarSelect, "", "").get(0);
        }*/
        tParkCarNew = tParkCarInsideService.getOwnerCar(tParkCarSelect, "", "").get(0);
//        TParkCar tParkCarNew = getTParkCarlist(tParkCarSelect, "", "").get(0);
        //编辑或新增后下发任务
        syncParkCarTask(tParkCarNew);
        return tParkCarNew;
    }


    @Override
    public List<TParkCar> getCloseParkCar(String closeType, String payRule, String parkid) {
        return tParkCarInsideService.getCloseParkCar(closeType, payRule, parkid);
//        return customizeMapper.selectCloseParkCar(closeType,payRule,parkid);
    }

    @Override
    public PageBean<TParkCar> getCloseParkCarbyPage(String closeType, String payRule, String parkid, Integer page, Integer limit) {
        return tParkCarInsideService.getCloseParkCarbyPage(closeType, payRule, parkid, page, limit);
    }

    @Override
    public JSONObject getNeedPay(Integer payStandard, Integer payCount, TParkCar tParkCar) {
        Double needPay = 0.0;//应收费
        String endDate = null;//截止日期
        JSONObject lan = new JSONObject();
        try {
//            TCarPayRule carPayRule = carPayRuleService.getCarPayRuleByid(tParkCar.getPayRule());
            TCarPayRule carPayRule = tCarPayRuleInsideService.getTCarPayRuleByID(tParkCar.getPayRule());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bigengDate = sdf.parse(tParkCar.getEndDate());


            Integer parkingNo = tParkCar.getParkingNo();
            if (carPayRule != null) {
                //年缴
                if (CommonUtil.CAR_PAY_RULE_TYPE_Y.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getYearFee();
                    endDate = sdf.format(StringUtil3.getBeforeDay(bigengDate, 365 * payCount));
                    //季度
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_Q.equals(payStandard)) {
                    Long needPay1 = (parkingNo * payCount * carPayRule.getSeasonFee());
                    needPay = needPay1.doubleValue();
                    endDate = sdf.format(StringUtil3.getBeforeMonth(bigengDate, 3 * payCount));
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_M.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getMonthFee();
                    endDate = sdf.format(StringUtil3.getBeforeMonth(bigengDate, payCount));
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_D.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getDayFee();
                    endDate = sdf.format(StringUtil3.getBeforeDay(bigengDate, payCount));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //result = "{\"result\":\"success\",\"needPay\":\"" + needPay + "\",\"endDate\":\"" + endDate + "\"}";
        /*lan.addProperty("id",tParkCar.getId());
        lan.addProperty("needPay",needPay);
        lan.addProperty("endDate",endDate);*/
        lan.put("id", tParkCar.getId());
        lan.put("needPay", needPay);
        lan.put("endDate", endDate);
        return lan;
    }


    @Override
    public String Resetport(String portids, String ids) {
//        customizeMapper.Resetport(portids,ids);
        tParkCarInsideService.Resetport(portids, ids);
        return "修改成功";
    }

    @Override
    public Integer getMaturityNo(String parkid) {
//        return customizeMapper.getMaturityNo(parkid);
        return customizeInsideService.getMaturityNo(parkid);
    }

    @Override
    public void exportList(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, String title, HttpServletResponse response) {
//        List<TParkCar> tParkCars = getTParkCarlist(tParkCar, endDateSelectStart, endDateSelectEnd);
        List<TParkCar> tParkCars = tParkCarInsideService.getOwnerCar(tParkCar, endDateSelectStart, endDateSelectEnd);
        List<ExcelParkCar> excelParkCarList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelParkCar防止数组越界
        if(tParkCars.size()<=0){
            ExcelParkCar excelParkCar = new ExcelParkCar();
            excelParkCarList.add(excelParkCar);
        }else{
            for (TParkCar tParkCarsNew : tParkCars) {
//                logger.info("oneCar"+JsonUtil.beanToJson(tParkCarsNew));
                ExcelParkCar excelParkCar = new ExcelParkCar();
                try {
                    excelParkCar = (ExcelParkCar) BeanCopyUtil.CopyBeanToBean(tParkCarsNew, excelParkCar);
                    excelParkCar.setParkingSpaceStr(tParkCarsNew.getParkingSpace());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelParkCarList.add(excelParkCar);
            }
        }
            try {
                ExportExcelUtil.exportExcel(response, title, title, title, excelParkCarList);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public String importMonthlyCar(String fileName, MultipartFile file, Integer parkID, String payRule, String portId) throws IOException {

        boolean notNull = false;
        int k = 0;
        int all = 0;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ActionRspException(ActionRspEnum.Execl_ERROR1);
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        //开始读取Execl
        logger.info("开始读取Execl");
        //InputStream is = file.getInputStream();
        logger.info("开始判断版本");
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(file.getInputStream());
        } else {
            wb = new XSSFWorkbook(file.getInputStream());
        }
        logger.info("版本判断结束");
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = wb.getSheetAt(i);
            logger.info("开始循环");
            all = sheet.getLastRowNum();

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                try {
                    Row row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    TParkCar tParkCar = new TParkCar();
                    String carPlate = row.getCell(0).getStringCellValue();//车牌
                    String carPlateString = (carPlate).replaceAll(" ", "").replaceAll("，", ",").replaceAll("\\s*", "");
                    List<String> cars = Arrays.asList(carPlateString.split(","));
                    for (String parkCarPlate : cars) {
                        if (!StringUtil.checkCarplate(parkCarPlate)) {
                            break;
                        }
                        TMonthBindingCar tMonthBindingCar = new TMonthBindingCar();
                        tMonthBindingCar.setCarPlate(parkCarPlate);
                        if (monthBindingCarService.getMonthBindingCar(tMonthBindingCar).size()>0){
                            break;
                        }
                    }
                    //绑定车数量赋值
//                    tParkCar.setBindingNum(cars.size());
                    if (carPlateString.substring(carPlateString.length() - 1).equals(",")) {
                        tParkCar.setCarPlate(carPlateString.substring(0, carPlateString.length() - 1));
                    } else {
                        tParkCar.setCarPlate(carPlateString);
                    }
                    String carNature = row.getCell(1).getStringCellValue();//车辆类型
                    switch (carNature) {
                        case "月租车":
                            tParkCar.setCarNature(1);
                            break;
                        case "车场充值车":
                            tParkCar.setCarNature(2);
                            break;
                        case "按临停缴费车":
                            tParkCar.setCarNature(3);
                            break;
                        default:
                            break;
                    }
                    logger.info("登记车性质: "+tParkCar.getCarNature().toString());
                    Integer parkingNo = Integer.valueOf(row.getCell(2).getStringCellValue());//占用车位数
                    logger.info("拥有车位数: "+parkingNo.toString());
                    tParkCar.setParkingNo(parkingNo);
                    String parkingSpace = row.getCell(3).getStringCellValue();//车位性质
                    logger.info("车位性质: "+parkingSpace);
                    switch (parkingSpace) {
                        case "机械车位":
                            tParkCar.setParkingSpace(1);
                            break;
                        case "子母车位":
                            tParkCar.setParkingSpace(2);
                            break;
                        case "普通车位":
                            tParkCar.setParkingSpace(3);
                            break;
                        case "车主车位":
                            tParkCar.setParkingSpace(4);
                            break;
                        default:
                            break;
                    }
                    String parkCode = row.getCell(4).getStringCellValue();//车位编码
                    logger.info("车位编码: "+parkCode);
                    if (parkCode != null)
                        tParkCar.setParkCode(parkCode.replaceAll(" ", "").replaceAll("\\s*", "").replaceAll("，", ","));
                    else
                        tParkCar.setParkCode("");
                    if (tParkCar.getParkCode() != null) {
                        if (!StringUtil.checkParkCode(tParkCar.getParkCode())) {
                            break;
                        }
                    }
                    Cell cellBeginDate = sheet.getRow(r).getCell(5);
                    if (cellBeginDate != null) {
                        if (HSSFDateUtil.isCellDateFormatted(cellBeginDate)) {
                            if (cellBeginDate.getNumericCellValue() != 0) {
                                Date dateBegin = HSSFDateUtil.getJavaDate(cellBeginDate.getNumericCellValue());
                                String beginDate = DateUtil.format(dateBegin, DateUtil.g_SimpleDateFormat_I);//月租有效期
                                logger.info("开始时间: "+beginDate);
                                tParkCar.setBeginDate(beginDate);
                            }
                        }
                    }
                    Cell cellEndDate = sheet.getRow(r).getCell(6);
                    if (cellEndDate != null) {
                        if (HSSFDateUtil.isCellDateFormatted(cellEndDate)) {
                            Date dateEnd = HSSFDateUtil.getJavaDate(cellEndDate.getNumericCellValue());
                            String endDate = DateUtil.format(dateEnd, DateUtil.g_SimpleDateFormat_I);//月租有效期
                            logger.info("到期时间: "+endDate);
                            if (tParkCar.getParkingSpace() != 4) {
                                tParkCar.setEndDate(endDate);
                            } else {
                                Date date = DateUtil.getBeforeYear(new Date(), 30);
                                tParkCar.setEndDate(DateUtil.format(date, DateUtil.g_SimpleDateFormat_I));
                            }
                        }
                    }
                    String realname = row.getCell(7).getStringCellValue();//联系人
                    logger.info("联系人: "+realname);
                    tParkCar.setRealname(realname);
                    //设置单元格类型为String，防止数字单元格的数据变为科学记数
                    row.getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String phone = row.getCell(8).getStringCellValue();//手机号码
                    logger.info("电话: "+phone);
                    tParkCar.setPhone(phone);
                    String address = row.getCell(9).getStringCellValue();//住址
                    logger.info("住宅: "+address);
                    tParkCar.setAddress(address);
                    tParkCar.setMemberId(0);
                    tParkCar.setCompanyId(SessionUtil.getCompany().getId());
                    tParkCar.setParkId(parkID);
                    tParkCar.setPayRule(Integer.valueOf(payRule));
                    TCarPayRule tCarPayRule = tCarPayRuleInsideService.getTCarPayRuleByID(Integer.valueOf(payRule));
                    if (tCarPayRule != null) {
                        tParkCar.setPayruleidstr(tCarPayRule.getRuleName());
                    }
                    tParkCar.setIsOnlinePayment(1);
                    tParkCar.setStatus((short) 1);
                    tParkCar.setPortId(portId);
                    tParkCar.setIsDelet((short) 0);
                    if (tParkCar.getParkCode() != null && !tParkCar.getParkCode().equals(""))
                        tParkCar.setIsOwner((short) 1); //有固定车位
                    else
                        tParkCar.setIsOwner((short) 2);
                    if (tParkCar.getCarPlate().contains(","))
                        tParkCar.setBindingName("多车绑定");
                    else
                        tParkCar.setBindingName("");
                    tParkCar.setAutoRenew(1);
                    if (tParkCar.getWalletBanance() == null && tParkCar.getWalletBanance() == "") {
                        tParkCar.setWalletBanance("0.0");
                    }
                    //现在默认为1小车
                    tParkCar.setCarType(1);
                    //月租车状态设为启用
                    tParkCar.setStatus((short)1);
                    UpdateTParkCar(tParkCar);
                    k++;
//                    k = k + tParkCarInsideService.insertTParkCarMap(tParkCar);
                } catch (Exception e) {
                    logger.info(e.toString());
                    //throw new ActionRspException(ActionRspEnum.Execl_ERROR);
                }
            }
        }
        return "一共" + (all) + "条数据，成功导入" + k + "条，失败" + (all - k) + "条";
    }

    /*根据主键id获取实体*/
    @Override
    public TParkCar getTParkCarById(Integer id) {
//        return tParkCarMapper.selectByPrimaryKey(id);
        return tParkCarInsideService.getTParkCarById(id);
    }

    /*月租车冲正*/
    @Override
    public String correct(TParkCar tParkCar, Double needPay, Short payType, String remark) {
        TCarPayment tCarPayment = new TCarPayment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newTime = sdf.format(new Date());
        //获取父停车场信息
        boolean istaks = false;
//        TParkCar parentParkCar = tParkCarMapper.selectByPrimaryKey(tParkCar.getId());
        TParkCar parentParkCar = tParkCarInsideService.getTParkCarById(tParkCar.getId());
        if (!tParkCar.getEndDate().trim().equals(parentParkCar.getEndDate().trim())) {
            istaks = true;
            parentParkCar.setIsSync((short) 0);//未同步
        }
        parentParkCar.setEndDate(tParkCar.getEndDate());
        parentParkCar.setBeginDate(tParkCar.getBeginDate());
        tCarPayment.setCarplate(parentParkCar.getCarPlate());
        tCarPayment.setOperType(CommonUtil.OPER_TYPE_2); //充正
        tCarPayment.setMemberId(parentParkCar.getMemberId());
        tCarPayment.setParkId(parentParkCar.getParkId());
        tCarPayment.setPayTime(DateUtil.getCurDateTime());
        tCarPayment.setActualPay(needPay * -1);
        tCarPayment.setNeedPay(needPay * -1);
        tCarPayment.setPayRule(parentParkCar.getPayRule());
        tCarPayment.setCompanyId(parentParkCar.getCompanyId());
        tCarPayment.setBeginDate(tParkCar.getBeginDate());
        tCarPayment.setEndDate(tParkCar.getEndDate());
        tCarPayment.setPayType(payType);
        tCarPayment.setRemark(remark);
//        tCarPaymentMapper.insert(tCarPayment);
//        tParkCarMapper.updateByPrimaryKey(parentParkCar);
        tCarPaymentInsideService.UpdateTCarPayment(tCarPayment);
        tParkCarInsideService.UpdateTParkCar(parentParkCar);
        if (istaks) {            //同步数据
            syncParkCarTask(parentParkCar);
        }
        return "成功冲正";
    }

    /*删除*/
    @Override
    public String refund(TParkCar parkCar, TCarPayment carPayment, String isRefund) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newTime = sdf.format(new Date());
        //获取父停车场信息
//        TParkCar parentParkCar = tParkCarMapper.selectByPrimaryKey(parkCar.getId());
        TParkCar parentParkCar = tParkCarInsideService.getTParkCarById(parkCar.getId());
        parentParkCar.setIsSync(CommonUtil.STATUS_NO.shortValue());//未同步
        parentParkCar.setEndDate(sdf.format(StringUtil3.getBeforeDay(new Date(), -1))); //过期时间为昨天
        parentParkCar.setIsDelet(CommonUtil.STATUS_YES.shortValue()); //删除状态
        if ("1".equals(isRefund)) {
            carPayment.setCarplate(parentParkCar.getCarPlate());
            carPayment.setOperType(CommonUtil.OPER_TYPE_3); //退款
            carPayment.setMemberId(parentParkCar.getMemberId());
            carPayment.setParkId(parentParkCar.getParkId());
            carPayment.setEndDate(sdf.format(StringUtil3.getBeforeDay(new Date(), -1))); //过期时间为昨天
            carPayment.setPayTime(DateUtil.getCurDateTime());
            carPayment.setActualPay(carPayment.getNeedPay() * -1);
            carPayment.setNeedPay(carPayment.getNeedPay() * -1);
            carPayment.setCompanyId(parentParkCar.getCompanyId());
//            tCarPaymentMapper.insert(carPayment);
            tCarPaymentInsideService.UpdateTCarPayment(carPayment);
        }
//        tParkCarMapper.deleteByPrimaryKey(parentParkCar.getId());
        //删除登记车绑定表的相关车辆数据
        TMonthBindingCar tMonthBindingCarSel = new TMonthBindingCar();
        tMonthBindingCarSel.setParkId(parkCar.getParkId());
        tMonthBindingCarSel.setParkCarId(parkCar.getId());
        List<TMonthBindingCar> tMonthBindingCarResultList = monthBindingCarService.getMonthBindingCar(tMonthBindingCarSel);
        if(tMonthBindingCarResultList.size()>0){
            for(TMonthBindingCar tMonthBindingCarOne:tMonthBindingCarResultList){
                monthBindingCarService.deleteMonthBindingCar(tMonthBindingCarOne);
            }
        }
        tParkCarInsideService.DeleteTParkCar(parentParkCar);
        //parkCarDao.update(parentParkCar);
        //数据同步
        syncParkCarTask(parentParkCar);
        return "删除成功";
    }

    @Override
    public String recharge(TCarPayment carPayment, TParkCar parkCar) {
        String error = null;
        try {
            SimpleDateFormat sdf_ss = new SimpleDateFormat("yyyy-MM-dd");
            //criteria.andIdEqualTo(parkCar.getId());
            TParkCar parentParkCar = parkCar;
            //if(StringUtil3.isNull(parkCar.getBeginDate()))
            //throw new ServiceException("请填写起始时间");
            //if(StringUtil3.isNull(parkCar.getEndDate()))
            //throw new ServiceException("请填写过期时间");
            //月租车过期
            if (sdf_ss.parse(parentParkCar.getEndDate()).getTime() < sdf_ss.parse(StringUtil3.newTime).getTime()) {
                //续费日期大于原截至日期
                if (sdf_ss.parse(parentParkCar.getEndDate()).getTime() < sdf_ss.parse(parkCar.getBeginDate()).getTime()) {
                    parentParkCar.setBeginDate(parkCar.getBeginDate());
                }
            }
            parentParkCar.setEndDate(carPayment.getEndDate());
            parentParkCar.setIsSync((short) 0);
            carPayment.setCarplate(parentParkCar.getCarPlate());
            carPayment.setOperType(1); //充值
            carPayment.setMemberId(parentParkCar.getMemberId());
            carPayment.setParkId(parentParkCar.getParkId());
            carPayment.setPayTime(DateUtil.getCurDateTime());
            carPayment.setCompanyId(parentParkCar.getCompanyId());
            carPayment.setPayType((short) 1);
            carPayment.setBeginDate(parentParkCar.getBeginDate());
            if (parentParkCar.getIsOwner() == null) {
                if (parentParkCar.getParkCode() == null || parentParkCar.getParkCode().equals("")) {//1=独占车位2=公用车位，此值根据是否有车位编码确定
                    parentParkCar.setIsOwner((short) 2);
                } else {
                    parentParkCar.setIsOwner((short) 1);
                }
            }
//            tCarPaymentMapper.insert(carPayment);
//            tParkCarMapper.updateByPrimaryKey(parentParkCar);
            tCarPaymentInsideService.UpdateTCarPayment(carPayment);
            tParkCarInsideService.UpdateTParkCar(parentParkCar);
            error = "success";
            parentParkCar.setEndDate(carPayment.getEndDate());
            //同步数据
            TMember member = new TMember();
            //syncMember(parentParkCar);
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
            throw new RuntimeException(e.getMessage());
        }
        return error;
    }


    /**
     * 用于月租车下发任务
     *
     * @param parkCar
     */
    public synchronized void syncParkCarTask(TParkCar parkCar) {
        ParkCarTask parkCarTask = new ParkCarTask();
        parkCarTask.setAddress(StringUtil.enCode(parkCar.getAddress()));
        parkCarTask.setBindingName(StringUtil.enCode(parkCar.getBindingName()));
        parkCarTask.setCar_plate(StringUtil.enCode(parkCar.getCarPlate()));
        parkCarTask.setCar_type(parkCar.getCarType());
        parkCarTask.setCloud_park_car_id(parkCar.getId());
        parkCarTask.setIsActivate(CommonUtil.MEMBER_LIVE_STATUS_2);
        //parkCarTask.setIsOwner(Integer.valueOf(parkCar.getIsOwner()));
        parkCarTask.setIsOwner(0);
        parkCarTask.setIsUse(parkCar.getIsUse());
        parkCarTask.setLane_rights(parkCar.getPortId());
        parkCarTask.setMcard_begin_date(parkCar.getBeginDate());
        //正常状态返回充值后的到期日期，否则返回暂停日期
        if (parkCar.getStatus() == 1) {
            parkCarTask.setMcard_end_date(parkCar.getEndDate());
        } else if (parkCar.getStatus() == 2) {
            parkCarTask.setMcard_end_date(parkCar.getStopTime());
        }
        parkCarTask.setMcard_status(parkCar.getStatus() == null ? 1 : Integer.valueOf(parkCar.getStatus()));
        parkCarTask.setMember_id(parkCar.getId());
        parkCarTask.setParkingNo(parkCar.getParkingNo());
        parkCarTask.setParkCode(parkCar.getParkCode());
        parkCarTask.setParkingSpace(parkCar.getParkingSpace());
        parkCarTask.setPhone(parkCar.getPhone());
        parkCarTask.setRealname(StringUtil.enCode(parkCar.getRealname()));
        parkCarTask.setP_card_id("");
        parkCarTask.setP_card_code("");
        //删除下发任务时，云端直接删除数据库信息，实体类isdelet改成1,传入
        if (parkCar.getIsDelet() == 1) {
            parkCarTask.setDelete("true");
        } else {
            parkCarTask.setDelete("false");
        }
        //填入月租车每日有效时间
//        TCarPayRule tCarPayRule = tCarPayRuleMapper.selectByPrimaryKey(parkCar.getPayRule());
        TCarPayRule tCarPayRule = tCarPayRuleInsideService.getTCarPayRuleByID(parkCar.getPayRule());
        parkCarTask.setRuleName(StringUtil.enCode(tCarPayRule.getRuleName()));
        parkCarTask.setDailyStartTime(tCarPayRule.getDailyStartTime());
        parkCarTask.setDailyEndTime(tCarPayRule.getDailyEndTime());
        parkCarTask.setCarNature(parkCar.getCarNature());
        parkCarTask.setRenewalTime(parkCar.getRenewalTime());
        parkCarTask.setTempPayRule(parkCar.getTempPayRule());
        parkCarTask.setBindingName(StringUtil.enCode(parkCar.getBindingName()));
        parkCarTask.setBindingNum(parkCar.getBindingNum());
        parkCarTask.setBalance(parkCar.getWalletBanance());
/*        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkCar.getParkId());
        //查找该停车场所有的出场电脑
        criteria.andPortTypeEqualTo(1);
        List<TParkPort> tParkPortList = tParkPortMapper.selectByExample(tParkPortCriteria);*/
//        System.out.println(JsonUtil.beanToJson(parkCarTask));
        TParkPort tParkPortSel = new TParkPort();
        tParkPortSel.setParkId(parkCar.getParkId());
        List<TParkPort> tParkPortList = tParkPortInsideService.getTParkPortOutPort(tParkPortSel);
        for (TParkPort tParkPort : tParkPortList) {
//        customizeMapper.insetParktable(parkCar.getParkId(),parkCar.getId(),parkCarTask,tParkPort.getComputerIndex());
            customizeInsideService.insetParktable(parkCar.getParkId(), parkCar.getId(), parkCarTask, tParkPort.getComputerIndex());
        }
    }

    @Override
    public PageBean<TParkCar> getOwnnerCarbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
        return tParkCarInsideService.getOwnnerCarbyPage(tParkCar, endDateSelectStart, endDateSelectEnd, page, limit);
    }

    @Override
    public List<TParkCar> getOwnerCar(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd) {
        return tParkCarInsideService.getOwnerCar(tParkCar, endDateSelectStart, endDateSelectEnd);
    }

    @Override
    public List<TParkCar> whetherParkCar(Integer parkId, String carPlate, String endDateSelectStart, String endDateSelectEnd) {
/*        TParkCar tParkCar = new TParkCar();
        tParkCar.setParkId(parkId);
        tParkCar.setCarPlate(carPlate);
        tParkCar.setEndDate(DateUtil.getCurDateTime());
        return tParkCarMapper.selectByExample(setCriteria(tParkCar));*/
        return tParkCarInsideService.whetherTParkCar(parkId, carPlate, endDateSelectStart, endDateSelectEnd);
    }


    @Override
    public String monthlyPrepayment(Integer parkId, String carPlate, Double needPay, Double actualPay, Integer ePayType, String beginDate, String endDate, Integer monthNum, Integer payRule, Integer companyId) {
        String msg = "";
        if (parkId != null && carPlate != null && carPlate != "") {
            TMonthBindingCar tMonthBindingCarSel = new TMonthBindingCar();
            tMonthBindingCarSel.setParkId(parkId);
            tMonthBindingCarSel.setCarPlate(carPlate);
            List<TMonthBindingCar> tMonthBindingCarList = monthBindingCarService.getMonthBindingCar(tMonthBindingCarSel);
            if (tMonthBindingCarList.size() > 0) {
                Integer parkCarId = tMonthBindingCarList.get(0).getParkCarId();
                TParkCar tParkCarSel = new TParkCar();
                tParkCarSel.setParkId(parkId);
                tParkCarSel.setCarPlate(carPlate);
                tParkCarSel.setId(parkCarId);
                if (tParkCarInsideService.getTParkCar(tParkCarSel, "", "").size() > 0) {
                    tParkCarSel.setBeginDate(beginDate);
                    tParkCarSel.setEndDate(endDate);
                    TParkCar tParkCar = new TParkCar();
                    tParkCar = UpdateTParkCar(tParkCarSel);
                    if (tParkCar != null) {
                        TCarPayment tCarPayment = new TCarPayment();
                        tCarPayment.setActualPay(actualPay);
                        tCarPayment.setBeginDate(beginDate);
                        tCarPayment.setCarplate(carPlate);
                        tCarPayment.setEndDate(endDate);
                        tCarPayment.setMemberId(tParkCarSel.getId());
                        tCarPayment.setNeedPay(needPay);
                        tCarPayment.setParkId(parkId);
                        tCarPayment.setPayCount(monthNum);
                        tCarPayment.setPayRule(payRule);
                        tCarPayment.setCompanyId(companyId);
//                        msg = carPaymentService.UpdateTCarPayment(tCarPayment);
                        msg = tCarPaymentInsideService.UpdateTCarPayment(tCarPayment);
//                        tCarPaymentService.UpdateTCarPayment(tCarPayment);
                    } else {
                        throw new ActionRspException(ActionRspEnum.MonthlyPrepayment_ERROR);
                    }
                }
            }
        }
        return msg;
    }

    @Override
    public Integer getPresentCarNum(List<Integer> parkIDlist) {
        String idList = JsonUtil.listToJson(parkIDlist);
        return tParkCarInsideService.getPresentCarNum(idList);
    }

    @Override
    @Transactional
    public String monthlyWalletRecharge(Integer id, Double needPay, Double actualPay, String remark,String rechargeType) {
        String msg = "";
        TParkCar tParkCarSel = new TParkCar();
        tParkCarSel.setId(id);
        List<TParkCar> tParkCarList = tParkCarInsideService.getTParkCar(tParkCarSel, "", "");
        if (tParkCarList.size() > 0) {
            TParkCar tParkCarUpdate = tParkCarList.get(0);
            if(rechargeType.equals("recharge")){
                tParkCarUpdate.setWalletBanance(String.valueOf((Double.valueOf(tParkCarUpdate.getWalletBanance()) + needPay)));
            }else{
                tParkCarUpdate.setWalletBanance(String.valueOf((Double.valueOf(tParkCarUpdate.getWalletBanance()) - needPay)));
            }
            String parkCarMsg = tParkCarInsideService.UpdateTParkCar(tParkCarUpdate);
            if (parkCarMsg.equals("更新成功")) {
                TCarownerPayment tCarownerPayment = new TCarownerPayment();
                tCarownerPayment.setCompanyId(SessionUtil.getCompany().getId());
                tCarownerPayment.setParkId(SessionUtil.getParkId());
                tCarownerPayment.setParkCarId(tParkCarUpdate.getId());
                tCarownerPayment.setCarplate(tParkCarUpdate.getCarPlate());
                tCarownerPayment.setRechargeNo(DateUtil.getCurDateTimeYMDHMS() + tParkCarUpdate.getId());
                tCarownerPayment.setWalletBanance(tParkCarUpdate.getWalletBanance());
                if(rechargeType.equals("recharge")){
                    tCarownerPayment.setOperType(1);
                    tCarownerPayment.setNeedPay(needPay);
                    tCarownerPayment.setActualPay(actualPay);
                }else{
                    tCarownerPayment.setOperType(2);
                    tCarownerPayment.setNeedPay(needPay*-1);
                    tCarownerPayment.setActualPay(actualPay*-1);
                }
                tCarownerPayment.setPayTime(DateUtil.getCurDateTime());
                tCarownerPayment.setPayType((short) 1);
                tCarownerPayment.setRemark(remark);
                tCarownerPayment.setOperator(SessionUtil.getUser().getUserAccout());
                msg = tCarownerPaymentInsideService.UpdateTCarownerPayment(tCarownerPayment);
            }
        }
        return msg;
    }

    @Override
    public List<TParkCar> getTParkCarByCarplate(Integer parkId, String carplate) {
        return customizeInsideService.getTParkCarByCarplate(parkId, carplate);
    }

    @Override
    public List<TParkCar> getTParkCarForStatistics(TParkCar tParkCar, String date) {
/*        TParkCar tParkCar = new TParkCar();
        tParkCar.setParkId(parkId);
        tParkCar.setCarPlate(carPlate);
        tParkCar.setEndDate(DateUtil.getCurDateTime());
        return tParkCarMapper.selectByExample(setCriteria(tParkCar));*/
        return tParkCarInsideService.getTParkCarForStatistics(tParkCar, date);
    }

    @Override
    public PageBean<TParkCar> getTParkCarFuzzybyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
//        if(tParkCar.getCarPlate()!=null && !tParkCar.getCarPlate().equals("")){
            List<Integer> parkCarIdList = new ArrayList<>();
            if(tParkCar.getCarPlate()!=null&&tParkCar.getCarPlate()!=""){
                TMonthBindingCar tMonthBindingCarSel = new TMonthBindingCar();
                tMonthBindingCarSel.setParkId(tParkCar.getParkId());
                tMonthBindingCarSel.setCarPlate(tParkCar.getCarPlate());
                List<TMonthBindingCar> tMonthBindingCarList = monthBindingCarService.getMonthBindingCar(tMonthBindingCarSel);
                if(tMonthBindingCarList.size()>0){
//                tParkCar.setId(tMonthBindingCarList.get(0).getParkCarId());
                    for(TMonthBindingCar tMonthBindingCarOne : tMonthBindingCarList){
                        parkCarIdList.add(tMonthBindingCarOne.getParkCarId());
                    }
                }else{
                    parkCarIdList.add(0);
                }
            }
        String idList = StringUtil.listToSring(parkCarIdList);
        return tParkCarInsideService.getTParkCarFuzzybyPage(tParkCar,idList,endDateSelectStart,endDateSelectEnd, page, limit);
    }

}
