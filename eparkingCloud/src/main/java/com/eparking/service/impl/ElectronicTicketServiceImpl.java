package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TElectronicTicket;
import com.eparking.insideService.TElectronicTicketInsideService;
import com.eparking.service.ElectronicTicketService;
import com.common.util.DateUtil;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectronicTicketServiceImpl implements ElectronicTicketService {


    @Autowired
    private TElectronicTicketInsideService tElectronicTicketInsideService;

    //查询电子券列表
    public List<TElectronicTicket> FindElectronicTicket(TElectronicTicket tElectronicTicket){
//        return tElectronicTicketMapper.selectByExample(getTElectronicTicketCriteria(tElectronicTicket));
        return tElectronicTicketInsideService.getTElectronicTicket(tElectronicTicket);
    }
    //设置查询条件
/*    public TElectronicTicketCriteria  getTElectronicTicketCriteria (TElectronicTicket tElectronicTicket){
        TElectronicTicketCriteria tElectronicTicketCriteria = new TElectronicTicketCriteria();
        TElectronicTicketCriteria.Criteria criteria = tElectronicTicketCriteria.createCriteria();
        if (tElectronicTicket!=null){

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
        return tElectronicTicketCriteria;
    }*/


    /**
     * 获取数据总量
     * @param tElectronicTicket
     * @param
     * @return
     */
/*    private Integer getCount(TElectronicTicket tElectronicTicket){
        Integer total = (int)tElectronicTicketMapper.countByExample(getTElectronicTicketCriteria(tElectronicTicket));
        return total;
    }*/

    /**
     * 电子券管理(分页)
     * @param tElectronicTicket
     * @param page
     * @param limit
     * @return
     */

    @Override
    public PageBean<TElectronicTicket> geteTicketListbyPage(TElectronicTicket tElectronicTicket, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"update_time desc");
        List<TElectronicTicket> tElectronicTicketLists=FindElectronicTicket(tElectronicTicket);
        Integer countNums =getCount(tElectronicTicket);
        PageBean<TElectronicTicket> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tElectronicTicketLists);
        return pageData;*/
        return tElectronicTicketInsideService.getTElectronicTicketbyPage(tElectronicTicket, page, limit);
    }

    /**
     * 新增电子券记录/更新电子券记录
     */
    @Override
    public String updateEticket(TElectronicTicket tElectronicTicket) {
/*        String msg="";
        if(tElectronicTicket.getId()!=null)                 //根据记录ID判断是更新还是插入
        {
            tElectronicTicket.setUpdateTime(DateUtil.getCurDateTime());
            tElectronicTicketMapper.updateByPrimaryKeySelective(tElectronicTicket);
            msg="更新成功";
        }
        else
        {
            tElectronicTicket.setUpdateTime(DateUtil.getCurDateTime());
            tElectronicTicketMapper.insertSelective(tElectronicTicket);
            msg="新增成功";
        }
        return msg;*/
        String msg="";
        tElectronicTicket.setUpdateTime(DateUtil.getCurDateTime());
        //判断同物业下是否有其他一样名称的电子券
        TElectronicTicket tElectronicTicketSel = new TElectronicTicket();
        tElectronicTicketSel.setCompanyId(tElectronicTicket.getCompanyId());
        tElectronicTicketSel.setTicketName(tElectronicTicket.getTicketName());
        List<TElectronicTicket> list = tElectronicTicketInsideService.getTElectronicTicket(tElectronicTicketSel);
        if(tElectronicTicket.getId()==null){
            //新增操作
            if(list.size()>0){
                msg = "已存在相同名称的电子券";
            }else{
                msg = tElectronicTicketInsideService.UpdateTElectronicTicket(tElectronicTicket);
            }
        }else{
            //编辑操作
            if(list.size()>0){
                for(TElectronicTicket tElectronicTicketOne:list){
                    if(tElectronicTicketOne.getId()!=tElectronicTicket.getId()){
                        msg = "已存在相同名称的电子券";
                        break;
                    }
                }
            }
            if(msg.equals("")){
                msg = tElectronicTicketInsideService.UpdateTElectronicTicket(tElectronicTicket);
            }
        }
        return msg;
    }


    /**
     * 删除电子券记录
     */
    @Override
    public String deleteEticket(TElectronicTicket tElectronicTicket) {
/*        String msg="";
        if(tElectronicTicket.getId()!=null){
            tElectronicTicketMapper.deleteByPrimaryKey(tElectronicTicket.getId());
            msg="删除成功";
        }
            return msg;*/
        return tElectronicTicketInsideService.DeleteTElectronicTicket(tElectronicTicket);
    }

    @Override
    public TElectronicTicket getElectronicTicketByPrimaryKey(Integer id) {
//        return tElectronicTicketMapper.selectByPrimaryKey(id);
        return tElectronicTicketInsideService.getElectronicTicketByPrimaryKey(id);
    }

    @Override
    public PageBean<TElectronicTicket> geteTicketListbyPageAndIds(String ids, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"update_time desc");
        List<TElectronicTicket> tElectronicTicketLists=FindElectronicTicketByIds(ids);
        Integer countNums =getCountByIds(ids);
        PageBean<TElectronicTicket> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tElectronicTicketLists);
        return pageData;*/
        return tElectronicTicketInsideService.geteTicketListbyPageAndIds(ids, page, limit);
    }


    @Override
    public List<TElectronicTicket> FindElectronicTicketByIds(String ids){
        return tElectronicTicketInsideService.FindElectronicTicketByIds(ids);
    }

/*    private  List<TElectronicTicket> FindElectronicTicketByIds(List<Integer> ids){
        if(ids.size()==0){
            return  null;
        }else{
        return tElectronicTicketMapper.selectByExample(getElectronicTicketCriteriaByIds(ids));}
    }*/
/*
    private Integer getCountByIds(List<Integer> ids){
        if(ids.size()==0){
            return 0;
        }else{
        return (int)tElectronicTicketMapper.countByExample(getElectronicTicketCriteriaByIds(ids));}
    }*/

/*    private TElectronicTicketCriteria getElectronicTicketCriteriaByIds(List<Integer> ids){
        TElectronicTicketCriteria tElectronicTicketCriteria = new TElectronicTicketCriteria();
        TElectronicTicketCriteria.Criteria criteria = tElectronicTicketCriteria.createCriteria();
        criteria.andIdIn(ids);
        return tElectronicTicketCriteria;
    }*/


}
