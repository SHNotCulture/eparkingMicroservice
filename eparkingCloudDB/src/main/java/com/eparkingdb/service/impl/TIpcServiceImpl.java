package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TIpc;
import com.common.entity.eparkingCloud.TIpcCriteria;
import com.eparkingdb.dao.TIpcMapper;
import com.eparkingdb.service.TIpcService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TIpcService接口实现类
* @author 谢轩然
* @date 2020/07/13 18:31
*/
@Service
public class TIpcServiceImpl  implements TIpcService {

    private  static final Logger logger= LoggerFactory.getLogger( TIpcServiceImpl.class);
    @Autowired
    private TIpcMapper tIpcMapper;

    /**
    * 设置查询条件
    * @param tIpc
    * @return
    */
    private  TIpcCriteria setCriteria(TIpc tIpc){
        TIpcCriteria tIpcCriteria= new TIpcCriteria();
        if(tIpc!=null){
        TIpcCriteria.Criteria criteria=tIpcCriteria.createCriteria();
        if(tIpc.getId()!=null){
        criteria.andIdEqualTo(tIpc.getId());
        }
            if(tIpc.getIpcType()!=null){
                criteria.andIpcTypeEqualTo(tIpc.getIpcType());
            }
            if(tIpc.getRtspurl()!=null && tIpc.getRtspurl()!=""){
                criteria.andRtspurlEqualTo(tIpc.getRtspurl());
            }
            if(tIpc.get相机厂家名称()!=null && tIpc.get相机厂家名称()!=""){
                criteria.and相机厂家名称EqualTo(tIpc.get相机厂家名称());
            }
        }
        return  tIpcCriteria;
    }

    /**
    * 获取数据总量
    * @param tIpc
    * @return
    */
    private Integer getCount(TIpc tIpc){
    Integer total =(int)tIpcMapper.countByExample(setCriteria(tIpc));
    return total;
    }

    /**
    *查询tIpc(分页)
    * @param tIpc
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TIpc> getTIpcbyPage(TIpc tIpc, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TIpc> tIpcs=getTIpc(tIpc);
            Integer countNums =getCount(tIpc);
            PageBean<TIpc> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tIpcs);
            return pageData;
        }

    /**
    * 查询tIpc
    * @param tIpc
    * @return
    */
    public List<TIpc> getTIpc(TIpc tIpc){
    List<TIpc>  tIpcs=tIpcMapper.selectByExample(setCriteria(tIpc));
    return tIpcs;
    }

    /**
    * 更新tIpc
    * @param tIpc
    * @return
    */
    public String UpdateTIpc(TIpc tIpc)
    {
            String msg="";
            try{
            if(tIpc.getId()!=null){
            tIpcMapper.updateByPrimaryKeySelective(tIpc);
                msg="更新TIpc成功";
            }
            else
            {
            tIpcMapper.insertSelective(tIpc);
                msg="新建TIpc成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tIpc
    * @param tIpc
    * @return
    */
    public String DeleteTIpc(TIpc tIpc){
            String msg="";
            if(tIpc.getId()!=null){
            tIpcMapper.deleteByPrimaryKey(tIpc.getId());
            msg="删除TIpc成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tIpc
    * @param id
    * @return
    */
    public TIpc getTIpcByID(Integer id) {
        return  tIpcMapper.selectByPrimaryKey(id);
    }
}
