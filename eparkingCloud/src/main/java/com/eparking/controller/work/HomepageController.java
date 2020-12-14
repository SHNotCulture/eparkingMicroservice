package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.eparking.insideService.CustomizeInsideService;
import com.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HomepageController
 * @Author jin
 * @Date 2018/10/16 22:06
 **/
@RestController
@RequestMapping(value = "homepage")
public class HomepageController {
/*    @Autowired
    private CustomizeMapper customizeMapper;*/
    @Autowired
    private CustomizeInsideService customizeInsideService;

    @PostMapping(value = "/changeMsg")
    @HttpLog(operationType = "1",modularTypeName = "纠正总车位数")
    public Integer doChangeMsg(HttpServletRequest request){
      /*  HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request1.getSession();*/
        String indexParkId = SessionUtil.getParkId().toString();
        String column = request.getParameter("id");
        String msg = request.getParameter("msg");
//        int data =customizeMapper.updateCompanyPark(indexParkId,column,Integer.valueOf(msg));
        Integer data = customizeInsideService.updateCompanyPark(indexParkId,column,Integer.valueOf(msg));
        return data;
    }
}
