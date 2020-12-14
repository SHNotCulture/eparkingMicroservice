package com.eparking.controller.view;

import com.common.annotation.HttpLog;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.TBusineInsideService;
import com.eparking.insideService.TCompanyUserInsideService;
import com.common.util.*;
import com.common.util.RequestUtils;
import com.eparking.service.*;
import com.eparking.writeLock.CarParkLock;
import com.eparking.writeLock.CarPayRuleLock;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:统一页面跳转控制器
 * @Date Create in 17:282018-7-4
 * @Modified By:
 */
@Controller
@RequestMapping("view")
public class ViewController {
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
    @Autowired
    private CarParkService carParkService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PresentCarService presentCarService;
    @Autowired
    private ParkingRecordService parkingRecordService;
    @Autowired
    private MonthlyCarService monthlyCarService;

    @Autowired
    private ModuleListService moduleListService;
    @Autowired
    private UserService userService;
    @Autowired
    private RolePowerService rolePowerService;

    @Autowired
    private  HomepageService homepageService;
    @Autowired
    private TCompanyUserInsideService tCompanyUserInsideService;
    @Autowired
    private TBusineInsideService tBusineInsideService;
    @Autowired
    private CarPayRuleLock carPayRuleLock;
    @Autowired
    private CarParkLock carParkLock;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/getIndex")
    public String index(HttpServletRequest request){
        String username = "未登录";
        String currentLoginUser = RequestUtils.currentLoginUser();

        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser)) {
            TCompanyUser user=new TCompanyUser();//用户信息
            List<TCompany> tCompanyList=new ArrayList<>();//用户车场信息
            List<TCompanyPark> tCompanyParkList=new ArrayList<>();//用户公司信息
            List<LayuiMenu> layuiMenuList=new ArrayList<>();//用户页面权限信息
            user =SessionUtil.getUser();
            /*List<Integer> ids= StringUtil.stringList2List(user.getParkIds().split(","));
            tCompanyParkList=carParkService.getCarParkbyIDIn(ids);*/



            if(!user.getUserAccout().equals("admin"))
            {

                TCompany tCompany=new TCompany();
                tCompany.setId(user.getCompanyId());
                tCompanyList=companyService.getCompany(tCompany);
                request.getSession().setAttribute(Common.Company,  JsonUtil.beanToJson(tCompanyList.get(0)));//将当前用户所属物业公司信息存入session
                TRolePowerNew tRolePower=new TRolePowerNew();
                tRolePower.setId(user.getRoleId());
                List<Integer> roleids= StringUtil.stringList2List(rolePowerService.getRolePowerById(tRolePower).get(0).getModuleId().split(","));
                for (Integer i=0;i< roleids.size();i++){
                    if(roleids.get(i)==12||roleids.get(i)==13){
                        roleids.remove(roleids.get(i));
                        i--;
                    }
                }
                TCompanyUser tCompanyUser = new TCompanyUser();
                tCompanyUser.setUserAccout(SessionUtil.getUser().getUserAccout());
                List<TCompanyUser> tCompanyUserList= tCompanyUserInsideService.getTCompanyUser(tCompanyUser);
                //判断是否是物业后台
                if(tCompanyUserList.size()>0){
                    TCompanyUser userSel=tCompanyUserList.get(0);
                    if(userSel.getIsAdmin().equals("0")){
                        TCompanyPark companyPark=new TCompanyPark();
                        List<TCompanyPark> parkList = carParkService.getCarPark(companyPark);
                        String ParkIds="";
                        if(parkList!=null)
                        {
                            for (TCompanyPark park:parkList) {
                                ParkIds+=park.getId()+",";
                            }
                        }
                        user.setParkIds(ParkIds);//超级管理员拥有所有的车场权限*//*
                    }
                    //物业一级用户
                    if(userSel.getEntityType()>=1&&userSel.getEntityType()<3){
                        TBusine tBusine = new TBusine();
                        tBusine.setAccount(tCompanyUserList.get(0).getUserAccout());
                        List<TBusine> tBusineList = tBusineInsideService.getTBusine(tBusine);
                        if(tBusineList.size()>0){
                            if(tBusineList.get(0).getDiscountType()!=3){
                                roleids.remove(new Integer(35));
                                roleids.remove(new Integer(37));
                            }
                        }
                        //查询该物业下所有的车场信息
                        if(userSel.getIsAdmin().equals("1") && userSel.getEntityType()==1){
                            TCompanyPark companyPark=new TCompanyPark();
                            companyPark.setCompanyId(userSel.getCompanyId());
                            String ParkIds="";
                            List<TCompanyPark> tCompanypark=carParkService.getCarPark(companyPark);
                            if(tCompanypark!=null)
                            {
                                for (TCompanyPark park:tCompanypark) {
                                    if(ParkIds.equals("")){
                                        ParkIds=park.getId().toString();
                                    }else{
                                        ParkIds=ParkIds+","+park.getId().toString();
                                    }
                                }
                            }
                            user.setParkIds(ParkIds);//管理员拥有该物业下所有的车场权限*//*
                        }
                    }
                    /*//物业二级用户
                    if(tCompanyUserList.get(0).getEntityType()==2){
                        TBusine tBusine = new TBusine();
                        tBusine.setAccount(tCompanyUserList.get(0).getUserAccout());
                        List<TBusine> tBusineList = tBusineInsideService.getTBusine(tBusine);
                        if(tBusineList.size()>0){
                            if(tBusineList.get(0).getDiscountType()!=3){
                                roleids.remove(new Integer(35));
                                roleids.remove(new Integer(37));
                            }
                        }
                    }*/
                    SessionUtil.setUser(user);
                }
                logger.info(JsonUtil.listToJson(roleids));
                layuiMenuList=moduleListService.getModuleListNewForLayuiMenu(roleids);

            }
            else
            {
                TCompany tCompany=new TCompany();
                tCompany.setId(user.getCompanyId());
                tCompany.setCompanyName("系统管理员");
                tCompanyList.add(tCompany);
                //得到全部页面权限
                TModuleListNew tModuleListNew=new TModuleListNew();
                List<TModuleListNew> tModuleListNews=moduleListService.getModuleList(tModuleListNew);
                String moduleId="";
                if(tModuleListNews.size()!=0){
                    for (TModuleListNew t:tModuleListNews) {
                            moduleId+=t.getId()+",";
                    }
                }
                List<Integer> roleids= StringUtil.stringList2List(moduleId.substring(0,moduleId.length()-1).split(","));
                layuiMenuList=moduleListService.getModuleListNewForLayuiMenu(roleids);
            }
            tCompanyParkList=carParkService.getCarParkbyIDIn(user.getParkIds());
            if(tCompanyParkList.size()!=0) {
                request.getSession().setAttribute(Common.ParkId, tCompanyParkList.get(0).getId());//将当前选择车场信息ID存入session
                request.getSession().setAttribute(Common.Park,  JsonUtil.beanToJson(tCompanyParkList.get(0)));//将当前选择车场信息存入session
            }else{
                request.getSession().setAttribute(Common.ParkId,0);//将当前选择车场信息存入session
                request.getSession().setAttribute(Common.Park,0);
            }
            request.setAttribute("user",JsonUtil.beanToJson(user));//用户信息
            request.setAttribute("tCompanyList", tCompanyList.get(0));//用户所属物业信息
            request.setAttribute("tCompanyParkList",  JsonUtil.listToJson(tCompanyParkList));//用户所属车场信息
            request.setAttribute("layuiMenuList",  JsonUtil.listToJson(layuiMenuList));//用户权限菜单信息

        } else {
            return "redirect:/view/getLogin";
        }


        return "homepage/Index";
    }
    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/getLogin")
    public String login(HttpServletRequest request){
        try{
            String code=StringUtil.RandomString(4);
            request.getSession().setAttribute(Common.Code, code);//保存验证码
            request.setAttribute("code", code);//验证码

        }
        catch (Exception e)
        {
            logger.info(e.toString());
        }

        return "homepage/Login";
    }

    @RequestMapping(value = "/ResetPS")
    public  String ResetPS(){
        return "homepage/ResetPS";
    }
    /**
     * carParkEdit 车场基础信息设置
     * @return
     */
    @RequestMapping(value = "/carParkEdit")
    public String carParkEdit(){
        return "carPark_Management/carParkEdit";
    }
    /**
     * parkingCorrection 车位矫正
     * @return
     */
    @RequestMapping(value = "/parkingCorrection")
    public String parkingCorrection(){
        return "carPark_Management/parkingCorrection";
    }
    /**
     * carPark 车场信息
     * @return
     */
    @RequestMapping(value = "/carPark")
    public String carPark(){
        return "carPark_Management/carPark";
    }

    /**
     * propertyInformation 物业信息
     * @return
     */
    @RequestMapping(value = "/propertyInformation")
    public String propertyInformation(){
        return "carPark_Management/propertyInformation";
    }
    /**
     * propertyInformationEdit 物业基础信息设置
     * @return
     */
    @RequestMapping(value = "/propertyInformationEdit")
    public String propertyInformationEdit(){
        return "carPark_Management/propertyInformationEdit";
    }

    /**
     * journal 操作日志
     * @return
     */
    @RequestMapping(value = "/journal")
    public String journal(){
        return "carPark_Management/journal";
    }
    /**
     * monthlyRule 月租规则管理
     * @return
     */
    @RequestMapping(value = "/monthlyRule")
    public String monthlyRule(){
        return "carPark_Management/monthlyRule";
    }
    /**
     * holiday 节假日管理
     * @return
     */
    @RequestMapping(value = "/holiday")
    public String holiday(){
        return "carPark_Management/holiday";
    }
    /**
     * holiday 调休日管理
     * @return
     */
    @RequestMapping(value = "/dayOff")
    public String dayOff(){
        return "carPark_Management/dayOff";
    }
    /**
     * temporaryRule 临停规则管理
     * @return
     */
    @RequestMapping(value = "/temporaryRule")
    public String temporaryRule(){
        return "carPark_Management/temporaryRule";
    }
    /**
     * carPort 车位管理
     * @return
     */
    @RequestMapping(value = "/truckSpace")
    public String carPort(){
        return "carPark_Management/truckSpace";
    }
    /**
     * role 角色
     * @return
     */
    @RequestMapping(value = "/role")
    public String role(){
        return "carPark_Management/role";
    }
    /**
     * companyUser 物业用户
     * @return
     */
    @RequestMapping(value = "/companyUser")
    public String companyUser(){
        return "carPark_Management/companyUser";
    }
    /**
     * user 操作员管理
     * @return
     */
    @RequestMapping(value = "/user")
    public String user(){
        return "carPark_Management/user";
    }

    /**
     * busine商户管理
     * @return
     */
    @RequestMapping(value = "/busine")
    @HttpLog(operationType = "1",modularTypeName = "1")
    public String busine(){
        return "busine_Management/busine";
    }

    /**
     * businePay商户充值记录
     * @return
     */
    @RequestMapping(value = "/businePay")
    public String businePay(){
        return "busine_Management/businePay";
    }

    /**
     * businePay商户充值记录
     * @return
     */
    @RequestMapping(value = "/carWalletReport")
    public String carWalletReport(HttpServletRequest request){
        //companyPark传到前台
        List<TCompanyPark> tCompanyParkList = carParkLock.getTCompanyPark();
        List<TCompanyPark> tCompanyParkResultList = new ArrayList<>();
        TCompanyUser user = SessionUtil.getUser();
        String parkIdsStr = user.getParkIds();
        List<Integer> parkIdsStrList = StringUtil.stringList2List(parkIdsStr.split(","));
        if(parkIdsStrList.size()>0){
            for(TCompanyPark tCompanyParkOne:tCompanyParkList){
                for(Integer parkId:parkIdsStrList){
                    if(tCompanyParkOne.getId().equals(parkId)){
                        tCompanyParkResultList.add(tCompanyParkOne);
                    }
                }
            }
        }
        request.setAttribute("tCompanyParkResultList",  JsonUtil.listToJson(tCompanyParkResultList));
        return "financial_statistics/carWalletReport";
    }
   /* @RequestMapping("/list")
    public String  listUser(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i <10; i++) {
            userList.add(new User(i,"张三","20","中国广州"));
        }

        model.addAttribute("users", userList);
        return "/index";
    }*/



    /**
     * parkingRecord 缴费记录
     * @return
     */
    @RequestMapping(value = "/payFeeRecord")
    public String payFeeRecord(Model model,HttpServletRequest request){
        return "Car_Management/payFeeRecord";
    }

    /**
     * parkingRecord 停车记录
     * @return
     */
    @RequestMapping(value = "/parkingRecord")
    public String parkingRecord(Model model,HttpServletRequest request){
        return "Car_Management/parkingRecord";
    }

    @RequestMapping(value = "/PresentCar")
    public String presentCar(Model model){
        Integer cNumTemporary = presentCarService.FindTemporaryCar();
        Integer cNumMonthly = presentCarService.FindMonthlyCar();
        Integer cNumPrivate = presentCarService.FindPrivateCar();
        Integer cNumTotal = presentCarService.FindTotalParkingSpace();
        Integer cNumRemaining = cNumTotal-cNumTemporary-cNumMonthly-cNumPrivate;
        model.addAttribute("cNumTemporary",cNumTemporary);
        model.addAttribute("cNumMonthly",cNumMonthly);
        model.addAttribute("cNumPrivate",cNumPrivate);
        model.addAttribute("cNumRemaining",cNumRemaining);
        return "Car_Management/PresentCar";
    }

    @Value("${ump.url}")
    private String umpUrl;
    @RequestMapping(value = "/getUmp")
    public String getUmp(HttpServletRequest request){
        TCompanyUser user=SessionUtil.getUser();
        String userAccout=StringUtil.enCode(user.getUserAccout());
        String url=umpUrl+"?userAccout="+userAccout+"&passWord="+user.getPassword();
        Map map=new HashMap();
        map.put("url",url);
        request.setAttribute("map",JsonUtil.mapToJson(map));
        return "carPark_Management/ump";
    }
    @RequestMapping(value = "/test")
    public String test(){
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //String indexParkId = session.getAttribute(Common.ParkId).toString();
        //JSONObject UserJson = JSONObject.fromObject(session.getAttribute("User").toString());
        Enumeration<String> attrs = session.getAttributeNames();
        while(attrs.hasMoreElements()){ String name = attrs.nextElement().toString();
            Object vakue = session.getAttribute(name);
            System.out.println("------" + name + ":" + vakue +"--------\n");
        }*/
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        JSONObject UserJson = JSONObject.fromObject(session.getAttribute("User").toString());
        String indexParkId = UserJson.getString("parkIds");
        List<String> list = new ArrayList<String>();
        int i = indexParkId.indexOf(',');
        while (i >= 0) {
            list.add(indexParkId.substring(0,i));
            indexParkId=indexParkId.substring(i + 1, indexParkId.length());
            i = indexParkId.indexOf(",");
        }
        list.add(indexParkId);
        System.out.println(list);*/
        //System.out.println(customizeMapper.selectMonthlyNum());

        //System.out.println(customizeMapper.getMaturityNo("135"));
        return  null;
    }

    /**
     * 跳转主页
     * @return
     */
    @RequestMapping(value = "/main")
    public String mainview(Model model,HttpServletRequest request){
        String parkid = SessionUtil.getParkId().toString();
        //扇形图数据
//        TCustomize tCustomize = homepageService.selectNumMsg();
//        model.addAttribute("monEle",tCustomize.getMonEle());
//        model.addAttribute("monCash",tCustomize.getMonCash());
//        model.addAttribute("temporaryEle",tCustomize.getTemporaryEle());
//        model.addAttribute("temporaryCash",tCustomize.getTemporaryCash());
//        model.addAttribute("yueEle",tCustomize.getYueEle());
//        model.addAttribute("yueCash",tCustomize.getYueCash());
        //柱状图数据
        Map mapEle = homepageService.selectHistogram("2018","before_pay+coupon+qpass_pay",parkid);
        Map mapCash = homepageService.selectHistogram("2018","actual_pay",parkid);
        Map mapFree = homepageService.selectHistogram("2018","localcoupon",parkid);
        request.setAttribute("mapEle",mapEle);
        request.setAttribute("mapCash",mapCash);
        request.setAttribute("mapFree",mapFree);
        //头顶车辆数据
//        Map mapTop = homepageService.selectCompanyPark();
//        request.setAttribute("mapTop",mapTop);

        //新主页
        //今日车流量
//        Integer totalNum = homepageService.selectTotalNum(parkid);
//        request.setAttribute("totalNum",totalNum);
        //今日按支付方式金额
//        Map payByType = homepageService.selectPayByType(parkid);
//        System.out.println(payByType);
//        request.setAttribute("payByType",payByType);
        //近七日车流量
        List<Map> seven = homepageService.selectSevenDays(parkid);
        Map mapdate =seven.get(0);
        Map mapnum =seven.get(1);
        request.setAttribute("mapdate",mapdate);
        request.setAttribute("mapnum",mapnum);
        //支付宝、微信、现金、银联次数
//        Map epaymap = homepageService.selectePayType(parkid);
//        System.out.println(epaymap);
//        request.setAttribute("epaymap",epaymap);
        //空位数量
//        Map carNumq = homepageService.selectCarNum(parkid);
//
//        Map<String,Integer> carNum = new HashMap<>();
//        carNum.put("total",Integer.valueOf(carNumq.get("total").toString()));
//        carNum.put("surplus",Integer.valueOf(carNumq.get("surplus").toString()));
        //System.out.println(carNum);
        //request.setAttribute("carNum",carNum);
        //首页大部分数据
        Map main = homepageService.selectMainAllNum(parkid);
        Double useCarsNum = Double.valueOf(main.get("total").toString())-Double.valueOf(main.get("surplus").toString());
        Double total = Double.valueOf(main.get("total").toString());
        Float turnover = Float.valueOf(main.get("turnover").toString());
        if (turnover<0){
            turnover=(float)0.00;
        }
       //System.out.println(useCarsNum/total);
        main.put("syl",useCarsNum/total);
        main.put("kcl",1-useCarsNum/total);
        main.put("turnover",turnover);
        //System.out.println(main);
        request.setAttribute("main",main);
        return "homepage/dataView";
    }
    @RequestMapping(value = "/dataView")
    public  String dataView(HttpServletRequest request){
        String parkid = SessionUtil.getParkId().toString();
        //扇形图数据
//        TCustomize tCustomize = homepageService.selectNumMsg();
//        model.addAttribute("monEle",tCustomize.getMonEle());
//        model.addAttribute("monCash",tCustomize.getMonCash());
//        model.addAttribute("temporaryEle",tCustomize.getTemporaryEle());
//        model.addAttribute("temporaryCash",tCustomize.getTemporaryCash());
//        model.addAttribute("yueEle",tCustomize.getYueEle());
//        model.addAttribute("yueCash",tCustomize.getYueCash());
        //柱状图数据
        Map mapEle = homepageService.selectHistogram("2018","before_pay+coupon+qpass_pay",parkid);
        Map mapCash = homepageService.selectHistogram("2018","actual_pay",parkid);
        Map mapFree = homepageService.selectHistogram("2018","localcoupon",parkid);
        request.setAttribute("mapEle",mapEle);
        request.setAttribute("mapCash",mapCash);
        request.setAttribute("mapFree",mapFree);
        //头顶车辆数据
//        Map mapTop = homepageService.selectCompanyPark();
//        request.setAttribute("mapTop",mapTop);

        //新主页
        //今日车流量
//        Integer totalNum = homepageService.selectTotalNum(parkid);
//        request.setAttribute("totalNum",totalNum);
        //今日按支付方式金额
//        Map payByType = homepageService.selectPayByType(parkid);
//        System.out.println(payByType);
//        request.setAttribute("payByType",payByType);
        //近七日车流量
        List<Map> seven = homepageService.selectSevenDays(parkid);
        Map mapdate =seven.get(0);
        Map mapnum =seven.get(1);
        request.setAttribute("mapdate",mapdate);
        request.setAttribute("mapnum",mapnum);
        //支付宝、微信、现金、银联次数
//        Map epaymap = homepageService.selectePayType(parkid);
//        System.out.println(epaymap);
//        request.setAttribute("epaymap",epaymap);
        //空位数量
//        Map carNumq = homepageService.selectCarNum(parkid);
//
//        Map<String,Integer> carNum = new HashMap<>();
//        carNum.put("total",Integer.valueOf(carNumq.get("total").toString()));
//        carNum.put("surplus",Integer.valueOf(carNumq.get("surplus").toString()));
        //System.out.println(carNum);
        //request.setAttribute("carNum",carNum);
        //首页大部分数据
        Map main = homepageService.selectMainAllNum(parkid);
        Double useCarsNum = Double.valueOf(main.get("total").toString())-Integer.valueOf(main.get("surplus").toString());
        Double total = Double.valueOf(main.get("total").toString());
        Float turnover = Float.valueOf(main.get("turnover").toString());
        if (turnover<0){
            turnover=(float)0.00;
        }
        //System.out.println(useCarsNum/total);
        main.put("syl",useCarsNum/total);
        main.put("kcl",1-useCarsNum/total);
        main.put("turnover",turnover);
        //System.out.println(main);
        request.setAttribute("main",main);
        return "homepage/dataView";
    }
    /**
     * 跳转月租车管理
     */
    @RequestMapping(value = "/MonthlyCar")
    public String MonthlyCar(Model model,HttpServletRequest request){
        String parkid = SessionUtil.getParkId().toString();
        TCustomize tCustomize = monthlyCarService.getCarNum(parkid);
        Integer maturityNo = monthlyCarService.getMaturityNo(parkid);
        model.addAttribute("totalCarNo",tCustomize.getTotalCarNo());
        model.addAttribute("parkingNo",tCustomize.getParkingNo());
        model.addAttribute("privateParkingNo",tCustomize.getPrivateParkingNo());
        model.addAttribute("pauseParkingNo",tCustomize.getPauseParkingNo());
        model.addAttribute("expiredParkingNo",tCustomize.getExpiredParkingNo());
        model.addAttribute("maturityNo",maturityNo);
        //payRule传到前台
        Integer parkId = SessionUtil.getParkId();
        List<TCarPayRule> tCarPayRuleList = carPayRuleLock.getTCarPayRule();
        List<TCarPayRule> tCarPayRuleResultList = new ArrayList<>();
        for(TCarPayRule tCarPayRuleOne:tCarPayRuleList){
            if(tCarPayRuleOne.getParkId().equals(parkId)){
                tCarPayRuleResultList.add(tCarPayRuleOne);
            }
        }
        request.setAttribute("tCarPayRuleResultList",  JsonUtil.listToJson(tCarPayRuleResultList));
        return "Vehicle_Management/MonthlyCar";
    }

    /**
     * 黑名单跳转
     */
    @RequestMapping(value = "/Blacklist")
    public String Blacklist(HttpServletRequest request){
        return "Vehicle_Management/Blacklist";
    }

    /**
     * electronicPayment 电子支付对账
     * @return
     */
    @RequestMapping(value = "/electronicPayment")
    public String electronicPayment(){
        return "financial_statistics/electronicPayment";
    }
    /**
     * cashPayment 现金支付对账
     * @return
     */
    @RequestMapping(value = "/cashPayment")
    public String cashPayment(){
        return "financial_statistics/cashPayment";
    }
    /**
     * financialStatements 财务报表统计
     * @return
     */
    @RequestMapping(value = "/financialStatements")
    public String financialStatements(){
        return "financial_statistics/financialStatements";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/MonthlyPayRecord")
    public String MonthlyPayRecord(){
        return "Vehicle_Management/MonthlyPayRecord";
    }

    //当班详情
    @RequestMapping(value = "/cashPaymentDetail")
    public String cashPaymentDetail(HttpServletRequest request,String beginTime,String endTime,String type){
        System.out.println("查询类型"+type);
        request.setAttribute("beginTime",beginTime);
        request.setAttribute("endTime",endTime);
        request.setAttribute("type",type);
        return "Car_Management/cashPaymentDetail";
    }

    /**
     * 月租车缴费清单
     *
     */
    @RequestMapping(value = "/MonthlyDetails")
    public String MonthlyDetails(){
        return "Vehicle_Management/MonthlyDetails";
    }

    /**
     * 电子券优惠记录查询界面
     * updated by xiexuanran
     */
    @RequestMapping(value = "/electronicCouponsRecords")
    public String electronicCouponsRecords(){
        return "Merchant_Platform/electronicCouponsRecords";
    }


    /**
     * 商户优惠记录查询页面
     * updated by xiexuanran
     */
    @RequestMapping(value = "/businesCoupon")
    public String businesCoupon(){
        return "Merchant_Platform/businesCoupon";
    }


    /**
     * 商户优惠减免界面测试
     * updated by xiexuanran
     */
    @RequestMapping(value = "/merchantIndex")
    public String merchantCouponIndex() {
       return "Merchant_Platform/merchantIndex";
    }

    /**
     * 商户充值记录查询
     * updated by xiexuanran
     */
    @RequestMapping(value = "/businesRechargeRecord")
    public String businesRechargeRecord() {
        return "Merchant_Platform/businesRechargeRecord";
    }

    /**
     * 商户电子券管理
     * updated by xiexuanran
     */
/*    @RequestMapping(value = "/eTicketManage")
    public String eTicketManage() {
        return "BusineTicket";
    }*/
    @RequestMapping(value = "/electronicTicket")
    public String electronicTicket() {
        return "busine_Management/electronicTicket";
    }

    /**
     * 二维码记录查询
     * updated by xiexuanran
     */
    @RequestMapping(value = "/couponQrcodeRecord")
    public String couponQrcodeRecord(){
        return "Merchant_Platform/couponQrcodeRecords";
    }

    /**
     * 商户查询商户电子券信息
     * @return
     */
    @RequestMapping(value = "/busineTicketRecord")
    public String busineTicketRecord(){
        return "Merchant_Platform/busineTicket";
    }

    /**
     * 商户电子券批次管理
     * @return
     */
    @RequestMapping(value = "/eTicketBatchManage")
    public String eTicketBatchManage(){
        return "busine_Management/eTicketBatchManage";
    }

    /**
     * 跳转月租车管理
     */
    @RequestMapping(value = "/ownerCar")
    public String ownerCar(){
        return "Vehicle_Management/ownerCar";
    }

    /**
     * 车场通道管理
     */
    @RequestMapping(value = "/portManage")
    public String portManage(){
        return "carPark_Management/portManage";
    }

    /**
     * 参数配置管理
     */
    @RequestMapping(value = "/globalManage")
    public String globalManage(){
        return "carPark_Management/globalManage";
    }

    /**
     * 住户认真审核管理
     */
    @RequestMapping(value = "/householdManage")
    public String householdManage(){
        return "PropertyPay_Platform/householdManagement";
    }

    /**
     * 住户信息管理
     */
    @RequestMapping(value = "/householdInfo")
    public String householdInfo(){
        return "PropertyPay_Platform/householdInfo";
    }

    /**
     * 住户自动账单管理
     */
    @RequestMapping(value = "/householdAutobill")
    public String householdAutobill(){
        return "PropertyPay_Platform/householdAutobill";
    }

    /**
     * 物业公告管理
     */
    @RequestMapping(value = "/companyNotice")
    public String companyNotice(){
        return "PropertyPay_Platform/companyNotice";
    }

    /**
     * 缴费项目管理
     */
    @RequestMapping(value = "/billinfoManagement")
    public String billinfoManagement(){
        return "PropertyPay_Platform/billinfoManagement";
    }

    /**
     * 车主反馈
     */
    @RequestMapping(value = "/householdFeeeparkingdback")
    public String householdFeeeparkingdback(){
        return "PropertyPay_Platform/householdFeeeparkingdback";
    }

    /**
     * 住户账单管理
     */
    @RequestMapping(value = "/householeparkingdbill")
    public String householeparkingdbill(){
        return "PropertyPay_Platform/householeparkingdbill";
    }

    /**
     * 钱包充值记录
     */
    @RequestMapping(value = "/walletRechargeRecord")
    public String walletRechargeRecord(){
        return "Vehicle_Management/walletRechargeRecord";
    }

    /**
     * 缴费明细报表
     */
    @RequestMapping(value = "/chargeDetailsReport")
    public String chargeDetailsReport(){
        return "financial_statistics/chargeDetailsReport";
    }

    /**
     * 当班统计查询
     */
    @RequestMapping(value = "/dutyStatistics")
    public String dutyStatistics(){
        return "financial_statistics/dutyStatistics";
    }
}
