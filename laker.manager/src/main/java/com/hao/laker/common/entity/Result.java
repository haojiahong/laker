package com.hao.laker.common.entity;

import com.hao.laker.common.exception.ServiceErrors;

import java.io.Serializable;

/**
 * Created by haojiahong on 2017/2/12.
 */
public class Result<D> implements Serializable {

    private static final String SUCCESS_CODE = "00000000";
    private D data;
    private boolean success = true;
    private String code;
    private String message;

    public static <D> Result<D> wrapSuccessfulResult(D data) {
        Result<D> result = new Result<>();
        result.data = data;
        result.success = true;
        result.code = SUCCESS_CODE;
        return result;
    }
    public static <T> Result<T> wrapSuccessfulResult(String message, T data) {
        Result<T> result = new Result<>();
        result.data = data;
        result.success = true;
        result.code = SUCCESS_CODE;
        result.message = message;
        return result;
    }
    public static <D> Result<D> wrapErrorResult(ServiceErrors error) {
        Result<D> result = new Result<>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMessage();
        return result;
    }

    public static <D> Result<D> wrapErrorResult(ServiceErrors error, Object... extendMsg) {
        Result<D> result = new Result<>();
        result.success = false;
        result.code = error.getCode();
        result.message = String.format(error.getMessage(), extendMsg);
        return result;
    }

    public static <D> Result<D> wrapErrorResult(String code, String message) {
        Result<D> result = new Result<>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public D getData() {
        return data;
    }

    public Result<D> setData(D data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<D> setSuccess(boolean success) {
        this.success = success;
        return this;

    }
    public String getCode() {
        return code;
    }
    public Result<D> setCode(String code) {
        this.code = code;
        return this;
    }
    public String getMessage() {
        return message;
    }

    public Result<D> setMessage(String message) {
        this.message = message;
        return this;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("success=");
        sb.append(success);
        sb.append(",");

        sb.append("code=");
        sb.append(code);
        sb.append(",");

        sb.append("message=");
        sb.append(message);
        sb.append(",");

        sb.append("data=");
        sb.append(data);

        sb.append("}");

        return sb.toString();
    }
}
