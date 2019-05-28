package com.dk.response;

/**
 * @author CRH
 * @date 2017年12月22日
 * @describe http请求接口返回状态码
 **/
public enum HttpStateCode {

    CONTINUE(100, "继续"),
    SWITCHING_PROTOCOLS(101, "切换协议"),
    PROCESSING(102, "处理"),
    CHECKPOINT(103, "检查点"),
    OK(200, "成功"),
    CREATED(201, "创建"),
    ACCEPTED(202, "公认的"),
    NON_AUTHORITATIVE_INFORMATION(203, "那些信息"),
    NO_CONTENT(204, "没有内容"),
    RESET_CONTENT(205, "重置内容"),
    PARTIAL_CONTENT(206, "部分内容"),
    MULTI_STATUS(207, "多种状态"),
    ALREADY_REPORTED(208, "已经存在"),
    IM_USED(226, "已使用"),
    MULTIPLE_CHOICES(300, "多个选择"),
    MOVED_PERMANENTLY(301, "永久移动"),
    FOUND(302, "发现"),
    SEE_OTHER(303, "看到其他"),
    NOT_MODIFIED(304, "没有修改"),
    USE_PROXY(305, "使用代理"),
    TEMPORARY_REDIRECT(307, "临时重定向"),
    PERMANENT_REDIRECT(308, "永久重定向"),
    BAD_REQUEST(400, "坏请求"),
    UNAUTHORIZED(401, "未经认证授权的"),
    PAYMENT_REQUIRED(402, "支付所必须"),
    FORBIDDEN(403, "拒绝服务"),
    NOT_FOUND(404, "没有找到"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),
    NOT_ACCEPTABLE(406, "不可接受的"),
    PROXY_AUTHENTICATION_REQUIRED(407, "需要代理验证"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "冲突"),
    GONE(410, "过时"),
    LENGTH_REQUIRED(411, "长度必需"),
    PRECONDITION_FAILED(412, "先决条件失败"),
    PAYLOAD_TOO_LARGE(413, "请求实体太大"),
    REQUEST_URI_TOO_LONG(414, "请求URI太长"),
    UNSUPPORTED_MEDIA_TYPE(415, "不被支持的媒体类型"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "请求范围不满足"),
    EXPECTATION_FAILED(417, "期望失败"),
    PRECONDITION_INPUT(418, "推送进度已完成"),
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "空间资源不足"),
    METHOD_FAILURE(420, "方法错误"),
    DESTINATION_LOCKED(421, "目标锁定"),
    LOCKED(423, "锁着的"),
    FAILED_DEPENDENCY(424, "失败的依赖"),
    UPGRADE_REQUIRED(426, "升级需要"),
    PRECONDITION_REQUIRED(428, "先决条件要求"),
    TOO_MANY_REQUESTS(429, "太多的请求"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "请求头字段太大"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "由于法律原因不可用"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    NOT_IMPLEMENTED(501, "不能实现"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不能获得"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP版本不支持"),
    VARIANT_ALSO_NEGOTIATES(506, "变体也协商"),
    INSUFFICIENT_STORAGE(507, "存储不足"),
    LOOP_DETECTED(508, "循环检测"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "带宽限制超过"),
    NOT_EXTENDED(510, "不延长"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "网络身份验证要求"),
    INVALID_TOKEN(512, "无效的token"),
    ALREADY_EXISTS(601,"请重新输入，数据库已存在"),
    OPERATION_FAILURE(602,"操作失败，请重新尝试"),
    SAVE_FAILURE(604,"保存失败！"),
    UPDATE_FAILURE(605,"修改失败！"),
    DELETE_FAILURE(606,"删除失败！"),
    SIGN_FAILURE(607,"标识失败！"),
    PARAMETERS_NULL(701,"参数为空！");






    private final int value;
    private final String reasonPhrase;


    private HttpStateCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

}
