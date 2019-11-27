package cn.ds.double_live_demo.exception;

import cn.ds.double_live_demo.util.BaseResponse;
import cn.ds.double_live_demo.util.Constant;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * post请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        //获取异常中随机一个异常信息
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return new BaseResponse<>(Constant.PARAM_ERROR,defaultMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<String> illegalArgumentException(IllegalArgumentException e){
        //获取异常中随机一个异常信息
        return new BaseResponse<>(Constant.PARAM_ERROR,e.getMessage());
    }
}
