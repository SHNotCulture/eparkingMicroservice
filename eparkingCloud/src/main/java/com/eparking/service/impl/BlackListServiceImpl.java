package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.ExcelBlacklist;
import com.common.entity.eparkingCloud.TBlacklist;
import com.eparking.insideService.TBlacklistInsideService;
import com.eparking.service.BlackListService;
import com.eparking.service.YuncsisApiService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BlackListServiceImpl
 * @Author jin
 * @Date 2018/10/22 17:12
 **/
@Service
public class BlackListServiceImpl implements BlackListService {
    private static final Logger logger = LoggerFactory.getLogger(BlackListServiceImpl.class);
    @Autowired
    private YuncsisApiService yuncsisApiService;
    @Autowired
    private TBlacklistInsideService tBlacklistInsideService;


    /**
     * 查询黑名单信息(分页)
     *
     * @param tBlacklist
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TBlacklist> getBlackListbyPage(TBlacklist tBlacklist, Integer page, Integer limit) {
        return tBlacklistInsideService.getTBlacklistbyPage(tBlacklist, page, limit);
    }

    @Override
    public List<TBlacklist> getBlackList(TBlacklist tBlacklist) {
        List<TBlacklist> tBlacklists = tBlacklistInsideService.getTBlacklist(tBlacklist);
        return tBlacklists;
    }

    @Override
    public String updateBlackList(TBlacklist tBlacklist) {

        String msg = tBlacklistInsideService.UpdateTBlacklist(tBlacklist);
/*        if(msg.equals("修改成功") || msg.equals("成功添加")){
            Map map = new HashMap();
            map.put("parkId",tBlacklist.getParkId());
            map.put("carPlate",tBlacklist.getCarPlate());
            map.put("id",tBlacklist.getId());
            map.put("delete","false");
            map.put("beginTime",tBlacklist.getBeginTime());
            map.put("endTime",tBlacklist.getEndTime());
            map.put("createPerson",tBlacklist.getCreatePerson());
            YuncsisResult yuncsisResult = yuncsisApiService.updateblackcar(map);
            logger.info(yuncsisResult.getSuccess());
        }*/
//logger.info(msg);
        return msg;
    }

    @Override
    public String deleteBlackList(TBlacklist tBlacklist) {
        tBlacklistInsideService.DeleteTBlacklist(tBlacklist);
        return "删除成功";
    }

    @Override
    public void exportList(TBlacklist tBlacklist, String title, HttpServletResponse response) {
        List<TBlacklist> tBlacklists = getBlackList(tBlacklist);
        List<ExcelBlacklist> excelBlacklists = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelBlacklist防止数组越界
        if (tBlacklists.size() <= 0) {
            ExcelBlacklist excelBlacklist = new ExcelBlacklist();
            excelBlacklists.add(excelBlacklist);
        } else {
            for (TBlacklist tBlacklistNew : tBlacklists) {
                ExcelBlacklist excelBlacklist = new ExcelBlacklist();
                try {
                    excelBlacklist = (ExcelBlacklist) BeanCopyUtil.CopyBeanToBean(tBlacklistNew, excelBlacklist);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelBlacklists.add(excelBlacklist);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelBlacklists);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public TBlacklist selectByPrimaryKey(Integer id) {
        return tBlacklistInsideService.getTBlacklistByID(id);
    }


}
