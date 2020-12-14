package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TGlobalInfo;
import com.common.entity.eparkingCloud.TGlobalInfoCriteria;
import com.eparkingdb.dao.TGlobalInfoMapper;
import com.eparkingdb.service.TGlobalInfoService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TGlobalInfoService接口实现类
* @author 谢轩然
* @date 2020/04/09 15:04
*/
@Service
public class TGlobalInfoServiceImpl  implements TGlobalInfoService {

    private  static final Logger logger= LoggerFactory.getLogger( TGlobalInfoServiceImpl.class);
    @Autowired
    private TGlobalInfoMapper tGlobalInfoMapper;

    /**
    * 设置查询条件
    * @param tGlobalInfo
    * @return
    */
    private TGlobalInfoCriteria setCriteria(TGlobalInfo tGlobalInfo){
        TGlobalInfoCriteria tGlobalInfoCriteria = new TGlobalInfoCriteria();
        if(tGlobalInfo!=null){
            TGlobalInfoCriteria.Criteria criteria = tGlobalInfoCriteria.createCriteria();
            if(tGlobalInfo.getParkId()!=null) {
                criteria.andParkIdEqualTo(tGlobalInfo.getParkId());
            }
            if (tGlobalInfo.getId()!=null){
                criteria.andIdEqualTo(tGlobalInfo.getId());
            }
            if(tGlobalInfo.getParamContext()!=null && tGlobalInfo.getParamContext()!=""){
                criteria.andParamContextEqualTo("%"+tGlobalInfo.getParamContext()+"%");
            }
            if(tGlobalInfo.getParamValue()!=null && tGlobalInfo.getParamValue()!=""){
                criteria.andParamValueEqualTo("%"+tGlobalInfo.getParamValue()+"%");
            }
            if(tGlobalInfo.getParamDesc()!=null && tGlobalInfo.getParamDesc()!=""){
                criteria.andParamDescEqualTo("%"+tGlobalInfo.getParamDesc()+"%");
            }
            if(tGlobalInfo.getGroupName()!=null && tGlobalInfo.getGroupName()!=""){
                criteria.andGroupNameEqualTo("%"+tGlobalInfo.getGroupName()+"%");
            }
            if (tGlobalInfo.getParamNumber()!=null){
                criteria.andParamNumberEqualTo(tGlobalInfo.getParamNumber());
            }
            if(tGlobalInfo.getParamType()!=null && tGlobalInfo.getParamType()!=""){
                criteria.andParamTypeEqualTo(tGlobalInfo.getParamType());
            }
        }
        return  tGlobalInfoCriteria;
    }

    /**
    * 获取数据总量
    * @param tGlobalInfo
    * @return
    */
    private Integer getCount(TGlobalInfo tGlobalInfo){
    Integer total =(int)tGlobalInfoMapper.countByExample(setCriteria(tGlobalInfo));
    return total;
    }

    /**
    *查询tGlobalInfo(分页)
    * @param tGlobalInfo
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TGlobalInfo> getTGlobalInfobyPage(TGlobalInfo tGlobalInfo, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TGlobalInfo> tGlobalInfos=getTGlobalInfo(tGlobalInfo);
            Integer countNums =getCount(tGlobalInfo);
            PageBean<TGlobalInfo> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tGlobalInfos);
            return pageData;
        }

    /**
    * 查询tGlobalInfo
    * @param tGlobalInfo
    * @return
    */
    public List<TGlobalInfo> getTGlobalInfo(TGlobalInfo tGlobalInfo){
    List<TGlobalInfo>  tGlobalInfos=tGlobalInfoMapper.selectByExample(setCriteria(tGlobalInfo));
    return tGlobalInfos;
    }

    /**
    * 更新tGlobalInfo
    * @param tGlobalInfo
    * @return
    */
    public String UpdateTGlobalInfo(TGlobalInfo tGlobalInfo)
    {
            String msg="";
            try{
            if(tGlobalInfo.getId()!=null){
            tGlobalInfoMapper.updateByPrimaryKeySelective(tGlobalInfo);
                msg="更新TGlobalInfo成功";
            }
            else
            {
            tGlobalInfoMapper.insertSelective(tGlobalInfo);
                msg="新建TGlobalInfo成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tGlobalInfo
    * @param tGlobalInfo
    * @return
    */
    public String DeleteTGlobalInfo(TGlobalInfo tGlobalInfo){
            String msg="";
            if(tGlobalInfo.getId()!=null){
            tGlobalInfoMapper.deleteByPrimaryKey(tGlobalInfo.getId());
            msg="删除TGlobalInfo成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tGlobalInfo
    * @param id
    * @return
    */
    public TGlobalInfo getTGlobalInfoByID(Integer id) {
        return  tGlobalInfoMapper.selectByPrimaryKey(id);
    }
}
