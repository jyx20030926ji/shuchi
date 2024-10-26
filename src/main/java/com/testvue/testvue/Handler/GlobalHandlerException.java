package com.testvue.testvue.Handler;

import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.exception.DeleteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@Slf4j
@ControllerAdvice
public class GlobalHandlerException {


    @ResponseBody
    @ExceptionHandler(value = DeleteException.class)
    public Result globalhander(DeleteException e)
    {

        log.info("-----------------------------------------{}",e.getErrorCode());
        return Result.error(e.getErrorMessage(),e.getErrorCode());
    }

    @ResponseBody
    @ExceptionHandler(value = AccountNoExistException.class)
    public Result accountException(AccountNoExistException e)
    {

        return Result.error(e.getErrorMessage(),e.getErrorCode());
    }


}
