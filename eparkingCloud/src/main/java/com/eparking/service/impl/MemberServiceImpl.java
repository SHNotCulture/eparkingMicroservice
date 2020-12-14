package com.eparking.service.impl;

import com.eparking.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberServiceImpl
 * @Author jin
 * @Date 2018/10/21 17:59
 **/
@Service
public class MemberServiceImpl implements MemberService {
/*    @Autowired
    private TParkCarMapper tParkCarMapper;
    @Autowired
    private TCarPaymentMapper tCarPaymentMapper;
    @Autowired
    private TMemberMapper tMemberMapper;
    @Autowired
    private TParkPortMapper tParkPortMapper;
    @Autowired
    private CustomizeMapper customizeMapper;
    @Autowired
    private TCarPayRuleMapper tCarPayRuleMapper;*/

   /* @Override
    public String recharge_byPc(TCarPayment carPayment, TParkCar parkCar) {
        String error = null;
        try{
            SimpleDateFormat sdf_ss = new SimpleDateFormat("yyyy-MM-dd");
            //criteria.andIdEqualTo(parkCar.getId());
            TParkCar parentParkCar = parkCar;
            if(StringUtil3.isNull(parkCar.getBeginDate()))
                //throw new ServiceException("请填写起始时间");
            if(StringUtil3.isNull(parkCar.getEndDate()))
                //throw new ServiceException("请填写过期时间");
            //月租车过期
            if(sdf_ss.parse(parentParkCar.getEndDate()).getTime()<sdf_ss.parse(StringUtil3.newTime).getTime()) {
                //续费日期大于原截至日期
                if( sdf_ss.parse(parentParkCar.getEndDate()).getTime()<sdf_ss.parse(parkCar.getBeginDate()).getTime()) {
                    parentParkCar.setBeginDate(parkCar.getBeginDate());
                }
            }
            parentParkCar.setEndDate(carPayment.getEndDate());
            parentParkCar.setIsSync((short)0);
            carPayment.setCarplate(parentParkCar.getCarPlate());
            carPayment.setOperType(1); //充值
            carPayment.setMemberId(parentParkCar.getMemberId());
            carPayment.setParkId(parentParkCar.getParkId());
            carPayment.setPayTime(sdf_ss.format(new Date()));
            carPayment.setCompanyId(parentParkCar.getCompanyId());
            carPayment.setPayType((short)1);
            carPayment.setBeginDate(parentParkCar.getBeginDate());

            if (parentParkCar.getIsOwner() == null){
                if(parentParkCar.getParkCode() == null || parentParkCar.getParkCode().equals(""))//1=独占车位2=公用车位，此值根据是否有车位编码确定
                    parentParkCar.setIsOwner((short)2);
                else
                    parentParkCar.setIsOwner((short)1);
            }
            tCarPaymentMapper.insert(carPayment);
            tParkCarMapper.updateByPrimaryKey(parentParkCar);
            error = "success";
            parentParkCar.setEndDate(carPayment.getEndDate());
            //同步数据
            TMember member = new TMember();
            syncMember(parentParkCar);
        }catch(Exception e){
            e.printStackTrace();
            error = e.getMessage();
            throw new RuntimeException(e.getMessage());
        }
        return error;
    }*/

    /*public synchronized void syncMember(TParkCar parkCar){
        MemberDown memberDown = new MemberDown();
        memberDown.setAddress(StringUtil3.enCode(parkCar.getAddress()));
        memberDown.setCar_plate(StringUtil3.enCode(parkCar.getCarPlate()));
        memberDown.setIsOwner(Integer.valueOf(parkCar.getIsOwner()));
        String portid = parkCar.getPortId();
        if (portid.substring(portid.length()-1,portid.length()).equals(",")){
            portid=portid.substring(0,portid.length()-1);
        }
        memberDown.setLane_rights(portid);
        memberDown.setMcard_begin_date(parkCar.getBeginDate());
        memberDown.setMcard_end_date(parkCar.getEndDate());
        memberDown.setCloud_park_car_id(parkCar.getId());
            memberDown.setCar_type(1);
            memberDown.setMember_id(0001);
            memberDown.setP_card_code("");
            memberDown.setP_card_id("");
            memberDown.setPhone(parkCar.getPhone());
            memberDown.setIsActivate(1);
            memberDown.setRealname(parkCar.getRealname());
        memberDown.setParkingSpace(parkCar.getParkingSpace());
        memberDown.setMcard_status(parkCar.getStatus() == null ? 1 : Integer.valueOf(parkCar.getStatus()));
        //删除状态
        if(parkCar.getIsDelet()==1){
            memberDown.setDelete("true");
        }else{
            memberDown.setDelete("false");
        }
        String ruleName = tCarPayRuleMapper.selectByPrimaryKey(parkCar.getPayRule()).getRuleName();
        memberDown.setRuleName(StringUtil3.enCode(ruleName)); //获得缴费规则，定制用
        memberDown.setBindingName(StringUtil3.enCode(parkCar.getBindingName()));
        ////统一多车位绑定
        memberDown.setParkingSpace(parkCar.getParkingSpace());//车位性质
        memberDown.setParkingNo(parkCar.getParkingNo());
        memberDown.setIsUse(parkCar.getIsUse());
        memberDown.setParkCode(parkCar.getParkCode());
        TCarPayRule tCarPayRule = tCarPayRuleMapper.selectByPrimaryKey(parkCar.getPayRule());
        memberDown.setDailyStartTime(tCarPayRule.getDailyStartTime());
        memberDown.setDailyEndTime(tCarPayRule.getDailyEndTime());
        customizeMapper.insetParktable(parkCar.getParkId(), parkCar.getId(),memberDown,"1");
    }*/
}
