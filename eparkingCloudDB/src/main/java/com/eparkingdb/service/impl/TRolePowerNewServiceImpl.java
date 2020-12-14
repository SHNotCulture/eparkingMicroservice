package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TRolePowerNew;
import com.common.entity.eparkingCloud.TRolePowerNewCriteria;
import com.eparkingdb.dao.TRolePowerNewMapper;
import com.eparkingdb.service.TRolePowerNewService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: TRolePowerNewService接口实现类
* @author 谢轩然
* @date 2020/05/15 15:20
*/
@Service
public class TRolePowerNewServiceImpl  implements TRolePowerNewService {

    private  static final Logger logger= LoggerFactory.getLogger( TRolePowerNewServiceImpl.class);
    @Autowired
    private TRolePowerNewMapper tRolePowerNewMapper;

    /**
    * 设置查询条件
    * @param tRolePowerNew
    * @return
    */
    private  TRolePowerNewCriteria setCriteria(TRolePowerNew tRolePowerNew){
        TRolePowerNewCriteria tRolePowerNewCriteria= new TRolePowerNewCriteria();
        if(tRolePowerNew!=null){
        TRolePowerNewCriteria.Criteria criteria=tRolePowerNewCriteria.createCriteria();
        if(tRolePowerNew.getId()!=null){
        criteria.andIdEqualTo(tRolePowerNew.getId());
        }
        if(tRolePowerNew.getCompanyId()!=null){
            criteria.andCompanyIdEqualTo(tRolePowerNew.getCompanyId());
        }
        if(tRolePowerNew.getRoleName()!=null && !tRolePowerNew.getRoleName().equals("")){
            criteria.andRoleNameEqualTo(tRolePowerNew.getRoleName());
        }
        }
        return  tRolePowerNewCriteria;
    }

    /**
    * 获取数据总量
    * @param tRolePowerNew
    * @return
    */
    private Integer getCount(TRolePowerNew tRolePowerNew){
    Integer total =(int)tRolePowerNewMapper.countByExample(setCriteria(tRolePowerNew));
    return total;
    }

    /**
    *查询tRolePowerNew(分页)
    * @param tRolePowerNew
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TRolePowerNew> getTRolePowerNewbyPage(TRolePowerNew tRolePowerNew, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TRolePowerNew> tRolePowerNews=getTRolePowerNew(tRolePowerNew);
            Integer countNums =getCount(tRolePowerNew);
            PageBean<TRolePowerNew> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tRolePowerNews);
            return pageData;
        }

    /**
    * 查询tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    public List<TRolePowerNew> getTRolePowerNew(TRolePowerNew tRolePowerNew){
    List<TRolePowerNew>  tRolePowerNews=tRolePowerNewMapper.selectByExample(setCriteria(tRolePowerNew));
    return tRolePowerNews;
    }

    /**
    * 更新tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    public String UpdateTRolePowerNew(TRolePowerNew tRolePowerNew)
    {
            String msg="";
            try{
            if(tRolePowerNew.getId()!=null){
            tRolePowerNewMapper.updateByPrimaryKeySelective(tRolePowerNew);
                msg="更新成功";
            }
            else
            {
            tRolePowerNewMapper.insertSelective(tRolePowerNew);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    public String DeleteTRolePowerNew(TRolePowerNew tRolePowerNew){
            String msg="";
            if(tRolePowerNew.getId()!=null){
            tRolePowerNewMapper.deleteByPrimaryKey(tRolePowerNew.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tRolePowerNew
    * @param id
    * @return
    */
    public TRolePowerNew getTRolePowerNewByID(Integer id) {
        return  tRolePowerNewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TRolePowerNew> getRolePowerById(TRolePowerNew tRolePowerNew) {
        TRolePowerNewCriteria tRolePowerCriteria= new TRolePowerNewCriteria();
        List<TRolePowerNew> tRolePowerList=new ArrayList<>();
        try {
            if(tRolePowerNew!=null){
                TRolePowerNewCriteria.Criteria criteria=tRolePowerCriteria.createCriteria();
                if(tRolePowerNew.getCompanyId()!=null){
                    criteria.andCompanyIdEqualTo(tRolePowerNew.getCompanyId());
                }
                if(tRolePowerNew.getId()!=null)
                {
                    criteria.andIdEqualTo(tRolePowerNew.getId());
                }
            }
            tRolePowerList=tRolePowerNewMapper.selectByExample(tRolePowerCriteria);
        }
        catch (Exception e){
            throw  e;
        }

        return tRolePowerList;
    }
}
