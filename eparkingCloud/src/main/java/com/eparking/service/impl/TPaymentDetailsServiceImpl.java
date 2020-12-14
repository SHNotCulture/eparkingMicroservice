package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TPaymentDetails;
import com.eparking.insideService.TPaymentDetailsInsideService;
import com.eparking.service.TPaymentDetailsService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TPaymentDetailsService接口实现类
* @author 谢轩然
* @date 2020/10/13 17:31
*/
@Service
public class TPaymentDetailsServiceImpl  implements TPaymentDetailsService {

    private  static final Logger logger= LoggerFactory.getLogger( TPaymentDetailsServiceImpl.class);
    @Autowired
    private TPaymentDetailsInsideService tPaymentDetailsInsideService;


    @Override
    public PageBean<TPaymentDetails> getTPaymentDetailsbyPage(TPaymentDetails tPaymentDetails, Integer page, Integer limit) {
        PageBean<TPaymentDetails> tPaymentDetailsPageBean = tPaymentDetailsInsideService.getTPaymentDetailsbyPage(tPaymentDetails, page, limit);
        return tPaymentDetailsPageBean;
    }

    /**
    * 查询tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    public List<TPaymentDetails> getTPaymentDetails(TPaymentDetails tPaymentDetails){
    List<TPaymentDetails>  tPaymentDetailss=tPaymentDetailsInsideService.getTPaymentDetails(tPaymentDetails);
    return tPaymentDetailss;
    }

    /**
    * 更新tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    public String UpdateTPaymentDetails(TPaymentDetails tPaymentDetails)
    {
        return tPaymentDetailsInsideService.UpdateTPaymentDetails(tPaymentDetails);
    }

    /**
    * 删除tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    public String DeleteTPaymentDetails(TPaymentDetails tPaymentDetails){
        return tPaymentDetailsInsideService.DeleteTPaymentDetails(tPaymentDetails);
    }

}
