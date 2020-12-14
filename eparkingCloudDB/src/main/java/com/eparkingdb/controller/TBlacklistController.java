package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBlacklist;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TBlacklistService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TBlacklistController类
* @author 谢轩然
* @date 2020/05/14 16:29
*/
@RestController
@RequestMapping("/tBlacklist")
public class TBlacklistController {

    private  static final Logger logger= LoggerFactory.getLogger(TBlacklistController.class);

    @Autowired
    private TBlacklistService tBlacklistService;

    /**
    * 查询TBlacklist信息
    * @paramtBlacklist
    * @return
    */
    @PostMapping(value = "/getTBlacklist")
    @HttpLog(operationType = "0",modularTypeName = "查询TBlacklist")
    public List<TBlacklist> getTBlacklist(@RequestBody TBlacklist tBlacklist){
    return tBlacklistService.getTBlacklist(tBlacklist);
    }

    /**
    * 查询TBlacklist信息(分页)
    * @paramtBlacklist
    * @return
    */
    @PostMapping(value = "/getTBlacklistbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TBlacklist(分页)")
    public PageBean<TBlacklist> getTBlacklistbyPage(@RequestBody TBlacklist tBlacklist, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tBlacklistService.getTBlacklistbyPage(tBlacklist,page,limit);
    }

    /**
    * 更新TBlacklist信息
    * @paramtBlacklist
    * @return
    */
    @PostMapping(value = "/updateTBlacklist")
    @HttpLog(operationType = "1",modularTypeName = "更新TBlacklist信息")
    public String UpdateTBlacklist(@RequestBody TBlacklist tBlacklist)
    {
        return tBlacklistService.UpdateTBlacklist(tBlacklist);
    }

    /**
    * 删除TBlacklist信息
    * @param tBlacklist
    * @return
    */
    @PostMapping(value = "/deleteTBlacklist")
    @HttpLog(operationType = "1",modularTypeName = "删除TBlacklist信息")
    public String DeleteTBlacklist(@RequestBody TBlacklist tBlacklist){
    return tBlacklistService.DeleteTBlacklist(tBlacklist);
    }

    @PostMapping(value = "/getTBlacklistByID")
    @HttpLog(operationType = "0",modularTypeName = "通过id查询TBlacklist信息")
    public TBlacklist getTBlacklistByID(@RequestParam("id") Integer id){
        return tBlacklistService.getTBlacklistByID(id);
    }
}