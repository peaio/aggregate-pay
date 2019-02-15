package vc.thinker.pay.request.entity;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: HeTongHao
 * @Date: 18/12/19 14:39
 * @Description:支付信息信息表
 */
public class AggregatePayInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单金额
     */
    private BigDecimal payPrice = BigDecimal.ZERO;
    /**
     * 回传参数
     */
    private Map<String, String> passBackParams = Maps.newHashMap();
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 客户端ip
     */
    private String clientIp;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 订单主体
     */
    private String body;
    /**
     * 支付类型 wechat-微信支付 alipay-支付宝 union-银联
     */
    private String payType;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Map<String, String> getPassBackParams() {
        return passBackParams;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public String getPayPriceFormat() {
        if (payPrice != null) {
            return payPrice.setScale(2, BigDecimal.ROUND_UP) + "\t";
        }
        return "0";
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
