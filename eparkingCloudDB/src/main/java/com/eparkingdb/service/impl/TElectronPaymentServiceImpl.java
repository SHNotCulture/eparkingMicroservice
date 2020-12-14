package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TElectronPayment;
import com.common.entity.eparkingCloud.TElectronPaymentCriteria;
import com.eparkingdb.dao.TElectronPaymentMapper;
import com.eparkingdb.service.TElectronPaymentService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢轩然
 * @Description: TElectronPaymentService接口实现类
 * @date 2020/04/09 14:58
 */
@Service
public class TElectronPaymentServiceImpl implements TElectronPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(TElectronPaymentServiceImpl.class);
    @Autowired
    private TElectronPaymentMapper tElectronPaymentMapper;

    /**
     * 设置查询条件
     *
     * @param tElectronPayment
     * @return
     */
    private TElectronPaymentCriteria setCriteria(TElectronPayment tElectronPayment, String beginTime, String endTime) {
        TElectronPaymentCriteria tElectronPaymentCriteria = new TElectronPaymentCriteria();
        if (tElectronPayment != null) {
            TElectronPaymentCriteria.Criteria criteria = tElectronPaymentCriteria.createCriteria();
            if (tElectronPayment.getId() != null) {
                criteria.andIdEqualTo(tElectronPayment.getId());
            }
            if (tElectronPayment.getParkId() != null) {
                criteria.andParkIdEqualTo(tElectronPayment.getParkId());
            }
            if (tElectronPayment.getCheckFlag() != null) {
                criteria.andCheckFlagEqualTo(tElectronPayment.getCheckFlag());
            }
            if (beginTime != null && endTime != null && beginTime != "" && endTime != "") {
                criteria.andCreateDateBetween(beginTime, endTime);
            }
        }
        return tElectronPaymentCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tElectronPayment
     * @return
     */
    private Integer getCount(TElectronPayment tElectronPayment, String beginTime, String endTime) {
        Integer total = (int) tElectronPaymentMapper.countByExample(setCriteria(tElectronPayment, beginTime, endTime));
        return total;
    }

    /**
     * 查询tElectronPayment(分页)
     *
     * @param tElectronPayment
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TElectronPayment> getTElectronPaymentbyPage(TElectronPayment tElectronPayment, String beginTime, String endTime, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<TElectronPayment> tElectronPayments = getTElectronPayment(tElectronPayment, beginTime, endTime);
        Integer countNums = getCount(tElectronPayment, beginTime, endTime);
        PageBean<TElectronPayment> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tElectronPayments);
        return pageData;
    }

    /**
     * 查询tElectronPayment
     *
     * @param tElectronPayment
     * @return
     */
    public List<TElectronPayment> getTElectronPayment(TElectronPayment tElectronPayment, String beginTime, String endTime) {
        List<TElectronPayment> tElectronPayments = tElectronPaymentMapper.selectByExample(setCriteria(tElectronPayment, beginTime, endTime));
        return tElectronPayments;
    }

    /**
     * 更新tElectronPayment
     *
     * @param tElectronPayment
     * @return
     */
    public String UpdateTElectronPayment(TElectronPayment tElectronPayment) {
        String msg = "";
        try {
            if (tElectronPayment.getId() != null) {
                tElectronPaymentMapper.updateByPrimaryKeySelective(tElectronPayment);
                msg = "更新TElectronPayment成功";
            } else {
                tElectronPaymentMapper.insertSelective(tElectronPayment);
                msg = "新建TElectronPayment成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    /**
     * 删除tElectronPayment
     *
     * @param tElectronPayment
     * @return
     */
    public String DeleteTElectronPayment(TElectronPayment tElectronPayment) {
        String msg = "";
        if (tElectronPayment.getId() != null) {
            tElectronPaymentMapper.deleteByPrimaryKey(tElectronPayment.getId());
            msg = "删除TElectronPayment成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tElectronPayment
     *
     * @param id
     * @return
     */
    public TElectronPayment getTElectronPaymentByID(Integer id) {
        return tElectronPaymentMapper.selectByPrimaryKey(id);
    }
}
