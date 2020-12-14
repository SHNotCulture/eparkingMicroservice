package com.eparking.controller.work;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description: TCompanyAreaController类
* @author 谢轩然
* @date 2020/04/23 17:28
*/
@RestController
@RequestMapping("/tCompanyArea")
public class TCompanyAreaController {

/*    private  static final Logger logger= LoggerFactory.getLogger(TCompanyAreaController.class);

    @Autowired
    private TCompanyAreaService tCompanyAreaService;

    *//**
    * 查询TCompanyArea信息
    * @paramtCompanyArea
    * @return
    *//*
    @PostMapping(value = "/getTCompanyArea")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyArea")
    public ActionRsp getTCompanyArea(@RequestBody TCompanyArea tCompanyArea, HttpServletRequest request){
    return ActionRspUtil.Success(tCompanyAreaService.getTCompanyArea(tCompanyArea));
    }

    *//**
    * 查询TCompanyArea信息(分页)
    * @paramtCompanyArea
    * @return
    *//*
    @PostMapping(value = "/getTCompanyAreabyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyArea(分页)")
    public ControllerRsp getTCompanyAreabyPage(@RequestBody TCompanyArea tCompanyArea, HttpServletRequest request, Integer page, Integer limit){
    return ControllerRspUtil.Success(tCompanyAreaService.getTCompanyAreabyPage(tCompanyArea,page,limit));
    }

    *//**
    * 更新TCompanyArea信息
    * @paramtCompanyArea
    * @return
    *//*
    @PostMapping(value = "/updateTCompanyArea")
    @HttpLog(operationType = "1",modularTypeName = "更新TCompanyArea信息")
    public ActionRsp UpdateTCompanyArea(@RequestBody TCompanyArea tCompanyArea,HttpServletRequest request)
    {
        return ActionRspUtil.Success(tCompanyAreaService. UpdateTCompanyArea(tCompanyArea));
    }

    *//**
    * 删除TCompanyArea信息
    * @param tCompanyArea
    * @return
    *//*
    @PostMapping(value = "/deleteTCompanyArea")
    @HttpLog(operationType = "1",modularTypeName = "删除TCompanyArea信息")
    public ActionRsp DeleteTCompanyArea(@RequestBody TCompanyArea tCompanyArea){
    return ActionRspUtil.Success(tCompanyAreaService.DeleteTCompanyArea( tCompanyArea));
    }*/
}