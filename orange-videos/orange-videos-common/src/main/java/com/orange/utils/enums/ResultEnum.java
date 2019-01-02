package com.orange.utils.enums;

public enum ResultEnum {
    SUCCESS(200,"成功"),
    UNKNOW_ERROR(-1,"未知错误"),
    USER_ERROR(8000,"用户名和密码不能为空"),
    REPEAT_ERROR(8001,"用户名已存在"),
    USER_NOT_EXIST(8002,"用户名或密码错误"),
    HTTPHEADER_ERROR(401,"请登录账号"),
    TOKEN_ERROR(8003,"账号在其它手机端登录，请重新登录"),
    File_ERROR(8005,"上传文件出错"),
    USERID_NOT_EXIST(8004,"缺少用户id"),
    USERANDVIDEOID_NOT_EXIST(8006,"缺少用户id和视频id"),
    USERANDFANID_NOT_EXIST(8007,"缺少用户id和粉丝id")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
