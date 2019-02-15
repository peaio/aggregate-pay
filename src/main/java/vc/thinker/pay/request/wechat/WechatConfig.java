package vc.thinker.pay.request.wechat;

import vc.thinker.pay.DBPayConfigFactory;
import vc.thinker.pay.weixin.WeixinConfig;
import vc.thinker.utils.SpringContextHolder;

/**
 * @Author: HeTongHao
 * @Date: 18/12/19 14:39
 * @Description:微信支付配置
 */
public final class WechatConfig {
    /**
     * 用户的id
     */
    public String appId;
    /**
     * 公众号AppSecret
     */
    public String jsapiAppSecret;
    /**
     * 商户api密钥(商户key)
     */
    public String appSecret;
    /**
     * 商户id
     */
    public String mchId;
    /**
     * 统一下单地址
     */
    public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 交易退款地址
     */
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 交易退款查询地址
     */
    public static final String REFUND_QUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
    /**
     * 订单查询
     */
    public static final String OUERY_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    /**
     * 支付成功回调
     */
    public static final String NOTIFY_PAY = "http://yule.com/notify/wechat.html";
    /**
     * 支付类型
     */
    public static final String TRADE_TYPE = "NATIVE";
    public static final String M_TRADE_TYPE = "MWEB";
    public static final String WECHAT_TRADE_TYPE = "JSAPI";
    /**
     * 证书地址
     */
    public static final String CERT_PATH = "wechat/cert/apiclient_cert.p12";
    /**
     * 成功标识
     */
    public static final String SUCCESS_REQUEST = "SUCCUSS";

    /**
     * 不可实例化
     */
    private WechatConfig(String appId, String mchId, String appSecret, String jsapiAppSecret) {
        this.appId = appId;
        this.mchId = mchId;
        this.appSecret = appSecret;
        this.jsapiAppSecret = jsapiAppSecret;
    }

    /**
     * 微信请求客户端入口
     */
    private volatile static WechatClient wechatClient = null;
    /**
     * 单例
     */
    private volatile static WechatConfig wechatConfig = null;

    /**
     * 数据库配置标识
     */
    public static final String CONFIG_MARC = "wx_js";

    /**
     * 双重锁单例
     *
     * @return WechatConfig实例
     */
    public static WechatConfig getInstance() {
        if (wechatConfig == null) {
            synchronized (WechatConfig.class) {
                if (wechatConfig == null) {
                    DBPayConfigFactory dbPayConfigFactory = SpringContextHolder.getBean(DBPayConfigFactory.class);
                    if (dbPayConfigFactory == null) {
                        throw new IllegalStateException("属性未注入, 请在applicationContext.xml中定义DBPayConfigFactory.");
                    }
                    WeixinConfig weixinConfig = (WeixinConfig) dbPayConfigFactory.getPayConfig(CONFIG_MARC);
                    wechatConfig = new WechatConfig(weixinConfig.getWxAppId(), weixinConfig.getTenpayPartner(), weixinConfig.getWxPaysignkey(), weixinConfig.getPayBankPublicKey());
                }
            }
        }
        return wechatConfig;
    }

    /**
     * 双重锁单例
     *
     * @return WechatClient实例
     */
    public static WechatClient getWechatClientInstance() {
        if (wechatClient == null) {
            synchronized (WechatConfig.class) {
                if (wechatClient == null) {
                    if (wechatConfig == null) {
                        getInstance();
                    }
                    return new WechatClient(wechatConfig.getAppId(), wechatConfig.getMchId(), wechatConfig.getAppSecret(), WECHAT_TRADE_TYPE);
                }
            }
        }
        return wechatClient;
    }

    public String getAppId() {
        return appId;
    }

    public String getJsapiAppSecret() {
        return jsapiAppSecret;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getMchId() {
        return mchId;
    }
}
