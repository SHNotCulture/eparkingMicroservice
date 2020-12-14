package com.common.entity.eparkingCloud;
//车场配置参数
public class GlobalInfo {
    //过期车允许入场
    private String memberOuttimeInEnableContext;
    private Integer memberOuttimeInEnable;
    private String memberOuttimeInEnableDesc;
    //月卡提前多少天预报过期
    private String preVoicecastFixcarContext;
    private Integer preVoicecastFixcar;
    private String preVoicecastFixcarDesc;
    //是否启用多车绑定功能
    private String bindingCarEnableContext;
    private Integer bindingCarEnable;
    private String bindingCarEnableDesc;
    //剩余车位为0时月租车是否允许自动入场
    private String fixcarInEnalbeAsLotfullContext;
    private Integer fixcarInEnalbeAsLotfull;
    private String fixcarInEnalbeAsLotfullDesc;
    //多车绑定车位是否允许场内换车
    private String bindingcarExchangecarEnableContext;
    private Integer bindingcarExchangecarEnable;
    private String bindingcarExchangecarEnableDesc;
    //月租过期车场内续费是否临停计费
    private String calfeeExpiredFixcarContext;
    private Integer calfeeExpiredFixcar;
    private String calfeeExpiredFixcarDesc;
    //月租过期车是否允许电子扣费
    private String calfeeEpayExpiredMonthcarContext;
    private Integer calfeeEpayExpiredMonthcar;
    private String calfeeEpayExpiredMonthcarDesc;
    //绑定车入场按正常月租车入场规则入场，否则按临停规则入场
    private String bindingCarInAsNormalFixCarContext;
    private Integer bindingCarInAsNormalFixCar;
    private String bindingCarInAsNormalFixCarDesc;
    //月租车牌是否判断省份
    private String fixCarPreciseJudgeContext;
    private Integer fixCarPreciseJudge;
    private String fixCarPreciseJudgeDesc;
    //出入场图片存放主目录
    private String picSaveUrlContext;
    private String picSaveUrl;
    private String picSaveUrlDesc;
    //WEB服务器IP地址
    private String webServerIpContext;
    private String webServerIp;
    private String webServerIpDesc;
    //出入场记录保存天数
    private String recordSaveDaysContext;
    private Integer recordSaveDays;
    private String recordSaveDaysDesc;
    //出入场图片保存天数
    private String picSaveDaysContext;
    private Integer picSaveDays;
    private String picSaveDaysDesc;
    //中央收费处的电脑IP地址
    private String centerPcIpContext;
    private String centerPcIp;
    private String centerPcIpDesc;
    //自动清理垃圾进出记录间隔时间
    private String wasteInfoSaveHoursContext;
    private Integer wasteInfoSaveHours;
    private String wasteInfoSaveHoursDesc;
    //是否启动云锁车
    private String lockCarEnaleContext;
    private Integer lockCarEnale;
    private String lockCarEnaleDesc;
    //主控器版本
    private String controllerVersionContext;
    private Integer controllerVersion;
    private String controllerVersionDesc;
    //音量
    private String volumeContext;
    private Integer volume;
    private String volumeDesc;
    //播音员
    private String speakerContext;
    private Integer speaker;
    private String speakerDesc;
    //是否播报车牌
    private String voiceCastCarplateContext;
    private Integer voiceCastCarplate;
    private String voiceCastCarplateDesc;
    //LED控制器版本
    private String LEDControllerVersionContext;
    private Integer LEDControllerVersion;
    private String LEDControllerVersionDesc;
    //岗亭电脑间数据同步机制
    private String sentryDataSynchronizeContext;
    private String sentryDataSynchronize;
    private String sentryDataSynchronizeDesc;
    //出场界面有未放行车辆时是否允许新车辆出场
    private String newCarEnterEnableContext;
    private Integer newCarEnterEnable;
    private String newCarEnterEnableDesc;
    //遇到跟车是否二次开闸
    private String sencondOpenEnableContext;
    private Integer sencondOpenEnable;
    private String sencondOpenEnableDesc;
    //临停无入场记录是否查询云端
    private String noInRecordQueryCloudContext;
    private Integer noInRecordQueryCloud;
    private String noInRecordQueryCloudDesc;
    //虚拟车牌是否覆盖未出场的入场记录
    private String virOverwriteInRecordContext;
    private Integer virOverwriteInRecord;
    private String virOverwriteInRecordDesc;
    //是否上传出入场记录到云端
    private String uploadRecEnableContext;
    private Integer uploadRecEnable;
    private String uploadRecEnableDesc;
    //入口屏显示内容选择
    private String LEDShowContextContext;
    private Integer LEDShowContext;
    private String LEDShowContextDesc;
    //LED屏长度
    private String LEDLengthContext;
    private Integer LEDLength;
    private String LEDLengthDesc;
    //隐藏界面上的当班实时统计数据
    private String hideRealTimeDataContext;
    private Integer hideRealTimeData;
    private String hideRealTimeDataDesc;
    //车牌识别摄像机型号
    private String cameraModelsContext;
    private Integer cameraModels;
    private String cameraModelsDesc;
    //LED屏是否显示计费时长
    private String LEDShowChargeTimeContext;
    private Integer LEDShowChargeTime;
    private String LEDShowChargeTimeDesc;
    //停车场云服务ID
    private String parkIdContext;
    private Integer parkId;
    private String parkIdDesc;
    //停车场云服务KEY
    private String parkKeyContext;
    private String parkKey;
    private String parkKeyDesc;
    //系统运行模式
    private String systemModelContext;
    private Integer systemModel;
    private String systemModelDesc;
    //上传url
    private String uploadURLContext;
    private String uploadURL;
    private String uploadURLDesc;
    //使用MQTT服务与云端数据交互
    private String useMQTTChangeDataWithCloudContext;
    private Integer useMQTTChangeDataWithCloud;
    private String useMQTTChangeDataWithCloudDesc;
    //MQTT服务器
    private String mqttServerContext;
    private String mqttServer;
    private String mqttServerDesc;
    //临时车在免费时间内出场是否自动放行
    private String tempcarFreeAutoOutContext;
    private Integer tempcarFreeAutoOut;
    private String tempcarFreeAutoOutDesc;
    //临时车按F10取消出场后是否计入应收
    private String tempcarCancelOutIncludeReceivablesContext;
    private Integer tempcarCancelOutIncludeReceivables;
    private String tempcarCancelOutIncludeReceivablesDesc;
    //临时车收费规则
    private String tempcarChargeRulesContext;
    private Integer tempcarChargeRules;
    private String tempcarChargeRulesDesc;
    //剩余车位为0时临时车是否允许自动入场
    private String tempcarInEnableAsLotfullContext;
    private Integer tempcarInEnableAsLotfull;
    private String tempcarInEnableAsLotfullDesc;
    //特殊（类）车牌入场干预，不区分月租或临停
    private String inSpecialCarInterveneContext;
    private String inSpecialCarIntervene;
    private String inSpecialCarInterveneDesc;
    //特殊（类）车牌出场干预，不区分月租或临停
    private String outSpecialCarInterveneContext;
    private String outSpecialCarIntervene;
    private String outSpecialCarInterveneDesc;
    //无入场记录须缴费车辆强制按固定金额收费（0.0=不启用本功能）
    private String noEntryChargeContext;
    private Double noEntryCharge;
    private String noEntryChargeDesc;
    //预缴后场内免计费时间（分钟）
    private String freeSecondsPrepayContext;
    private Integer freeSecondsPrepay;
    private String freeSecondsPrepayDesc;
    //是否启用云闪付（博众聚合）
    private String bzPayEnaleContext;
    private Integer bzPayEnale;
    private String bzPayEnaleDesc;
    //云闪付（博众支付）成功后是否自动开闸
    private String bzPayAutoOpenEnableContext;
    private Integer bzPayAutoOpenEnable;
    private String bzPayAutoOpenEnableDesc;
    //是否启用其他无感支付
    private String otherPayEnableContext;
    private Integer otherPayEnable;
    private String otherPayEnableDesc;
    //支付Terminal的URL
    private String enableTerminalUrlContext;
    private String enableTerminalUrl;
    private String enableTerminalUrlDesc;
    //商户优惠全免时效限制
    private String limitFullCouponContext;
    private Integer limitFullCoupon;
    private String limitFullCouponDesc;
    //新能源车按其他车规则计费
    private String isSpecialCarChargeRuleContext;
    private Integer isSpecialCarChargeRule;
    private String isSpecialCarChargeRuleDesc;
    //支付Terminal接入方式
    private String terminalInTypeContext;
    private Integer terminalInType;
    private String terminalInTypeDesc;
    //是否自动申请电子扣费
    private String ePayAutoRequestContext;
    private Integer ePayAutoRequest;
    private String ePayAutoRequestDesc;
    //是否支持Terminal的独立通知出场指令
    private String enableTerminalIndependentNoticeOutContext;
    private Integer enableTerminalIndependentNoticeOut;
    private String enableTerminalIndependentNoticeOutDesc;
    //出场是否实时获取优惠代缴信息
    private String requestRealTimeCouponInfoContext;
    private Integer requestRealTimeCouponInfo;
    private String requestRealTimeCouponInfoDesc;
    //预缴超时出场后重新计费模式
    private String prepayOuttimeReCalModeContext;
    private Integer prepayOuttimeReCalMode;
    private String prepayOuttimeReCalModeDesc;

    public String getMemberOuttimeInEnableContext() {
        return memberOuttimeInEnableContext;
    }

    public void setMemberOuttimeInEnableContext(String memberOuttimeInEnableContext) {
        this.memberOuttimeInEnableContext = memberOuttimeInEnableContext;
    }

    public Integer getMemberOuttimeInEnable() {
        return memberOuttimeInEnable;
    }

    public void setMemberOuttimeInEnable(Integer memberOuttimeInEnable) {
        this.memberOuttimeInEnable = memberOuttimeInEnable;
    }

    public String getMemberOuttimeInEnableDesc() {
        return memberOuttimeInEnableDesc;
    }

    public void setMemberOuttimeInEnableDesc(String memberOuttimeInEnableDesc) {
        this.memberOuttimeInEnableDesc = memberOuttimeInEnableDesc;
    }

    public String getPreVoicecastFixcarContext() {
        return preVoicecastFixcarContext;
    }

    public void setPreVoicecastFixcarContext(String preVoicecastFixcarContext) {
        this.preVoicecastFixcarContext = preVoicecastFixcarContext;
    }

    public Integer getPreVoicecastFixcar() {
        return preVoicecastFixcar;
    }

    public void setPreVoicecastFixcar(Integer preVoicecastFixcar) {
        this.preVoicecastFixcar = preVoicecastFixcar;
    }

    public String getPreVoicecastFixcarDesc() {
        return preVoicecastFixcarDesc;
    }

    public void setPreVoicecastFixcarDesc(String preVoicecastFixcarDesc) {
        this.preVoicecastFixcarDesc = preVoicecastFixcarDesc;
    }

    public String getBindingCarEnableContext() {
        return bindingCarEnableContext;
    }

    public void setBindingCarEnableContext(String bindingCarEnableContext) {
        this.bindingCarEnableContext = bindingCarEnableContext;
    }

    public Integer getBindingCarEnable() {
        return bindingCarEnable;
    }

    public void setBindingCarEnable(Integer bindingCarEnable) {
        this.bindingCarEnable = bindingCarEnable;
    }

    public String getBindingCarEnableDesc() {
        return bindingCarEnableDesc;
    }

    public void setBindingCarEnableDesc(String bindingCarEnableDesc) {
        this.bindingCarEnableDesc = bindingCarEnableDesc;
    }

    public String getFixcarInEnalbeAsLotfullContext() {
        return fixcarInEnalbeAsLotfullContext;
    }

    public void setFixcarInEnalbeAsLotfullContext(String fixcarInEnalbeAsLotfullContext) {
        this.fixcarInEnalbeAsLotfullContext = fixcarInEnalbeAsLotfullContext;
    }

    public Integer getFixcarInEnalbeAsLotfull() {
        return fixcarInEnalbeAsLotfull;
    }

    public void setFixcarInEnalbeAsLotfull(Integer fixcarInEnalbeAsLotfull) {
        this.fixcarInEnalbeAsLotfull = fixcarInEnalbeAsLotfull;
    }

    public String getFixcarInEnalbeAsLotfullDesc() {
        return fixcarInEnalbeAsLotfullDesc;
    }

    public void setFixcarInEnalbeAsLotfullDesc(String fixcarInEnalbeAsLotfullDesc) {
        this.fixcarInEnalbeAsLotfullDesc = fixcarInEnalbeAsLotfullDesc;
    }

    public String getBindingcarExchangecarEnableContext() {
        return bindingcarExchangecarEnableContext;
    }

    public void setBindingcarExchangecarEnableContext(String bindingcarExchangecarEnableContext) {
        this.bindingcarExchangecarEnableContext = bindingcarExchangecarEnableContext;
    }

    public Integer getBindingcarExchangecarEnable() {
        return bindingcarExchangecarEnable;
    }

    public void setBindingcarExchangecarEnable(Integer bindingcarExchangecarEnable) {
        this.bindingcarExchangecarEnable = bindingcarExchangecarEnable;
    }

    public String getBindingcarExchangecarEnableDesc() {
        return bindingcarExchangecarEnableDesc;
    }

    public void setBindingcarExchangecarEnableDesc(String bindingcarExchangecarEnableDesc) {
        this.bindingcarExchangecarEnableDesc = bindingcarExchangecarEnableDesc;
    }

    public String getCalfeeExpiredFixcarContext() {
        return calfeeExpiredFixcarContext;
    }

    public void setCalfeeExpiredFixcarContext(String calfeeExpiredFixcarContext) {
        this.calfeeExpiredFixcarContext = calfeeExpiredFixcarContext;
    }

    public Integer getCalfeeExpiredFixcar() {
        return calfeeExpiredFixcar;
    }

    public void setCalfeeExpiredFixcar(Integer calfeeExpiredFixcar) {
        this.calfeeExpiredFixcar = calfeeExpiredFixcar;
    }

    public String getCalfeeExpiredFixcarDesc() {
        return calfeeExpiredFixcarDesc;
    }

    public void setCalfeeExpiredFixcarDesc(String calfeeExpiredFixcarDesc) {
        this.calfeeExpiredFixcarDesc = calfeeExpiredFixcarDesc;
    }

    public String getCalfeeEpayExpiredMonthcarContext() {
        return calfeeEpayExpiredMonthcarContext;
    }

    public void setCalfeeEpayExpiredMonthcarContext(String calfeeEpayExpiredMonthcarContext) {
        this.calfeeEpayExpiredMonthcarContext = calfeeEpayExpiredMonthcarContext;
    }

    public Integer getCalfeeEpayExpiredMonthcar() {
        return calfeeEpayExpiredMonthcar;
    }

    public void setCalfeeEpayExpiredMonthcar(Integer calfeeEpayExpiredMonthcar) {
        this.calfeeEpayExpiredMonthcar = calfeeEpayExpiredMonthcar;
    }

    public String getCalfeeEpayExpiredMonthcarDesc() {
        return calfeeEpayExpiredMonthcarDesc;
    }

    public void setCalfeeEpayExpiredMonthcarDesc(String calfeeEpayExpiredMonthcarDesc) {
        this.calfeeEpayExpiredMonthcarDesc = calfeeEpayExpiredMonthcarDesc;
    }

    public String getBindingCarInAsNormalFixCarContext() {
        return bindingCarInAsNormalFixCarContext;
    }

    public void setBindingCarInAsNormalFixCarContext(String bindingCarInAsNormalFixCarContext) {
        this.bindingCarInAsNormalFixCarContext = bindingCarInAsNormalFixCarContext;
    }

    public Integer getBindingCarInAsNormalFixCar() {
        return bindingCarInAsNormalFixCar;
    }

    public void setBindingCarInAsNormalFixCar(Integer bindingCarInAsNormalFixCar) {
        this.bindingCarInAsNormalFixCar = bindingCarInAsNormalFixCar;
    }

    public String getBindingCarInAsNormalFixCarDesc() {
        return bindingCarInAsNormalFixCarDesc;
    }

    public void setBindingCarInAsNormalFixCarDesc(String bindingCarInAsNormalFixCarDesc) {
        this.bindingCarInAsNormalFixCarDesc = bindingCarInAsNormalFixCarDesc;
    }

    public String getFixCarPreciseJudgeContext() {
        return fixCarPreciseJudgeContext;
    }

    public void setFixCarPreciseJudgeContext(String fixCarPreciseJudgeContext) {
        this.fixCarPreciseJudgeContext = fixCarPreciseJudgeContext;
    }

    public Integer getFixCarPreciseJudge() {
        return fixCarPreciseJudge;
    }

    public void setFixCarPreciseJudge(Integer fixCarPreciseJudge) {
        this.fixCarPreciseJudge = fixCarPreciseJudge;
    }

    public String getFixCarPreciseJudgeDesc() {
        return fixCarPreciseJudgeDesc;
    }

    public void setFixCarPreciseJudgeDesc(String fixCarPreciseJudgeDesc) {
        this.fixCarPreciseJudgeDesc = fixCarPreciseJudgeDesc;
    }

    public String getPicSaveUrlContext() {
        return picSaveUrlContext;
    }

    public void setPicSaveUrlContext(String picSaveUrlContext) {
        this.picSaveUrlContext = picSaveUrlContext;
    }

    public String getPicSaveUrl() {
        return picSaveUrl;
    }

    public void setPicSaveUrl(String picSaveUrl) {
        this.picSaveUrl = picSaveUrl;
    }

    public String getPicSaveUrlDesc() {
        return picSaveUrlDesc;
    }

    public void setPicSaveUrlDesc(String picSaveUrlDesc) {
        this.picSaveUrlDesc = picSaveUrlDesc;
    }

    public String getWebServerIpContext() {
        return webServerIpContext;
    }

    public void setWebServerIpContext(String webServerIpContext) {
        this.webServerIpContext = webServerIpContext;
    }

    public String getWebServerIp() {
        return webServerIp;
    }

    public void setWebServerIp(String webServerIp) {
        this.webServerIp = webServerIp;
    }

    public String getWebServerIpDesc() {
        return webServerIpDesc;
    }

    public void setWebServerIpDesc(String webServerIpDesc) {
        this.webServerIpDesc = webServerIpDesc;
    }

    public String getRecordSaveDaysContext() {
        return recordSaveDaysContext;
    }

    public void setRecordSaveDaysContext(String recordSaveDaysContext) {
        this.recordSaveDaysContext = recordSaveDaysContext;
    }

    public Integer getRecordSaveDays() {
        return recordSaveDays;
    }

    public void setRecordSaveDays(Integer recordSaveDays) {
        this.recordSaveDays = recordSaveDays;
    }

    public String getRecordSaveDaysDesc() {
        return recordSaveDaysDesc;
    }

    public void setRecordSaveDaysDesc(String recordSaveDaysDesc) {
        this.recordSaveDaysDesc = recordSaveDaysDesc;
    }

    public String getPicSaveDaysContext() {
        return picSaveDaysContext;
    }

    public void setPicSaveDaysContext(String picSaveDaysContext) {
        this.picSaveDaysContext = picSaveDaysContext;
    }

    public Integer getPicSaveDays() {
        return picSaveDays;
    }

    public void setPicSaveDays(Integer picSaveDays) {
        this.picSaveDays = picSaveDays;
    }

    public String getPicSaveDaysDesc() {
        return picSaveDaysDesc;
    }

    public void setPicSaveDaysDesc(String picSaveDaysDesc) {
        this.picSaveDaysDesc = picSaveDaysDesc;
    }

    public String getCenterPcIpContext() {
        return centerPcIpContext;
    }

    public void setCenterPcIpContext(String centerPcIpContext) {
        this.centerPcIpContext = centerPcIpContext;
    }

    public String getCenterPcIp() {
        return centerPcIp;
    }

    public void setCenterPcIp(String centerPcIp) {
        this.centerPcIp = centerPcIp;
    }

    public String getCenterPcIpDesc() {
        return centerPcIpDesc;
    }

    public void setCenterPcIpDesc(String centerPcIpDesc) {
        this.centerPcIpDesc = centerPcIpDesc;
    }

    public String getWasteInfoSaveHoursContext() {
        return wasteInfoSaveHoursContext;
    }

    public void setWasteInfoSaveHoursContext(String wasteInfoSaveHoursContext) {
        this.wasteInfoSaveHoursContext = wasteInfoSaveHoursContext;
    }

    public Integer getWasteInfoSaveHours() {
        return wasteInfoSaveHours;
    }

    public void setWasteInfoSaveHours(Integer wasteInfoSaveHours) {
        this.wasteInfoSaveHours = wasteInfoSaveHours;
    }

    public String getWasteInfoSaveHoursDesc() {
        return wasteInfoSaveHoursDesc;
    }

    public void setWasteInfoSaveHoursDesc(String wasteInfoSaveHoursDesc) {
        this.wasteInfoSaveHoursDesc = wasteInfoSaveHoursDesc;
    }

    public String getLockCarEnaleContext() {
        return lockCarEnaleContext;
    }

    public void setLockCarEnaleContext(String lockCarEnaleContext) {
        this.lockCarEnaleContext = lockCarEnaleContext;
    }

    public Integer getLockCarEnale() {
        return lockCarEnale;
    }

    public void setLockCarEnale(Integer lockCarEnale) {
        this.lockCarEnale = lockCarEnale;
    }

    public String getLockCarEnaleDesc() {
        return lockCarEnaleDesc;
    }

    public void setLockCarEnaleDesc(String lockCarEnaleDesc) {
        this.lockCarEnaleDesc = lockCarEnaleDesc;
    }

    public String getControllerVersionContext() {
        return controllerVersionContext;
    }

    public void setControllerVersionContext(String controllerVersionContext) {
        this.controllerVersionContext = controllerVersionContext;
    }

    public Integer getControllerVersion() {
        return controllerVersion;
    }

    public void setControllerVersion(Integer controllerVersion) {
        this.controllerVersion = controllerVersion;
    }

    public String getControllerVersionDesc() {
        return controllerVersionDesc;
    }

    public void setControllerVersionDesc(String controllerVersionDesc) {
        this.controllerVersionDesc = controllerVersionDesc;
    }

    public String getVolumeContext() {
        return volumeContext;
    }

    public void setVolumeContext(String volumeContext) {
        this.volumeContext = volumeContext;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getVolumeDesc() {
        return volumeDesc;
    }

    public void setVolumeDesc(String volumeDesc) {
        this.volumeDesc = volumeDesc;
    }

    public String getSpeakerContext() {
        return speakerContext;
    }

    public void setSpeakerContext(String speakerContext) {
        this.speakerContext = speakerContext;
    }

    public Integer getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Integer speaker) {
        this.speaker = speaker;
    }

    public String getSpeakerDesc() {
        return speakerDesc;
    }

    public void setSpeakerDesc(String speakerDesc) {
        this.speakerDesc = speakerDesc;
    }

    public String getVoiceCastCarplateContext() {
        return voiceCastCarplateContext;
    }

    public void setVoiceCastCarplateContext(String voiceCastCarplateContext) {
        this.voiceCastCarplateContext = voiceCastCarplateContext;
    }

    public Integer getVoiceCastCarplate() {
        return voiceCastCarplate;
    }

    public void setVoiceCastCarplate(Integer voiceCastCarplate) {
        this.voiceCastCarplate = voiceCastCarplate;
    }

    public String getVoiceCastCarplateDesc() {
        return voiceCastCarplateDesc;
    }

    public void setVoiceCastCarplateDesc(String voiceCastCarplateDesc) {
        this.voiceCastCarplateDesc = voiceCastCarplateDesc;
    }

    public String getLEDControllerVersionContext() {
        return LEDControllerVersionContext;
    }

    public void setLEDControllerVersionContext(String LEDControllerVersionContext) {
        this.LEDControllerVersionContext = LEDControllerVersionContext;
    }

    public Integer getLEDControllerVersion() {
        return LEDControllerVersion;
    }

    public void setLEDControllerVersion(Integer LEDControllerVersion) {
        this.LEDControllerVersion = LEDControllerVersion;
    }

    public String getLEDControllerVersionDesc() {
        return LEDControllerVersionDesc;
    }

    public void setLEDControllerVersionDesc(String LEDControllerVersionDesc) {
        this.LEDControllerVersionDesc = LEDControllerVersionDesc;
    }

    public String getSentryDataSynchronizeContext() {
        return sentryDataSynchronizeContext;
    }

    public void setSentryDataSynchronizeContext(String sentryDataSynchronizeContext) {
        this.sentryDataSynchronizeContext = sentryDataSynchronizeContext;
    }

    public String getSentryDataSynchronize() {
        return sentryDataSynchronize;
    }

    public void setSentryDataSynchronize(String sentryDataSynchronize) {
        this.sentryDataSynchronize = sentryDataSynchronize;
    }

    public String getSentryDataSynchronizeDesc() {
        return sentryDataSynchronizeDesc;
    }

    public void setSentryDataSynchronizeDesc(String sentryDataSynchronizeDesc) {
        this.sentryDataSynchronizeDesc = sentryDataSynchronizeDesc;
    }

    public String getNewCarEnterEnableContext() {
        return newCarEnterEnableContext;
    }

    public void setNewCarEnterEnableContext(String newCarEnterEnableContext) {
        this.newCarEnterEnableContext = newCarEnterEnableContext;
    }

    public Integer getNewCarEnterEnable() {
        return newCarEnterEnable;
    }

    public void setNewCarEnterEnable(Integer newCarEnterEnable) {
        this.newCarEnterEnable = newCarEnterEnable;
    }

    public String getNewCarEnterEnableDesc() {
        return newCarEnterEnableDesc;
    }

    public void setNewCarEnterEnableDesc(String newCarEnterEnableDesc) {
        this.newCarEnterEnableDesc = newCarEnterEnableDesc;
    }

    public String getSencondOpenEnableContext() {
        return sencondOpenEnableContext;
    }

    public void setSencondOpenEnableContext(String sencondOpenEnableContext) {
        this.sencondOpenEnableContext = sencondOpenEnableContext;
    }

    public Integer getSencondOpenEnable() {
        return sencondOpenEnable;
    }

    public void setSencondOpenEnable(Integer sencondOpenEnable) {
        this.sencondOpenEnable = sencondOpenEnable;
    }

    public String getSencondOpenEnableDesc() {
        return sencondOpenEnableDesc;
    }

    public void setSencondOpenEnableDesc(String sencondOpenEnableDesc) {
        this.sencondOpenEnableDesc = sencondOpenEnableDesc;
    }

    public String getNoInRecordQueryCloudContext() {
        return noInRecordQueryCloudContext;
    }

    public void setNoInRecordQueryCloudContext(String noInRecordQueryCloudContext) {
        this.noInRecordQueryCloudContext = noInRecordQueryCloudContext;
    }

    public Integer getNoInRecordQueryCloud() {
        return noInRecordQueryCloud;
    }

    public void setNoInRecordQueryCloud(Integer noInRecordQueryCloud) {
        this.noInRecordQueryCloud = noInRecordQueryCloud;
    }

    public String getNoInRecordQueryCloudDesc() {
        return noInRecordQueryCloudDesc;
    }

    public void setNoInRecordQueryCloudDesc(String noInRecordQueryCloudDesc) {
        this.noInRecordQueryCloudDesc = noInRecordQueryCloudDesc;
    }

    public String getVirOverwriteInRecordContext() {
        return virOverwriteInRecordContext;
    }

    public void setVirOverwriteInRecordContext(String virOverwriteInRecordContext) {
        this.virOverwriteInRecordContext = virOverwriteInRecordContext;
    }

    public Integer getVirOverwriteInRecord() {
        return virOverwriteInRecord;
    }

    public void setVirOverwriteInRecord(Integer virOverwriteInRecord) {
        this.virOverwriteInRecord = virOverwriteInRecord;
    }

    public String getVirOverwriteInRecordDesc() {
        return virOverwriteInRecordDesc;
    }

    public void setVirOverwriteInRecordDesc(String virOverwriteInRecordDesc) {
        this.virOverwriteInRecordDesc = virOverwriteInRecordDesc;
    }

    public String getUploadRecEnableContext() {
        return uploadRecEnableContext;
    }

    public void setUploadRecEnableContext(String uploadRecEnableContext) {
        this.uploadRecEnableContext = uploadRecEnableContext;
    }

    public Integer getUploadRecEnable() {
        return uploadRecEnable;
    }

    public void setUploadRecEnable(Integer uploadRecEnable) {
        this.uploadRecEnable = uploadRecEnable;
    }

    public String getUploadRecEnableDesc() {
        return uploadRecEnableDesc;
    }

    public void setUploadRecEnableDesc(String uploadRecEnableDesc) {
        this.uploadRecEnableDesc = uploadRecEnableDesc;
    }

    public String getLEDShowContextContext() {
        return LEDShowContextContext;
    }

    public void setLEDShowContextContext(String LEDShowContextContext) {
        this.LEDShowContextContext = LEDShowContextContext;
    }

    public Integer getLEDShowContext() {
        return LEDShowContext;
    }

    public void setLEDShowContext(Integer LEDShowContext) {
        this.LEDShowContext = LEDShowContext;
    }

    public String getLEDShowContextDesc() {
        return LEDShowContextDesc;
    }

    public void setLEDShowContextDesc(String LEDShowContextDesc) {
        this.LEDShowContextDesc = LEDShowContextDesc;
    }

    public String getLEDLengthContext() {
        return LEDLengthContext;
    }

    public void setLEDLengthContext(String LEDLengthContext) {
        this.LEDLengthContext = LEDLengthContext;
    }

    public Integer getLEDLength() {
        return LEDLength;
    }

    public void setLEDLength(Integer LEDLength) {
        this.LEDLength = LEDLength;
    }

    public String getLEDLengthDesc() {
        return LEDLengthDesc;
    }

    public void setLEDLengthDesc(String LEDLengthDesc) {
        this.LEDLengthDesc = LEDLengthDesc;
    }

    public String getHideRealTimeDataContext() {
        return hideRealTimeDataContext;
    }

    public void setHideRealTimeDataContext(String hideRealTimeDataContext) {
        this.hideRealTimeDataContext = hideRealTimeDataContext;
    }

    public Integer getHideRealTimeData() {
        return hideRealTimeData;
    }

    public void setHideRealTimeData(Integer hideRealTimeData) {
        this.hideRealTimeData = hideRealTimeData;
    }

    public String getHideRealTimeDataDesc() {
        return hideRealTimeDataDesc;
    }

    public void setHideRealTimeDataDesc(String hideRealTimeDataDesc) {
        this.hideRealTimeDataDesc = hideRealTimeDataDesc;
    }

    public String getCameraModelsContext() {
        return cameraModelsContext;
    }

    public void setCameraModelsContext(String cameraModelsContext) {
        this.cameraModelsContext = cameraModelsContext;
    }

    public Integer getCameraModels() {
        return cameraModels;
    }

    public void setCameraModels(Integer cameraModels) {
        this.cameraModels = cameraModels;
    }

    public String getCameraModelsDesc() {
        return cameraModelsDesc;
    }

    public void setCameraModelsDesc(String cameraModelsDesc) {
        this.cameraModelsDesc = cameraModelsDesc;
    }

    public String getLEDShowChargeTimeContext() {
        return LEDShowChargeTimeContext;
    }

    public void setLEDShowChargeTimeContext(String LEDShowChargeTimeContext) {
        this.LEDShowChargeTimeContext = LEDShowChargeTimeContext;
    }

    public Integer getLEDShowChargeTime() {
        return LEDShowChargeTime;
    }

    public void setLEDShowChargeTime(Integer LEDShowChargeTime) {
        this.LEDShowChargeTime = LEDShowChargeTime;
    }

    public String getLEDShowChargeTimeDesc() {
        return LEDShowChargeTimeDesc;
    }

    public void setLEDShowChargeTimeDesc(String LEDShowChargeTimeDesc) {
        this.LEDShowChargeTimeDesc = LEDShowChargeTimeDesc;
    }

    public String getParkIdContext() {
        return parkIdContext;
    }

    public void setParkIdContext(String parkIdContext) {
        this.parkIdContext = parkIdContext;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getParkIdDesc() {
        return parkIdDesc;
    }

    public void setParkIdDesc(String parkIdDesc) {
        this.parkIdDesc = parkIdDesc;
    }

    public String getParkKeyContext() {
        return parkKeyContext;
    }

    public void setParkKeyContext(String parkKeyContext) {
        this.parkKeyContext = parkKeyContext;
    }

    public String getParkKey() {
        return parkKey;
    }

    public void setParkKey(String parkKey) {
        this.parkKey = parkKey;
    }

    public String getParkKeyDesc() {
        return parkKeyDesc;
    }

    public void setParkKeyDesc(String parkKeyDesc) {
        this.parkKeyDesc = parkKeyDesc;
    }

    public String getSystemModelContext() {
        return systemModelContext;
    }

    public void setSystemModelContext(String systemModelContext) {
        this.systemModelContext = systemModelContext;
    }

    public Integer getSystemModel() {
        return systemModel;
    }

    public void setSystemModel(Integer systemModel) {
        this.systemModel = systemModel;
    }

    public String getSystemModelDesc() {
        return systemModelDesc;
    }

    public void setSystemModelDesc(String systemModelDesc) {
        this.systemModelDesc = systemModelDesc;
    }

    public String getUploadURLContext() {
        return uploadURLContext;
    }

    public void setUploadURLContext(String uploadURLContext) {
        this.uploadURLContext = uploadURLContext;
    }

    public String getUploadURL() {
        return uploadURL;
    }

    public void setUploadURL(String uploadURL) {
        this.uploadURL = uploadURL;
    }

    public String getUploadURLDesc() {
        return uploadURLDesc;
    }

    public void setUploadURLDesc(String uploadURLDesc) {
        this.uploadURLDesc = uploadURLDesc;
    }

    public String getUseMQTTChangeDataWithCloudContext() {
        return useMQTTChangeDataWithCloudContext;
    }

    public void setUseMQTTChangeDataWithCloudContext(String useMQTTChangeDataWithCloudContext) {
        this.useMQTTChangeDataWithCloudContext = useMQTTChangeDataWithCloudContext;
    }

    public Integer getUseMQTTChangeDataWithCloud() {
        return useMQTTChangeDataWithCloud;
    }

    public void setUseMQTTChangeDataWithCloud(Integer useMQTTChangeDataWithCloud) {
        this.useMQTTChangeDataWithCloud = useMQTTChangeDataWithCloud;
    }

    public String getUseMQTTChangeDataWithCloudDesc() {
        return useMQTTChangeDataWithCloudDesc;
    }

    public void setUseMQTTChangeDataWithCloudDesc(String useMQTTChangeDataWithCloudDesc) {
        this.useMQTTChangeDataWithCloudDesc = useMQTTChangeDataWithCloudDesc;
    }

    public String getMqttServerContext() {
        return mqttServerContext;
    }

    public void setMqttServerContext(String mqttServerContext) {
        this.mqttServerContext = mqttServerContext;
    }

    public String getMqttServer() {
        return mqttServer;
    }

    public void setMqttServer(String mqttServer) {
        this.mqttServer = mqttServer;
    }

    public String getMqttServerDesc() {
        return mqttServerDesc;
    }

    public void setMqttServerDesc(String mqttServerDesc) {
        this.mqttServerDesc = mqttServerDesc;
    }

    public String getTempcarFreeAutoOutContext() {
        return tempcarFreeAutoOutContext;
    }

    public void setTempcarFreeAutoOutContext(String tempcarFreeAutoOutContext) {
        this.tempcarFreeAutoOutContext = tempcarFreeAutoOutContext;
    }

    public Integer getTempcarFreeAutoOut() {
        return tempcarFreeAutoOut;
    }

    public void setTempcarFreeAutoOut(Integer tempcarFreeAutoOut) {
        this.tempcarFreeAutoOut = tempcarFreeAutoOut;
    }

    public String getTempcarFreeAutoOutDesc() {
        return tempcarFreeAutoOutDesc;
    }

    public void setTempcarFreeAutoOutDesc(String tempcarFreeAutoOutDesc) {
        this.tempcarFreeAutoOutDesc = tempcarFreeAutoOutDesc;
    }

    public String getTempcarCancelOutIncludeReceivablesContext() {
        return tempcarCancelOutIncludeReceivablesContext;
    }

    public void setTempcarCancelOutIncludeReceivablesContext(String tempcarCancelOutIncludeReceivablesContext) {
        this.tempcarCancelOutIncludeReceivablesContext = tempcarCancelOutIncludeReceivablesContext;
    }

    public Integer getTempcarCancelOutIncludeReceivables() {
        return tempcarCancelOutIncludeReceivables;
    }

    public void setTempcarCancelOutIncludeReceivables(Integer tempcarCancelOutIncludeReceivables) {
        this.tempcarCancelOutIncludeReceivables = tempcarCancelOutIncludeReceivables;
    }

    public String getTempcarCancelOutIncludeReceivablesDesc() {
        return tempcarCancelOutIncludeReceivablesDesc;
    }

    public void setTempcarCancelOutIncludeReceivablesDesc(String tempcarCancelOutIncludeReceivablesDesc) {
        this.tempcarCancelOutIncludeReceivablesDesc = tempcarCancelOutIncludeReceivablesDesc;
    }

    public String getTempcarChargeRulesContext() {
        return tempcarChargeRulesContext;
    }

    public void setTempcarChargeRulesContext(String tempcarChargeRulesContext) {
        this.tempcarChargeRulesContext = tempcarChargeRulesContext;
    }

    public Integer getTempcarChargeRules() {
        return tempcarChargeRules;
    }

    public void setTempcarChargeRules(Integer tempcarChargeRules) {
        this.tempcarChargeRules = tempcarChargeRules;
    }

    public String getTempcarChargeRulesDesc() {
        return tempcarChargeRulesDesc;
    }

    public void setTempcarChargeRulesDesc(String tempcarChargeRulesDesc) {
        this.tempcarChargeRulesDesc = tempcarChargeRulesDesc;
    }

    public String getTempcarInEnableAsLotfullContext() {
        return tempcarInEnableAsLotfullContext;
    }

    public void setTempcarInEnableAsLotfullContext(String tempcarInEnableAsLotfullContext) {
        this.tempcarInEnableAsLotfullContext = tempcarInEnableAsLotfullContext;
    }

    public Integer getTempcarInEnableAsLotfull() {
        return tempcarInEnableAsLotfull;
    }

    public void setTempcarInEnableAsLotfull(Integer tempcarInEnableAsLotfull) {
        this.tempcarInEnableAsLotfull = tempcarInEnableAsLotfull;
    }

    public String getTempcarInEnableAsLotfullDesc() {
        return tempcarInEnableAsLotfullDesc;
    }

    public void setTempcarInEnableAsLotfullDesc(String tempcarInEnableAsLotfullDesc) {
        this.tempcarInEnableAsLotfullDesc = tempcarInEnableAsLotfullDesc;
    }

    public String getInSpecialCarInterveneContext() {
        return inSpecialCarInterveneContext;
    }

    public void setInSpecialCarInterveneContext(String inSpecialCarInterveneContext) {
        this.inSpecialCarInterveneContext = inSpecialCarInterveneContext;
    }

    public String getInSpecialCarIntervene() {
        return inSpecialCarIntervene;
    }

    public void setInSpecialCarIntervene(String inSpecialCarIntervene) {
        this.inSpecialCarIntervene = inSpecialCarIntervene;
    }

    public String getInSpecialCarInterveneDesc() {
        return inSpecialCarInterveneDesc;
    }

    public void setInSpecialCarInterveneDesc(String inSpecialCarInterveneDesc) {
        this.inSpecialCarInterveneDesc = inSpecialCarInterveneDesc;
    }

    public String getOutSpecialCarInterveneContext() {
        return outSpecialCarInterveneContext;
    }

    public void setOutSpecialCarInterveneContext(String outSpecialCarInterveneContext) {
        this.outSpecialCarInterveneContext = outSpecialCarInterveneContext;
    }

    public String getOutSpecialCarIntervene() {
        return outSpecialCarIntervene;
    }

    public void setOutSpecialCarIntervene(String outSpecialCarIntervene) {
        this.outSpecialCarIntervene = outSpecialCarIntervene;
    }

    public String getOutSpecialCarInterveneDesc() {
        return outSpecialCarInterveneDesc;
    }

    public void setOutSpecialCarInterveneDesc(String outSpecialCarInterveneDesc) {
        this.outSpecialCarInterveneDesc = outSpecialCarInterveneDesc;
    }

    public String getNoEntryChargeContext() {
        return noEntryChargeContext;
    }

    public void setNoEntryChargeContext(String noEntryChargeContext) {
        this.noEntryChargeContext = noEntryChargeContext;
    }

    public Double getNoEntryCharge() {
        return noEntryCharge;
    }

    public void setNoEntryCharge(Double noEntryCharge) {
        this.noEntryCharge = noEntryCharge;
    }

    public String getNoEntryChargeDesc() {
        return noEntryChargeDesc;
    }

    public void setNoEntryChargeDesc(String noEntryChargeDesc) {
        this.noEntryChargeDesc = noEntryChargeDesc;
    }

    public String getFreeSecondsPrepayContext() {
        return freeSecondsPrepayContext;
    }

    public void setFreeSecondsPrepayContext(String freeSecondsPrepayContext) {
        this.freeSecondsPrepayContext = freeSecondsPrepayContext;
    }

    public Integer getFreeSecondsPrepay() {
        return freeSecondsPrepay;
    }

    public void setFreeSecondsPrepay(Integer freeSecondsPrepay) {
        this.freeSecondsPrepay = freeSecondsPrepay;
    }

    public String getFreeSecondsPrepayDesc() {
        return freeSecondsPrepayDesc;
    }

    public void setFreeSecondsPrepayDesc(String freeSecondsPrepayDesc) {
        this.freeSecondsPrepayDesc = freeSecondsPrepayDesc;
    }

    public String getBzPayEnaleContext() {
        return bzPayEnaleContext;
    }

    public void setBzPayEnaleContext(String bzPayEnaleContext) {
        this.bzPayEnaleContext = bzPayEnaleContext;
    }

    public Integer getBzPayEnale() {
        return bzPayEnale;
    }

    public void setBzPayEnale(Integer bzPayEnale) {
        this.bzPayEnale = bzPayEnale;
    }

    public String getBzPayEnaleDesc() {
        return bzPayEnaleDesc;
    }

    public void setBzPayEnaleDesc(String bzPayEnaleDesc) {
        this.bzPayEnaleDesc = bzPayEnaleDesc;
    }

    public String getBzPayAutoOpenEnableContext() {
        return bzPayAutoOpenEnableContext;
    }

    public void setBzPayAutoOpenEnableContext(String bzPayAutoOpenEnableContext) {
        this.bzPayAutoOpenEnableContext = bzPayAutoOpenEnableContext;
    }

    public Integer getBzPayAutoOpenEnable() {
        return bzPayAutoOpenEnable;
    }

    public void setBzPayAutoOpenEnable(Integer bzPayAutoOpenEnable) {
        this.bzPayAutoOpenEnable = bzPayAutoOpenEnable;
    }

    public String getBzPayAutoOpenEnableDesc() {
        return bzPayAutoOpenEnableDesc;
    }

    public void setBzPayAutoOpenEnableDesc(String bzPayAutoOpenEnableDesc) {
        this.bzPayAutoOpenEnableDesc = bzPayAutoOpenEnableDesc;
    }

    public String getOtherPayEnableContext() {
        return otherPayEnableContext;
    }

    public void setOtherPayEnableContext(String otherPayEnableContext) {
        this.otherPayEnableContext = otherPayEnableContext;
    }

    public Integer getOtherPayEnable() {
        return otherPayEnable;
    }

    public void setOtherPayEnable(Integer otherPayEnable) {
        this.otherPayEnable = otherPayEnable;
    }

    public String getOtherPayEnableDesc() {
        return otherPayEnableDesc;
    }

    public void setOtherPayEnableDesc(String otherPayEnableDesc) {
        this.otherPayEnableDesc = otherPayEnableDesc;
    }

    public String getEnableTerminalUrlContext() {
        return enableTerminalUrlContext;
    }

    public void setEnableTerminalUrlContext(String enableTerminalUrlContext) {
        this.enableTerminalUrlContext = enableTerminalUrlContext;
    }

    public String getEnableTerminalUrl() {
        return enableTerminalUrl;
    }

    public void setEnableTerminalUrl(String enableTerminalUrl) {
        this.enableTerminalUrl = enableTerminalUrl;
    }

    public String getEnableTerminalUrlDesc() {
        return enableTerminalUrlDesc;
    }

    public void setEnableTerminalUrlDesc(String enableTerminalUrlDesc) {
        this.enableTerminalUrlDesc = enableTerminalUrlDesc;
    }

    public String getLimitFullCouponContext() {
        return limitFullCouponContext;
    }

    public void setLimitFullCouponContext(String limitFullCouponContext) {
        this.limitFullCouponContext = limitFullCouponContext;
    }

    public Integer getLimitFullCoupon() {
        return limitFullCoupon;
    }

    public void setLimitFullCoupon(Integer limitFullCoupon) {
        this.limitFullCoupon = limitFullCoupon;
    }

    public String getLimitFullCouponDesc() {
        return limitFullCouponDesc;
    }

    public void setLimitFullCouponDesc(String limitFullCouponDesc) {
        this.limitFullCouponDesc = limitFullCouponDesc;
    }

    public String getIsSpecialCarChargeRuleContext() {
        return isSpecialCarChargeRuleContext;
    }

    public void setIsSpecialCarChargeRuleContext(String isSpecialCarChargeRuleContext) {
        this.isSpecialCarChargeRuleContext = isSpecialCarChargeRuleContext;
    }

    public Integer getIsSpecialCarChargeRule() {
        return isSpecialCarChargeRule;
    }

    public void setIsSpecialCarChargeRule(Integer isSpecialCarChargeRule) {
        this.isSpecialCarChargeRule = isSpecialCarChargeRule;
    }

    public String getIsSpecialCarChargeRuleDesc() {
        return isSpecialCarChargeRuleDesc;
    }

    public void setIsSpecialCarChargeRuleDesc(String isSpecialCarChargeRuleDesc) {
        this.isSpecialCarChargeRuleDesc = isSpecialCarChargeRuleDesc;
    }

    public String getTerminalInTypeContext() {
        return terminalInTypeContext;
    }

    public void setTerminalInTypeContext(String terminalInTypeContext) {
        this.terminalInTypeContext = terminalInTypeContext;
    }

    public Integer getTerminalInType() {
        return terminalInType;
    }

    public void setTerminalInType(Integer terminalInType) {
        this.terminalInType = terminalInType;
    }

    public String getTerminalInTypeDesc() {
        return terminalInTypeDesc;
    }

    public void setTerminalInTypeDesc(String terminalInTypeDesc) {
        this.terminalInTypeDesc = terminalInTypeDesc;
    }

    public String getePayAutoRequestContext() {
        return ePayAutoRequestContext;
    }

    public void setePayAutoRequestContext(String ePayAutoRequestContext) {
        this.ePayAutoRequestContext = ePayAutoRequestContext;
    }

    public Integer getePayAutoRequest() {
        return ePayAutoRequest;
    }

    public void setePayAutoRequest(Integer ePayAutoRequest) {
        this.ePayAutoRequest = ePayAutoRequest;
    }

    public String getePayAutoRequestDesc() {
        return ePayAutoRequestDesc;
    }

    public void setePayAutoRequestDesc(String ePayAutoRequestDesc) {
        this.ePayAutoRequestDesc = ePayAutoRequestDesc;
    }

    public String getEnableTerminalIndependentNoticeOutContext() {
        return enableTerminalIndependentNoticeOutContext;
    }

    public void setEnableTerminalIndependentNoticeOutContext(String enableTerminalIndependentNoticeOutContext) {
        this.enableTerminalIndependentNoticeOutContext = enableTerminalIndependentNoticeOutContext;
    }

    public Integer getEnableTerminalIndependentNoticeOut() {
        return enableTerminalIndependentNoticeOut;
    }

    public void setEnableTerminalIndependentNoticeOut(Integer enableTerminalIndependentNoticeOut) {
        this.enableTerminalIndependentNoticeOut = enableTerminalIndependentNoticeOut;
    }

    public String getEnableTerminalIndependentNoticeOutDesc() {
        return enableTerminalIndependentNoticeOutDesc;
    }

    public void setEnableTerminalIndependentNoticeOutDesc(String enableTerminalIndependentNoticeOutDesc) {
        this.enableTerminalIndependentNoticeOutDesc = enableTerminalIndependentNoticeOutDesc;
    }

    public String getRequestRealTimeCouponInfoContext() {
        return requestRealTimeCouponInfoContext;
    }

    public void setRequestRealTimeCouponInfoContext(String requestRealTimeCouponInfoContext) {
        this.requestRealTimeCouponInfoContext = requestRealTimeCouponInfoContext;
    }

    public Integer getRequestRealTimeCouponInfo() {
        return requestRealTimeCouponInfo;
    }

    public void setRequestRealTimeCouponInfo(Integer requestRealTimeCouponInfo) {
        this.requestRealTimeCouponInfo = requestRealTimeCouponInfo;
    }

    public String getRequestRealTimeCouponInfoDesc() {
        return requestRealTimeCouponInfoDesc;
    }

    public void setRequestRealTimeCouponInfoDesc(String requestRealTimeCouponInfoDesc) {
        this.requestRealTimeCouponInfoDesc = requestRealTimeCouponInfoDesc;
    }

    public String getPrepayOuttimeReCalModeContext() {
        return prepayOuttimeReCalModeContext;
    }

    public void setPrepayOuttimeReCalModeContext(String prepayOuttimeReCalModeContext) {
        this.prepayOuttimeReCalModeContext = prepayOuttimeReCalModeContext;
    }

    public Integer getPrepayOuttimeReCalMode() {
        return prepayOuttimeReCalMode;
    }

    public void setPrepayOuttimeReCalMode(Integer prepayOuttimeReCalMode) {
        this.prepayOuttimeReCalMode = prepayOuttimeReCalMode;
    }

    public String getPrepayOuttimeReCalModeDesc() {
        return prepayOuttimeReCalModeDesc;
    }

    public void setPrepayOuttimeReCalModeDesc(String prepayOuttimeReCalModeDesc) {
        this.prepayOuttimeReCalModeDesc = prepayOuttimeReCalModeDesc;
    }

    public GlobalInfo() {
        this.memberOuttimeInEnableContext = "允许车主超期进场";
        this.memberOuttimeInEnable = 0;
        this.memberOuttimeInEnableDesc = "默认否";
        this.preVoicecastFixcarContext = "月卡提前多少天预报过期";
        this.preVoicecastFixcar = 15;
        this.preVoicecastFixcarDesc = "默认15天";
        this.bindingCarEnableContext = "是否启用多车绑定功能";
        this.bindingCarEnable = 0;
        this.bindingCarEnableDesc = "默认是";
        this.fixcarInEnalbeAsLotfullContext = "剩余车位为0时月租车是否允许自动入场";
        this.fixcarInEnalbeAsLotfull = 0;
        this.fixcarInEnalbeAsLotfullDesc = "默认是";
        this.bindingcarExchangecarEnableContext = "多车绑定车位是否允许场内换车";
        this.bindingcarExchangecarEnable = 0;
        this.bindingcarExchangecarEnableDesc = "是=允许，否=不允许（默认）";
        this.calfeeExpiredFixcarContext = "月租过期车场内续费是否临停计费";
        this.calfeeExpiredFixcar = 0;
        this.calfeeExpiredFixcarDesc = "是=计费（默认），否=不计费";
        this.calfeeEpayExpiredMonthcarContext = "月租过期车是否允许电子扣费";
        this.calfeeEpayExpiredMonthcar = 0;
        this.calfeeEpayExpiredMonthcarDesc = "0=否（默认），1=是";
        this.bindingCarInAsNormalFixCarContext = "绑定车入场按正常月租车入场规则入场，否则按临停规则入场";
        this.bindingCarInAsNormalFixCar = 0;
        this.bindingCarInAsNormalFixCarDesc = "0=否（默认），1=是";
        this.fixCarPreciseJudgeContext = "月租车牌是否判断省份";
        this.fixCarPreciseJudge = 0;
        this.fixCarPreciseJudgeDesc = "1=是（默认），1=是";
        this.picSaveUrlContext = "出入场图片存放主目录";
        this.picSaveUrl = "";
        this.picSaveUrlDesc = "";
        this.webServerIpContext = "WEB服务器IP地址";
        this.webServerIp = "192.168.1.229";
        this.webServerIpDesc = "默认192.168.1.229";
        this.recordSaveDaysContext = "出入场记录保存天数";
        this.recordSaveDays = 60;
        this.recordSaveDaysDesc = "默认60天";
        this.picSaveDaysContext = "出入场图片保存天数";
        this.picSaveDays = 60;
        this.picSaveDaysDesc = "默认60天";
        this.centerPcIpContext = "中央收费处的电脑IP地址";
        this.centerPcIp = "192.168.1.229";
        this.centerPcIpDesc = "默认192.168.1.229";
        this.wasteInfoSaveHoursContext = "自动清理垃圾进出记录间隔时间";
        this.wasteInfoSaveHours = 72;
        this.wasteInfoSaveHoursDesc = "默认清理72小时前数据";
        this.lockCarEnaleContext = "是否启动云锁车";
        this.lockCarEnale = 0;
        this.lockCarEnaleDesc = "默认否";
        this.controllerVersionContext = "主控器版本";
        this.controllerVersion = 0;
        this.controllerVersionDesc = "0=V3.x，1=V4.x，2=无控制器";
        this.volumeContext = "音量";
        this.volume = 0;
        this.volumeDesc = "0=静音，10=最大";
        this.speakerContext = "播音员";
        this.speaker = 3;
        this.speakerDesc = "3=小燕，51=许久，52=许多，53=小萍，54=唐老鸭，55=许小宝";
        this.voiceCastCarplateContext = "是否播报车牌";
        this.voiceCastCarplate = 0;
        this.voiceCastCarplateDesc = "默认是";
        this.LEDControllerVersionContext = "LED控制器版本";
        this.LEDControllerVersion = 0;
        this.LEDControllerVersionDesc = "0=V1.4.x，1=V1.5.x，2=V2.x";
        this.sentryDataSynchronizeContext = "岗亭电脑间数据同步机制";
        this.sentryDataSynchronize = "云端同步";
        this.sentryDataSynchronizeDesc = "默认云端同步";
        this.newCarEnterEnableContext = "出场界面有未放行车辆时是否允许新车辆出场";
        this.newCarEnterEnable = 0;
        this.newCarEnterEnableDesc = "默认是";
        this.sencondOpenEnableContext = "遇到跟车是否二次开闸";
        this.sencondOpenEnable = 0;
        this.sencondOpenEnableDesc = "默认否";
        this.noInRecordQueryCloudContext = "临停无入场记录是否查询云端";
        this.noInRecordQueryCloud = 0;
        this.noInRecordQueryCloudDesc = "默认否";
        this.virOverwriteInRecordContext = "虚拟车牌是否覆盖未出场的入场记录";
        this.virOverwriteInRecord = 0;
        this.virOverwriteInRecordDesc = "默认是：是=不严格模式，否=严格模式";
        this.uploadRecEnableContext = "是否上传出入场记录到云端";
        this.uploadRecEnable = 0;
        this.uploadRecEnableDesc = "默认是，慎重选择";
        this.LEDShowContextContext = "入口屏显示内容选择";
        this.LEDShowContext = 1;
        this.LEDShowContextDesc = "1@时间，2@剩余车位";
        this.LEDLengthContext = "LED屏长度";
        this.LEDLength = 1;
        this.LEDLengthDesc = "1@5汉字,2@4汉字";
        this.hideRealTimeDataContext = "隐藏界面上的当班实时统计数据";
        this.hideRealTimeData = 0;
        this.hideRealTimeDataDesc = "是=隐藏，否=不隐藏（默认）";
        this.cameraModelsContext = "车牌识别摄像机型号";
        this.cameraModels = 1;
        this.cameraModelsDesc = "1=标准(默认),2=VZ,3=ZK,4=HX";
        this.LEDShowChargeTimeContext = "LED屏是否显示计费时长";
        this.LEDShowChargeTime = 0;
        this.LEDShowChargeTimeDesc = "默认是";
        this.parkIdContext = "停车场云服务ID";
        this.parkId = 0;
        this.parkIdDesc = "认证数据上传到指定物业数据中心";
        this.parkKeyContext = "停车场云服务KEY";
        this.parkKey = "";
        this.parkKeyDesc = "认证数据上传到指定物业数据中心";
        this.systemModelContext = "系统运行模式";
        this.systemModel = 0;
        this.systemModelDesc = "默认接入云平台";
        this.uploadURLContext = "上传url";
        this.uploadURL = "http://yun.eparking.top:8080";
        this.uploadURLDesc = "默认是http://yun.eparking.top:8080";
        this.useMQTTChangeDataWithCloudContext = "使用MQTT服务与云端数据交互";
        this.useMQTTChangeDataWithCloud = 0;
        this.useMQTTChangeDataWithCloudDesc = "是=MQTT方式，否=传统方式（默认）";
        this.mqttServerContext = "MQTT服务器";
        this.mqttServer = "exchange.eparking.top";
        this.mqttServerDesc = "exchange.eparking.top";
        this.tempcarFreeAutoOutContext = "临时车在免费时间内出场是否自动放行";
        this.tempcarFreeAutoOut = 0;
        this.tempcarFreeAutoOutDesc = "默认是";
        this.tempcarCancelOutIncludeReceivablesContext = "临时车按F10取消出场后是否计入应收";
        this.tempcarCancelOutIncludeReceivables = 0;
        this.tempcarCancelOutIncludeReceivablesDesc = "默认是";
        this.tempcarChargeRulesContext = "临时车收费规则";
        this.tempcarChargeRules = 1;
        this.tempcarChargeRulesDesc = "默认标准：1@规则一（标准），2@规则二，3@规则三，4@规则四，5@规则五，6@规则六";
        this.tempcarInEnableAsLotfullContext = "剩余车位为0时临时车是否允许自动入场";
        this.tempcarInEnableAsLotfull = 0;
        this.tempcarInEnableAsLotfullDesc = "默认是";
        this.inSpecialCarInterveneContext = "特殊（类）车牌入场干预，不区分月租或临停";
        this.inSpecialCarIntervene = "";
        this.inSpecialCarInterveneDesc = "如不限制则不填，如有则填写全车牌或部分车牌。如：鄂";
        this.outSpecialCarInterveneContext = "特殊（类）车牌出场干预，不区分月租或临停";
        this.outSpecialCarIntervene = "";
        this.outSpecialCarInterveneDesc = "如不限制则不填，如有则填写全车牌或部分车牌。如：鄂";
        this.noEntryChargeContext = "无入场记录须缴费车辆强制按固定金额收费（0.0=不启用本功能）";
        this.noEntryCharge = 0.0;
        this.noEntryChargeDesc = "0=无强制收费，单位：元";
        this.freeSecondsPrepayContext = "预缴后场内免计费时间（分钟）";
        this.freeSecondsPrepay = 15;
        this.freeSecondsPrepayDesc = "默认15分钟";
        this.bzPayEnaleContext = "是否启用云闪付（博众聚合）";
        this.bzPayEnale = 0;
        this.bzPayEnaleDesc = "默认0（否）；1（是）";
        this.bzPayAutoOpenEnableContext = "云闪付（博众支付）成功后是否自动开闸";
        this.bzPayAutoOpenEnable = 0;
        this.bzPayAutoOpenEnableDesc = "默认是";
        this.otherPayEnableContext = "是否启用其他无感支付";
        this.otherPayEnable = 0;
        this.otherPayEnableDesc = "默认0（否）；1（银联）；2（优停）";
        this.enableTerminalUrlContext = "支付Terminal的URL";
        this.enableTerminalUrl = "";
        this.enableTerminalUrlDesc = "例：本地=http://127.0.0.1:8080";
        this.limitFullCouponContext = "商户优惠全免时效限制";
        this.limitFullCoupon = 0;
        this.limitFullCouponDesc = "默认有";
        this.isSpecialCarChargeRuleContext = "新能源车按其他车规则计费";
        this.isSpecialCarChargeRule = 0;
        this.isSpecialCarChargeRuleDesc = "是=允许，否=不允许（默认）";
        this.terminalInTypeContext = "支付Terminal接入方式";
        this.terminalInType = 1;
        this.terminalInTypeDesc = "1=本地（默认），2=云端";
        this.ePayAutoRequestContext = "是否自动申请电子扣费";
        this.ePayAutoRequest = 0;
        this.ePayAutoRequestDesc = "默认是";
        this.enableTerminalIndependentNoticeOutContext = "是否支持Terminal的独立通知出场指令";
        this.enableTerminalIndependentNoticeOut = 0;
        this.enableTerminalIndependentNoticeOutDesc = "默认否";
        this.requestRealTimeCouponInfoContext = "出场是否实时获取优惠代缴信息";
        this.requestRealTimeCouponInfo = 0;
        this.requestRealTimeCouponInfoDesc = "默认否";
        this.prepayOuttimeReCalModeContext = "预缴超时出场后重新计费模式";
        this.prepayOuttimeReCalMode = 1;
        this.prepayOuttimeReCalModeDesc = "模式1（默认）=按入场时间重新计费，模式2=按预缴时间重新计费";
    }
}
