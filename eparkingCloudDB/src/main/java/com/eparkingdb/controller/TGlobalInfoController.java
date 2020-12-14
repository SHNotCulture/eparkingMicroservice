package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TGlobalInfo;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TGlobalInfoService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢轩然
 * @Description: TGlobalInfoController类
 * @date 2020/04/09 15:04
 */
@RestController
@RequestMapping("/tGlobalInfo")
public class TGlobalInfoController {

    private static final Logger logger = LoggerFactory.getLogger(TGlobalInfoController.class);

    @Autowired
    private TGlobalInfoService tGlobalInfoService;

    /**
     * 查询TGlobalInfo信息
     *
     * @return
     * @paramtGlobalInfo
     */
    @PostMapping(value = "/getTGlobalInfo")
    @HttpLog(operationType = "0", modularTypeName = "查询TGlobalInfo")
    public List<TGlobalInfo> getTGlobalInfo(@RequestBody TGlobalInfo tGlobalInfo) {
        return tGlobalInfoService.getTGlobalInfo(tGlobalInfo);
    }

    /**
     * 查询TGlobalInfo信息(分页)
     *
     * @return
     * @paramtGlobalInfo
     */
    @PostMapping(value = "/getTGlobalInfobyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TGlobalInfo(分页)")
    public PageBean<TGlobalInfo> getTGlobalInfobyPage(@RequestBody TGlobalInfo tGlobalInfo, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tGlobalInfoService.getTGlobalInfobyPage(tGlobalInfo, page, limit);
    }

    /**
     * 更新TGlobalInfo信息
     *
     * @return
     * @paramtGlobalInfo
     */
    @PostMapping(value = "/updateTGlobalInfo")
    @HttpLog(operationType = "1", modularTypeName = "更新TGlobalInfo信息")
    public String UpdateTGlobalInfo(@RequestBody TGlobalInfo tGlobalInfo) {
        return tGlobalInfoService.UpdateTGlobalInfo(tGlobalInfo);
    }

    /**
     * 删除TGlobalInfo信息
     *
     * @param tGlobalInfo
     * @return
     */
    @PostMapping(value = "/deleteTGlobalInfo")
    @HttpLog(operationType = "1", modularTypeName = "删除TGlobalInfo信息")
    public String DeleteTGlobalInfo(@RequestBody TGlobalInfo tGlobalInfo) {
        return tGlobalInfoService.DeleteTGlobalInfo(tGlobalInfo);
    }


}