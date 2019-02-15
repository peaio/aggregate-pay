package vc.thinker.pay.action;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.sinco.common.utils.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vc.thinker.pay.PayHandler;
import vc.thinker.pay.request.alipay.AliPayConfig;
import vc.thinker.pay.request.alipay.AlipayTrade;
import vc.thinker.pay.request.constants.PayConstants;
import vc.thinker.pay.request.entity.AggregatePayInfo;
import vc.thinker.pay.request.utils.HttpUtils;
import vc.thinker.pay.request.utils.UrlUtils;
import vc.thinker.pay.request.wechat.WechatClient;
import vc.thinker.pay.request.wechat.WechatConfig;
import vc.thinker.pay.request.wechat.WechatUnifiedOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @Author: HeTongHao
 * @Date: 18/12/20 11:17
 * @Description:
 */
public abstract class BaseAggregatePayController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${pay.callback}")
    private String domain;

    /**
     * 微信支付成功回调地址
     */
    @Value("${pay.callback}/weixin/notify")
    private String wechatPayNotifyUrl;
    /**
     * 支付宝支付成功回调地址
     */
    @Value("${pay.callback}/alipay/notify")
    private String aliPayNotifyUrl;

    /**
     * 支付宝支付回调页面(可不配置)
     */
    @Value("${pay.aliPayReturn:#{null}}")
    private String aliPayReturn;


    /**
     * 获取给用户扫的二维码
     *
     * @return
     */
    @RequestMapping(value = "getQrCode", method = RequestMethod.GET)
    @ResponseBody
    public String getQrCode(HttpServletRequest request, String oid, String businessType, String price) {
        return UrlUtils.stitchingUrl(domain, "/aggregatePay",
                "/pay?oid=" + oid + "&businessType=" + businessType + "&price=" + price);
    }

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public abstract ModelAndView pay(HttpServletRequest request, HttpServletResponse response);


    /**
     * 给用户展示付款页面
     *
     * @param request
     * @param response
     * @return
     */
    protected ModelAndView returnPayPage(HttpServletRequest request, HttpServletResponse response, AggregatePayInfo aggregatePayInfo) {
        //记录用户ip
        aggregatePayInfo.setClientIp(IPUtil.getIpAddr(request));
        //业务类型
        aggregatePayInfo.getPassBackParams().put(PayHandler.ATTACH_KEY_REQ_ATTACH, aggregatePayInfo.getBusinessType());
        Map<String, Object> map = new ModelMap();
        //临时页面，支付宝用于展示表单，微信则需要根据返回的参数再次调起支付请求
        ModelAndView mav = new ModelAndView("aggregate_pay_temp");
        String userAgent = request.getHeader("User-Agent");
        //判断浏览器为微信
        if (userAgent.toLowerCase().contains(PayConstants.MICRO_MESSENGER.toLowerCase())) {
            logger.info(PayConstants.PAY_MODE_WECHAT + "扫码进入...");
            aggregatePayInfo.setPayType(PayConstants.PAY_MODE_WECHAT);
            //微信公众号支付路径需要添加授权
            String openid = handleWechatBrow(request, response, UrlUtils.stitchingUrl(domain, request.getRequestURI()));
            if (StringUtils.isNotBlank(openid)) {
                map = this.morderConsume(aggregatePayInfo, openid);
                mav.addAllObjects(map);
            }
            //判断浏览器为支付宝
        } else if (userAgent.toLowerCase().contains(PayConstants.ALI_PAY_CLIENT.toLowerCase())) {
            logger.info(PayConstants.PAY_MODE_ALI + "扫码进入...");
            aggregatePayInfo.setPayType(PayConstants.PAY_MODE_ALI);
            String form = mAlipayOrder(aggregatePayInfo);
            map.put("form", form);
            mav.addAllObjects(map);
        } else {
            logger.error("不支持的支付浏览器");
        }
        return mav;
    }

    /**
     * web支付宝处理
     *
     * @return
     */
//    @RequestMapping("/pay/web")
//    @ResponseBody
//    public Map<String, Object> webAlipay(HttpServletRequest request) {
//        String oid = request.getParameter("oid");//重新下单
//        Map<String, Object> resultMap = new HashMap<>();
//        try {
//            if (oid.equals(aggregatePayInfo.getOrderNo()) && aggregatePayInfo != null && aggregatePayInfo.getOrderStatus() == 0) {
//                aggregatePayInfo.setClientIp("0.0.0.0");//ip
//                System.out.println("web支付宝支付...");
//                aggregatePayInfo.setPayType(PayConstants.PAY_MODE_ALI);//payType
//                String form = alipayOrder(aggregatePayInfo);
//                resultMap.put("form", form);
//            } else {
//                System.out.println("一码两付订单不存在");
//            }
//        } catch (Exception e) {
//            System.out.println("web支付宝支付异常");
//            e.printStackTrace();
//        }
//        return resultMap;
//    }

    /**
     * m微信下单
     *
     * @param aggregatePayInfo
     * @return
     */
    private WechatUnifiedOrder.Response mWechatOrder(AggregatePayInfo aggregatePayInfo, String openid) {
        WechatUnifiedOrder request = new WechatUnifiedOrder();
        String goodsName = aggregatePayInfo.getGoodsName();
        if (goodsName != null && goodsName.length() > 40) {
            goodsName = goodsName.substring(0, 40);
        }
        request.setBody(goodsName);
        request.setDetail(aggregatePayInfo.getBody());
        request.setOut_trade_no(aggregatePayInfo.getOrderNo());
        request.setFee_type("CNY");
        //回传参数
        Map<String, String> passBackParams = aggregatePayInfo.getPassBackParams();
        passBackParams.put(PayHandler.ATTACH_KEY_CONFIG_MARK, WechatConfig.CONFIG_MARC);
        request.setAttach(JSON.toJSONString(passBackParams));
        //乘100，变成分钱
        BigDecimal minute = aggregatePayInfo.getPayPrice().multiply(new BigDecimal(100).setScale(0, BigDecimal.ROUND_DOWN));
        request.setTotal_fee(minute.intValue());
        request.setSpbill_create_ip(aggregatePayInfo.getClientIp());
        request.setTime_start(System.currentTimeMillis() + "");
        request.setNotify_url(wechatPayNotifyUrl);
        if (StringUtils.isNotEmpty(openid)) {
            request.setOpenid(openid);
            request.setTrade_type(WechatConfig.WECHAT_TRADE_TYPE);
        } else {
            request.setTrade_type(WechatConfig.TRADE_TYPE);
        }
        return WechatConfig.getWechatClientInstance().unifiedOrder(request);
    }

    /**
     * m版下单
     *
     * @param aggregatePayInfo
     * @param openid           公众号支付openid
     * @return
     */
    private Map<String, Object> morderConsume(AggregatePayInfo aggregatePayInfo, String openid) {
        HashMap<String, Object> res = new HashMap<>();
        String orderNo = null;
        try {
            res.put("result", PayConstants.RESULT_SUCC);
            orderNo = aggregatePayInfo.getOrderNo();
            String mode = aggregatePayInfo.getPayType();
            //微信支付
            if (StringUtils.equals(mode, PayConstants.PAY_MODE_WECHAT)) {
                WechatUnifiedOrder.Response resp = this.mWechatOrder(aggregatePayInfo, openid);
                if (StringUtils.equals(resp.getResult_code(), PayConstants.WECHAT_RESULT_CODE_SUCC)) {
                    if (StringUtils.isNotEmpty(openid)) {
                        res = this.wechatJSAPI(resp, res);
                    }
                } else {
                    res.put("result", PayConstants.WECHAT_RESULT_CODE_FAIL);
                }
            }
        } catch (Exception e) {
            res.put("result", PayConstants.RESULT_ERROR);
            res.put("msg", e.getMessage());
        }
        res.put("orderNo", orderNo);
        return res;
    }

    private HashMap<String, Object> wechatJSAPI(WechatUnifiedOrder.Response resp, HashMap<String, Object> map) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        TreeMap<String, Object> tm = new TreeMap<>();
        tm.put("appId", resp.getAppid());
        tm.put("timeStamp", timeStamp);
        tm.put("nonceStr", resp.getNonce_str());
        tm.put("package", "prepay_id=" + resp.getPrepay_id());
        tm.put("signType", "MD5");
        map.put("appId", resp.getAppid());//公众号名称，由商户传入
        map.put("timeStamp", timeStamp);//时间戳，自1970年以来的秒数
        map.put("nonceStr", resp.getNonce_str());//随机串
        map.put("prepay_id", resp.getPrepay_id());
        map.put("signType", "MD5"); //微信签名方式：
        WechatClient instance = WechatConfig.getWechatClientInstance();
        map.put("paySign", (instance.sign(tm)).toUpperCase()); //微信签名
        return map;
    }

    /**
     * 微信内置浏览器 支付处理
     *
     * @param request
     * @return
     */
    private String handleWechatBrow(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
        try {
            String code = request.getParameter("code");
            boolean wechatBrow = isWechatBrow(request);
            if (wechatBrow && StringUtils.isNotEmpty(code)) {
                return getWxOpenid(request, code);
            } else if (wechatBrow) {
                String queryurl = request.getQueryString();
                if (null != queryurl) {
                    redirectUrl += "?" + queryurl;
                }
                String redirect_uri = URLEncoder.encode(redirectUrl, "UTF-8");
                String redirect = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WechatConfig.getInstance().getAppId() + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                response.sendRedirect(redirect);
            }
        } catch (IOException e) {

        }
        return null;
    }

    /**
     * 微信浏览器
     *
     * @param request
     * @return
     */
    private boolean isWechatBrow(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if (userAgent.indexOf("micromessenger") > -1) {//微信客户端
            return true;
        } else {
            return false;
        }
    }

    /**
     * 微信内置浏览器 获取openid
     *
     * @param request
     * @param code
     * @return
     */
    private String getWxOpenid(HttpServletRequest request, String code) {
        try {
            WechatConfig wechatConfig = WechatConfig.getInstance();
            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", wechatConfig.getAppId(), wechatConfig.getJsapiAppSecret(), code);
            String wxuser = HttpUtils.httpsGet(url);
            Map<String, String> o = new Gson().fromJson(wxuser, Map.class);
            return o.get("openid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * pc 支付宝下单
     *
     * @param aggregatePayInfo
     * @return
     */
    public String alipayOrder(AggregatePayInfo aggregatePayInfo) {
        try {
            AlipayTrade alipayTrade = new AlipayTrade();
            Map<String, String> paraMap = new HashMap<>();
            //商户订单号，商户网站订单系统中唯一订单号，必填
            paraMap.put("out_trade_no", aggregatePayInfo.getOrderNo());
            paraMap.put("total_amount", String.valueOf(aggregatePayInfo.getPayPrice()));
            String goodsName = aggregatePayInfo.getGoodsName();
            if (goodsName != null && goodsName.length() > 40) {
                goodsName = goodsName.substring(0, 40);
            }
            paraMap.put("subject", goodsName);
            paraMap.put("body", aggregatePayInfo.getBody());
            paraMap.put("product_code", "FAST_INSTANT_TRADE_PAY");
            paraMap.put("notifyUrl", aliPayNotifyUrl);
            if (StringUtils.isNotBlank(aliPayReturn)) {
                paraMap.put("returnUrl", aliPayReturn);
            }
            return alipayTrade.TradeWapPayRequest(paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * m 支付宝下单
     *
     * @param aggregatePayInfo
     * @return
     */
    public String mAlipayOrder(AggregatePayInfo aggregatePayInfo) {
        try {
            AlipayTrade alipayTrade = new AlipayTrade();
            Map<String, String> paraMap = new HashMap<>();
            //商户订单号，商户网站订单系统中唯一订单号，必填
            paraMap.put("out_trade_no", aggregatePayInfo.getOrderNo());
            //总金额,取值范围最小0.01
            Double totalAmount = aggregatePayInfo.getPayPrice().setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            paraMap.put("total_amount", String.valueOf(totalAmount));
            String goodsName = aggregatePayInfo.getGoodsName();
            if (goodsName != null && goodsName.length() > 40) {
                goodsName = goodsName.substring(0, 40);
            }
            paraMap.put("subject", goodsName);
            paraMap.put("body", aggregatePayInfo.getBody());
            //回传参数
            Map<String, String> passBackParams = aggregatePayInfo.getPassBackParams();
            //配置mark
            passBackParams.put(PayHandler.ATTACH_KEY_CONFIG_MARK, AliPayConfig.CONFIG_MARC);
            paraMap.put("passback_params", JSON.toJSONString(passBackParams));
            paraMap.put("product_code", "QUICK_WAP_WAY");
            paraMap.put("timeout_express", "120");
            paraMap.put("notifyUrl", aliPayNotifyUrl);
            if (StringUtils.isNotBlank(aliPayReturn)) {
                paraMap.put("returnUrl", aliPayReturn);
            }
            return alipayTrade.mobilePayRequest(paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/hello")
    public String index(HashMap<String, Object> map) {
        map.put("greeting", "欢迎进入HTML页面");
        map.put("sayHi", "hello world!");
        System.out.println("欢迎进入HTML页面");
        return "/index";
    }


    /**
     * 生成预订单
     *
     * @return
     */
    @RequestMapping("/pay/gen")
    @ResponseBody
    public Map<String, Object> preOrder() {
        Map result = new HashMap();
        String oid = new Random().nextLong() + "";
        result.put("oid", oid);
        return result;
    }
}
