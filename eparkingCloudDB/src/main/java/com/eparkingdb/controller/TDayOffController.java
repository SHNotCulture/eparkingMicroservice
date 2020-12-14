package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TDayOff;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TDayOffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tDayOff")
public class TDayOffController {
    private static final Logger logger = LoggerFactory.getLogger(TDayOffController.class);

    @Autowired
    private TDayOffService tDayOffService;

    @PostMapping(value = "/getTDayOff")
    @HttpLog(operationType = "0", modularTypeName = "查询TDayOff")
    public List<TDayOff> getTDayOff(@RequestBody TDayOff tDayOff) {
        return tDayOffService.getTDayOff(tDayOff);
    }

    @PostMapping(value = "/getTDayOffbyPage")
    @HttpLog(operationType = "0", modularTypeName = "查询TDayOff(分页)")
    public PageBean<TDayOff> getTDayOffbyPage(@RequestBody TDayOff tDayOff, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return tDayOffService.getTDayOffbyPage(tDayOff, page, limit);
    }

    @PostMapping(value = "/updateTDayOff")
    @HttpLog(operationType = "1", modularTypeName = "更新TDayOff信息")
    public String UpdateTDayOff(@RequestBody TDayOff tDayOff) {
        return tDayOffService.UpdateTDayOff(tDayOff);
    }

    @PostMapping(value = "/deleteTDayOff")
    @HttpLog(operationType = "1", modularTypeName = "删除TDayOff信息")
    public String DeleteTDayoff(@RequestBody TDayOff tDayOff) {
        return tDayOffService.DeleteTDayoff(tDayOff);
    }
}
