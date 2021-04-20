package com.xuwei.music.utils;

import lombok.Data;

public class Result<T> {
    private int code = 200;
    private String msg;
    private Boolean success ;
    private T data;
    public static <T> Result<T> of(){
        Result<T> result = new Result();
        return result;
    }

    public static <T> Result<T> of(int code ,String msg){
        Result<T> result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> of(int code , String msg ,T data){
        Result<T> result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> of(int code,T data){
        Result<T> result = new Result();
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> of(T data){
        Result<T> result = new Result();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> of(ResultCodeInterface result){
        Result<T> of = of(result.getCode(), result.getMsg());
        return of;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return this.code == 200;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
