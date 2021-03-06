package com.ni235.common.exception;


public enum BizCodeEnum {

    UNKNOW_EXCEPTION(10001, "系统未知异常"),

    VAILD_EXCEPTION(10001, "参数格式校验失败");


    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
