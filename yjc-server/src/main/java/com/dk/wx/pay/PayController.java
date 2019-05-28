package com.dk.wx.pay;

import com.dk.config.Constant;
import com.dk.data.entity.Appointment;
import com.dk.data.entity.Order;
import com.dk.data.entity.WxUser;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.AppointmentService;
import com.dk.service.OrderService;
import com.dk.service.WxUserService;
import com.dk.shiro.JWToken;
import com.dk.utils.WXPayUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Api(value = "pay", description = "微信支付接口")
@RestController
@RequestMapping("rest/pay")
public class PayController {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/prepay", method = RequestMethod.GET)
    private WrappedResponse createOrder(String orderUuid) throws WxPayException, ParserConfigurationException {

        WrappedResponse result = new WrappedResponse();

        //获取订单信息
        Order order = orderService.findByUuid(orderUuid);

        WxUser wxUser = wxUserService.findByUuid(order.getUser());

        //获取接口数据拼装
//        OrderList orderList =smcyService.getOrderDetailService(orderId);


        //构造统一下单的请求参数
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("易检测");
        orderRequest.setNotifyUrl("http://www.jiankangchou.cn/index.html");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String str = df.format(new Date()).replace("-", "").toLowerCase();
        orderRequest.setOutTradeNo(str+new Random().nextInt());

        orderRequest.setOpenid(wxUser.getOpenid());
        orderRequest.setSpbillCreateIp("117.34.176.28");
//        orderRequest.setTimeStart("yyyyMMddHHmmss");
//        orderRequest.setTimeExpire("yyyyMMddHHmmss");
//        Double d1 = Double.parseDouble(orderList.getOrderTotal());
        Double d1 = 1.0d;
        Double d2 = d1 * 100;
        orderRequest.setTotalFee((d1.intValue()));//元转成分
        orderRequest.setTradeType("JSAPI");

        WxPayMpOrderResult payResult = wxPayService.createOrder(orderRequest);
        if (StringUtils.isEmpty(payResult)) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
        } else {
            result.setCode(HttpStateCode.OK.getValue());
            result.setMsg(HttpStateCode.OK.getReasonPhrase());
            result.setSuccessObject(payResult);
        }
        return result;
    }

    /*
     * @Description
     * @Date 2018/12/21
     * @Param 订单uuid 改约时间 改约金额
     * @return
     * @Author zhy
     **/
    @PostMapping("appointmentPrepay")
    private WrappedResponse appointmentPrepay(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                              @RequestBody Order param) throws WxPayException {
        WrappedResponse result = new WrappedResponse();
        if (param == null || param.getUuid() == null || param.getRescheduleTime() == null) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if (token == null && "".equals(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);

        //获取订单信息
        Order bean = orderService.findByUserAndUuid(user, param.getUuid());
        if (param.getReschedulePrice() == null || param.getReschedulePrice().compareTo(BigDecimal.ZERO) == 0) {
            Boolean flag = appointmentService.isExtraPrice(bean.getUuid());
            if (flag) {
                result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                return result;
            }

            // 更新订单
            int count = bean.getRescheduleCount();
            count += 1;
            bean.setRescheduleCount(count);
            bean.setRescheduleTime(param.getRescheduleTime());
            orderService.update(bean);

            // 更新预约
            Appointment appointment = appointmentService.findByOrderList(bean.getUuid());
            appointment.setAppointmentTime(bean.getRescheduleTime());
            int total = appointment.getTotal();
            total += 1;
            appointment.setTotal(total);
            Appointment appoint = appointmentService.update(appointment);
            if (appoint == null) {
                result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                return result;
            }
            result.setCode(HttpStateCode.OK.getValue());
            result.setMsg(HttpStateCode.OK.getReasonPhrase());
            result.setSuccessObject(appoint);
            return result;
        }

        // 更新订单信息
        bean.setRescheduleTime(param.getRescheduleTime());
        Order order = orderService.update(bean);
        if (order == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }

        // 更新预约
        Appointment appointment = appointmentService.findByOrderList(bean.getUuid());
        appointment.setReschedulePrice(param.getReschedulePrice());
        Appointment appoint = appointmentService.update(appointment);
        if (appoint == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        WxUser wxUser = wxUserService.findByUuid(order.getUser());

        //构造统一下单的请求参数
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("易检测");
        orderRequest.setNotifyUrl("http://www.jiankangchou.cn/rest/pay/notifyAppointment");
        // 附加信息
        orderRequest.setAttach(order.getUuid() + "," + order.getRescheduleCount().toString());
        // 外部商号
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String str = df.format(new Date()).replace("-", "").toLowerCase();
        orderRequest.setOutTradeNo(str + new Random().nextInt());

        orderRequest.setOpenid(wxUser.getOpenid());
        orderRequest.setSpbillCreateIp("117.34.176.28");

//        Double feePrice = order.getReschedulePrice().doubleValue() * 100;
        Double feePrice = 1.0d;

        orderRequest.setTotalFee((feePrice.intValue()));//元转成分
        orderRequest.setTradeType("JSAPI");

        WxPayMpOrderResult payResult = wxPayService.createOrder(orderRequest);
        if (StringUtils.isEmpty(payResult)) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
        } else {
            result.setCode(HttpStateCode.OK.getValue());
            result.setMsg(HttpStateCode.OK.getReasonPhrase());
            result.setSuccessObject(payResult);
        }
        return result;
    }

    /*
     * @Description 微信支付回调
     * @Date 2018/12/26
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "notifyAppointment")
    public void notifyWeiXinPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultxml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> params = null;
        try {
            params = WXPayUtil.xmlToMap(resultxml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        outSteam.close();
        inStream.close();
        if ("SUCCESS".equals(params.get("result_code")) && "SUCCESS".equals(params.get("return_code"))) {
            String[] attach = params.get("attach").split(",");
            String uuid = attach[0];
            String rescheduleCountStr = attach[1];
            int rescheduleCount = Integer.valueOf(rescheduleCountStr);
            if (uuid != null || !"".equals(uuid)) {
                Order order = orderService.findByUuid(uuid);
                if (rescheduleCount == order.getRescheduleCount()) {
                    Appointment appointment = appointmentService.findByOrderList(order.getUuid());

                    // 更新订单
                    int count = order.getRescheduleCount();
                    count += 1;
                    order.setRescheduleCount(count);
                    order.setReschedulePrice(order.getReschedulePrice().add(appointment.getReschedulePrice()));
                    orderService.update(order);

                    // 更新预约
                    appointment.setAppointmentTime(order.getRescheduleTime());
                    int total = appointment.getTotal();
                    total += 1;
                    appointment.setTotal(total);
                    appointmentService.update(appointment);
                }
            }
        }
    }

}
