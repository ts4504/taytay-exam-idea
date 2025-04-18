package com.athome.exception;

import com.athome.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage():"操作失败");
    }

    @ExceptionHandler
    public Result handleSqlException(MethodArgumentNotValidException e){
        e.printStackTrace();
        return Result.error("缺少必要的参数！");
    }
}
