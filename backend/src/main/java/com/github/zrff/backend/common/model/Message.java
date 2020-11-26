package com.github.zrff.backend.common.model;

/**
 * 本类制定了消息返回的格式
 */
public class Message {
    private String version = "v1"; // 消息的版本号
    private int code; // 0-成功 非0-失败
    private String message = "";//失败原因
    private Object data;

    /**
     * 创建一般的消息体
     * @param code 0-成功 非0-失败
     * @param message 失败原因
     * @param data 消息体
     * @return
     */
    public static Message createMessage(int code,String message,Object data){
        Message msg = new Message();
        msg.setCode(code).setMessage(message).setData(data);
        return msg;
    }
    public static Message createMessage(int code,String message){
        return createMessage(code,message,null);
    }
    public static Message createMessage(MessageCode message,Object data){
        Message msg = new Message();
        msg.setCode(message.getCode()).setMessage(message.getMessage()).setData(data);
        return msg;
    }
    public static Message createMessage(MessageCode message){
        return createMessage(message,null);
    }

    /**
     * 创建成功的消息体
     * @param data 消息体
     * @return
     */
    public static Message createSuccessMessage(Object data){
        return createMessage(MessageCode.SUCCESS,data);
    }
    public static Message createSuccessMessage(){
        return createMessage(MessageCode.SUCCESS);
    }
    public static Message createFailureMessage(Object data){
        return createMessage(MessageCode.FAILURE,data);
    }
    public static Message createFailureMessage(){
        return createMessage(MessageCode.FAILURE);
    }
    public static Message createUnAuthorityMessage(Object data){
        return createMessage(MessageCode.UNAUTHORITY,data);
    }
    public static Message createUnAuthorityMessage(){
        return createMessage(MessageCode.UNAUTHORITY);
    }
    public static Message createLogoutMessage(){
        return createMessage(MessageCode.LOGOUT);
    }

    public int getCode() {
        return code;
    }
    public Message setCode(int code) {
        this.code = code;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public Message setMessage(String message) {
        this.message = message;
        return this;
    }
    public Object getData() {
        return data;
    }
    public Message setData(Object data) {
        this.data = data;
        return this;
    }

}