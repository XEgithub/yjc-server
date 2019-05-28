package com.dk.response;


/**
 * Created by wangyulin on 15/11/2017.
 */
public class WrappedResponse {

    private boolean isSuccess = true;
    private int code;
    private String msg;
    private Object successObject;
    private Object errorLocations;

    private static WrappedResponse reponse = null;

    public WrappedResponse() {
        super();
    }

   /* public static WrappedResponse getReponse(){
        if (reponse == null) {
            synchronized (WrappedResponse.class) {
                if (reponse == null) {
                    reponse = new WrappedResponse();
                }
            }
        }
        return reponse;
    }*/



    public WrappedResponse(Object successObject) {
        super();
        this.successObject = successObject;
    }

    public static WrappedResponse success(Object successObject) {
        WrappedResponse response = new WrappedResponse();
        response.code = HttpStateCode.OK.getValue();
        response.msg = HttpStateCode.OK.getReasonPhrase();
        response.setSuccessObject(successObject);
        return response;
    }

    public static WrappedResponse success() {
        return success(null);
    }

    public static WrappedResponse error(int code, String msg) {
        WrappedResponse response = new WrappedResponse();
        response.code = code;
        response.msg = msg;
        response.setSuccessObject(null);
        return response;
    }

    public WrappedResponse(boolean failure, int errorCode, String errorMsg, Object errorLocations) {
        super();
        this.isSuccess = failure;
        this.code = errorCode;
        this.msg = errorMsg;
        this.errorLocations = errorLocations;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getSuccessObject() {
        return successObject;
    }

    public void setSuccessObject(Object successObject) {
        this.successObject = successObject;
    }

    public Object getErrorLocations() {
        return errorLocations;
    }

    public void setErrorLocations(Object errorLocations) {
        this.errorLocations = errorLocations;
    }
}
