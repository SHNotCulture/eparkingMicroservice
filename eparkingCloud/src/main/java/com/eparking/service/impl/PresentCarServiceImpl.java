package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.ExcelPresentCarParkInOut;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.insideService.TCompanyParkInsideService;
import com.eparking.insideService.TPresentCarInsideService;
import com.eparking.service.PresentCarService;
import com.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PresentCarServiceImpl
 * @Author jin
 * @Date 2018/9/18 10:38
 **/

@Service
public class PresentCarServiceImpl implements PresentCarService {

/*    @Autowired
    private TParkInOutMapper tParkInOutMapper;
    @Autowired
    private TCompanyParkMapper tCompanyParkMapper;
    @Autowired
    private CustomizeMapper customizeMapper;*/
    @Autowired
    private CustomizeInsideService customizeInsideService;
    @Autowired
    private TCompanyParkInsideService tCompanyParkInsideService;
    @Autowired
    private TPresentCarInsideService tPresentCarInsideService;

    /**
     * 查询此车场在场的 临时车 数量
     * @return
     */
    @Override
    public Integer FindTemporaryCar(){
        /*TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
        criteria.andCarNatureEqualTo(3);
        criteria.andInTimeIsNotNull();
        criteria.andOutTimeIsNull();
        return tParkInOutMapper.sumPresentCar(tParkInOutCriteria);*/
/*        List<TParkInOut> list = customizeMapper.selectPresentCar(SessionUtil.getParkId().toString(),"and car_nature=3");
        return list.size();*/
        Integer num = customizeInsideService.selectPresentCar(SessionUtil.getParkId().toString(),"and car_nature=3").size();
        return num;
    }

    /**
     * 查询此车场在场的 月租车 数量
     * @return
     */
    @Override
    public Integer FindMonthlyCar(){
        /*TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
        criteria.andCarNatureEqualTo(2);
        criteria.andInTimeIsNotNull();
        criteria.andOutTimeIsNull();*/
//        return customizeMapper.selectPresentCar(SessionUtil.getParkId().toString(),"and car_nature=2").size();
        Integer num = customizeInsideService.selectPresentCar(SessionUtil.getParkId().toString(),"and car_nature=2").size();
        return num;
    }
    /**
     * 查询此车场在场的 私家车 数量
     * @return
     */
    @Override
    public Integer FindPrivateCar(){
       /* TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
        criteria.andCarNatureEqualTo(1);
        criteria.andInTimeIsNotNull();
        criteria.andOutTimeIsNull();*/
//        return customizeMapper.selectPresentCar(SessionUtil.getParkId().toString(),"and car_nature=1").size();
        Integer num = customizeInsideService.selectPresentCar(SessionUtil.getParkId().toString(),"and car_nature=1").size();
        return num;
    }
    /**
     * 查询此车场的 总车位 数量
     * @return
     */
    @Override
    public Integer FindTotalParkingSpace(){
/*        TCompanyPark tCompanyPark = tCompanyParkMapper.selectByPrimaryKey(SessionUtil.getParkId());
        return tCompanyPark.getTotalParkingSpace();*/
        TCompanyPark tCompanyParkSel = new TCompanyPark();
        tCompanyParkSel.setId(SessionUtil.getParkId());
        TCompanyPark tCompanyPark = tCompanyParkInsideService.getTCompanyParkByID(tCompanyParkSel);
        return tCompanyPark.getTotalParkingSpace();
    }

    /**
     *设置查询条件
     * @param tParkInOut
     * @param timeType
     * @return
     */
/*    private String setCriteria(TParkInOut tParkInOut,String timeType){
        String bDate = null;
        String eData = null;
        String nData = DateUtil.getCurDateTime();
        switch (timeType) {
            case "stageOne":
                bDate = DateUtil.getStartDateInterval(nData,-30);
                break;
            case "stageTwo":
                bDate = DateUtil.getStartDateInterval(nData,-60);
                eData = DateUtil.getStartDateInterval(nData,-30);
                break;
            case "stageThree":
                bDate = DateUtil.getStartDateInterval(nData,-(60*24));
                eData = DateUtil.getStartDateInterval(nData,-60);
                break;
            case "stageFour":
                bDate = DateUtil.getStartDateInterval(nData,-(60*24*7));
                eData = DateUtil.getStartDateInterval(nData,-(60*24));
                break;
            case "stageFive":
                eData = DateUtil.getStartDateInterval(nData,-(60*24*7));
                break;
            default:
                bDate = null;
                eData = null;
                break;
        }
        *//*TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();*//*
        StringBuffer stringBuffer = new StringBuffer();
        if (bDate!=null && eData!=null){
            stringBuffer.append(" and in_time between '"+bDate+"' and '"+eData+"'");
        }if (bDate==null && eData!=null){
            stringBuffer.append(" and in_time<='"+eData+"'");
        }if (bDate!=null && eData==null){
            stringBuffer.append(" and in_time>='"+bDate+"'");
        }
        if(tParkInOut!=null){
            if (tParkInOut.getInPortId()!=null){
                stringBuffer.append(" and in_port_id="+tParkInOut.getInPortId()+"");
            }if (tParkInOut.getCarNature()!=null && tParkInOut.getCarNature()!=0){
                stringBuffer.append(" and car_nature="+tParkInOut.getCarNature()+"");
            }
            if (!StringUtils.isBlank(tParkInOut.getInCarPlate())){
                stringBuffer.append(" and in_car_plate like '%"+tParkInOut.getInCarPlate()+"%'");
            }
            if (tParkInOut.getInTime()!=null || tParkInOut.getOutTime()!=null){if (tParkInOut.getInTime().length()>1 || tParkInOut.getOutTime().length()>1){
                stringBuffer.append(" and in_time between '"+tParkInOut.getInTime()+"' and '"+tParkInOut.getOutTime()+"'");
            }}
            if (tParkInOut.getUpdatedincarplate()!=null){
                stringBuffer.append(" and LENGTH(updatedInCarplate)>0");
            }
        }
        return  stringBuffer.toString();
    }*/

    /**
     * 在场车辆(分页)
     * @param tParkInOut
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TParkInOut> getPresentCarbyPage(TParkInOut tParkInOut, String parkId, String timeType, Integer page, Integer limit){
/*        PageHelper.startPage(page, limit,"occur_time desc");
        List<TParkInOut> tParkInOuts=getPresentCar(tParkInOut,timeType,parkId);
        Integer countNums =getCount(tParkInOut,timeType,parkId);
        PageBean<TParkInOut> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkInOuts);
        return pageData;*/
        return tPresentCarInsideService.getTPresentCarbyPage(tParkInOut, parkId, timeType, page, limit);
    }
    /**
     * 表单搜索查询在场车辆
     * @param tParkInOut
     * @return
     */
    @Override
    public List<TParkInOut> getPresentCar(TParkInOut tParkInOut,String timeType,String parkId) {

//         List<TParkInOut> tParkInOutList = customizeMapper.selectPresentCar(parkId,andSql);
/*        String andSql = setCriteria(tParkInOut,timeType);
        List<TParkInOut> tParkInOutList = customizeInsideService.selectPresentCar(parkId,andSql);
        for (TParkInOut parkInOut:tParkInOutList) {
            parkInOut.setStopTime(DateUtil.nowBetweenStrtime(parkInOut.getInTime()));
        }
        return tParkInOutList;*/
        return tPresentCarInsideService.getTPresentCar(tParkInOut, timeType, parkId);
    }

    @Override
    public void exportList(TParkInOut tParkInOut,String timeType, String title,String parkId, HttpServletResponse response) {
        List<TParkInOut> tParkInOutList = getPresentCar(tParkInOut,timeType,parkId);
        List<ExcelPresentCarParkInOut> excelPresentCarParkInOutList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelPresentCarParkInOut防止数组越界
        if (tParkInOutList.size() <= 0) {
            ExcelPresentCarParkInOut excelPresentCarParkInOut = new ExcelPresentCarParkInOut();
            excelPresentCarParkInOutList.add(excelPresentCarParkInOut);
        } else {
            for (TParkInOut ParkInOutlist : tParkInOutList){
                ExcelPresentCarParkInOut excelPresentCarParkInOut = new ExcelPresentCarParkInOut();
                try {
                    excelPresentCarParkInOut=(ExcelPresentCarParkInOut) BeanCopyUtil.CopyBeanToBean(ParkInOutlist,excelPresentCarParkInOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelPresentCarParkInOutList.add(excelPresentCarParkInOut);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response,title,title,title,excelPresentCarParkInOutList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索在场记录（根据车牌）
     * @param
     * @return
     */
    @Override
    public String selectPresentCarByCarplate(Integer parkId,String carplate){
//        String inTime = customizeMapper.selectPresentCarByCarplate(parkId.toString(),carplate).getInTime();
        String inTime = customizeInsideService.selectPresentCarByCarplate(parkId.toString(),carplate).getInTime();
        return inTime;
    }


    /**
     * 模糊查询车牌
     * @param parkId
     * @param carPlate
     * @return
     */
    @Override
    public List<String> selectPresentCarLikeCarplate(Integer parkId, String carPlate) {
//        List<String> carlist = customizeMapper.selectPresentCarLikeCarplate(parkId, carPlate);
          List<String> carlist = customizeInsideService.selectPresentCarLikeCarplate(parkId, carPlate);
        return carlist;
    }

}
