package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarWalletReport;
import com.common.entity.eparkingCloud.TCarWalletReportCriteria;
import com.eparkingdb.dao.TCarWalletReportMapper;
import com.eparkingdb.service.TCarWalletReportService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TCarWalletReportService接口实现类
* @author 李书瀚
* @date 2020/10/26 23:19
*/
@Service
public class TCarWalletReportServiceImpl implements TCarWalletReportService {

    private  static final Logger logger= LoggerFactory.getLogger( TCarWalletReportServiceImpl.class);
    @Autowired
    private TCarWalletReportMapper tCarWalletReportMapper;

    /**
    * 设置查询条件
    * @param tCarWalletReport
    * @return
    */
    private  TCarWalletReportCriteria setCriteria(TCarWalletReport tCarWalletReport,String beginTime,String endTime){
                TCarWalletReportCriteria tCarWalletReportCriteria= new TCarWalletReportCriteria();
                tCarWalletReportCriteria.setOrderByClause("statistics_time DESC");
                if(tCarWalletReport!=null){
                TCarWalletReportCriteria.Criteria criteria=tCarWalletReportCriteria.createCriteria();
                if(tCarWalletReport.getId()!=null){
                criteria.andIdEqualTo(tCarWalletReport.getId());
                }
                if(tCarWalletReport.getParkId()!=null){
                    criteria.andParkIdEqualTo(tCarWalletReport.getParkId());
                }
                if(tCarWalletReport.getStatisticsTime()!=null){
                    criteria.andStatisticsTimeEqualTo(tCarWalletReport.getStatisticsTime());
                }
                if(tCarWalletReport.getCompanyId()!=null){
                     criteria.andCompanyIdEqualTo(tCarWalletReport.getCompanyId());
                }
                if(beginTime!=null&&beginTime!=""){
                    criteria.andStatisticsTimeGreaterThanOrEqualTo(beginTime);
                }
                if(endTime!=null&&endTime!=""){
                    criteria.andStatisticsTimeLessThanOrEqualTo(endTime);
                }

            }
        return  tCarWalletReportCriteria;
    }

    /**
    * 获取数据总量
    * @param tCarWalletReport
    * @return
    */
    private Integer getCount(TCarWalletReport tCarWalletReport,String beginTime,String endTime){
    Integer total =(int)tCarWalletReportMapper.countByExample(setCriteria(tCarWalletReport,beginTime,endTime));
    return total;
    }

    /**
    *查询tCarWalletReport信息(分页)
    * @param tCarWalletReport
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TCarWalletReport> getTCarWalletReportbyPage(TCarWalletReport tCarWalletReport,String beginTime,String endTime, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"statistics_time desc");
        List<TCarWalletReport> tCarWalletReports=getTCarWalletReport(tCarWalletReport,beginTime,endTime);
            Integer countNums =getCount(tCarWalletReport,beginTime,endTime);
            PageBean<TCarWalletReport> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCarWalletReports);
            return pageData;
        }

    /**
    * 查询tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    public List<TCarWalletReport> getTCarWalletReport(TCarWalletReport tCarWalletReport,String beginTime,String endTime){
    List<TCarWalletReport>  tCarWalletReports=tCarWalletReportMapper.selectByExample(setCriteria(tCarWalletReport,beginTime,endTime));
    return tCarWalletReports;
    }

    /**
    * 更新tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    public String UpdateTCarWalletReport(TCarWalletReport tCarWalletReport)
    {
            String msg="";
            try{
                if(tCarWalletReport.getId()!=null){
                tCarWalletReportMapper.updateByPrimaryKeySelective(tCarWalletReport);
                    msg="更新TCarWalletReport成功";
                }
                else
                {
                tCarWalletReportMapper.insertSelective(tCarWalletReport);
                    msg="新建TCarWalletReport成功";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return msg;
    }

    /**
    * 删除tCarWalletReport信息
    * @param tCarWalletReport
    * @return
    */
    public String DeleteTCarWalletReport(TCarWalletReport tCarWalletReport){
            String msg="";
            if(tCarWalletReport.getId()!=null){
            tCarWalletReportMapper.deleteByPrimaryKey(tCarWalletReport.getId());

            }else{
                tCarWalletReportMapper.deleteByExample(setCriteria(tCarWalletReport,"",""));
            }
            msg="删除TCarWalletReport成功";
            return msg;
    }

    /**
    * 根据ID查询tCarWalletReport信息
    * @param id
    * @return
    */
    public TCarWalletReport getTCarWalletReportByID(Integer id) {
        return  tCarWalletReportMapper.selectByPrimaryKey(id);
    }
}
