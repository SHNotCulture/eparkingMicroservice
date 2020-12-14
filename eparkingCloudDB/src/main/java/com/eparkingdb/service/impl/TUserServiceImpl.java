package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TUser;
import com.common.entity.eparkingCloud.TUserCriteria;
import com.eparkingdb.dao.TUserMapper;
import com.eparkingdb.service.TUserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TUserService接口实现类
* @author 谢轩然
* @date 2020/04/10 16:11
*/
@Service
public class TUserServiceImpl  implements TUserService {

    private  static final Logger logger= LoggerFactory.getLogger( TUserServiceImpl.class);
    @Autowired
    private TUserMapper tUserMapper;

    /**
    * 设置查询条件
    * @param tUser
    * @return
    */
    private  TUserCriteria setCriteria(TUser tUser){
        TUserCriteria tUserCriteria= new TUserCriteria();
        if(tUser!=null){
        TUserCriteria.Criteria criteria=tUserCriteria.createCriteria();
        if(tUser.getId()!=null){
        criteria.andIdEqualTo(tUser.getId());
        }

        }
        return  tUserCriteria;
    }

    /**
    * 获取数据总量
    * @param tUser
    * @return
    */
    private Integer getCount(TUser tUser){
    Integer total =(int)tUserMapper.countByExample(setCriteria(tUser));
    return total;
    }

    /**
    *查询tUser(分页)
    * @param tUser
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TUser> getTUserbyPage(TUser tUser, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TUser> tUsers=getTUser(tUser);
            Integer countNums =getCount(tUser);
            PageBean<TUser> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tUsers);
            return pageData;
        }

    /**
    * 查询tUser
    * @param tUser
    * @return
    */
    public List<TUser> getTUser(TUser tUser){
    List<TUser>  tUsers=tUserMapper.selectByExample(setCriteria(tUser));
    return tUsers;
    }

    /**
    * 更新tUser
    * @param tUser
    * @return
    */
    public String UpdateTUser(TUser tUser)
    {
            String msg="";
            try{
            if(tUser.getId()!=null){
            tUserMapper.updateByPrimaryKeySelective(tUser);
                msg="更新TUser成功";
            }
            else
            {
            tUserMapper.insertSelective(tUser);
                msg="新建TUser成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tUser
    * @param tUser
    * @return
    */
    public String DeleteTUser(TUser tUser){
            String msg="";
            if(tUser.getId()!=null){
            tUserMapper.deleteByPrimaryKey(tUser.getId());
            msg="删除TUser成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tUser
    * @param id
    * @return
    */
    public TUser getTUserByID(Integer id) {
        return  tUserMapper.selectByPrimaryKey(id);
    }
}
