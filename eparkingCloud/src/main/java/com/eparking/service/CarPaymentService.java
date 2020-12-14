package com.eparking.service;

import com.common.entity.eparkingCloud.TCarPayment;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CarPaymentService {
    List<TCarPayment> getCarPayment(TCarPayment tCarPayment, String beginData, String endData);
    PageBean<TCarPayment> getCarPaymentByPage(TCarPayment tCarPayment, String beginData, String endData, Integer page, Integer limit);
    void exportListMonthlyDetails(TCarPayment tCarPayment, String title, HttpServletResponse response, String beginData, String endData);
    String UpdateTCarPayment(TCarPayment tCarPayment);
}
