package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelParkingRecordParkInOut;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.insideService.TParkingRecordInsideService;
import com.eparking.service.ParkingRecordService;
import com.common.util.BeanCopyUtil;
import com.common.util.ExportExcelUtil;
import com.common.util.JsonUtil;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ParkingRecordServiceImpl
 * @Author jin
 * @Date 2018/9/20 23:35
 **/
@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {
    @Autowired
    private TParkingRecordInsideService tParkingRecordInsideService;
    @Autowired
    private CustomizeInsideService customizeInsideService;

    /**
     * 设置查询条件
     *
     * @param parkInOut
     * @return
     */
/*    private TParkInOutCriteria setCriteria(TParkInOut parkInOut) {
        TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
        //TParkInOutCriteria.Criteria criteria2 = tParkInOutCriteria.createCriteria();
        criteria.andOutTimeIsNotNull();
        //criteria2.andOutTimeIsNotNull();
        if (parkInOut != null) {
            if (parkInOut.getUpdatedincarplate() != null) {
                criteria.andUpdatedincarplateIsUpdated();
                //criteria2.andUpdatedoutcarplateIsNotNull();
                criteria.andUpdatedoutcarplateIsUpdated();
                //criteria2.andUpdatedoutcarplateLengthGreaterThan(0);
            }
            if (parkInOut.getOutPortId() != null) {
                criteria.andOutPortIdEqualTo(parkInOut.getOutPortId());
                //criteria2.andOutPortIdEqualTo(parkInOut.getOutPortId());
            }
            if (parkInOut.getInCarPlate() != null && parkInOut.getInCarPlate().length() > 0) {
                criteria.andInCarPlateLike("%" + parkInOut.getInCarPlate() + "%");
                //criteria2.andInCarPlateLike("%"+parkInOut.getInCarPlate()+"%");
            }
            if (parkInOut.getOutType() != null) {
                criteria.andOutTypeEqualTo(parkInOut.getOutType());
                //criteria2.andOutTypeEqualTo(parkInOut.getOutType());
            }
            if (parkInOut.getInCardCode() != null) {
                if (parkInOut.getInCardCode().equals("actual_pay")) {
                    criteria.andActualPayGreaterThan(0.0);
                    //criteria2.andActualPayGreaterThan(0.0);
                }
                if (parkInOut.getInCardCode().equals("qpass_pay")) {
                    criteria.andQpassPayGreaterThan(0.0);
                    //criteria2.andQpassPayGreaterThan(0.0);
                }
                if (parkInOut.getInCardCode().equals("coupon")) {
                    criteria.andCouponGreaterThan(0.0);
                    //criteria2.andCouponGreaterThan(0.0);
                }
                if (parkInOut.getInCardCode().equals("before_pay")) {
                    criteria.aneparkingdbeforePayGreaterThan(0.0);
                    //criteria2.aneparkingdbeforePayGreaterThan(0.0);
                }
            }
            if (parkInOut.getInPortName() != null && parkInOut.getInPortName() != "") {
                criteria.andInPortNameEqualTo(parkInOut.getInPortName());
            }
            if (parkInOut.getOutPortName() != null && parkInOut.getOutPortName() != "") {
                criteria.andOutPortNameEqualTo(parkInOut.getOutPortName());
            }
            if (parkInOut.getDutyPerson() != null && parkInOut.getDutyPerson().length() > 0) {
                criteria.andDutyPersonLike("%" + parkInOut.getDutyPerson() + "%");
                //criteria2.andDutyPersonLike("%"+parkInOut.getDutyPerson()+"%");
            }
            if (parkInOut.getInTime() != null && parkInOut.getInTime().length() > 0) {
                criteria.andInTimeGreaterThanOrEqualTo(parkInOut.getInTime());
                //criteria2.andInTimeGreaterThanOrEqualTo(parkInOut.getInTime());
            }
            if (parkInOut.getOutTime() != null && parkInOut.getOutTime().length() > 0) {
                criteria.andInTimeLessThanOrEqualTo(parkInOut.getOutTime());
                //criteria2.andInTimeLessThanOrEqualTo(parkInOut.getOutTime());
            }
            if (parkInOut.getChargeType() != null && !parkInOut.getChargeType().equals("")) {
                criteria.andChargeTypeEqualTo(parkInOut.getChargeType());
                //criteria2.andChargeTypeEqualTo(parkInOut.getChargeType());
            }
            if (parkInOut.getCloudOrderId() != null) {
                criteria.andCloudOrderIdLike("%" + parkInOut.getCloudOrderId() + "%");
            }
            if (parkInOut.getSettleDate() != null) {
                String temp[] = parkInOut.getSettleDate().split("@");
                if (temp[0].length() > 2) {
                    criteria.andSettleDateGreaterThanOrEqualTo(temp[0]);
                }
                if (temp[1].length() > 2) {
                    criteria.andSettleDateLessThanOrEqualTo(temp[1]);
                }
            }
        }
        return tParkInOutCriteria;
    }*/

    /**
     * 获取数据总量
     *
     * @param tParkInOut
     * @return
     */
/*    private Integer getCount(TParkInOut tParkInOut) {
        Integer total = (int) tParkInOutMapper.countByExample(setCriteria(tParkInOut), tParkInOut.getParkId());
        return total;
    }*/

    /**
     * 查询进出场记录（分页）
     *
     * @param tParkInOut
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkInOut> getParkinoutbyPage(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit, "occur_time desc");
        List<TParkInOut> tParkInOuts = getParkInOut(tParkInOut);
        *//*System.out.println("旧"+tParkInOuts.size());
        if (tParkInOut.getUpdatedincarplate()!=null){
            Iterator<TParkInOut> it = tParkInOuts.iterator();
            while(it.hasNext()){
                TParkInOut x = it.next();
                if(StringUtils.isBlank(x.getUpdatedincarplate()) || StringUtils.isBlank(x.getUpdatedoutcarplate())){
                    it.remove();
                }
            }
        }
        System.out.println("新"+tParkInOuts.size());*//*
        Integer countNums = getCount(tParkInOut);
        PageBean<TParkInOut> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkInOuts);
        return pageData;*/
        return tParkingRecordInsideService.getParkinoutbyPage(tParkInOut,outTimeBegin,outTimeEnd, page, limit);
    }

    /**
     * 查询进出场详情（分页）
     *
     * @param tParkInOut
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkInOut> getParkinoutDetailbyPage(TParkInOut tParkInOut, String parkId, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit, "id desc");
        List<TParkInOut> tParkInOuts = null;
        String timeType = "";
        Integer countNums = null;
        if (tParkInOut.getInTime() != null && tParkInOut.getInTime().length() > 0) {
            timeType += "and out_time>='" + tParkInOut.getInTime() + "'";
        }
        if (tParkInOut.getOutTime() != null && tParkInOut.getOutTime().length() > 0) {
            timeType += "and out_time<='" + tParkInOut.getOutTime() + "'";
        }
        if (tParkInOut.getChargeType() == 3) {
            tParkInOuts = customizeMapper.selectDetail(parkId, "actual_pay>0.0 " + timeType + "");
            countNums = customizeMapper.selectDetailNum(parkId, "actual_pay>0.0 " + timeType + "");
        }
        if (tParkInOut.getChargeType() == 1) {
            tParkInOuts = customizeMapper.selectDetail(parkId, "(qpass_pay+coupon+before_pay)>0.0 " + timeType + "");
            countNums = customizeMapper.selectDetailNum(parkId, "(qpass_pay+coupon+before_pay)>0.0 " + timeType + "");
        }
        PageBean<TParkInOut> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkInOuts);
        return pageData;*/
        return tParkingRecordInsideService.getParkinoutDetailbyPage(tParkInOut, parkId, page, limit);
    }

    /**
     * 查询未出场车辆信息
     *
     * @param tParkInOut
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TParkInOut> getInCarbyPage(TParkInOut tParkInOut, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit, "in_time desc");
        String andSql = "";
        if (tParkInOut.getInCarPlate() != null && tParkInOut.getInCarPlate().length() > 0) {
            andSql = " and in_car_plate like '%" + tParkInOut.getInCarPlate() + "%'";
        }

        List<TParkInOut> tParkInOutList = customizeMapper.selectPresentCar(tParkInOut.getParkId().toString(), andSql);
*//*        List<TParkInOut> tParkInOuts=getParkinout(tParkInOut);
        List<TParkInOut> tInCarList = null;*//*

         *//*        if(tParkInOuts.size()>0){
            for(int i=0;i<tParkInOuts.size();i++){
                if(tParkInOuts.get(i).getInTime()!=null && tParkInOuts.get(i).getInTime().length()>0  && tParkInOuts.get(i).getOutTime()==null){
                    tInCarList.add(tParkInOuts.get(i));
                }
            }
        }*//*
        Integer countNums = tParkInOutList.size();
        PageBean<TParkInOut> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkInOutList);
        return pageData;*/
        return tParkingRecordInsideService.getInCarbyPage(tParkInOut, page, limit);
    }

    /**
     * 查询进出场记录
     *
     * @param tParkInOut
     * @return
     */
    public List<TParkInOut> getParkInOut(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd) {
//        List<TParkInOut> tParkInOuts = tParkInOutMapper.selectByExample(setCriteria(tParkInOut), tParkInOut.getParkId());
        List<TParkInOut> tParkInOuts = tParkingRecordInsideService.getParkInOut(tParkInOut,outTimeBegin,outTimeEnd);
        return tParkInOuts;
    }

    /*@Override
    public String findSumByColumntype(String columntype,String chargeType,String sqlbytime) {
        String money = tParkInOutMapper.sumByColumntype(columntype,chargeType,Common.getSqlWhere(sqlbytime));
        if (money==null){
            money="0.00";
        }
        return money;
    }*/

    @Override
    public Map selectNum(String parkid) {
        //        return customizeMapper.selectParkingRecordNum(parkid);
        String aa = customizeInsideService.selectNum(parkid);
        return JsonUtil.json2Map(aa);
    }

    @Override
    public void exportList(TParkInOut tParkInOut, String title, HttpServletResponse response) {
        List<TParkInOut> tParkInOutList = tParkingRecordInsideService.getTParkInOutUnlimit(tParkInOut);
        List<ExcelParkingRecordParkInOut> excelParkingRecordParkInOutList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelParkingRecordParkInOut防止数组越界
        if (tParkInOutList.size() <= 0) {
            ExcelParkingRecordParkInOut excelParkingRecordParkInOut = new ExcelParkingRecordParkInOut();
            excelParkingRecordParkInOutList.add(excelParkingRecordParkInOut);
        } else {
            for (TParkInOut parkInOut : tParkInOutList) {
                ExcelParkingRecordParkInOut excelParkingRecordParkInOut = new ExcelParkingRecordParkInOut();
                try {
                    excelParkingRecordParkInOut = (ExcelParkingRecordParkInOut) BeanCopyUtil.CopyBeanToBean(parkInOut, excelParkingRecordParkInOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelParkingRecordParkInOutList.add(excelParkingRecordParkInOut);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelParkingRecordParkInOutList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String advanceNotice(Integer parkId, String inCarPlate, String cloudOrderId, Double needPay, Double actualPay, Integer ePayType) {
        String msg = "";
        String outTimeBegin="";
        String outTimeEnd="";
        TParkInOut tParkInOutSel = new TParkInOut();
        tParkInOutSel.setParkId(parkId);
        tParkInOutSel.setInCarPlate(inCarPlate);
        tParkInOutSel.setCloudOrderId(cloudOrderId);
        if (cloudOrderId != null && cloudOrderId != "") {
            List<TParkInOut> tParkInOutList = getParkInOut(tParkInOutSel,outTimeBegin,outTimeEnd);
            if (tParkInOutList.size() > 0) {
                TParkInOut tParkInOut = new TParkInOut();
                tParkInOut.setActualPay(tParkInOutList.get(0).getBeforePay() + actualPay);
                tParkInOut.setEpaytype(ePayType);
                tParkInOut.setId(tParkInOutList.get(0).getId());
//                updateParkInOut(tParkInOut, tParkInOutList.get(0).getParkId());
                updateParkInOut(tParkInOut);
            }
            msg = "预缴通知成功";
        } else {
            msg = "预缴通知失败";
        }
        return msg;
    }

    @Override
    public String updateParkInOut(TParkInOut tParkInOut) {
/*        String msg = "";

        if (tParkInOut.getId() != null) {//编辑
            tParkInOutMapper.updateByPrimaryKeySelective(tParkInOut, tParkInOut.getParkId());
            msg = "编辑成功";
        } else {//新增
            tParkInOutMapper.insertSelective(tParkInOut, tParkInOut.getParkId());
            msg = "成功添加";
        }
        return msg;*/
        return tParkingRecordInsideService.updateParkInOut(tParkInOut);
    }

    @Override
    public List<TParkInOut> getTParkInOutForStatistics(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd) {
        return tParkingRecordInsideService.getTParkInOutForStatistics(tParkInOut, outTimeBegin, outTimeEnd);
    }

    @Override
    public PageBean<TParkInOut> getParkinoutUnlimitByPage(TParkInOut tParkInOut, Integer page, Integer limit) {
        return tParkingRecordInsideService.getTParkinoutUnlimitByPage(tParkInOut,page,limit);
    }

    @Override
    public void exportPayFeeRecord(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd, String title, HttpServletResponse response) {
        List<TParkInOut> tParkInOutList = tParkingRecordInsideService.getParkInOut(tParkInOut,outTimeBegin,outTimeEnd);
        List<ExcelParkingRecordParkInOut> excelParkingRecordParkInOutList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelParkingRecordParkInOut防止数组越界
        if (tParkInOutList.size() <= 0) {
            ExcelParkingRecordParkInOut excelParkingRecordParkInOut = new ExcelParkingRecordParkInOut();
            excelParkingRecordParkInOutList.add(excelParkingRecordParkInOut);
        } else {
            for (TParkInOut parkInOut : tParkInOutList) {
                ExcelParkingRecordParkInOut excelParkingRecordParkInOut = new ExcelParkingRecordParkInOut();
                try {
                    excelParkingRecordParkInOut = (ExcelParkingRecordParkInOut) BeanCopyUtil.CopyBeanToBean(parkInOut, excelParkingRecordParkInOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelParkingRecordParkInOutList.add(excelParkingRecordParkInOut);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelParkingRecordParkInOutList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
