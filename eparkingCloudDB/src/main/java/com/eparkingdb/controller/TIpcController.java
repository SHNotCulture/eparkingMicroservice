package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TIpc;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TIpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tIpc")
public class TIpcController {
    private  static final Logger logger= LoggerFactory.getLogger(TIpcController.class);
    @Autowired
    private TIpcService tIpcService;
    /**
     * 查询TIpc信息
     * @paramtIpc
     * @return
     */
    @PostMapping(value = "/getTIpc")
    @HttpLog(operationType = "0",modularTypeName = "查询TIpc")
    public List<TIpc> getTIpc(@RequestBody TIpc tIpc){
        return tIpcService.getTIpc(tIpc);
    }

    /**
     * 查询TParkPort信息(分页)
     * @paramtIpc
     * @return
     */
    @PostMapping(value = "/getTIpcbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TIpc(分页)")
    public PageBean<TIpc> getTIpcbyPage(@RequestBody TIpc tIpc, Integer page, Integer limit){
        return tIpcService.getTIpcbyPage(tIpc,page,limit);
    }

    /**
     * 更新TParkPort信息
     * @paramtIpc
     * @return
     */
    @PostMapping(value = "/updateTIpc")
    @HttpLog(operationType = "1",modularTypeName = "更新TIpc信息")
    public String updateTIpc(@RequestBody TIpc tIpc)
    {
        return tIpcService.UpdateTIpc(tIpc);
    }

    /**
     * 删除TParkPort信息
     * @param tIpc
     * @return
     */
    @PostMapping(value = "/deleteTIpc")
    @HttpLog(operationType = "1",modularTypeName = "删除TIpc信息")
    public String deleteTIpc(@RequestBody TIpc tIpc){
        return tIpcService.DeleteTIpc(tIpc);
    }
}
