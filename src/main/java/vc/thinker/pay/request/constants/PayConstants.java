package vc.thinker.pay.request.constants;

/**
 * @Author: HeTongHao
 * @Date: 18/12/14 10:30
 * @Description:
 */
public class PayConstants {

    //支付方式
    public static final String PAY_MODE_WECHAT = "wechat";//微信支付
    public static final String PAY_MODE_ALI = "alipay";//支付宝

    /**
     * 判断user-Agent是微信还是支付宝浏览器的关键字
     */
    public static final String ALI_PAY_CLIENT = "AliPayClient";
    public static final String MICRO_MESSENGER = "MicroMessenger";

    //微信支付下单返回结果
    public static final String WECHAT_RESULT_CODE_SUCC = "SUCCESS";
    public static final String WECHAT_RESULT_CODE_FAIL = "FAIL";

    //结果
    public static final String RESULT_SUCC = "succ";
    public static final String RESULT_ERROR = "error";
    public static final String RESULT_FAIL = "fail";

    public static final String SUCC_CODE = "Y";
    public static final String ERROR_CODE = "N";
    public static final String FAIL_CODE = "F";
}
