package com.eparkingdb.service.impl;

import com.common.entity.eparkingCloud.TModuleListNew;
import com.common.entity.eparkingCloud.TModuleListNewCriteria;
import com.eparkingdb.dao.TModuleListNewMapper;
import com.eparkingdb.service.TModuleListNewService;
import com.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TModuleListNewServiceImpl implements TModuleListNewService {

    @Autowired
    private TModuleListNewMapper tModuleListNewMapper;

    @Override
    public List<TModuleListNew> getModuleListNewForLayuiMenu(List<Integer> tModuleListNew) {
        TModuleListNewCriteria tModuleListCriteria= new TModuleListNewCriteria();
        if(tModuleListNew!=null){
            TModuleListNewCriteria.Criteria criteria=tModuleListCriteria.createCriteria();
            criteria.andIdIn(tModuleListNew);
            criteria.andPidEqualTo(0);
            tModuleListCriteria.setOrderByClause("id asc");
        }
        //得到一级菜单信息
        List<TModuleListNew> tModuleListsTop=tModuleListNewMapper.selectByExample(tModuleListCriteria);
        return tModuleListsTop;
    }

    @Override
    public List<TModuleListNew> getModuleList(TModuleListNew tModuleListNew) {
        TModuleListNewCriteria tModuleListNewCriteria= new TModuleListNewCriteria();
        if(tModuleListNew!=null){
            TModuleListNewCriteria.Criteria criteria=tModuleListNewCriteria.createCriteria();
        }
        List<TModuleListNew> tModuleListNews=tModuleListNewMapper.selectByExample(tModuleListNewCriteria);
        return tModuleListNews;
    }

    @Override
    public List<TModuleListNew> getModuleListForZtree(TModuleListNew tModuleListNew) {
        TModuleListNewCriteria tModuleListCriteria= new TModuleListNewCriteria();
        List<Integer> tModuleList= StringUtil.stringList2List("12,13".split(","));

        if(tModuleListNew!=null){
            TModuleListNewCriteria.Criteria criteria=tModuleListCriteria.createCriteria();
            criteria.andIdNotIn(tModuleList);//物业用户不能拥有车场管理及物业管理页面
        }
        List<TModuleListNew> tModuleLists=tModuleListNewMapper.selectByExample(tModuleListCriteria);
        return tModuleLists;
    }

    @Override
    public List<TModuleListNew> getModuleListNewForSecond(Integer pid, List<Integer> tModuleListNew) {
        TModuleListNewCriteria tModuleListCriteria= new TModuleListNewCriteria();
        TModuleListNewCriteria.Criteria criteria=tModuleListCriteria.createCriteria();
        criteria.andPidEqualTo(pid);
        criteria.andIdIn(tModuleListNew);
        tModuleListCriteria.setOrderByClause("id asc");
        List<TModuleListNew> tModuleLists=tModuleListNewMapper.selectByExample(tModuleListCriteria);
        return tModuleLists;
    }
}
