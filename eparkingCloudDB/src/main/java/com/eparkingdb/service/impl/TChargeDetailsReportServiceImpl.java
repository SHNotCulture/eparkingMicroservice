package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.common.entity.eparkingCloud.TChargeDetailsReportCriteria;
import com.eparkingdb.dao.TChargeDetailsReportMapper;
import com.eparkingdb.service.CustomizeService;
import com.eparkingdb.service.TChargeDetailsReportService;
import com.common.util.DateUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description: TChargeDetailsReportService接口实现类
* @author 谢轩然
* @date 2020/11/03 16:47
*/
@Service
public class TChargeDetailsReportServiceImpl  implements TChargeDetailsReportService {

    private  static final Logger logger= LoggerFactory.getLogger( TChargeDetailsReportServiceImpl.class);
    @Autowired
    private TChargeDetailsReportMapper tChargeDetailsReportMapper;
    @Autowired
    private CustomizeService customizeService;
    /**
    * 设置查询条件
    * @param tChargeDetailsReport
    * @return
    */
    private  TChargeDetailsReportCriteria setCriteria(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate){
        TChargeDetailsReportCriteria tChargeDetailsReportCriteria= new TChargeDetailsReportCriteria();
        if(tChargeDetailsReport!=null){
        TChargeDetailsReportCriteria.Criteria criteria=tChargeDetailsReportCriteria.createCriteria();
        if(tChargeDetailsReport.getId()!=null){
        criteria.andIdEqualTo(tChargeDetailsReport.getId());
        }
            if(tChargeDetailsReport.getParkId()!=null){
                criteria.andParkIdEqualTo(tChargeDetailsReport.getParkId());
            }
            if(tChargeDetailsReport.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tChargeDetailsReport.getCompanyId());
            }
            if(tChargeDetailsReport.getOutPortId()!=null){
                criteria.andOutPortIdEqualTo(tChargeDetailsReport.getOutPortId());
            }
            if(tChargeDetailsReport.getDutyPerson()!=null && !tChargeDetailsReport.getDutyPerson().equals("")){
                criteria.andDutyPersonEqualTo(tChargeDetailsReport.getDutyPerson());
            }
            if(beginDate!=null && !beginDate.equals("")){
                criteria.andChargeDateGreaterThanOrEqualTo(beginDate);
            }
            if(endDate!=null && !endDate.equals("")){
                criteria.andChargeDateLessThanOrEqualTo(endDate);
            }
        }
        return  tChargeDetailsReportCriteria;
    }

    /**
    * 获取数据总量
    * @param tChargeDetailsReport
    * @return
    */
    private Integer getCount(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate){
    Integer total =(int)tChargeDetailsReportMapper.countByExample(setCriteria(tChargeDetailsReport,beginDate,endDate));
    return total;
    }

    /**
    *查询tChargeDetailsReport(分页)
    * @param tChargeDetailsReport
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TChargeDetailsReport> getTChargeDetailsReportbyPage(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"charge_date desc");
        List<TChargeDetailsReport> tChargeDetailsReports=getTChargeDetailsReport(tChargeDetailsReport,beginDate,endDate);
            Integer countNums =getCount(tChargeDetailsReport,beginDate,endDate);
            PageBean<TChargeDetailsReport> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tChargeDetailsReports);
            return pageData;
        }

    /**
    * 查询tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    public List<TChargeDetailsReport> getTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport,String beginDate ,String endDate){
    List<TChargeDetailsReport>  tChargeDetailsReports=tChargeDetailsReportMapper.selectByExample(setCriteria(tChargeDetailsReport,beginDate,endDate));
    return tChargeDetailsReports;
    }

    /**
    * 更新tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    public String UpdateTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport)
    {
            String msg="";
            try{
            if(tChargeDetailsReport.getId()!=null){
            tChargeDetailsReportMapper.updateByPrimaryKeySelective(tChargeDetailsReport);
                msg="更新成功";
            }
            else
            {
            tChargeDetailsReportMapper.insertSelective(tChargeDetailsReport);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tChargeDetailsReport
    * @param tChargeDetailsReport
    * @return
    */
    public String DeleteTChargeDetailsReport(TChargeDetailsReport tChargeDetailsReport){
            String msg="";
            if(tChargeDetailsReport.getId()!=null){
            tChargeDetailsReportMapper.deleteByPrimaryKey(tChargeDetailsReport.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tChargeDetailsReport
    * @param id
    * @return
    */
    public TChargeDetailsReport getTChargeDetailsReportByID(Integer id) {
        return  tChargeDetailsReportMapper.selectByPrimaryKey(id);
    }

    @Override
    public String chargeReportStatistics(TChargeDetailsReport tChargeDetailsReport,String beginDate,String endDate) {
        String msg="统计成功";
        List<Map> mapList= customizeService.chargeReportStatistics(tChargeDetailsReport.getParkId(),beginDate,endDate);
//        logger.info("mapList: "+mapList.size());
        TChargeDetailsReport tChargeDetailsReportSel = new TChargeDetailsReport();
        tChargeDetailsReportSel.setParkId(tChargeDetailsReport.getParkId());
        //tChargeDetailsReportSel.setCompanyId(tChargeDetailsReport.getCompanyId());
        List<TChargeDetailsReport> chargeDetailsReportDelList = getTChargeDetailsReport(tChargeDetailsReportSel,beginDate.substring(0,10),endDate.substring(0,10));
//        logger.info("chargeDetailsReportDelList: "+chargeDetailsReportDelList.size());
        if(chargeDetailsReportDelList.size()>0){
            for(TChargeDetailsReport tChargeDetailsReportDel:chargeDetailsReportDelList){
                DeleteTChargeDetailsReport(tChargeDetailsReportDel);
            }
        }
        if(mapList.size()>0){
            for(Map map:mapList){
                TChargeDetailsReport tChargeDetailsReportUpdate = new TChargeDetailsReport();
                tChargeDetailsReportUpdate.setParkId(Integer.valueOf(map.get("park_id").toString()));
                tChargeDetailsReportUpdate.setCompanyId(Integer.valueOf(map.get("company_id").toString()));
                tChargeDetailsReportUpdate.setCarNatureDesc(map.get("car_nature_desc").toString());
                tChargeDetailsReportUpdate.setChargeAmount(Integer.valueOf(map.get("charge_amount").toString()));
                tChargeDetailsReportUpdate.setCouponTotal(Double.valueOf(map.get("coupon_total").toString()));
                tChargeDetailsReportUpdate.setQpasspayTotal(Double.valueOf(map.get("qpasspay_total").toString()));
                tChargeDetailsReportUpdate.setActualpayTotal(Double.valueOf(map.get("actualpay_total").toString()));
                tChargeDetailsReportUpdate.setNeedpayTotal(Double.valueOf(map.get("needpay_total").toString()));
                tChargeDetailsReportUpdate.setBehalfpayTotal(Double.valueOf(map.get("behalfpay_total").toString()));
                tChargeDetailsReportUpdate.setPrepayTotal(Double.valueOf(map.get("prepay_total").toString()));
                if(map.get("walletpay_total")==null){
                    tChargeDetailsReportUpdate.setWalletpayTotal(0.00);
                }else{
                    tChargeDetailsReportUpdate.setWalletpayTotal(Double.valueOf(map.get("walletpay_total").toString()));
                }
                if(map.get("inaccesspaid_total")==null){
                    tChargeDetailsReportUpdate.setInaccesspaidTotal(0.00);
                }else{
                    tChargeDetailsReportUpdate.setInaccesspaidTotal(Double.valueOf(map.get("inaccesspaid_total").toString()));
                }
                tChargeDetailsReportUpdate.setChargeDate(map.get("charge_date").toString());
                tChargeDetailsReportUpdate.setCreateTime(DateUtil.getCurDateTime());
                tChargeDetailsReportUpdate.setCompanyId(tChargeDetailsReport.getCompanyId());
                UpdateTChargeDetailsReport(tChargeDetailsReportUpdate);
//                logger.info("update "+tChargeDetailsReportUpdate.getId());
            }
        }
        return msg;
    }

    @Override
    public PageBean<TChargeDetailsReport> getDutyStatisticsbyPage(TChargeDetailsReport tChargeDetailsReport, String beginTime, String endTime, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"charge_date,duty_person desc");
        List<TChargeDetailsReport> tChargeDetailsReports=new ArrayList<>();
        List<Map> mapList= customizeService.getDutyStatistics(tChargeDetailsReport.getParkId(),tChargeDetailsReport.getDutyPerson(),beginTime,endTime);
        if(mapList.size()>0){
            for(Map map:mapList){
                TChargeDetailsReport tChargeDetailsReportUpdate = new TChargeDetailsReport();
                tChargeDetailsReportUpdate.setParkId(Integer.valueOf(map.get("park_id").toString()));
                tChargeDetailsReportUpdate.setCompanyId(Integer.valueOf(map.get("company_id").toString()));
                tChargeDetailsReportUpdate.setDutyPerson(map.get("duty_person").toString());
                tChargeDetailsReportUpdate.setChargeAmount(Integer.valueOf(map.get("charge_amount").toString()));
                tChargeDetailsReportUpdate.setCouponTotal(Double.valueOf(map.get("coupon_total").toString()));
                tChargeDetailsReportUpdate.setQpasspayTotal(Double.valueOf(map.get("qpasspay_total").toString()));
                tChargeDetailsReportUpdate.setActualpayTotal(Double.valueOf(map.get("actualpay_total").toString()));
                tChargeDetailsReportUpdate.setNeedpayTotal(Double.valueOf(map.get("needpay_total").toString()));
                tChargeDetailsReportUpdate.setBehalfpayTotal(Double.valueOf(map.get("behalfpay_total").toString()));
                tChargeDetailsReportUpdate.setPrepayTotal(Double.valueOf(map.get("prepay_total").toString()));
                if(map.get("walletpay_total")==null){
                    tChargeDetailsReportUpdate.setWalletpayTotal(0.00);
                }else{
                    tChargeDetailsReportUpdate.setWalletpayTotal(Double.valueOf(map.get("walletpay_total").toString()));
                }
                if(map.get("inaccesspaid_total")==null){
                    tChargeDetailsReportUpdate.setInaccesspaidTotal(0.00);
                }else{
                    tChargeDetailsReportUpdate.setInaccesspaidTotal(Double.valueOf(map.get("inaccesspaid_total").toString()));
                }
                tChargeDetailsReportUpdate.setChargeDate(map.get("charge_date").toString());
                tChargeDetailsReportUpdate.setCreateTime(DateUtil.getCurDateTime());
                tChargeDetailsReports.add(tChargeDetailsReportUpdate);
            }
        }
//        logger.info(String.valueOf(tChargeDetailsReports.size()));
        Integer countNums =customizeService.getDutyStatisticsNum(tChargeDetailsReport.getParkId(),tChargeDetailsReport.getDutyPerson(),beginTime,endTime);
        PageBean<TChargeDetailsReport> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tChargeDetailsReports);
        return pageData;
    }
}
