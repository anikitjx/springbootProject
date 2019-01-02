package com.orange.utils.exception;

import com.orange.utils.Result;
import com.orange.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    //日志记录系统异常
    private final static Logger logger = Logger.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof MyException){
            MyException myException = (MyException)e;
            return ResultUtil.error(myException.getCode(),myException.getMessage());
        }else{
            logger.error("[系统异常]{}",e);
            return ResultUtil.error(-1,"未知错误");
        }
    }
}
