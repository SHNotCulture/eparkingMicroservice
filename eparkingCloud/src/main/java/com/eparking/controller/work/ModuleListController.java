package com.eparking.controller.work;


import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TModuleListNew;
import com.eparking.service.ModuleListService;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:082018-8-20
 * @Modified By:
 */
@RestController
@RequestMapping(value = "moduleList")
public class ModuleListController {
    private  static final Logger logger= LoggerFactory.getLogger(ModuleListController.class);

    @Autowired
    private ModuleListService moduleListService;

    /**
     * 查询全部页面信息Ztree
     * @return
     */
    @PostMapping(value = "/getModuleListForZtree")
    @HttpLog(operationType = "0",modularTypeName = "查询全部页面信息Ztree")
    public ControllerRsp getModuleListForZtree(TModuleListNew tModuleList) throws Exception {
        return ControllerRspUtil.Success(moduleListService.getModuleListForZtree(tModuleList));
    }

}
