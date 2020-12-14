package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyNotice;
import com.common.entity.eparkingCloud.TCompanyNoticeCriteria;
import com.eparkingdb.dao.TCompanyNoticeMapper;
import com.eparkingdb.service.TCompanyNoticeService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TCompanyNoticeService接口实现类
* @author 谢轩然
* @date 2020/04/09 14:46
*/
@Service
public class TCompanyNoticeServiceImpl  implements TCompanyNoticeService {

    private  static final Logger logger= LoggerFactory.getLogger( TCompanyNoticeServiceImpl.class);
    @Autowired
    private TCompanyNoticeMapper tCompanyNoticeMapper;

    /**
    * 设置查询条件
    * @param tCompanyNotice
    * @return
    */
    private  TCompanyNoticeCriteria setCriteria(TCompanyNotice tCompanyNotice){
        TCompanyNoticeCriteria tCompanyNoticeCriteria= new TCompanyNoticeCriteria();
        if(tCompanyNotice!=null){
        TCompanyNoticeCriteria.Criteria criteria=tCompanyNoticeCriteria.createCriteria();
        if(tCompanyNotice.getId()!=null){
        criteria.andIdEqualTo(tCompanyNotice.getId());
        }

            if(tCompanyNotice.getParkId()!=null) {
                criteria.andParkIdEqualTo(tCompanyNotice.getParkId());
            }
            if (tCompanyNotice.getMemberId()!=null){
                criteria.andMemberIdEqualTo(tCompanyNotice.getMemberId());
            }
            if (tCompanyNotice.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tCompanyNotice.getCompanyId());
            }
            if(tCompanyNotice.getTitle()!=null && tCompanyNotice.getTitle()!=""){
                criteria.andTitleLike("%"+tCompanyNotice.getTitle()+"%");
            }
            if (tCompanyNotice.getContent()!=null && tCompanyNotice.getContent()!=""){
                criteria.andContentLike("%"+tCompanyNotice.getContent()+"%");
            }
            if (tCompanyNotice.getCreateTime()!=null && tCompanyNotice.getCreateTime()!="") {
                criteria.andCreateTimeEqualTo(tCompanyNotice.getCreateTime());
            }
            if (tCompanyNotice.getUserId()!=null){
                criteria.andUserIdEqualTo(tCompanyNotice.getUserId());
            }
            if (tCompanyNotice.getUpdateTime()!=null && tCompanyNotice.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tCompanyNotice.getUpdateTime());
            }
        }
        return  tCompanyNoticeCriteria;
    }

    /**
    * 获取数据总量
    * @param tCompanyNotice
    * @return
    */
    private Integer getCount(TCompanyNotice tCompanyNotice){
    Integer total =(int)tCompanyNoticeMapper.countByExample(setCriteria(tCompanyNotice));
    return total;
    }

    /**
    *查询tCompanyNotice(分页)
    * @param tCompanyNotice
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TCompanyNotice> getTCompanyNoticebyPage(TCompanyNotice tCompanyNotice, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TCompanyNotice> tCompanyNotices=getTCompanyNotice(tCompanyNotice);
            Integer countNums =getCount(tCompanyNotice);
            PageBean<TCompanyNotice> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCompanyNotices);
            return pageData;
        }

    /**
    * 查询tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    public List<TCompanyNotice> getTCompanyNotice(TCompanyNotice tCompanyNotice){
    List<TCompanyNotice>  tCompanyNotices=tCompanyNoticeMapper.selectByExample(setCriteria(tCompanyNotice));
    return tCompanyNotices;
    }

    /**
    * 更新tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    public String UpdateTCompanyNotice(TCompanyNotice tCompanyNotice)
    {
            String msg="";
            try{
            if(tCompanyNotice.getId()!=null){
            tCompanyNoticeMapper.updateByPrimaryKeySelective(tCompanyNotice);
                msg="编辑成功";
            }
            else
            {
            tCompanyNoticeMapper.insertSelective(tCompanyNotice);
                msg="成功添加";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tCompanyNotice
    * @param tCompanyNotice
    * @return
    */
    public String DeleteTCompanyNotice(TCompanyNotice tCompanyNotice){
            String msg="";
            if(tCompanyNotice.getId()!=null){
            tCompanyNoticeMapper.deleteByPrimaryKey(tCompanyNotice.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tCompanyNotice
    * @param id
    * @return
    */
    public TCompanyNotice getTCompanyNoticeByID(Integer id) {
        return  tCompanyNoticeMapper.selectByPrimaryKey(id);
    }
}
