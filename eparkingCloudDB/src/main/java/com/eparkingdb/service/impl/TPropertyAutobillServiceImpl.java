package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.common.entity.eparkingCloud.TPropertyAutobillCriteria;
import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.eparkingdb.dao.TPropertyAutobillMapper;
import com.eparkingdb.service.TPropertyAutobillService;
import com.eparkingdb.service.TPropertyBillItemsService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TPropertyAutobillService接口实现类
* @author 谢轩然
* @date 2020/04/28 16:27
*/
@Service
public class TPropertyAutobillServiceImpl  implements TPropertyAutobillService {

    private  static final Logger logger= LoggerFactory.getLogger( TPropertyAutobillServiceImpl.class);
    @Autowired
    private TPropertyAutobillMapper tPropertyAutobillMapper;
    @Autowired
    private TPropertyBillItemsService tPropertyBillItemsService;

    /**
    * 设置查询条件
    * @param tPropertyAutobill
    * @return
    */
    private  TPropertyAutobillCriteria setCriteria(TPropertyAutobill tPropertyAutobill){
        TPropertyAutobillCriteria tPropertyAutobillCriteria = new TPropertyAutobillCriteria();
        if(tPropertyAutobill!=null){
            TPropertyAutobillCriteria.Criteria criteria = tPropertyAutobillCriteria.createCriteria();
            if(tPropertyAutobill.getParkId()!=null) {
                criteria.andParkIdEqualTo(tPropertyAutobill.getParkId());
            }
            if (tPropertyAutobill.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tPropertyAutobill.getCompanyId());
            }
            if (tPropertyAutobill.getBillItemId()!=null){
                criteria.aneparkingdbillItemIdEqualTo(tPropertyAutobill.getBillItemId());
            }
            if(tPropertyAutobill.getBillName()!=null && tPropertyAutobill.getBillName()!=""){
                criteria.aneparkingdbillNameLike("%"+tPropertyAutobill.getBillName()+"%");
            }
            if (tPropertyAutobill.getCheckoutDate()!=null && tPropertyAutobill.getCheckoutDate()!=""){
                criteria.andCheckoutDateEqualTo(tPropertyAutobill.getCheckoutDate());
            }
            if (tPropertyAutobill.getAutobillPushDate()!=null && tPropertyAutobill.getAutobillPushDate()!=""){
                criteria.andAutobillPushDateEqualTo(tPropertyAutobill.getAutobillPushDate());
            }
            if (tPropertyAutobill.getAmount()!=null){
                criteria.andAmountEqualTo(tPropertyAutobill.getAmount());
            }
            if (tPropertyAutobill.getCreateTime()!=null && tPropertyAutobill.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tPropertyAutobill.getCreateTime());
            }
            if (tPropertyAutobill.getUpdateTime()!=null && tPropertyAutobill.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tPropertyAutobill.getUpdateTime());
            }
        }
        return  tPropertyAutobillCriteria;
    }

    /**
    * 获取数据总量
    * @param tPropertyAutobill
    * @return
    */
    private Integer getCount(TPropertyAutobill tPropertyAutobill){
    Integer total =(int)tPropertyAutobillMapper.countByExample(setCriteria(tPropertyAutobill));
    return total;
    }

    /**
    *查询tPropertyAutobill(分页)
    * @param tPropertyAutobill
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TPropertyAutobill> getTPropertyAutobillbyPage(TPropertyAutobill tPropertyAutobill, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TPropertyAutobill> tPropertyAutobills=getTPropertyAutobill(tPropertyAutobill);
            Integer countNums =getCount(tPropertyAutobill);
            PageBean<TPropertyAutobill> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tPropertyAutobills);
            return pageData;
        }

    /**
    * 查询tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    public List<TPropertyAutobill> getTPropertyAutobill(TPropertyAutobill tPropertyAutobill){
    List<TPropertyAutobill>  tPropertyAutobills=tPropertyAutobillMapper.selectByExample(setCriteria(tPropertyAutobill));
    return tPropertyAutobills;
    }

    /**
    * 更新tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    public String UpdateTPropertyAutobill(TPropertyAutobill tPropertyAutobill)
    {
            String msg="";
        if(tPropertyAutobill.getBillItemId()!=null){
            TPropertyBillItems tPropertyBillItems = new TPropertyBillItems();
            tPropertyBillItems.setId(tPropertyAutobill.getBillItemId());
            tPropertyAutobill.setItemsType(tPropertyBillItemsService.getTPropertyBillItems(tPropertyBillItems).get(0).getItemsType());
        }
            try{
            if(tPropertyAutobill.getId()!=null){
                tPropertyAutobillMapper.updateByPrimaryKeySelective(tPropertyAutobill);
                msg = "编辑成功";
            }
            else
            {
                tPropertyAutobillMapper.insertSelective(tPropertyAutobill);
                msg = "成功添加";
            }
            }
            catch (Exception e)
            {
                logger.info(e.toString());
            }
            return msg;
    }

    /**
    * 删除tPropertyAutobill
    * @param tPropertyAutobill
    * @return
    */
    public String DeleteTPropertyAutobill(TPropertyAutobill tPropertyAutobill){
            String msg="";
            if(tPropertyAutobill.getId()!=null){
            tPropertyAutobillMapper.deleteByPrimaryKey(tPropertyAutobill.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tPropertyAutobill
    * @param id
    * @return
    */
    public TPropertyAutobill getTPropertyAutobillByID(Integer id) {
        return  tPropertyAutobillMapper.selectByPrimaryKey(id);
    }
}
