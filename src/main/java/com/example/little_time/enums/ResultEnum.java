package com.example.little_time.enums;

public enum ResultEnum {
    //接口
    OK(200,"OK"),

    //未知错误
    UNKOWN_ERROR(500000,"UNKOWN_ERROR"),

    //用户
    USER_EXIST(400001,"USER_IS_EXISTED"),
    USER_NOT_EXIST(400002,"USER_NOT_EXIST"),
    USER_EXCEPTION(400003,"USER_EXCEPTION"),

    //TOKEN
    TOKEN_TYPE_ERROR(400101,"TOKEN_TYPE_ERROR"),
    REFRESH_TOKEN_ERROR(400102,"REFRESH_TOKEN_ERROR"),
    TOKEN_IS_EXPIRED(400103,"TOKEN_IS_EXPIRED"),

    //验证码
    VERTIFICATION_NOT_EXIST(400201,"VERTIFICATION_NOT_EXIST"),
    VERTIFY_ERROR(400202,"VERTIFY_ERROR"),

    //手机
    PHONENUMBER_ERROR(400301,"PHONENUMBER_ERROR"),

    //存在必要项未填写
    NECESSARY_ITEMS_NOT_FINISHED(400401,"NECESSARY_ITEMS_NOT_FINISHED"),

    //逻辑错误
    LOGIC_ERROR(500,"LOGIC_ERROR"),

    //密码
    WRONG_PASSWORD(400501,"WRONG_PASSWORD"),
    SAME_NEW_AND_OLD_PASSWORD(400502,"SAME_NEW_AND_OLD_PASSWORD"),
    PASSWORD_IS_NOT_NORMAL(400503,"PASSWORD_IS_NOT_NORMAL"),

    //信息上传失败
    UPLOAD_EXCEPTION(400601,"UPLOAD_EXCEPTION");
    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public Integer getCode(){return code;}
    public void setCode(Integer code){this.code=code;}
    public String getMsg(){return msg;}
    public void setMsg(String msg){this.msg=msg;}
}
