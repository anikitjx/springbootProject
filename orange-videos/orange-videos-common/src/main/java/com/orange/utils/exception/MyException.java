package com.orange.utils.exception;

import com.orange.utils.enums.ResultEnum;

public class MyException extends RuntimeException {
    /**
     * spring框架只对抛出的异常是RuntimeException才会进行事务回滚
     */
    private Integer code;

    public MyException(ResultEnum resultEnum){
        //RuntimeException的构造方法本身就会传message字符串
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
