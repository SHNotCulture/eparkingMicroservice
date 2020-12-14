package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinePay;
import com.common.util.StringUtil;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:372018-9-14
 * @Modified By:
 */
@Service
public class BusineServiceImpl implements BusineService {
    private  static final Logger logger= LoggerFactory.getLogger(BusineServiceImpl.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TBusineInsideService tBusineInsideService;


    /**
     * 查询商户信息(分页)
     * @return
     */
    @Override
    public PageBean<TBusine> getBusinebyPage(TBusine tBusine, Integer page, Integer limit){
        return tBusineInsideService.getTBusinebyPage(tBusine, page, limit);
    }
    /**
     * 查询商户信息
     * @return
     */
    @Override
    public  List<TBusine> getBusine(TBusine tBusine){
        List<TBusine> tBusineList=tBusineInsideService.getTBusine(tBusine);
        return tBusineList;
    }

    /**
     * 更新商户信息
     * @param tBusine
     * @return
     */
    @Override
    public String UpdateBusine(TBusine tBusine)
    {
        return tBusineInsideService.UpdateTBusine(tBusine);
    }

    @Override
    public TBusine selectByPrimaryKey(Integer id) {
        return tBusineInsideService.selectById(id);
//        return tBusineMapper.selectByPrimaryKey(id);
    }

    @Override
    public TBusine selectByAccount(String account) {
        return tBusineInsideService.selectByAccount(account);
    }

    @Override
    public String monthlyRecharge(TBusine tBusineUpdate) {
        String msg="";
        //查询哪些商户具有自动充值的权限
/*        TBusine tBusine = new TBusine();
        tBusine.setParkId(114);
        tBusine.setIsAuto(1);
        List<TBusine> tBusineList = getBusine(tBusine);
        if(tBusineList.size()>0){
            for(TBusine tBusineUpdate:tBusineList){*/
                //判断余额是否小于充值金额
                if(tBusineUpdate.getBalance()<tBusineUpdate.getInitialAmount()){
                    TBusinePay tBusinePay = new TBusinePay();
                    //商户表余额补齐
                    tBusinePay.setNeedPay(tBusineUpdate.getInitialAmount()-tBusineUpdate.getBalance());
                    tBusinePay.setActualPay(0.0);
                    tBusinePay.setBusineName(tBusineUpdate.getBusineName());
                    String type ="3";
                    tBusinePay.setParkId(tBusineUpdate.getParkId());
                    tBusinePay.setBusineId(tBusineUpdate.getId());
                    tBusinePay.setCompanyId(tBusineUpdate.getCompanyId());
                    String rechargeMsg = tBusineInsideService.BusineRecharge(tBusinePay,type);
                    if(rechargeMsg.equals("月初充值成功")){
                        msg= StringUtil.stringJoin(msg,"商户自动充值成功");
                    }else{
                        msg=StringUtil.stringJoin(msg,"商户自动充值失败");
                    }
                }else{
                    msg=StringUtil.stringJoin(msg,"余额大于月初初始金额");
                }
/*            }
        }else{
            msg="未查找到月初可自动充值商户";
        }*/
        return msg;
    }

    /**
     * 商户充值
     * @param tBusinePay
     * @return
     */
    @Override
    public  String BusineRecharge(TBusinePay tBusinePay,String type){
        return tBusineInsideService.BusineRecharge(tBusinePay,type);
    }

    /**
     * 删除商户信息
     * @param tBusine
     * @return
     */
    @Override
    public String DeleteBusine(TBusine tBusine){
        return tBusineInsideService.DeleteTBusine(tBusine);
    }


}
