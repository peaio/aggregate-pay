package vc.thinker.pay.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;

public class PayConfig extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private BigDecimal alipayRate;

    /**  **/
    private String appPrivateKey;

    /**  **/
    private String appPublicKey;

    /**  **/
    private String chinabankAccount;

    /**  **/
    private String chinabankKey;

    /**  **/
    private String content;

    /** 支付货币种类 **/
    private String currencyCode;

    /**  **/
    private Boolean install;

    /**  **/
    private Integer interfaceType;

    /**  **/
    private String mark;

    /**  **/
    private String merchantAcctid;

    /**  **/
    private String name;

    /**  **/
    private String partner;

    /** paypal商户id **/
    private String paypalUserId;

    /**  **/
    private String pid;

    /** 支付手续费 **/
    private BigDecimal poundage;

    /**  **/
    private String rmbKey;

    /**  **/
    private String safeKey;

    /**  **/
    private String sellerEmail;

    /**  **/
    private String spname;

    /**  **/
    private String tenpayKey;

    /**  **/
    private String tenpayPartner;

    /**  **/
    private Integer tradeMode;

    /** 微信支付AppSecret **/
    private String wxAppsecret;

    /** 微信支付appid **/
    private String wxAppid;

    /** 微信支付paySignKey **/
    private String wxPaysignkey;

    /**  **/
    private String payChannel;

    /**  **/
    private String signType;

    /** 支付宝应用ID **/
    private String alipayAppId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAlipayRate() {
        return alipayRate;
    }

    public void setAlipayRate(BigDecimal alipayRate) {
        this.alipayRate = alipayRate;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public String getChinabankAccount() {
        return chinabankAccount;
    }

    public void setChinabankAccount(String chinabankAccount) {
        this.chinabankAccount = chinabankAccount;
    }

    public String getChinabankKey() {
        return chinabankKey;
    }

    public void setChinabankKey(String chinabankKey) {
        this.chinabankKey = chinabankKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Boolean getInstall() {
        return install;
    }

    public void setInstall(Boolean install) {
        this.install = install;
    }

    public Integer getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Integer interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMerchantAcctid() {
        return merchantAcctid;
    }

    public void setMerchantAcctid(String merchantAcctid) {
        this.merchantAcctid = merchantAcctid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPaypalUserId() {
        return paypalUserId;
    }

    public void setPaypalUserId(String paypalUserId) {
        this.paypalUserId = paypalUserId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public String getRmbKey() {
        return rmbKey;
    }

    public void setRmbKey(String rmbKey) {
        this.rmbKey = rmbKey;
    }

    public String getSafeKey() {
        return safeKey;
    }

    public void setSafeKey(String safeKey) {
        this.safeKey = safeKey;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public String getTenpayKey() {
        return tenpayKey;
    }

    public void setTenpayKey(String tenpayKey) {
        this.tenpayKey = tenpayKey;
    }

    public String getTenpayPartner() {
        return tenpayPartner;
    }

    public void setTenpayPartner(String tenpayPartner) {
        this.tenpayPartner = tenpayPartner;
    }

    public Integer getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(Integer tradeMode) {
        this.tradeMode = tradeMode;
    }

    public String getWxAppsecret() {
        return wxAppsecret;
    }

    public void setWxAppsecret(String wxAppsecret) {
        this.wxAppsecret = wxAppsecret;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getWxPaysignkey() {
        return wxPaysignkey;
    }

    public void setWxPaysignkey(String wxPaysignkey) {
        this.wxPaysignkey = wxPaysignkey;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAlipayAppId() {
        return alipayAppId;
    }

    public void setAlipayAppId(String alipayAppId) {
        this.alipayAppId = alipayAppId;
    }
}