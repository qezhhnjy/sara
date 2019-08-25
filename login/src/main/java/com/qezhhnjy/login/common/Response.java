package com.qezhhnjy.login.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fuzy
 * create time 19-3-25-下午4:17
 */
@Data
@JsonSerialize()
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Response<T> implements Serializable {
    private int status;
    private String message;
    private T data;

    public Response(Info info) {
        status = info.status;
        message = info.message;
    }

    public Response<T> message(String message) {
        this.message = message;
        return this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == Info.SUCCESS.status;
    }

    public static Response success() {
        return new Response(Info.SUCCESS);
    }

    public static <T> Response<T> success(T data) {
        Response<T> success = new Response<>(Info.SUCCESS);
        success.data = data;
        return success;
    }

    public static Response error() {
        return new Response(Info.ERROR);
    }

    public static <T> Response<T> error(T data) {
        Response<T> error = new Response<>(Info.ERROR);
        error.data = data;
        return error;
    }

    enum Info {
        SUCCESS(0, "success"),
        ERROR(1, "error"),
        NEED_LOGIN(10, "need_login"),
        ILLEGAL_ARGUMENT(2, "illegal_argument");

        final int status;
        final String message;

        Info(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

}

