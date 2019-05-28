package com.dk.config;

/**
 *
 * 功能描述:
 *
 * @auther: my
 * @Date: 2018/10/23
 * @Param:
 * @return:
 */
public class Constant {

    public static final String WX_TOKEN = "WX_TOKEN";

    public static final String EXPIRES = "EXPIRES";

    public static final Integer NORMAL_STATUS = 1;

    public static final Integer UN_Subscribe = 0;

    public static final String ROLE_CHECKED = "checked";

    public static final Byte USER_NO_ACTIVE = 0;

    public static final int PARAM_ERROR = 400;
    public static final int SERVER_ERROR = 500;

    public static final Byte NO = 0;
    public static final Byte YES = 1;

    //订单状态  0:已取消 1:未付款 2:已付款 3:已发货 4:交易成功 5:交易关闭
    public static final Byte ORDER_CANCEL = 0;
    public static final Byte ORDER_NO_PAY = 1;
    public static final Byte ORDER_PAYED = 2;
    public static final Byte ORDER_SENDED = 3;
    public static final Byte ORDER_SUCCESS = 4;
    public static final Byte ORDER_CLOSED = 5;

    //支付状态  0:已取消 1:支付成功
    public static final Byte PAY_CANCEL = 0;
    public static final Byte PAY_SUCCESS = 1;

    //提现状态  0:审批不通过 1:申请提现 2:审批通过 3:交易完成
    public static final Byte WITHDRAW_CANCEL = 0;
    public static final Byte WITHDRAW_APPLY = 1;
    public static final Byte WITHDRAW_APPLY_PASS = 2;
    public static final Byte WITHDRAW_COMPLETE = 3;

    //收货方式  0:自提 1:快递
    public static final Byte RECEIVE_METHOD_SELF = 1;
    public static final Byte RECEIVE_METHOD_EXPRESS = 1;

}
