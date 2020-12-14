package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.insideService.TCompanyUserInsideService;
import com.eparking.service.UserService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:422018-8-29
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService{

    private  static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
/*    @Autowired
    private TCompanyUserMapper tCompanyUserMapper;*/

    @Autowired
    private TCompanyUserInsideService tCompanyUserInsideService;

    /**
     *设置查询条件
     * @param tCompanyUser
     * @return
     */
/*    private TCompanyUserCriteria setCriteria(TCompanyUser tCompanyUser){
        TCompanyUserCriteria tCompanyUserCriteria= new TCompanyUserCriteria();
            if(tCompanyUser!=null){
                TCompanyUserCriteria.Criteria criteria=tCompanyUserCriteria.createCriteria();
                if(tCompanyUser.getId()!=null)
                {
                    criteria.andIdEqualTo(tCompanyUser.getId());
                }
                if(tCompanyUser.getCompanyId()!=null)
                {
                    criteria.andCompanyIdEqualTo(tCompanyUser.getCompanyId());
                }
                if(tCompanyUser.getUserAccout()!=null)
                {
                    criteria.andUserAccoutEqualTo(tCompanyUser.getUserAccout());
                }
                if(tCompanyUser.getEntityType()!=null)
                {
                    criteria.andEntityTypeEqualTo(tCompanyUser.getEntityType());
                }
                if(tCompanyUser.getIsAdmin()!=null){
                    criteria.andIsAdminEqualTo(tCompanyUser.getIsAdmin());
                }
                if(tCompanyUser.getUserName()!=null){
                    criteria.andUserNameEqualTo(tCompanyUser.getUserName());
                }
                if(tCompanyUser.getPassword()!=null){
                    criteria.andPasswordEqualTo(tCompanyUser.getPassword());
                }



            }
        return  tCompanyUserCriteria;
    }*/
    /**
     *获取数据总量
     * @param tCompanyUser
     * @return
     */
/*    private Integer getCount(TCompanyUser tCompanyUser){
        Integer total =(int)tCompanyUserMapper.countByExample(setCriteria( tCompanyUser));
        return total;
    }*/
    /**
     * 查询全部物业用户信息（分页）
     * @param tCompanyUser
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TCompanyUser> getCompanyUserbyPage(TCompanyUser tCompanyUser, Integer page, Integer limit){
/*        PageHelper.startPage(page, limit,"id desc");
        List<TCompanyUser> tCompanyUserList=getCompanyUser(tCompanyUser);
        Integer countNums =getCount(tCompanyUser);
        PageBean<TCompanyUser> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCompanyUserList);
        return pageData;*/
        return tCompanyUserInsideService.getTCompanyUserbyPage(tCompanyUser, page, limit);
    }
    /**
     * 查询全部物业用户信息
     * @param tCompanyUser
     * @return
     */
    @Override
    public List<TCompanyUser> getCompanyUser(TCompanyUser tCompanyUser){
/*         List<TCompanyUser>  tCompanyUserList=tCompanyUserMapper.selectByExample(setCriteria( tCompanyUser));
        return  tCompanyUserList;*/
        List<TCompanyUser> tCompanyUserList = tCompanyUserInsideService.getTCompanyUser(tCompanyUser);
//        logger.info("companyUserList: ", JsonUtil.listToJson(tCompanyUserList));
        return tCompanyUserList;
    }


    /**
     * 更新物业用户信息
     * @param tCompanyUser
     * @return
     */
    @Override
    public String UpdateCompanyUser(TCompanyUser tCompanyUser){
/*        try{
            if(tCompanyUser.getId()!=null){
                tCompanyUserMapper.updateByPrimaryKeySelective(tCompanyUser);
            }
            else
            {
                tCompanyUser.setPassword(MD5Util.MD5Encode("123456"));
                tCompanyUser.setIsAdmin("2");//默认物业超级账户
                tCompanyUser.setEntityType(1);//默认物业后台
                tCompanyUserMapper.insertSelective(tCompanyUser);
            }
        }
        catch (Exception e)
        {
            throw  e;
        }
            return "更新成功";*/
        String msg="";
        TCompanyUser tCompanyUserSel = new TCompanyUser();
        tCompanyUserSel.setCompanyId(tCompanyUser.getCompanyId());
        tCompanyUserSel.setUserAccout(tCompanyUser.getUserAccout());
        List<TCompanyUser> tCompanyUserResultList = tCompanyUserInsideService.getTCompanyUser(tCompanyUserSel);
        if(tCompanyUser.getId()==null){
            //新增操作
            if(tCompanyUserResultList.size()>0){
                msg = "该物业已存在相同的用户账号";
            }else{
                msg = tCompanyUserInsideService.UpdateTCompanyUser(tCompanyUser);
            }
        }else{
            //编辑操作
            if(tCompanyUserResultList.size()>0){
                for(TCompanyUser tCompanyUserOne:tCompanyUserResultList){
                    if(!tCompanyUserOne.getId().equals(tCompanyUser.getId())){
                        msg = "该物业已存在相同的用户账号";
                        break;
                    }
                }
            }
            if(msg.equals("")){
                msg = tCompanyUserInsideService.UpdateTCompanyUser(tCompanyUser);
            }
        }
        return msg;
//        return tCompanyUserInsideService.UpdateTCompanyUser(tCompanyUser);
    }


    /**
     * 删除物业用户信息
     * @param tCompanyUser
     * @return
     */
    @Override
   public String DeleteCompanyUser(TCompanyUser tCompanyUser){
/*       try {
           tCompanyUserMapper.deleteByPrimaryKey(id);
       }
       catch (Exception e){
           throw  e;
       }
        return "删除成功";*/
        return tCompanyUserInsideService.DeleteTCompanyUser(tCompanyUser);
    }


    /**
     * 核对登录用户信息
     * @param tCompanyUser
     * @return
     */
    @Override
    public  TCompanyUser CheckPassword(TCompanyUser tCompanyUser){
/*        TCompanyUserCriteria tCompanyUserCriteria= new TCompanyUserCriteria();
        try{
            if(tCompanyUser!=null){
                TCompanyUserCriteria.Criteria criteria=tCompanyUserCriteria.createCriteria();
                criteria.andUserAccoutEqualTo(tCompanyUser.getUserAccout());
                criteria.andPasswordEqualTo(tCompanyUser.getPassword());
            }
            else{
                throw new ActionRspException(ActionRspEnum.Login_ERROR);
            }
            List<TCompanyUser> tCompanyUserList=tCompanyUserMapper.selectByExample(tCompanyUserCriteria);
            if(tCompanyUserList.size()==1)
            {
                tCompanyUser=tCompanyUserList.get(0);

            }
            else
            {
                throw new ActionRspException(ActionRspEnum.Login_ERROR);
            }
        }
        catch (Exception e){

        }
        return tCompanyUser;*/
        return tCompanyUserInsideService.CheckPassword(tCompanyUser);
    }

    @Override
    public TCompanyUser selectByPrimaryKey(Integer id) {
//        return tCompanyUserMapper.selectByPrimaryKey(id);
        return tCompanyUserInsideService.getTCompanyUserByID(id);
    }
}
