package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TJournal;
import com.common.entity.eparkingCloud.TJournalCriteria;
import com.eparkingdb.dao.TJournalMapper;
import com.eparkingdb.service.TJournalService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TJournalService接口实现类
* @author 谢轩然
* @date 2020/04/09 15:17
*/
@Service
public class TJournalServiceImpl  implements TJournalService {

    private  static final Logger logger= LoggerFactory.getLogger( TJournalServiceImpl.class);
    @Autowired
    private TJournalMapper tJournalMapper;

    /**
    * 设置查询条件
    * @param tJournal
    * @return
    */
    private  TJournalCriteria setCriteria(TJournal tJournal, String beginTime, String endTime){
        TJournalCriteria tJournalCriteria= new TJournalCriteria();
        if(tJournal!=null){
        TJournalCriteria.Criteria criteria=tJournalCriteria.createCriteria();
        if(tJournal.getId()!=null){
        criteria.andIdEqualTo(tJournal.getId());
        }
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
            if(tJournal.getOperationContent()!=null && !tJournal.getOperationContent().equals("")){
                criteria.andOperationContentLike("%"+tJournal.getOperationContent()+"%");
            }
        }
        return  tJournalCriteria;
    }

    /**
    * 获取数据总量
    * @param tJournal
    * @return
    */
    private Integer getCount(TJournal tJournal, String beginTime, String endTime){
    Integer total =(int)tJournalMapper.countByExample(setCriteria(tJournal,beginTime,endTime));
    return total;
    }

    /**
    *查询tJournal(分页)
    * @param tJournal
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TJournal> getTJournalbyPage(TJournal tJournal, String beginTime, String endTime, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"id desc");
        List<TJournal> tJournals=getTJournal(tJournal,beginTime,endTime);
            Integer countNums =getCount(tJournal,beginTime,endTime);
            PageBean<TJournal> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tJournals);
            return pageData;
        }

    /**
    * 查询tJournal
    * @param tJournal
    * @return
    */
    @Override
    public List<TJournal> getTJournal(TJournal tJournal, String beginTime, String endTime) {
    List<TJournal>  tJournals=tJournalMapper.selectByExample(setCriteria(tJournal,beginTime,endTime));
    return tJournals;
    }



    /**
    * 更新tJournal
    * @param tJournal
    * @return
    */
    public String UpdateTJournal(TJournal tJournal)
    {
            String msg="";
            try{
            if(tJournal.getId()!=null){
            tJournalMapper.updateByPrimaryKeySelective(tJournal);
                msg="更新成功";
            }
            else
            {
            tJournalMapper.insertSelective(tJournal);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tJournal
    * @param tJournal
    * @return
    */
    public String DeleteTJournal(TJournal tJournal){
            String msg="";
            if(tJournal.getId()!=null){
            tJournalMapper.deleteByPrimaryKey(tJournal.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tJournal
    * @param id
    * @return
    */
    public TJournal getTJournalByID(Integer id) {
        return  tJournalMapper.selectByPrimaryKey(id);
    }
}
