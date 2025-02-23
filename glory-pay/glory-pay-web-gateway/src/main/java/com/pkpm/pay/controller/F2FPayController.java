package com.pkpm.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.pkpm.pay.common.core.utils.DateUtils;
import com.pkpm.pay.controller.common.BaseController;
import com.pkpm.pay.service.CnpPayService;
import com.pkpm.pay.trade.exception.TradeBizException;
import com.pkpm.pay.trade.service.RpTradePaymentManagerService;
import com.pkpm.pay.trade.service.RpTradePaymentQueryService;
import com.pkpm.pay.trade.utils.MerchantApiUtil;
import com.pkpm.pay.trade.vo.F2FPayResultVo;
import com.pkpm.pay.user.entity.RpUserPayConfig;
import com.pkpm.pay.user.exception.UserBizException;
import com.pkpm.pay.user.service.RpUserPayConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <b>功能说明:
 * </b>
 *
 * @author jansure
 *
 */
@Controller
@RequestMapping(value = "/f2fPay")
public class F2FPayController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(F2FPayController.class);

    @Autowired
    private RpTradePaymentManagerService rpTradePaymentManagerService;

    @Autowired
    private RpUserPayConfigService rpUserPayConfigService;

    @Autowired
    private CnpPayService cnpPayService;

    @Autowired
    private RpTradePaymentQueryService queryService;

    /**
     * 条码支付,商户通过前置设备获取到用户支付授权码后,请求支付网关支付.
     *
     * @return
     */
    @RequestMapping("/doPay")
    public String initPay(HttpServletRequest httpServletRequest, ModelMap modelMap) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //获取商户传入参数
        String payKey = getString_UrlDecode_UTF8("payKey"); // 企业支付KEY
        paramMap.put("payKey", payKey);
        String authCode = getString_UrlDecode_UTF8("authCode"); // 企业支付KEY
        paramMap.put("authCode", authCode);
        String productName = getString_UrlDecode_UTF8("productName"); // 商品名称
        paramMap.put("productName", productName);
        String orderNo = getString_UrlDecode_UTF8("orderNo"); // 订单编号
        paramMap.put("orderNo", orderNo);
        String orderPriceStr = getString_UrlDecode_UTF8("orderPrice"); // 订单金额 , 单位:元
        paramMap.put("orderPrice", orderPriceStr);
        String payWayCode = getString_UrlDecode_UTF8("payWayCode"); // 支付方式编码 支付宝: ALIPAY  微信:WEIXIN
        paramMap.put("payWayCode", payWayCode);
        String orderIp = getString_UrlDecode_UTF8("orderIp"); // 下单IP
        paramMap.put("orderIp", orderIp);
        String orderDateStr = getString_UrlDecode_UTF8("orderDate"); // 订单日期
        paramMap.put("orderDate", orderDateStr);
        String orderTimeStr = getString_UrlDecode_UTF8("orderTime"); // 订单日期
        paramMap.put("orderTime", orderTimeStr);
        String remark = getString_UrlDecode_UTF8("remark"); // 支付备注
        paramMap.put("remark", remark);
        String sign = getString_UrlDecode_UTF8("sign"); // 签名

        String field1 = getString_UrlDecode_UTF8("field1"); // 扩展字段1
        paramMap.put("field1", field1);
        String field2 = getString_UrlDecode_UTF8("field2"); // 扩展字段2
        paramMap.put("field2", field2);
        String field3 = getString_UrlDecode_UTF8("field3"); // 扩展字段3
        paramMap.put("field3", field3);
        String field4 = getString_UrlDecode_UTF8("field4"); // 扩展字段4
        paramMap.put("field4", field4);
        String field5 = getString_UrlDecode_UTF8("field5"); // 扩展字段5
        paramMap.put("field5", field5);
        //格式化时间
        Date orderDate = DateUtils.parseDate(orderDateStr, "yyyyMMdd");
        Date orderTime = DateUtils.parseDate(orderTimeStr, "yyyyMMddHHmmss");

        //获取支付配置
        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付配置有误");
        }
        //ip校验
        cnpPayService.checkIp(rpUserPayConfig, httpServletRequest);
        //验签
        if (!MerchantApiUtil.isRightSign(paramMap, rpUserPayConfig.getPaySecret(), sign)) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订单签名异常");
        }

        //发起支付
        BigDecimal orderPrice = BigDecimal.valueOf(Double.valueOf(orderPriceStr));
        F2FPayResultVo f2FPayResultVo = rpTradePaymentManagerService.f2fPay(payKey, authCode, productName, orderNo, orderDate, orderTime, orderPrice, payWayCode, orderIp, remark, field1, field2, field3, field4, field5);

        //String payResultJson = JSONObject.toJSONString(f2FPayResultVo);
        logger.debug("条码支付--支付结果==>{}", f2FPayResultVo);
        modelMap.put("result", f2FPayResultVo);
        // httpServletResponse.setContentType("text/text;charset=UTF-8");
        // write(httpServletResponse, payResultJson);
        return "/f2fAffirmPay";
    }

    @RequestMapping("/order/query")
    @ResponseBody
    public String orderQuery(String trxNo) {
        return JSONObject.toJSONString(queryService.getRecordByTrxNo(trxNo));
    }

}
