package com.hao.laker.common.entity;

import com.hao.laker.common.exception.ServiceErrors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haojiahong on 2017/2/12.
 */
public class PagingResult<T> implements Serializable{
    private List<T> list;
    private int total;

    private boolean success;
    private String code;
    private String message;

    public static <T> PagingResult<T> wrapSuccessfulResult(List<T> data, int total) {
        PagingResult<T> result = new PagingResult<>();
        result.list = data;
        result.total = total;
        result.success = true;
        return result;
    }

    public static <T> PagingResult<T> wrapErrorResult(ServiceErrors error) {
        PagingResult<T> result = new PagingResult<>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMessage();
        return result;
    }

    public static <T> PagingResult<T> wrapErrorResult(String code, String message) {
        PagingResult<T> result = new PagingResult<>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public List<T> getList() {
        return list;
    }

    public PagingResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PagingResult<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public PagingResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PagingResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PagingResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
