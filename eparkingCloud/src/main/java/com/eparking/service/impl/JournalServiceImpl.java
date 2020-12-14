package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TJournal;
import com.eparking.insideService.TJournalInsideService;
import com.eparking.service.JournalService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:332018-10-19
 * @Modified By:
 */
@Service
public class JournalServiceImpl implements JournalService {
    private  static final Logger logger= LoggerFactory.getLogger(JournalServiceImpl.class);
    @Autowired
    private TJournalInsideService tJournalInsideService;

    /**
     * 设置查询条件
     * @param tJournal
     * @param beginTime
     * @param endTime
     * @return
     */
/*    private TJournalCriteria setCriteria(TJournal tJournal,String beginTime,String endTime){
        TJournalCriteria tJournalCriteria= new TJournalCriteria();
        if(tJournal!=null){
            TJournalCriteria.Criteria criteria=tJournalCriteria.createCriteria();
            if(tJournal.getParkid()!=null){
                criteria.andParkidEqualTo(tJournal.getParkid());
            }
            if(tJournal.getCompanyUserId()!=null){
                criteria.andCompanyUserIdEqualTo(tJournal.getCompanyUserId());
            }
            if(tJournal.getOperatingType()!=null){
                criteria.andOperatingTypeEqualTo(tJournal.getOperatingType());
            }
            if(beginTime!=null&&endTime!=null&&beginTime!=""&&endTime!="")
            {
                criteria.andDateBetween(beginTime,endTime);
            }

        }
        return  tJournalCriteria;
    }*/

    /**
     * 查询操作日志
     * @return
     */
    public List<TJournal> getTJournal(TJournal tJournal,String beginTime,String endTime){
/*        logger.info("tJournal:"+tJournal);
        //查询操作日志
        List<TJournal> journalList=tJournalMapper.selectByExample(setCriteria( tJournal, beginTime, endTime));
        return journalList;*/
        return tJournalInsideService.getTJournal(tJournal, beginTime, endTime);
    }
    /**
     * 查询操作日志(分页)
     * @param tJournal
     * @param beginTime
     * @param endTime
     * @param page
     * @param limit
     * @return
     */
    public  PageBean<TJournal> getTJournalByPage(TJournal tJournal,String beginTime,String endTime,Integer page, Integer limit){
//        logger.info("tJournal:"+tJournal);
/*        PageHelper.startPage(page, limit,"id desc");
        //查询操作日志
        List<TJournal> journalList=getTJournal( tJournal, beginTime, endTime);
        Integer countNums =getCount(tJournal,beginTime,endTime);
        PageBean<TJournal> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(journalList);
        return pageData;*/
        return tJournalInsideService.getTJournalbyPage(tJournal, beginTime, endTime, page, limit);
    }

    /**
     * 获取数据总量
     * @param tJournal
     * @param beginTime
     * @param endTime
     * @return
     */
/*    private Integer getCount(TJournal tJournal,String beginTime,String endTime){
       Integer total =(int)tJournalMapper.countByExample(setCriteria(tJournal, beginTime, endTime));
        return total;
    }*/
    /**
     * 更新操作日志
     * @param tJournal
     * @return
     */
    public String UpdateTJournal(TJournal tJournal)
    {
//        logger.info(tJournal.toString());
/*        if(tJournal.getId()!=null){
            tJournalMapper.updateByPrimaryKeySelective(tJournal);
        }
        else
        {
            tJournalMapper.insertSelective(tJournal);
        }
        return "更新成功";*/
        return tJournalInsideService.UpdateTJournal(tJournal);
    }
    public String InsertJournal(){
        return "添加成功";
    }
}
