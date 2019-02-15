package vc.thinker.pay.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vc.thinker.pay.request.entity.AggregatePayInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @Author: HeTongHao
 * @Date: 18/12/19 14:39
 * @Description:聚合支付(支付宝、微信二码合一)
 */
@Controller
@RequestMapping("aggregatePay")
public class ControllerAggregatePayController extends BaseAggregatePayController {
    @Override
    public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) {
        //订单号
        String orderId = request.getParameter("oid");
        //业务类型(用于回传，区分不同业务)
        String businessType = request.getParameter("businessType");
        /**
         * 取预订单信息(封装到聚合支付信息)
         */
        AggregatePayInfo aggregatePayInfo = new AggregatePayInfo();
        //订单号
        aggregatePayInfo.setOrderNo(orderId);
        //金额p
        aggregatePayInfo.setPayPrice(new BigDecimal(request.getParameter("price")));
        //aggregatePayInfo.setPayPrice(new BigDecimal(0.01));
        //商品名称
        aggregatePayInfo.setGoodsName("停车费");
        //业务类型(用于回传，区分不同业务)
        aggregatePayInfo.setBusinessType(businessType);
        //body
        aggregatePayInfo.setBody("");
        return super.returnPayPage(request, response, aggregatePayInfo);
    }
}
