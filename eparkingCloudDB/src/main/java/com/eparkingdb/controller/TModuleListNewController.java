package com.eparkingdb.controller;

import com.common.entity.eparkingCloud.TModuleListNew;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TModuleListNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tModuleListNew")
public class TModuleListNewController {
    private static final Logger logger = LoggerFactory.getLogger(TModuleListNewController.class);

    @Autowired
    private TModuleListNewService tModuleListNewService;

    @PostMapping(value = "/getModuleListNewForLayuiMenu")
    @HttpLog(operationType = "0", modularTypeName = "根据用户权限查询菜单信息")
    public List<TModuleListNew> getModuleListNewForLayuiMenu(@RequestParam("tModuleListNew") List<Integer> tModuleListNew) {
        return tModuleListNewService.getModuleListNewForLayuiMenu(tModuleListNew);
    }

    @PostMapping(value = "/getModuleList")
    @HttpLog(operationType = "0", modularTypeName = "查询全部页面信息")
    public List<TModuleListNew> getModuleList(@RequestBody TModuleListNew tModuleListNew) {
        return tModuleListNewService.getModuleList(tModuleListNew);
    }

    @PostMapping(value = "/getModuleListForZtree")
    @HttpLog(operationType = "0", modularTypeName = "查询全部页面信息Ztree")
    public List<TModuleListNew> getModuleListForZtree(@RequestBody TModuleListNew tModuleListNew) {
        return tModuleListNewService.getModuleListForZtree(tModuleListNew);
    }

    @PostMapping(value = "/getModuleListNewForSecond")
    @HttpLog(operationType = "0", modularTypeName = "根据用户权限查询二级菜单")
    public List<TModuleListNew> getModuleListNewForSecond(@RequestParam("pid") Integer pid,@RequestParam("tModuleListNew") List<Integer> tModuleListNew) {
        return tModuleListNewService.getModuleListNewForSecond(pid,tModuleListNew);
    }
}
