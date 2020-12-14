package com.eparking.enums;

/**
 * @Author lishuhan
 * @Description:统一返回格式枚举
 * @Date Create in 17:192018-5-9
 * @Modified By:
 */
public enum ActionRspEnum {
    UNKONW_ERROR(-1,"UNKONW_ERROR"),
    SUCCESS(0,"SUCCESS"),
    FORMAT_ERROR(1,"参数格式错误"),
    HTTP_ERROR(2,"上传进出场信息失败"),
    PAY_ERROR(3,"PAY_ERROR"),
    In_ERROR(4,"入场失败,此车牌已入场"),
    Out_ERROR(5,"出场失败,此车牌已出场"),
    Login_ERROR(6,"登录失败,请检查用户名或密码是否正确"),
    Code_ERROR(7,"验证码不正确"),
    Reset_ERROR1(8,"重置密码错误,原始密码不正确"),
    Reset_ERROR2(9,"重置密码错误,二次密码错误"),
    Execl_ERROR(10,"导入execl出错"),
    Execl_ERROR1(11,"导入execl格式出错"),
    SESSION_OUT(12,"Session超时,请重新登录"),
    Permission_ERROR(13,"该物业公司权限不足，请联系管理员"),
    REPETITION_ERROR(14,"车牌已存在"),
    QrcodeName_ERROR(15,"优惠券名称已存在"),
    GreaterThanDiscountAmount_ERROR(16,"超过该商户的单笔最大消费金额"),
    BusineInsufficientBalance_ERROR(17,"商户余额不足"),
    BusineNameExist_ERROR(18,"账号名称重复，无法完成更新操作"),
    BillItemsUsed_ERROR(19,"缴费项目正被使用中"),
    MonthlyPrepayment_ERROR(20,"月租预缴失败"),
    CarplateExist_ERROR(21,"该车牌已存在"),
    TicketExist_ERROR(22,"该电子券已删除"),
    CarplateCheck_ERROR(23,"车牌不合法")
    ;
    private Integer code;
    private String message;

    ActionRspEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
