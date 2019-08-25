package com.qezhhnjy.login.advice;

import com.google.common.collect.Lists;
import com.qezhhnjy.login.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.AccountException;
import java.util.List;
import java.util.Objects;

/**
 * @author fuzy
 * create time 19-3-25-下午5:01
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleShiroException(Exception e) {
        return ResponseEntity.badRequest().body(Response.error().message(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        return result(result);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindException(BindException e) {
        BindingResult result = e.getBindingResult();
        return result(result);
    }

    private ResponseEntity result(BindingResult result) {
        if (result.hasErrors()) {
            List<String> msg = Lists.newArrayList();
            for (ObjectError error : result.getAllErrors()) {
                msg.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Response.error(msg));
        }
        return null;
    }
}
