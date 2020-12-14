package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparkingdb.dao.TBusineMapper;
import com.eparkingdb.dao.TBusinePayMapper;
import com.eparkingdb.dao.TCompanyParkMapper;
import com.eparkingdb.dao.TCompanyUserMapper;
import com.eparkingdb.service.TBusineService;
import com.eparkingdb.service.TCompanyUserService;
import com.common.util.DateUtil;
import com.common.util.MD5Util;
import com.common.util.StringUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description: TBusineService接口实现类
* @author 谢轩然
* @date 2020/04/08 18:35
*/
@Service
public class TBusineServiceImpl  implements TBusineService {

    private  static final Logger logger= LoggerFactory.getLogger( TBusineServiceImpl.class);
    @Autowired
    private TBusineMapper tBusineMapper;
    @Autowired
    private TCompanyUserService tCompanyUserService;
    @Autowired
    private TCompanyParkMapper tCompanyParkMapper;
    @Autowired
    private TBusinePayMapper tBusinePayMapper;
    @Autowired
    private TCompanyUserMapper tCompanyUserMapper;


    /**
    * 设置查询条件
    * @param tBusine
    * @return
    */
    private  TBusineCriteria setCriteria(TBusine tBusine){
        TBusineCriteria tBusineCriteria= new TBusineCriteria();
        if(tBusine!=null){
        TBusineCriteria.Criteria criteria=tBusineCriteria.createCriteria();
        //不查询虚拟商户
            criteria.andDiscountTypeNotEqualTo(4);
            if(tBusine.getId()!=null){
            criteria.andIdEqualTo(tBusine.getId());
            }
            if(tBusine.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tBusine.getParkId());
            }
            if(tBusine.getBusineName()!=null)
            {
                criteria.aneparkingdbusineNameLike("%"+tBusine.getBusineName()+"%");
            }
            if(tBusine.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tBusine.getCompanyId());
            }
            if(tBusine.getAccount()!=null){
                criteria.andAccountEqualTo(tBusine.getAccount());
            }

            if(tBusine.getIsAuto()!=null){
                criteria.andIsAutoEqualTo(tBusine.getIsAuto());
            }
        }
        return  tBusineCriteria;
    }

    /**
    * 获取数据总量
    * @param tBusine
    * @return
    */
    private Integer getCount(TBusine tBusine){
    Integer total =(int)tBusineMapper.countByExample(setCriteria(tBusine));
    return total;
    }

    /**
    *查询tBusine(分页)
    * @param tBusine
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TBusine> getTBusinebyPage(TBusine tBusine, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TBusine> tBusines=getTBusine(tBusine);
            Integer countNums =getCount(tBusine);
            PageBean<TBusine> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tBusines);
            return pageData;
        }

    /**
    * 查询tBusine
    * @param tBusine
    * @return
    */
    public List<TBusine> getTBusine(TBusine tBusine){
    List<TBusine>  tBusines=tBusineMapper.selectByExample(setCriteria(tBusine));
    return tBusines;
    }

    /**
    * 更新tBusine
    * @param tBusine
    * @return
    */
    public String UpdateTBusine(TBusine tBusine) {
//        logger.info(tBusine.toString());
        TCompanyUser tCompanyUser=null;
        String msg="";
        if(tBusine.getId()!=null){ //编辑商户信息
            TBusineCriteria tBusineCriteria= new TBusineCriteria();
            TBusineCriteria.Criteria criteria=tBusineCriteria.createCriteria();
            criteria.andIdNotEqualTo(tBusine.getId());
            criteria.andAccountEqualTo(tBusine.getAccount());
            List<TBusine> tBusineList=tBusineMapper.selectByExample(tBusineCriteria);
            if(tBusineList.size()==0){
                TBusine tBusineSel = new TBusine();
                tBusineSel.setId(tBusine.getId());
                String account =  getTBusine(tBusineSel).get(0).getAccount();
                TCompanyUser tCompanyUserSel = new TCompanyUser();

                tCompanyUserSel.setUserAccout(account);
                TCompanyUser tCompanyUserOld = tCompanyUserService.getTCompanyUser(tCompanyUserSel).get(0);
                TCompanyUserCriteria tCompanyUserCriteria = new TCompanyUserCriteria();
                TCompanyUserCriteria.Criteria criteria1 = tCompanyUserCriteria.createCriteria();
                criteria1.andUserAccoutEqualTo(tBusine.getAccount());
                criteria1.andIdNotEqualTo(tCompanyUserOld.getId());
                List<TCompanyUser> tCompanyUserList =tCompanyUserMapper.selectByExample(tCompanyUserCriteria);
                if(tCompanyUserList.size()==0){
                    tBusineMapper.updateByPrimaryKeySelective(tBusine);
                    tCompanyUser= tCompanyUserOld;
                }else{
//                    throw new ActionRspException(ActionRspEnum.BusineNameExist_ERROR);
                 msg = "账号名称重复，无法完成更新操作";
                }
            }else{
//                throw new ActionRspException(ActionRspEnum.BusineNameExist_ERROR);
                 msg = "商户名称重复，无法完成更新操作";
            }
        }
        else
        {  //新增商户信息
            TCompanyUser tCompanyUser1 = new TCompanyUser();
            tCompanyUser1.setUserAccout(tBusine.getAccount());
            List<TCompanyUser> tCompanyUserList = tCompanyUserService.getTCompanyUser(tCompanyUser1);
            if(tCompanyUserList.size()==0){//物业账户不存在已有记录 可以新增商户
                tBusine.setPassword(MD5Util.MD5Encode("123456"));
                tBusineMapper.insertSelective(tBusine);
                tCompanyUser= new TCompanyUser();
            }else{

//                throw new ActionRspException(ActionRspEnum.BusineNameExist_ERROR);
                msg = "物业账户存在已有记录，无法完成新增操作";
            }


        }



        if(tCompanyUser!=null)
        {
            tCompanyUser.setRoleId(212);
            tCompanyUser.setParkIds(tBusine.getParkId().toString());
            tCompanyUser.setUserName(tBusine.getBusineName());
            tCompanyUser.setUserAccout(tBusine.getAccount());
            tCompanyUser.setPassword(tBusine.getPassword());
            tCompanyUser.setCompanyId(tBusine.getCompanyId());
            tCompanyUser.setIsAdmin("3");
            tCompanyUser.setEntityType(2);
            tCompanyUserService.UpdateTCompanyUser(tCompanyUser);
            msg = "更新成功";
        }

        return msg;
    }

    /**
    * 删除tBusine
    * @param tBusine
    * @return
    */
    public String DeleteTBusine(TBusine tBusine){
            String msg="";
            if(tBusine.getId()!=null){
            tBusineMapper.deleteByPrimaryKey(tBusine.getId());
            msg="删除TBusine成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tBusine
    * @param id
    * @return
    */
    public TBusine getTBusineByID(Integer id) {
        return  tBusineMapper.selectByPrimaryKey(id);
    }

    @Override
    public TBusine selectByAccount(String account) {
        TBusineCriteria tBusineCriteria = new TBusineCriteria();
        TBusineCriteria.Criteria criteria1 = tBusineCriteria.createCriteria();
        criteria1.andAccountEqualTo(account);
        return tBusineMapper.selectByExample(tBusineCriteria).get(0) ;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public String BusineRecharge(TBusinePay tBusinePay, String type) {
        String msg = null;
        TCompanyPark tCompanyPark=tCompanyParkMapper.selectByPrimaryKey(tBusinePay.getParkId());
        //更改商户余额
        TBusine tBusine = tBusineMapper.selectByPrimaryKey(tBusinePay.getBusineId());
        if (type.equals("1")) {//充值
            tBusinePay.setRemark("充值");
            tBusine.setBalance(tBusine.getBalance() + tBusinePay.getActualPay());
            msg = "充值成功";
        }else if (type.equals("0")){//冲正
            tBusinePay.setRemark("充正");
            tBusine.setBalance(tBusine.getBalance() - tBusinePay.getActualPay());
            tBusinePay.setActualPay(tBusinePay.getActualPay()*(-1));
            tBusinePay.setNeedPay(tBusinePay.getNeedPay()*(-1));
            msg = "充正成功";
        }else if (type.equals("2")){//回购
            tBusinePay.setRemark("电子券回购");
            tBusine.setBalance(tBusine.getBalance() + tBusinePay.getActualPay());
            msg = "回购成功";
        }else if(type.equals("3")){//月初充值
            tBusinePay.setRemark("月初充值");
            tBusine.setBalance(tBusine.getBalance() + tBusinePay.getNeedPay());
            msg = "月初充值成功";
        }
        tBusineMapper.updateByPrimaryKeySelective(tBusine);


        //添加充值记录
        tBusinePay.setParkName(tCompanyPark.getParkName());
        tBusinePay.setOrderNumber(StringUtil.RandomOrder());
        tBusinePay.setPayTime(DateUtil.getCurDateTime());
        tBusinePay.setBalance(tBusine.getBalance());
        tBusinePayMapper.insertSelective(tBusinePay);
        return msg;
    }

}
