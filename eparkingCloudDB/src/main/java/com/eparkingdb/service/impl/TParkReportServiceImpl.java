package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReport;
import com.common.entity.eparkingCloud.TParkReportCriteria;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.dao.TParkReportMapper;
import com.eparkingdb.service.TParkReportService;
import com.common.util.SessionUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 22:122018-11-1
 * @Modified By:
 */
@Service
public class TParkReportServiceImpl implements TParkReportService {
    private  static final Logger logger= LoggerFactory.getLogger(TParkReportServiceImpl.class);
    @Autowired
    private TParkReportMapper tParkReportMapper;
    @Autowired
    private CustomizeMapper customizeMapper;

    /**
     *设置查询条件
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @return
     */
    private TParkReportCriteria setCriteria(TParkReport tParkReport,String beginTime,String endTime){
        //查询日报表信息
        TParkReportCriteria tParkReportCriteria= new TParkReportCriteria();
        if(tParkReport!=null){
            TParkReportCriteria.Criteria criteria=tParkReportCriteria.createCriteria();
            if(tParkReport.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tParkReport.getParkId());
            }
            if(tParkReport.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tParkReport.getCompanyId());
            }
            if(beginTime!=null&&endTime!=null&&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }

        }
        return  tParkReportCriteria;
    }
    /**
     * 获取数据总量
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @return
     */
    private Integer getCount(TParkReport tParkReport,String beginTime,String endTime){
        Integer total =(int)tParkReportMapper.countByExample(setCriteria(tParkReport, beginTime, endTime));
        return total;
    }
    /**
     * 查询日报表信息(分页)
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkReport> getTParkReportbyPage(TParkReport tParkReport,String beginTime,String endTime, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"create_date desc");
        List<TParkReport> tParkReportList=getTParkReport(tParkReport,beginTime, endTime);
        Integer countNums =getCount(tParkReport,beginTime, endTime);
        PageBean<TParkReport> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkReportList);
        return pageData;
    }

    /**
     * 查询日报表信息
     * @return
     */
    public List<TParkReport> getTParkReport(TParkReport tParkReport,String beginTime,String endTime){
        List<TParkReport> tParkReportList=tParkReportMapper.selectByExample(setCriteria(tParkReport,beginTime, endTime));
        return tParkReportList;
    }


    @Override
    public Map getTParkReportSum(String beginTime, String endTime,String tabelName) {
        return customizeMapper.selectReportSum(SessionUtil.getParkId(),beginTime,endTime,tabelName);
    }

    /**
     * 每日从日报表取数据至电子对账表
     * @return
     */
    public String paymentForDay()
    {
        String result="";
        Integer successNum=customizeMapper.paymentForDay();
        result=String.format("成功从日报表取出%s条数据",successNum);
//        logger.info(result);
        return result;
    }

    @Override
    public String updateTParkReport(TParkReport tParkReport) {
        String msg = "";
        try {
            if (tParkReport.getId() != null) {
                tParkReportMapper.updateByPrimaryKeySelective(tParkReport);
                msg = "更新成功";
            } else {
                tParkReportMapper.insertSelective(tParkReport);
                msg = "新建成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    @Override
    public String deleteTParkReport(TParkReport tParkReport) {
        String msg = "";
        if (tParkReport.getId() != null) {
            tParkReportMapper.deleteByPrimaryKey(tParkReport.getId());
            msg = "删除成功";
        }
        return msg;
    }
}
