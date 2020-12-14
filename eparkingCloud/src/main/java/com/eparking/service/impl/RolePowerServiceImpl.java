package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TRolePowerNew;
import com.eparking.insideService.TRolePowerNewInsideService;
import com.eparking.service.RolePowerService;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 19:012018-11-6
 * @Modified By:
 */
@Service
public class RolePowerServiceImpl implements RolePowerService {
/*    @Autowired
    private TRolePowerNewMapper tRolePowerMapper;*/
    @Autowired
    private TRolePowerNewInsideService tRolePowerNewInsideService;

    /**
     *设置查询条件
     * @param tRolePower
     * @return
     */
/*    private TRolePowerNewCriteria setCriteria(TRolePowerNew tRolePower){
        TRolePowerNewCriteria tRolePowerCriteria= new TRolePowerNewCriteria();
        List<TRolePower> tRolePowerList=new ArrayList<>();
            if(tRolePower!=null){
                TRolePowerNewCriteria.Criteria criteria=tRolePowerCriteria.createCriteria();
                criteria.andUserIdNotEqualTo(0);
                if(tRolePower.getCompanyId()!=null){
                    criteria.andCompanyIdEqualTo(tRolePower.getCompanyId());
                }
                if(tRolePower.getId()!=null)
                {
                    criteria.andIdEqualTo(tRolePower.getId());
                }
            }
        return  tRolePowerCriteria;
    }*/
    /**
     *获取数据总量
     * @param tRolePower
     * @return
     */
/*    private Integer getCount(TRolePowerNew tRolePower){
        Integer total =(int)tRolePowerMapper.countByExample(setCriteria(tRolePower));
        return total;
    }*/
    /**
     * 查询全部角色信息（分页）
     * @param tRolePower
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TRolePowerNew> getRolePowerbyPage(TRolePowerNew tRolePower, Integer page, Integer limit){
/*        PageHelper.startPage(page, limit,"id desc");
        List<TRolePowerNew> tRolePowerList=getRolePower(tRolePower);
        Integer countNums =getCount(tRolePower);
        PageBean<TRolePowerNew> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tRolePowerList);
        return pageData;*/
        return tRolePowerNewInsideService.getTRolePowerNewbyPage(tRolePower, page, limit);
    }
    /**
     * 查询全部角色信息
     * @param tRolePower
     * @return
     */
    @Override
    public List<TRolePowerNew> getRolePower(TRolePowerNew tRolePower){
/*        List<TRolePowerNew> tRolePowerList=tRolePowerMapper.selectByExample(setCriteria(tRolePower));
        return tRolePowerList;*/
        return tRolePowerNewInsideService.getTRolePowerNew(tRolePower);
    }
    /**
     * 通过ID查询角色信息
     * @param tRolePowerNew
     * @return
     */
    @Override
    public List<TRolePowerNew> getRolePowerById(TRolePowerNew tRolePowerNew){
/*        TRolePowerNewCriteria tRolePowerCriteria= new TRolePowerNewCriteria();
        List<TRolePowerNew> tRolePowerList=new ArrayList<>();
        try {
            if(tRolePower!=null){
                TRolePowerNewCriteria.Criteria criteria=tRolePowerCriteria.createCriteria();
                if(tRolePower.getCompanyId()!=null){
                    criteria.andCompanyIdEqualTo(tRolePower.getCompanyId());
                }
                if(tRolePower.getId()!=null)
                {
                    criteria.andIdEqualTo(tRolePower.getId());
                }
            }
            tRolePowerList=tRolePowerMapper.selectByExample(tRolePowerCriteria);
        }
        catch (Exception e){
            throw  e;
        }

        return tRolePowerList;*/
        return tRolePowerNewInsideService.getRolePowerById(tRolePowerNew);
    }
    /**
     * 更新角色信息
     * @param tRolePower
     * @return
     */
    @Override
    public String UpdateRolePower(TRolePowerNew tRolePower)
    {
/*        if(tRolePower.getId()!=null){
            tRolePowerMapper.updateByPrimaryKeySelective(tRolePower);
        }
        else
        {
            tRolePowerMapper.insertSelective(tRolePower);
        }
        return "更新成功";*/
        String msg="";
        TRolePowerNew tRolePowerNewSel = new TRolePowerNew();
        tRolePowerNewSel.setCompanyId(tRolePower.getCompanyId());
        tRolePowerNewSel.setRoleName(tRolePower.getRoleName());
        List<TRolePowerNew> tRolePowerNewResultList = tRolePowerNewInsideService.getTRolePowerNew(tRolePowerNewSel);
        if(tRolePower.getId()==null){
            //新增操作
            if(tRolePowerNewResultList.size()>0){
                msg = "该物业已存在相同的角色名称";
            }else{
                msg = tRolePowerNewInsideService.UpdateTRolePowerNew(tRolePower);
            }
        }else{
            //编辑操作
            if(tRolePowerNewResultList.size()>0){
                for(TRolePowerNew tRolePowerNewOne:tRolePowerNewResultList){
                    if(!tRolePowerNewOne.getId().equals(tRolePower.getId())){
                        msg = "该物业已存在相同的角色名称";
                        break;
                    }
                }
            }
            if(msg.equals("")){
                msg = tRolePowerNewInsideService.UpdateTRolePowerNew(tRolePower);
            }
        }
        return msg;
//        return tRolePowerNewInsideService.UpdateTRolePowerNew(tRolePower);
    }
    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @Override
    public String DeleteRolePower(TRolePowerNew tRolePowerNew){
/*        try {
            tRolePowerMapper.deleteByPrimaryKey(id);
        }
        catch (Exception e){
            throw  e;
        }
        return "删除成功";*/
        return tRolePowerNewInsideService.DeleteTRolePowerNew(tRolePowerNew);
    }

    @Override
    public TRolePowerNew selectByPrimaryKey(Integer id) {
//        return tRolePowerMapper.selectByPrimaryKey(id);
        return tRolePowerNewInsideService.selectByPrimaryKey(id);
    }
}
