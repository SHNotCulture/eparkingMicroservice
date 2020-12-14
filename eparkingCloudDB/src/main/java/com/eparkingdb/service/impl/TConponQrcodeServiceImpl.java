package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TConponQrcode;
import com.common.entity.eparkingCloud.TConponQrcodeCriteria;
import com.eparkingdb.dao.TBusineMapper;
import com.eparkingdb.dao.TConponQrcodeMapper;
import com.eparkingdb.service.TConponQrcodeService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TConponQrcodeService接口实现类
* @author 谢轩然
* @date 2020/04/09 14:55
*/
@Service
public class TConponQrcodeServiceImpl  implements TConponQrcodeService {

    private  static final Logger logger= LoggerFactory.getLogger( TConponQrcodeServiceImpl.class);
    @Autowired
    private TConponQrcodeMapper tConponQrcodeMapper;
    @Autowired
    private TBusineMapper tBusineMapper;

    /**
    * 设置查询条件
    * @param tConponQrcode
    * @return
    */
    private  TConponQrcodeCriteria setCriteria(TConponQrcode tConponQrcode){
        TConponQrcodeCriteria tConponQrcodeCriteria= new TConponQrcodeCriteria();
        if(tConponQrcode!=null){
        TConponQrcodeCriteria.Criteria criteria=tConponQrcodeCriteria.createCriteria();
        if(tConponQrcode.getId()!=null){
        criteria.andIdEqualTo(tConponQrcode.getId());
        }
            if (tConponQrcode.getQrcodeName()!=null && tConponQrcode.getQrcodeName()!=""){        //优惠二维码名称
                criteria.andQrcodeNameLike("%" + tConponQrcode.getQrcodeName() + "%");
            }
            if(tConponQrcode.getBusineId()!=null){
                criteria.aneparkingdbusineIdEqualTo(tConponQrcode.getBusineId());
            }
        }
        return  tConponQrcodeCriteria;
    }

    //设置精确查询条件
    public TConponQrcodeCriteria getPreciseTConponQrcodeCriteria (TConponQrcode tConponQrcode){
        TConponQrcodeCriteria tConponQrcodeCriteria = new TConponQrcodeCriteria();
        TConponQrcodeCriteria.Criteria criteria = tConponQrcodeCriteria.createCriteria();
        if (tConponQrcode!=null){

            if (!StringUtils.isBlank(tConponQrcode.getQrcodeName())){        //优惠二维码名称
                criteria.andQrcodeNameEqualTo( tConponQrcode.getQrcodeName());
            }
        }
        return tConponQrcodeCriteria;
    }

    /**
    * 获取数据总量
    * @param tConponQrcode
    * @return
    */
    private Integer getCount(TConponQrcode tConponQrcode){
    Integer total =(int)tConponQrcodeMapper.countByExample(setCriteria(tConponQrcode));
    return total;
    }

    /**
    *查询tConponQrcode(分页)
    * @param tConponQrcode
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TConponQrcode> getTConponQrcodebyPage(TConponQrcode tConponQrcode, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TConponQrcode> tConponQrcodes=getTConponQrcode(tConponQrcode);
            Integer countNums =getCount(tConponQrcode);
            PageBean<TConponQrcode> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tConponQrcodes);
            return pageData;
        }

    /**
    * 查询tConponQrcode
    * @param tConponQrcode
    * @return
    */
    public List<TConponQrcode> getTConponQrcode(TConponQrcode tConponQrcode){
    List<TConponQrcode>  tConponQrcodes=tConponQrcodeMapper.selectByExample(setCriteria(tConponQrcode));
    return tConponQrcodes;
    }

    /**
    * 更新tConponQrcode
    * @param tConponQrcode
    * @return
    */
    public String UpdateTConponQrcode(TConponQrcode tConponQrcode)
    {
            String msg="";
            try{
            if(tConponQrcode.getId()!=null){
            tConponQrcodeMapper.updateByPrimaryKeySelective(tConponQrcode);
                msg="更新成功";
            }
            else
            {
            tConponQrcodeMapper.insertSelective(tConponQrcode);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tConponQrcode
    * @param tConponQrcode
    * @return
    */
    public String DeleteTConponQrcode(TConponQrcode tConponQrcode){
            String msg="";
            if(tConponQrcode.getId()!=null){
            tConponQrcodeMapper.deleteByPrimaryKey(tConponQrcode.getId());
            msg="删除TConponQrcode成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tConponQrcode
    * @param id
    * @return
    */
    public TConponQrcode getTConponQrcodeByID(Integer id) {
        return  tConponQrcodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TConponQrcode> getPreciseTConponQrcode(TConponQrcode tConponQrcode) {
        List<TConponQrcode> tConponQrcodeList = tConponQrcodeMapper.selectByExample(getPreciseTConponQrcodeCriteria(tConponQrcode));
        return tConponQrcodeList;
    }

/*    @Override
    public void existTConponQrcodeName(TConponQrcode tConponQrcode) {
        if(tConponQrcodeMapper.selectByExample(getPreciseTConponQrcodeCriteria(tConponQrcode)).size()>0){
            throw new ActionRspException(ActionRspEnum.QrcodeName_ERROR);
        }
    }*/

/*    @Override
    public void lessDiscountAmount(TConponQrcode tConponQrcode, Integer busineId) {
        //当优惠类型不为时间时执行小于单笔最大消费金额判断
        if (tConponQrcode.getCouponType()!=1){
            Integer discountAmount=tBusineMapper.selectByPrimaryKey(busineId).getDiscountAmount();
            if (tConponQrcode.getCouponPay()>discountAmount){
                throw new ActionRspException(ActionRspEnum.GreaterThanDiscountAmount_ERROR);
            }
        }
    }*/
}
