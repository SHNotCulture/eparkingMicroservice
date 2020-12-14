package com.eparking.service.impl;

import com.common.entity.eparkingCloud.LayuiMenu;
import com.common.entity.eparkingCloud.TModuleListNew;
import com.common.entity.eparkingCloud.ZtreeRsp;
import com.eparking.insideService.TModuleListNewInsideService;
import com.eparking.service.ModuleListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:282018-9-14
 * @Modified By:
 */
@Service
public class ModuleListServiceImpl implements ModuleListService {
    private  static final Logger logger= LoggerFactory.getLogger(ModuleListServiceImpl.class);

    @Autowired
    private TModuleListNewInsideService tModuleListNewInsideService;

    /**
     * 查询全部页面信息
     * @return
     */
    public  List<TModuleListNew> getModuleList(TModuleListNew tModuleListNew){
//        logger.info("tModuleListNew:"+tModuleListNew);
        //查询全部车场信息
/*        TModuleListNewCriteria tModuleListNewCriteria= new TModuleListNewCriteria();
        if(tModuleListNew!=null){
            TModuleListNewCriteria.Criteria criteria=tModuleListNewCriteria.createCriteria();

        }
        List<TModuleListNew> tModuleListNews=tModuleListNewMapper.selectByExample(tModuleListNewCriteria);*/
        List<TModuleListNew> tModuleListNews= tModuleListNewInsideService.getModuleList(tModuleListNew);
        return tModuleListNews;
    }
    /**
     * 查询全部页面信息Ztree
     * @return
     */
    public List<ZtreeRsp> getModuleListForZtree(TModuleListNew tModuleListNew) {
        List<ZtreeRsp> ztreeRspList=new ArrayList<ZtreeRsp>();
        try{
/*            TModuleListNewCriteria tModuleListCriteria= new TModuleListNewCriteria();
            List<Integer> tModuleList= StringUtil.stringList2List("12,13".split(","));

            if(tModuleListNew!=null){
                TModuleListNewCriteria.Criteria criteria=tModuleListCriteria.createCriteria();
                criteria.andIdNotIn(tModuleList);//物业用户不能拥有车场管理及物业管理页面
            }
            List<TModuleListNew> tModuleLists=tModuleListNewMapper.selectByExample(tModuleListCriteria);*/
            List<TModuleListNew> tModuleLists = tModuleListNewInsideService.getModuleListForZtree(tModuleListNew);
            for (TModuleListNew moduleList:tModuleLists) {
                ZtreeRsp ztreeRsp=new ZtreeRsp();
                ztreeRsp.setId(moduleList.getId());
                ztreeRsp.setpId(moduleList.getPid());
                ztreeRsp.setName(moduleList.getName());
                if(moduleList.getPid().equals(0)){
                    ztreeRsp.setOpen(true);
                }
                //BeanCopyUtil.CopyBeanToBean(moduleList,ztreeRsp);
                ztreeRspList.add(ztreeRsp);
            }
        }
        catch (Exception e){
            logger.info("获取Ztree信息异常,异常信息为："+e.toString());
        }

        return ztreeRspList;
    }

    /**
     * 根据用户权限查询菜单信息
     * @param tModuleListNew
     * @return
     * @throws Exception
     */
    public List<LayuiMenu> getModuleListNewForLayuiMenu(List<Integer> tModuleListNew) {
        List<LayuiMenu> LayuiMenuList=new ArrayList<LayuiMenu>();
        try{
            //设置查询条件,查询权限内的一级菜单信息
/*            TModuleListNewCriteria tModuleListCriteria= new TModuleListNewCriteria();
            if(tModuleListNew!=null){
                TModuleListNewCriteria.Criteria criteria=tModuleListCriteria.createCriteria();
                criteria.andIdIn(tModuleListNew);
                criteria.andPidEqualTo(0);
                tModuleListCriteria.setOrderByClause("id asc");
            }
            //得到一级菜单信息
            List<TModuleListNew> tModuleListsTop=tModuleListNewMapper.selectByExample(tModuleListCriteria);*/
//            logger.info("tModuleListCriteria: ",tModuleListCriteria);
            List<TModuleListNew> tModuleListsTop = tModuleListNewInsideService.getModuleListNewForLayuiMenu(tModuleListNew);
            //循环一级菜单
            for (TModuleListNew moduleList:tModuleListsTop) {
                if(moduleList.getPid().equals(0)){
                    LayuiMenu layuiMenu=new LayuiMenu();
                    layuiMenu.setID(moduleList.getId());
                    layuiMenu.setIconCls(moduleList.getIcon());
                    layuiMenu.setName(moduleList.getName());
                    layuiMenu.setXrc(moduleList.getUrl());
                    //查询二级菜单
                    List<TModuleListNew> tModuleListNewSecond=getModuleListNewForSecond(moduleList.getId(), tModuleListNew);
                    //如果下面存在二级菜单
                    if(!tModuleListNewSecond.equals(null))
                    {
                        List<LayuiMenu> LayuiMenuListSecond=new ArrayList<LayuiMenu>();
                        //循环二级菜单
                        for (TModuleListNew moduleListSecond:tModuleListNewSecond) {
                            LayuiMenu layuiMenuSecond=new LayuiMenu();
                            layuiMenuSecond.setID(moduleListSecond.getId());
                            layuiMenuSecond.setIconCls(moduleListSecond.getIcon());
                            layuiMenuSecond.setName(moduleListSecond.getName());
                            layuiMenuSecond.setXrc(moduleListSecond.getUrl());
                            LayuiMenuListSecond.add(layuiMenuSecond);
                        }
                        layuiMenu.setChildren(LayuiMenuListSecond);
                    }
                    LayuiMenuList.add(layuiMenu);
                }


            }
        }
        catch (Exception e)
        {
            logger.info("获取菜单信息异常,异常信息为："+e.toString());
        }

        return LayuiMenuList;
    }

    /**
     * 根据用户权限查询二级菜单
     * @param pid
     * @param tModuleListNew
     * @return
     */
    public  List<TModuleListNew> getModuleListNewForSecond(Integer pid,List<Integer> tModuleListNew){
/*        TModuleListNewCriteria tModuleListCriteria= new TModuleListNewCriteria();
        TModuleListNewCriteria.Criteria criteria=tModuleListCriteria.createCriteria();
        criteria.andPidEqualTo(pid);
        criteria.andIdIn(tModuleListNew);
        tModuleListCriteria.setOrderByClause("id asc");
        List<TModuleListNew> tModuleLists=tModuleListNewMapper.selectByExample(tModuleListCriteria);*/
        List<TModuleListNew> tModuleLists = tModuleListNewInsideService.getModuleListNewForSecond(pid,tModuleListNew);
        return tModuleLists;
    }
}
