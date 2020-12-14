package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TElectronicTicket;
import com.common.entity.eparkingCloud.TElectronicTicketCriteria;
import com.eparkingdb.dao.TElectronicTicketMapper;
import com.eparkingdb.service.TElectronicTicketService;
import com.common.util.DateUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TElectronicTicketService接口实现类
* @author 谢轩然
* @date 2020/04/09 15:01
*/
@Service
public class TElectronicTicketServiceImpl  implements TElectronicTicketService {

    private  static final Logger logger= LoggerFactory.getLogger(TElectronicTicketServiceImpl.class);
    @Autowired
    private TElectronicTicketMapper tElectronicTicketMapper;

    /**
    * 设置查询条件
    * @param tElectronicTicket
    * @return
    */
    private  TElectronicTicketCriteria setCriteria(TElectronicTicket tElectronicTicket){
        TElectronicTicketCriteria tElectronicTicketCriteria= new TElectronicTicketCriteria();
        if(tElectronicTicket!=null){
        TElectronicTicketCriteria.Criteria criteria=tElectronicTicketCriteria.createCriteria();
        if(tElectronicTicket.getId()!=null){
        criteria.andIdEqualTo(tElectronicTicket.getId());
        }
            if (tElectronicTicket.getTicketName()!=null && tElectronicTicket.getTicketName()!=""){        //电子券名称
                criteria.andTicketNameLike("%" + tElectronicTicket.getTicketName() + "%");
            }
            if(tElectronicTicket.getCompanyId()!=null){                                                    //companyid
                criteria.andCompanyIdEqualTo(tElectronicTicket.getCompanyId());
            }
            if(tElectronicTicket.getTicketPay()!=null ){                                                //电子券对应现金
                criteria.andTicketPayEqualTo(tElectronicTicket.getTicketPay());
            }
            if(tElectronicTicket.getTicketType()!=null){                                                //电子券类型
                criteria.andTicketTypeEqualTo(tElectronicTicket.getTicketType());
            }
            if(tElectronicTicket.getTicketValue()!=null){                                                  //电子券价值
                criteria.andTicketValueEqualTo(tElectronicTicket.getTicketValue());
            }
        }
        return  tElectronicTicketCriteria;
    }

    /**
    * 获取数据总量
    * @param tElectronicTicket
    * @return
    */
    private Integer getCount(TElectronicTicket tElectronicTicket){
    Integer total =(int)tElectronicTicketMapper.countByExample(setCriteria(tElectronicTicket));
    return total;
    }

    /**
    *查询tElectronicTicket(分页)
    * @param tElectronicTicket
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TElectronicTicket> getTElectronicTicketbyPage(TElectronicTicket tElectronicTicket, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"update_time desc");
        List<TElectronicTicket> tElectronicTickets=getTElectronicTicket(tElectronicTicket);
            Integer countNums =getCount(tElectronicTicket);
            PageBean<TElectronicTicket> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tElectronicTickets);
            return pageData;
        }

    /**
    * 查询tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    public List<TElectronicTicket> getTElectronicTicket(TElectronicTicket tElectronicTicket){
    List<TElectronicTicket>  tElectronicTickets=tElectronicTicketMapper.selectByExample(setCriteria(tElectronicTicket));
    return tElectronicTickets;
    }

    /**
    * 更新tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    public String UpdateTElectronicTicket(TElectronicTicket tElectronicTicket)
    {
            String msg="";
            try{
            if(tElectronicTicket.getId()!=null){
            tElectronicTicket.setUpdateTime(DateUtil.getCurDateTime());
            tElectronicTicketMapper.updateByPrimaryKeySelective(tElectronicTicket);
                msg="更新成功";
            }
            else
            {
            tElectronicTicket.setUpdateTime(DateUtil.getCurDateTime());
            tElectronicTicketMapper.insertSelective(tElectronicTicket);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            logger.info("msg: ",msg);
            return msg;
    }

    /**
    * 删除tElectronicTicket
    * @param tElectronicTicket
    * @return
    */
    public String DeleteTElectronicTicket(TElectronicTicket tElectronicTicket){
            String msg="";
            if(tElectronicTicket.getId()!=null){
            tElectronicTicketMapper.deleteByPrimaryKey(tElectronicTicket.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tElectronicTicket
    * @param id
    * @return
    */
    public TElectronicTicket getTElectronicTicketByID(Integer id) {
        return  tElectronicTicketMapper.selectByPrimaryKey(id);
    }


    @Override
    public PageBean<TElectronicTicket> geteTicketListbyPageAndIds(List<Integer> ids, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"update_time desc");
        List<TElectronicTicket> tElectronicTicketLists=FindElectronicTicketByIds(ids);
        Integer countNums =getCountByIds(ids);
        PageBean<TElectronicTicket> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tElectronicTicketLists);
        return pageData;
    }

    public   List<TElectronicTicket> FindElectronicTicketByIds(List<Integer> ids){
        if(ids.size()==0){
            return  null;
        }else{
            return tElectronicTicketMapper.selectByExample(getElectronicTicketCriteriaByIds(ids));}
    }

    private Integer getCountByIds(List<Integer> ids){
        if(ids.size()==0){
            return 0;
        }else{
            return (int)tElectronicTicketMapper.countByExample(getElectronicTicketCriteriaByIds(ids));}
    }

    private TElectronicTicketCriteria getElectronicTicketCriteriaByIds(List<Integer> ids){
        TElectronicTicketCriteria tElectronicTicketCriteria = new TElectronicTicketCriteria();
        TElectronicTicketCriteria.Criteria criteria = tElectronicTicketCriteria.createCriteria();
        criteria.andIdIn(ids);
        return tElectronicTicketCriteria;
    }

    @Override
    public TElectronicTicket getElectronicTicketByPrimaryKey(Integer id) {
        return tElectronicTicketMapper.selectByPrimaryKey(id);
    }
}
