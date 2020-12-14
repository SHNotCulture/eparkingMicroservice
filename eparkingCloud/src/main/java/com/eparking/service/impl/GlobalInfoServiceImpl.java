package com.eparking.service.impl;

import com.common.entity.eparkingCloud.GlobalInfo;
import com.common.entity.eparkingCloud.TGlobalInfo;
import com.common.entity.eparkingCloud.TGlobalInfoCriteria;
import com.common.entity.eparkingCloud.YuncsisResult;
import com.eparking.insideService.TGlobalInsideService;
import com.eparking.service.GlobalInfoService;
import com.eparking.service.YuncsisApiService;
import com.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalInfoServiceImpl implements GlobalInfoService {
    private static final Logger logger = LoggerFactory.getLogger(GlobalInfoServiceImpl.class);
    @Autowired
    private YuncsisApiService yuncsisApiService;
    @Autowired
    private TGlobalInsideService tGlobalInsideService;
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





    @Override
    public GlobalInfo getGlobalInfo(TGlobalInfo tGlobalInfo) {
//        List<TGlobalInfo> tGlobalInfoLists = tGlobalInfoMapper.selectByExample(setCriteria(tGlobalInfo));
        List<TGlobalInfo> tGlobalInfoLists = tGlobalInsideService.getTGlobalInfo(tGlobalInfo);
        GlobalInfo globalInfoNew = new GlobalInfo();
        Field[] field = globalInfoNew.getClass().getDeclaredFields();
//        System.out.println("list: "+tGlobalInfoLists.size());
        try{
            for(int listIndex=0;listIndex<tGlobalInfoLists.size();listIndex++){
                for(int fieldIndex=0;fieldIndex<field.length;fieldIndex=fieldIndex+3){
                    field[fieldIndex].setAccessible(true);
                    field[fieldIndex + 1].setAccessible(true);
                    field[fieldIndex + 2].setAccessible(true);
                    //判断list的param_context的值和field的Context值是否一致，是的话进行赋值
                    if(tGlobalInfoLists.get(listIndex).getParamContext().equals(field[fieldIndex].get(globalInfoNew).toString())){
          /*              System.out.println("listContext: "+tGlobalInfoLists.get(listIndex).getParamContext());
                        System.out.println("globalInfoContext: "+field[fieldIndex].get(globalInfoNew));
                        System.out.println(true);*/
                        field[fieldIndex].set(globalInfoNew,tGlobalInfoLists.get(listIndex).getParamContext());
                        if(field[fieldIndex+1].getType()==Integer.class){
                            field[fieldIndex+1].set(globalInfoNew,Integer.valueOf(tGlobalInfoLists.get(listIndex).getParamValue()));
                        }else if(field[fieldIndex+1].getType()==Double.class){
                            field[fieldIndex+1].set(globalInfoNew,Double.parseDouble(tGlobalInfoLists.get(listIndex).getParamValue()));
                        }else{
                            field[fieldIndex+1].set(globalInfoNew,tGlobalInfoLists.get(listIndex).getParamValue());
                        }
                        field[fieldIndex+2].set(globalInfoNew,tGlobalInfoLists.get(listIndex).getParamDesc());
                        break;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return globalInfoNew;
    }

    @Override
    public String updateGlobalInfo(GlobalInfo globalInfo) {
        String msg="";
        Field[] field = globalInfo.getClass().getDeclaredFields();
        Object context, value, desc;
        TGlobalInfo tGlobalInfoDel = new TGlobalInfo();
        tGlobalInfoDel.setParkId(globalInfo.getParkId());
        tGlobalInsideService.DeleteTGlobalInfo(tGlobalInfoDel);
//        tGlobalInfoMapper.deleteByExample(setCriteria(tGlobalInfoDel));
        try {
        for (int i = 0; i < field.length; i = i + 3) {
            field[i].setAccessible(true);
            field[i + 1].setAccessible(true);
            field[i + 2].setAccessible(true);
            context = field[i].get(globalInfo);
            value = field[i + 1].get(globalInfo);
            desc = field[i + 2].get(globalInfo);
            TGlobalInfo tGlobalInfo = new TGlobalInfo();
            tGlobalInfo.setParkId(globalInfo.getParkId());
            tGlobalInfo.setParamContext(context.toString());
            tGlobalInfo.setParamValue(value.toString());
            tGlobalInfo.setParamDesc(desc.toString());
//            tGlobalInfoMapper.insertSelective(tGlobalInfo);
            tGlobalInsideService.UpdateTGlobalInfo(tGlobalInfo);
        }
            msg="更新成功";
            YuncsisResult yuncsisResult = yuncsisApiService.updateParkParams(JsonUtil.json2Map(JsonUtil.beanToJson(globalInfo)),1);
//            logger.info(yuncsisResult.getSuccess());
    } catch (Exception e) {
        System.out.println(e.toString());
    }
/*        TGlobalInfo tGlobalInfo;
        List<TGlobalInfo> tGlobalInfoList = new ArrayList<>();

        JsonUtil.beanToJson(globalInfo);
        if (tGlobalInfo.getId()!=null){//编辑
*//*           tGlobalInfoMapper.updateByPrimaryKeySelective(tGlobalInfo);
            msg = "修改成功";*//*

            }else{
*//*            tGlobalInfoMapper.insertSelective(tGlobalInfo);
            msg = "新增成功";*//*
        }*/
        return msg;
    }

    public static void main(String[] args) {
        GlobalInfo globalInfoNew = new GlobalInfo();
        globalInfoNew.setParkId(114);
        Field[] field = globalInfoNew.getClass().getDeclaredFields();

//        Field context, value, desc;
        Object context, value, desc;
        List<TGlobalInfo> tGlobalInfoList = new ArrayList<>();
        try {
/*            field[0].setAccessible(true);
            field[1].setAccessible(true);
            field[2].setAccessible(true);
            context = field[0].get(globalInfoNew);
            value = field[1].get(globalInfoNew);
            desc= field[2].get(globalInfoNew);
            tGlobalInfo.setParamContext(context.toString());
            tGlobalInfo.setParamValue(value.toString());
            tGlobalInfo.setParamDesc(desc.toString());*/
            for (int i = 0; i < field.length; i = i + 3) {
                field[i].setAccessible(true);
                field[i + 1].setAccessible(true);
                field[i + 2].setAccessible(true);
                context = field[i].get(globalInfoNew);
                value = field[i + 1].get(globalInfoNew);
                desc = field[i + 2].get(globalInfoNew);
                TGlobalInfo tGlobalInfo = new TGlobalInfo();
                tGlobalInfo.setParkId(114);
                tGlobalInfo.setParamContext(context.toString());
                tGlobalInfo.setParamValue(value.toString());
                tGlobalInfo.setParamDesc(desc.toString());
                tGlobalInfoList.add(tGlobalInfo);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(JsonUtil.listToJson(tGlobalInfoList));
    }


}
