package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TConponQrcode;
import com.eparking.enums.ActionRspEnum;
import com.eparking.exception.ActionRspException;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TConponQrcodeInsideService;
import com.eparking.service.CouponQrcodeRecordsService;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponQrcodeRecordsServiceImpl implements CouponQrcodeRecordsService {

    @Autowired
    private TConponQrcodeInsideService tConponQrcodeInsideService;
    @Autowired
    private TBusineInsideService tBusineInsideService;
/*    @Autowired
    private TConponQrcodeMapper tConponQrcodeMapper;
    @Autowired
    private TBusineMapper tBusineMapper;*/

    //设置模糊查询条件
/*    public TConponQrcodeCriteria getTConponQrcodeCriteria (TConponQrcode tConponQrcode){
        TConponQrcodeCriteria tConponQrcodeCriteria = new TConponQrcodeCriteria();
        TConponQrcodeCriteria.Criteria criteria = tConponQrcodeCriteria.createCriteria();
        if (tConponQrcode!=null){

            if (tConponQrcode.getQrcodeName()!=null && tConponQrcode.getQrcodeName()!=""){        //优惠二维码名称
                criteria.andQrcodeNameLike("%" + tConponQrcode.getQrcodeName() + "%");
            }
        }
        return tConponQrcodeCriteria;
    }*/

    //设置精确查询条件
/*    public TConponQrcodeCriteria getPreciseTConponQrcodeCriteria (TConponQrcode tConponQrcode){
        TConponQrcodeCriteria tConponQrcodeCriteria = new TConponQrcodeCriteria();
        TConponQrcodeCriteria.Criteria criteria = tConponQrcodeCriteria.createCriteria();
        if (tConponQrcode!=null){

            if (!StringUtils.isBlank(tConponQrcode.getQrcodeName())){        //优惠二维码名称
                criteria.andQrcodeNameEqualTo( tConponQrcode.getQrcodeName());
            }
        }
        return tConponQrcodeCriteria;
    }*/

    /**
     * 获取数据总量
     * @param tConponQrcode
     * @param
     * @return
     */
/*    private Integer getCount(TConponQrcode tConponQrcode){
        Integer total = (int)tConponQrcodeMapper.countByExample(getTConponQrcodeCriteria(tConponQrcode));
        return total;
    }*/

    //查询优惠二维码列表
    public List<TConponQrcode> getTConponQrcode(TConponQrcode tConponQrcode){
/*        List<TConponQrcode> t = tConponQrcodeMapper.selectByExample(getTConponQrcodeCriteria(tConponQrcode));
        //System.out.println(t);
        return t;*/
        return tConponQrcodeInsideService.getTConponQrcode(tConponQrcode);
    }

    /**
     * 优惠二维码管理(分页)
     * @param tConponQrcode
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TConponQrcode> getCouponQrcodeListbyPage(TConponQrcode tConponQrcode, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TConponQrcode> tConponQrcodeLists =FindTConponQrcode(tConponQrcode);
        Integer countNums =getCount(tConponQrcode);
        PageBean<TConponQrcode> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tConponQrcodeLists);
        return pageData;*/
        return tConponQrcodeInsideService.getTConponQrcodebyPage(tConponQrcode, page, limit);
    }

    /**
     * 更新优惠二维码记录
     */
    @Override
    public String updateCouponQrcode(TConponQrcode tConponQrcode) {
/*        String msg="";
        if(tConponQrcode.getId()!=null)                 //根据记录ID判断是更新还是插入
        {
            tConponQrcode.setCreateTime(DateUtil.getCurDateTime());
            tConponQrcodeMapper.updateByPrimaryKeySelective(tConponQrcode);
            msg="更新成功";
        }
        return msg;*/
        return tConponQrcodeInsideService.UpdateTConponQrcode(tConponQrcode);
    }


    /**
     * 删除优惠二维码记录
     */
    @Override
    public String deleteCouponQrcode(TConponQrcode tConponQrcode) {
/*        String msg="";
        if(tConponQrcode.getId()!=null){
            tConponQrcodeMapper.deleteByPrimaryKey(tConponQrcode.getId());
            msg="删除成功";
        }
        return msg;*/
        return tConponQrcodeInsideService.DeleteTConponQrcode(tConponQrcode);
    }

    /**
     * 生成优惠二维码记录
     */
/*    @Override
    public String createCouponQrcode(TConponQrcode tConponQrcode) {
        String msg="";
        if(tConponQrcode.getId()==null){
            tConponQrcodeMapper.insertSelective(tConponQrcode);
            msg="新建成功";
        }
        return msg;
    }*/

    @Override
    public Integer getIdCouponQrcode(TConponQrcode tConponQrcode) {

//        return  tConponQrcodeMapper.selectByExample(getTConponQrcodeCriteria(tConponQrcode)).get(0).getId();
        return tConponQrcodeInsideService.getTConponQrcode(tConponQrcode).get(0).getId();
    }

    /*
    根据qrcodeName查询数据库
     */
    @Override
    public void existQrcodeName(TConponQrcode tConponQrcode) {

/*        if(tConponQrcodeMapper.selectByExample(getPreciseTConponQrcodeCriteria(tConponQrcode)).size()>0){
            throw new ActionRspException(ActionRspEnum.QrcodeName_ERROR);
        }*/
        List<TConponQrcode> tConponQrcodeList= getPreciseTConponQrcode(tConponQrcode);
        if(tConponQrcodeList.size()>0){
            throw new ActionRspException(ActionRspEnum.QrcodeName_ERROR);
        }
    }

    @Override
    public void lessDiscountAmount(TConponQrcode tConponQrcode, Integer busineId) {
        //当优惠类型不为时间时执行小于单笔最大消费金额判断
        if (tConponQrcode.getCouponType()!=1){
//        Integer discountAmount=tBusineMapper.selectByPrimaryKey(busineId).getDiscountAmount();
            Integer discountAmount=tBusineInsideService.selectById(busineId).getDiscountAmount();
        if (tConponQrcode.getCouponPay()>discountAmount){
            throw new ActionRspException(ActionRspEnum.GreaterThanDiscountAmount_ERROR);
        }
    }
    }

    List<TConponQrcode> getPreciseTConponQrcode(TConponQrcode tConponQrcode){
        return tConponQrcodeInsideService.getPreciseTConponQrcode(tConponQrcode);
    }
}
