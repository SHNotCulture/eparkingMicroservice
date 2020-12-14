package com.eparking.aspect;

import com.common.annotation.HttpLog;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.TCarPayRuleInsideService;
import com.eparking.insideService.TParkCarInsideService;
import com.eparking.service.*;
import com.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author lishuhan
 * @Description:Http请求监听
 * @Date Create in 11:402018-5-9
 * @Modified By:
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Autowired
    private JournalService journalService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private CarPayRuleService carPayRuleService;
    @Autowired
    private TruckSpaceService truckSpaceService;
    @Autowired
    private RolePowerService rolePowerService;
    @Autowired
    private UserService userService;
    @Autowired
    private TParkCarInsideService tParkCarInsideService;
    @Autowired
    private TCarPayRuleInsideService tCarPayRuleInsideService;

    private String logMsg;

    @Pointcut("execution(public * com.eparking.controller.work.*.*(..))")
    private void mainAction() {
    }

    @Before("mainAction()")
    public void dobefore(JoinPoint joinPoint) throws ClassNotFoundException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

    }

    @After("mainAction()")
    public void doAfter(JoinPoint joinPoint) throws ClassNotFoundException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr().toString();
        Map<String, String> map = getLogInfo(joinPoint);
        String operationType = map.get("operationType");
        String logInfo = getLogInfo(joinPoint).get("logInfo");
        if (Integer.valueOf(operationType) != 0) {
            TJournal tJournal = new TJournal();
            TCompanyUser user = SessionUtil.getUser();
            tJournal.setCompanyUser(user.getUserName());
            tJournal.setCompanyUserId(user.getId());
            tJournal.setParkid(SessionUtil.getParkId());
            tJournal.setDate(DateUtil.getCurDateTime());
            tJournal.setIp(ip);
            tJournal.setOperatingType(Integer.valueOf(operationType));
            tJournal.setOperationContent(logInfo);
            tJournal.setStatus(1);
            journalService.UpdateTJournal(tJournal);
        }
        logger.info("访问结束");
    }

    @AfterReturning(returning = "object", pointcut = "mainAction()")
    public void doAfterReturn(Object object) {
        //返回数据
        if(object!=null)
        {
            logger.info("response={}", object.toString());
        }

    }

    private Map<String, String> getLogInfo(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        Object[] arguments = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String modulerType = "";
        String operationType = "";
        String logInfo = "";
        if (methods.length > 0) {
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        if (method.getAnnotation(HttpLog.class) != null) {
                            modulerType = method.getAnnotation(HttpLog.class).modularTypeName();
                            operationType = method.getAnnotation(HttpLog.class).operationType();
                            break;
                        }
                    }
                }
            }
        }
        if (modulerType != "" && operationType != "") {
            if (Integer.valueOf(operationType) != 0) {
                //logger.info("开始记录日志,modulerType="+modulerType+",operationType="+operationType);
                Object argOne = arguments[0];
                String chargeType = "";
                switch (modulerType) {
                    case "商户充值":
                        if (argOne instanceof TBusinePay) {
                            TBusinePay tBusinePay = (TBusinePay) argOne;
                            String arg_Second = arguments[1].toString();
                            if (arg_Second.equals(1)) {
                                logInfo = String.format("商户%s充值%s元", tBusinePay.getBusineName(), tBusinePay.getActualPay());
                            } else {
                                logInfo = String.format("商户%s冲正%s元", tBusinePay.getBusineName(), tBusinePay.getActualPay());
                            }
                        }
                        break;
                    case "更新商户信息":
                        if (argOne instanceof TBusine) {
                            TBusine tBusine = (TBusine) argOne;
                            if (tBusine.getId() != null) {
                                TBusine tBusinePar = busineService.selectByPrimaryKey(tBusine.getId());
                                String[] ignore = {"id", "companyId", "parkId"};
                                String msg = getMsgInfo(tBusinePar, tBusine, ignore);
                                logInfo = String.format("编辑商户信息:%s", msg);
                            }
                        }
                        break;
                    case "修改商户密码":
                        //String newpassword = arguments[3].toString();
                        logInfo = String.format("修改商户密码");
                        break;
                    case "重置商户密码":
                        //String newpassword = arguments[3].toString();
                        logInfo = String.format("重置商户密码为【123456】");
                        break;
                    case "删除商户信息":
                        //String newpassword = arguments[3].toString();
                        TBusine tBusine = busineService.selectByPrimaryKey(Integer.valueOf(argOne.toString()));
                        logInfo = String.format("删除商户:%s", tBusine.getBusineName());
                        break;
                    case "编辑黑名单":
                        if (argOne instanceof TBlacklist) {
                            TBlacklist tBlacklist = (TBlacklist) argOne;
                            if (tBlacklist.getId() != null) {
                                TBlacklist tBlacklistPar = blackListService.selectByPrimaryKey(tBlacklist.getId());
                                /*Map<String, List<Object>> compareResult = CompareFields.compareFields(tBlacklistPar,tBlacklist,new String[]{"id","createTime","parkId","createPerson"});
                                Set<String> keySet = compareResult.keySet();
                                StringBuffer msg = new StringBuffer();
                                for(String key : keySet){
                                    List<Object> list = compareResult.get(key);
                                    msg.append(key+"【"+list.get(1)+"】");
                                }*/
                                String[] ignore = {"id", "createTime", "parkId", "createPerson"};
                                String msg = getMsgInfo(tBlacklistPar, tBlacklist, ignore);
                                logInfo = String.format("编辑黑名单:%s", msg);
                            } else {
                                logInfo = String.format("新增黑名单:车牌【%s】启动时间【%s】截止时间【%s】", tBlacklist.getCarPlate(), tBlacklist.getBeginTime(), tBlacklist.getEndTime());
                            }
                        }
                        break;
                    case "删除黑名单":
                        if (argOne instanceof TBlacklist) {
                            TBlacklist tBlacklist = (TBlacklist) argOne;
                            logInfo = String.format("删除黑名单：车牌【%s】", tBlacklist.getCarPlate());
                        }
                        break;
                    case "更新登记规则":
                        if (argOne instanceof TCarPayRule) {
                            TCarPayRule tCarPayRule = (TCarPayRule) argOne;
                            if (tCarPayRule.getId() != null) {
                                TCarPayRule tCarPayRulePar = carPayRuleService.selectByPrimaryKey(tCarPayRule.getId());
                                String[] ignore = {"id", "parkId", "remark"};
                                String msg = getMsgInfo(tCarPayRulePar, tCarPayRule, ignore);
                                logInfo = String.format("更新登记规则:%s", msg);
                            } else {
                                logInfo = String.format("新增登记规则:【%s】", tCarPayRule.getRuleName());
                            }
                        }
                        break;
                    case "删除登记规则":
                        TCarPayRule tCarPayRule = carPayRuleService.selectByPrimaryKey(Integer.valueOf(argOne.toString()));
                        logInfo = String.format("删除登记规则:【%s】", tCarPayRule.getRuleName());
                        break;
                    case "更新车位信息":
                        if (argOne instanceof TTruckSpace) {
                            TTruckSpace tTruckSpace = (TTruckSpace) argOne;
                            if (tTruckSpace.getId() != null) {
                                TTruckSpace tTruckSpacePar = truckSpaceService.selectByPrimaryKey(tTruckSpace.getId());
                                String[] ignore = {"id", "parkId", "remark"};
                                String msg = getMsgInfo(tTruckSpacePar, tTruckSpace, ignore);
                                logInfo = String.format("更新车位规则:%s", msg);
                            } else {
                                logInfo = String.format("新增车位:【%s】,【%s】", tTruckSpace.getParkingState(), tTruckSpace.getParkCode());
                            }
                        }
                        break;
                    case "删除车位信息":
                        TTruckSpace tTruckSpace = truckSpaceService.selectByPrimaryKey(Integer.valueOf(argOne.toString()));
                        logInfo = String.format("删除车位信息:【%s】", tTruckSpace.getParkCode());
                        break;
                    case "更新角色信息":
                        if (argOne instanceof TRolePowerNew) {
                            TRolePowerNew tRolePowerNew = (TRolePowerNew) argOne;
                            if (tRolePowerNew.getId() != null) {
                                TRolePowerNew tRolePowerNewPar = rolePowerService.selectByPrimaryKey(tRolePowerNew.getId());
                                String[] ignore = {"id", "companyId", "moduleId", "userId"};
                                String msg = getMsgInfo(tRolePowerNewPar, tRolePowerNew, ignore);
                                logInfo = String.format("更新角色信息:%s", msg);
                            } else {
                                logInfo = String.format("新增角色:角色名称【%s】角色描述【%s】", tRolePowerNew.getRoleName(), tRolePowerNew.getRemark());
                            }
                        }
                        break;
                    //doAfter记录日志时已在数据库里删除了记录，取值会空指针报错
/*                    case "删除角色信息":
                        TRolePowerNew tRolePowerNew = rolePowerService.selectByPrimaryKey(Integer.valueOf(argOne.toString()));
                        logInfo = String.format("删除角色信息:角色名称【%s】角色描述【%s】", tRolePowerNew.getRoleName(), tRolePowerNew.getRemark());
                        break;*/
                    case "更新物业用户信息":
                        if (argOne instanceof TCompanyUser) {
                            TCompanyUser tCompanyUser = (TCompanyUser) argOne;
                            if (tCompanyUser.getId() != null) {
                                TCompanyUser tCompanyUserPar = userService.selectByPrimaryKey(tCompanyUser.getId());
                                String[] ignore = {"id", "companyId", "parkIds", "password", "remark", "roleId", "isAdmin", "entityType", "parentUser"};
                                String msg = getMsgInfo(tCompanyUserPar, tCompanyUser, ignore);
                                logInfo = String.format("更新物业用户信息:%s", msg);
                            } else {
                                logInfo = String.format("新增物业用户：账号【%s】角色【%s】姓名【%s】手机【%s】", tCompanyUser.getUserAccout(), tCompanyUser.getRoleId(), tCompanyUser.getUserName(), tCompanyUser.getPhone());
                            }
                        }
                        break;
                    case "修改用户密码":
                        Integer id = Integer.valueOf(arguments[1].toString());
                        TCompanyUser tCompanyUser = userService.selectByPrimaryKey(id);
                        logInfo = String.format("修改用户密码:账号【%s】修改密码", tCompanyUser.getUserAccout());
                        break;
                    case "重置物业用户密码":
                        TCompanyUser tCompanyUser1 = userService.selectByPrimaryKey(Integer.valueOf(argOne.toString()));
                        logInfo = String.format("重置物业用户【%s】密码", tCompanyUser1.getUserAccout());
                        break;
                        //doAfter记录日志时已在数据库里删除了记录，取值会空指针报错
/*                    case "删除物业用户信息":
                        TCompanyUser tCompanyUser2 = userService.selectByPrimaryKey(Integer.valueOf(argOne.toString()));
                        logInfo = String.format("删除物业用户【%s】", tCompanyUser2.getUserAccout());
                        break;*/
                    case "批量充值":
                        switch (Integer.valueOf(arguments[1].toString())) {
                            case 0:
                                chargeType = "按日";
                                break;
                            case 1:
                                chargeType = "按月";
                                break;
                            case 2:
                                chargeType = "按季度";
                                break;
                            case 3:
                                chargeType = "按年";
                                break;
                            default:
                                break;
                        }
                        String[] carIdList = arguments[0].toString().split(",");
                        List<String> carplateList = new ArrayList<>();
                        for (int i = 0; i < carIdList.length; i++) {
                            TParkCar tParkCar = new TParkCar();
                            tParkCar.setId(Integer.valueOf(carIdList[i]));
                            List<TParkCar> tParkCarList = tParkCarInsideService.getTParkCar(tParkCar,"","");
                            if (tParkCarList.size() > 0) {
                                for (TParkCar tParkCarOne : tParkCarList) {
                                    carplateList.add(tParkCarOne.getCarPlate());
                                }
                            }
                        }
                        logInfo = String.format("登记车批量充值:车牌【%s】充值方式【%s】数量【%s】", StringUtils.join(carplateList.toArray(), ","), chargeType, Integer.valueOf(arguments[2].toString()));
                        break;
                    case "重置通道权限":
/*                        for(int i = 0;i<arguments.length;i++){
                            logger.info("arg"+i+": "+arguments[i].toString());
                        }*/

                        break;
                    case "更新登记车信息":
                        if (argOne instanceof TParkCar) {
                            TParkCar tParkCar = (TParkCar) argOne;
                            if (tParkCar.getId() != null) {
                                logInfo = String.format("更新登记车辆:【%s】", tParkCar.getCarPlate());
                            } else {
                                logInfo = String.format("新增登记车辆:【%s】", tParkCar.getCarPlate());
                            }
                        }
                        break;
                    case "导入登记车信息":
                          logInfo = String.format("导入登记车信息");
                        break;
                    case "导入车位信息":
                          logInfo = String.format("导入车位信息");
                        break;
                    case "登记车钱包充值":
                        String carplate = "";
                        TParkCar tParkCar = new TParkCar();
                        tParkCar.setId((int) argOne);
                        List<TParkCar> tParkCarList = tParkCarInsideService.getTParkCar(tParkCar,"","");
                        if (tParkCarList.size() > 0) {
                            carplate = tParkCarList.get(0).getCarPlate();
                        }
                        logInfo = String.format("登记车钱包充值:车牌【%s】应收【%s】实收【%s】备注【%s】", carplate, arguments[1], arguments[2], arguments[3]);
                        break;
                    case "登记车充值":
                        if (argOne instanceof TCarPayment) {
                            TCarPayment tCarPayment = (TCarPayment) argOne;
                            TCarPayRule tCarPayRule1 = new TCarPayRule();
                            tCarPayRule1.setId(tCarPayment.getPayRule());
                            List<TCarPayRule> tCarPayRuleList = tCarPayRuleInsideService.getTCarPayRule(tCarPayRule1);
                            switch (tCarPayment.getPayStandard()) {
                                case 0:
                                    chargeType = "按日";
                                    break;
                                case 1:
                                    chargeType = "按月";
                                    break;
                                case 2:
                                    chargeType = "按季度";
                                    break;
                                case 3:
                                    chargeType = "按年";
                                    break;
                                default:
                                    break;
                            }
                            logger.info("tCarPayment: " + JsonUtil.beanToJson(tCarPayment));
                            logInfo = String.format("登记车充值:车牌【%s】规则【%s】充值方式【%s】数量【%s】起始日期【%s】截止日期【%s】应收【%s】实收【%s】备注【%s】", tCarPayment.getCarplate(), tCarPayRuleList.get(0).getRuleName(), chargeType, tCarPayment.getPayCount(), tCarPayment.getBeginDate(), tCarPayment.getEndDate(), tCarPayment.getNeedPay(), tCarPayment.getActualPay(), tCarPayment.getRemark());
                        }
                        break;
                    case "登记车冲正":
                        if (argOne instanceof TParkCar) {
                            TParkCar tParkCar1 = (TParkCar) argOne;
                            if ((short) arguments[2] == 1) {
                                chargeType = "现金";
                            } else if ((short) arguments[2] == 2) {
                                chargeType = "电子";
                            }
                            logInfo = String.format("登记车冲正:车牌【%s】开始日期【%s】截止日期【%s】缴费方式【%s】冲正金额【%s】备注【%s】", tParkCar1.getCarPlate(), tParkCar1.getBeginDate(), tParkCar1.getEndDate(), chargeType, arguments[1], arguments[3]);
                        }
                        break;
                    case "登记车删除":
                        if(arguments[1] instanceof TParkCar){
                            TParkCar tParkCar1= (TParkCar)arguments[1];
//                            logger.info(JsonUtil.beanToJson(tParkCar1));
                            logInfo = String.format("登记车删除:车牌【%s】", tParkCar1.getCarPlate());
                        }
                        break;
                    case "导出商户充值记录":
                        logInfo = String.format("导出商户充值记录");
                        break;
                    case "导出当班支付对账信息":
                        logInfo = String.format("导出当班支付对账信息");
                        break;
                    case "导出车主钱包对账信息":
                        logInfo = String.format("导出车主钱包对账信息");
                        break;
                    case "导出财务报表信息":
                        logInfo = String.format("导出财务报表信息");
                        break;
                    case "导出在场车辆信息":
                        logInfo = String.format("导出在场车辆信息");
                        break;
                    case "导出停车记录信息":
                        logInfo = String.format("导出停车记录信息");
                        break;
                    case "导出缴费记录信息":
                        logInfo = String.format("导出缴费记录信息");
                        break;
                    case "导出分类统计报表":
                        logInfo = String.format("导出分类统计报表");
                        break;
                    case "导出登记车信息":
                        logInfo = String.format("导出登记车信息");
                        break;
                    case "导出车主车位信息":
                        logInfo = String.format("导出车主车位信息");
                        break;
                    case "导出黑名单信息":
                        logInfo = String.format("导出黑名单信息");
                        break;
                    case "导出钱包充值记录":
                        logInfo = String.format("导出钱包充值记录");
                        break;
                    case "购买电子券":
                        logInfo = String.format("购买电子券");
                        break;
                    case "回购电子券":
                        logInfo = String.format("回购电子券");
                        break;
                    case "修改批次权限":
                        logInfo = String.format("修改批次权限");
                        break;
                    case "更新车场信息":
                        logInfo = String.format("更新车场信息");
                        break;
                    case "删除车场信息":
                        logInfo = String.format("删除车场信息");
                        break;
                    case "更新登记车规则":
                        logInfo = String.format("更新登记车规则");
                        break;
                    case "删除登记车规则":
                        logInfo = String.format("删除登记车规则");
                        break;
                    case "更新临停缴费信息":
                        logInfo = String.format("更新临停缴费信息");
                        break;
                    case "删除临停缴费信息":
                        logInfo = String.format("删除临停缴费信息");
                        break;
                    case "更新节假日信息":
                        logInfo = String.format("更新节假日信息");
                        break;
                    case "删除节假日信息":
                        logInfo = String.format("删除节假日信息");
                        break;
                    case "更新调休日信息":
                        logInfo = String.format("更新调休日信息");
                        break;
                    case "删除调休日信息":
                        logInfo = String.format("删除调休日信息");
                        break;
                    case "更新物业信息":
                        logInfo = String.format("更新物业信息");
                        break;
                    case "删除物业信息":
                        logInfo = String.format("删除物业信息");
                        break;
                    case "生成优惠二维码列表":
                        logInfo = String.format("生成优惠二维码列表");
                        break;
                    case "编辑优惠二维码":
                        logInfo = String.format("编辑优惠二维码");
                        break;
                    case "删除优惠二维码":
                        logInfo = String.format("删除优惠二维码");
                        break;
                    case "新增电子券":
                        logInfo = String.format("新增电子券");
                        break;
                    case "删除电子券":
                        logInfo = String.format("删除电子券");
                        break;
                    case "编辑参数配置":
                        logInfo = String.format("编辑参数配置");
                        break;
                    case "纠正总车位数":
                        logInfo = String.format("纠正总车位数");
                        break;
                    case "商户下发优惠":
                        logInfo = String.format("商户下发优惠");
                        break;
                    case "编辑登记绑定车":
                        logInfo = String.format("编辑登记绑定车");
                        break;
                    case "删除登记绑定车":
                        logInfo = String.format("删除登记绑定车");
                        break;
                    case "登记车预缴":
                        logInfo = String.format("登记车预缴");
                        break;
                    case "编辑车场通道":
                        logInfo = String.format("编辑车场通道");
                        break;
                    case "删除车场通道":
                        logInfo = String.format("删除车场通道");
                        break;
                    case "分类报表统计":
                        logInfo = String.format("分类报表统计");
                        break;
                    default:
                        break;
                }
            }
        }
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("logInfo", logInfo);
        map.put("operationType", operationType);
        return map;
    }

    public String getMsgInfo(Object obPar, Object ob, String[] ignore) {
        Map<String, List<Object>> compareResult = CompareFields.compareFields(obPar, ob, ignore);
        Set<String> keySet = compareResult.keySet();
        StringBuffer msg = new StringBuffer();
        for (String key : keySet) {
            List<Object> list = compareResult.get(key);
            msg.append(key + "【" + list.get(1) + "】");
        }
        return msg.toString();
    }
}
