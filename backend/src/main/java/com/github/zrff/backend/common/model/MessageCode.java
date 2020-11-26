package com.github.zrff.backend.common.model;

public enum MessageCode {
    SUCCESS("操作成功",0),
    FAILURE("操作异常",100),
    UNAUTHORITY("无权限",401),
    LOGOUT("注销登陆成功",301);

    private String message;
    private int code;

    private MessageCode(String message,int code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
