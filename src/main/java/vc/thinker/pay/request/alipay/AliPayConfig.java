package vc.thinker.pay.request.alipay;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import vc.thinker.pay.DBPayConfigFactory;
import vc.thinker.pay.alipay.AlipayConfig;
import vc.thinker.pay.request.wechat.WechatConfig;
import vc.thinker.utils.SpringContextHolder;

/**
 * @Author: HeTongHao
 * @Date: 18/12/19 14:39
 * @Description:配置公共参数
 */
public final class AliPayConfig {
    /**
     * 应用号
     */
    public String APP_ID;
    /**
     * 商户的私钥
     */
    public String APP_PRIVATE_KEY;

    /**
     * 支付宝公钥
     */
    public String ALIPAY_PUBLIC_KEY;

    /**
     * 签名方式
     */
    public String SIGN_TYPE;
    /**
     * 编码
     */
    public static String CHARSET = "utf-8";

    /**
     * 支付宝网关地址
     */
    //
    private static String GATEWAY = "https://openapi.alipay.com/gateway.do";
    /**
     * 成功付款回调
     */
    public static String PAY_NOTIFY = "http://yule.com/pay/ali.html";
    /**
     * 支付成功回调
     */
    public static String REFUND_NOTIFY = "http://yule.com/notify/alipay_refund";
    /**
     * 前台通知地址
     */
    public static String RETURN_URL = "http://yule.com/return/alipay.html";
    /**
     * wap前台通知地址
     */
    public static String WAP_RETURN_URL = "http://yule.com/return/alipay.html";
    /**
     * 参数类型
     */
    public static String PARAM_TYPE = "json";
    /**
     * 成功标识
     */
    public static final String SUCCESS_REQUEST = "TRADE_SUCCESS";
    /**
     * 交易关闭回调(当该笔订单全部退款完毕,则交易关闭)
     */
    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    /**
     * 支付宝请求客户端入口
     */
    private volatile static AlipayClient alipayClient = null;
    /**
     * 单例
     */
    private volatile static AliPayConfig aliPayConfig = null;

    /**
     * 不可外部实例化
     */
    private AliPayConfig(String APP_ID, String APP_PRIVATE_KEY, String ALIPAY_PUBLIC_KEY, String SIGN_TYPE) {
        this.APP_ID = APP_ID;
        this.APP_PRIVATE_KEY = APP_PRIVATE_KEY;
        this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
        this.SIGN_TYPE = SIGN_TYPE;
    }

    /**
     * 数据库配置标识
     */
    public static final String CONFIG_MARC = "alipay_app";

    /**
     * 双重锁单例
     *
     * @return WechatConfig实例
     */
    public static AliPayConfig getInstance() {
        if (aliPayConfig == null) {
            synchronized (WechatConfig.class) {
                if (aliPayConfig == null) {
                    DBPayConfigFactory dbPayConfigFactory = SpringContextHolder.getBean(DBPayConfigFactory.class);
                    if (dbPayConfigFactory == null) {
                        throw new IllegalStateException("属性未注入, 请在applicationContext.xml中定义DBPayConfigFactory.");
                    }
                    AlipayConfig alipayConfig = (AlipayConfig) dbPayConfigFactory.getPayConfig(CONFIG_MARC);
                    aliPayConfig = new AliPayConfig(alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
                }
            }
        }
        return aliPayConfig;
    }

    /**
     * 双重锁单例
     *
     * @return 支付宝请求客户端实例
     */
    public static AlipayClient getAlipayClientInstance() {
        if (alipayClient == null) {
            synchronized (AliPayConfig.class) {
                //没有配置
                if (aliPayConfig == null) {
                    //获取配置
                    getInstance();
                }
                alipayClient = new DefaultAlipayClient(GATEWAY, aliPayConfig.getAPP_ID(), aliPayConfig.getAPP_PRIVATE_KEY(), PARAM_TYPE, CHARSET, aliPayConfig.getALIPAY_PUBLIC_KEY(), aliPayConfig.getSIGN_TYPE());
            }
        }
        return alipayClient;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public String getAPP_PRIVATE_KEY() {
        return APP_PRIVATE_KEY;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public String getSIGN_TYPE() {
        return SIGN_TYPE;
    }
}
