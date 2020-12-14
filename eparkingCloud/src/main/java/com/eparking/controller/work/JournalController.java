package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.entity.eparkingCloud.TJournal;
import com.eparking.insideService.TJournalInsideService;
import com.eparking.service.JournalService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:182018-10-19
 * @Modified By:
 */
@RestController
@RequestMapping(value = "journal")
public class JournalController {
    private  static final Logger logger= LoggerFactory.getLogger(JournalController.class);
    @Autowired
    private JournalService journalService;
    @Autowired
    private TJournalInsideService tJournalInsideService;
    public void base(String page, String limit)
    {
        logger.info("page:"+page);
        logger.info("limit:"+limit);
    }

    /**
     * 查询操作日志信息
     * @return
     */
    @PostMapping(value = "/getJournal")
    @HttpLog(operationType = "0",modularTypeName = "查询操作日志信息")
    public ControllerRsp getJournal (Integer companyUserId,Integer operatingType,String beginTime,String endTime,Integer page, Integer limit,HttpServletRequest request)  {

        TCompanyUser user= SessionUtil.getUser();
     /*   tJournal.setCompanyId(user.getCompanyId());*/
        TJournal tJournal=new TJournal();
        tJournal.setParkid(SessionUtil.getParkId());
        tJournal.setCompanyUserId(companyUserId);
        tJournal.setOperatingType(operatingType);
        if(beginTime==null){
            beginTime="";
        }
        if(endTime==null){
            endTime="";
        }
        return ControllerRspUtil.Success(journalService.getTJournalByPage(tJournal,beginTime,endTime,page,limit));
//        return tJournalInsideService.getTJournalbyPage(tJournal,beginTime,endTime,page,limit);
    }
}
