package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.common.entity.eparkingCloud.TPropertyBillItemsCriteria;
import com.eparkingdb.dao.TPropertyBillItemsMapper;
import com.eparkingdb.service.TPropertyBillItemsService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TPropertyBillItemsService接口实现类
* @author 谢轩然
* @date 2020/04/29 10:33
*/
@Service
public class TPropertyBillItemsServiceImpl  implements TPropertyBillItemsService {

    private  static final Logger logger= LoggerFactory.getLogger( TPropertyBillItemsServiceImpl.class);
    @Autowired
    private TPropertyBillItemsMapper tPropertyBillItemsMapper;

    /**
    * 设置查询条件
    * @param tPropertyBillItems
    * @return
    */
    private  TPropertyBillItemsCriteria setCriteria(TPropertyBillItems tPropertyBillItems){
        TPropertyBillItemsCriteria tPropertyBillItemsCriteria= new TPropertyBillItemsCriteria();
        if(tPropertyBillItems!=null){
        TPropertyBillItemsCriteria.Criteria criteria=tPropertyBillItemsCriteria.createCriteria();
        if(tPropertyBillItems.getId()!=null){
        criteria.andIdEqualTo(tPropertyBillItems.getId());
        }
            if(tPropertyBillItems.getParkId()!=null) {
                criteria.andParkIdEqualTo(tPropertyBillItems.getParkId());
            }
            if (tPropertyBillItems.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tPropertyBillItems.getCompanyId());
            }
            if(tPropertyBillItems.getBillName()!=null && tPropertyBillItems.getBillName()!=""){
                criteria.aneparkingdbillNameLike("%"+tPropertyBillItems.getBillName()+"%");
            }
            if (tPropertyBillItems.getItemsType()!=null){
                criteria.andItemsTypeEqualTo(tPropertyBillItems.getItemsType());
            }
            if (tPropertyBillItems.getItemsAmount()!=null){
                criteria.andItemsAmountEqualTo(tPropertyBillItems.getItemsAmount());
            }
            if (tPropertyBillItems.getCreateTime()!=null && tPropertyBillItems.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tPropertyBillItems.getCreateTime());
            }
            if (tPropertyBillItems.getUpdateTime()!=null && tPropertyBillItems.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tPropertyBillItems.getUpdateTime());
            }
        }
        return  tPropertyBillItemsCriteria;
    }

    /**
    * 获取数据总量
    * @param tPropertyBillItems
    * @return
    */
    private Integer getCount(TPropertyBillItems tPropertyBillItems){
    Integer total =(int)tPropertyBillItemsMapper.countByExample(setCriteria(tPropertyBillItems));
    return total;
    }

    /**
    *查询tPropertyBillItems(分页)
    * @param tPropertyBillItems
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TPropertyBillItems> getTPropertyBillItemsbyPage(TPropertyBillItems tPropertyBillItems, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TPropertyBillItems> tPropertyBillItemss=getTPropertyBillItems(tPropertyBillItems);
            Integer countNums =getCount(tPropertyBillItems);
            PageBean<TPropertyBillItems> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tPropertyBillItemss);
            return pageData;
        }

    /**
    * 查询tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    public List<TPropertyBillItems> getTPropertyBillItems(TPropertyBillItems tPropertyBillItems){
    List<TPropertyBillItems>  tPropertyBillItemss=tPropertyBillItemsMapper.selectByExample(setCriteria(tPropertyBillItems));
    return tPropertyBillItemss;
    }

    /**
    * 更新tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    public String UpdateTPropertyBillItems(TPropertyBillItems tPropertyBillItems)
    {
            String msg="";
            try{
            if(tPropertyBillItems.getId()!=null){
            tPropertyBillItemsMapper.updateByPrimaryKeySelective(tPropertyBillItems);
                msg="编辑成功";
            }
            else
            {
            tPropertyBillItemsMapper.insertSelective(tPropertyBillItems);
                msg="成功添加";
            }
            }
            catch (Exception e)
            {
                logger.info(e.toString());
            }
            return msg;
    }

    /**
    * 删除tPropertyBillItems
    * @param tPropertyBillItems
    * @return
    */
    public String DeleteTPropertyBillItems(TPropertyBillItems tPropertyBillItems){
            String msg="";
            if(tPropertyBillItems.getId()!=null){
            tPropertyBillItemsMapper.deleteByPrimaryKey(tPropertyBillItems.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tPropertyBillItems
    * @param id
    * @return
    */
    public TPropertyBillItems getTPropertyBillItemsByID(Integer id) {
        return  tPropertyBillItemsMapper.selectByPrimaryKey(id);
    }
}
