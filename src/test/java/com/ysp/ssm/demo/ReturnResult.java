package com.ysp.ssm.demo;

/**
 * Created by yuhuanxi on 16/8/22.
 */
public class ReturnResult {

    /**
     * success : true
     * code : 200
     * message : 操作正确
     */

    private boolean success;
    private int code;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
