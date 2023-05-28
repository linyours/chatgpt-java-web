package com.blue.cat.utils;

import com.blue.cat.bean.common.BaseCode;
import com.blue.cat.bean.common.BaseCodeEnum;

/**
 * web接口统一返回结果
 */
public class ResultVO<T> {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static <T> ResultVO<T> build(BaseCode baseCode, T data) {
        return new ResultVO<>(baseCode, data);
    }

    public static <T> ResultVO<T> build(BaseCode baseCode) {
        return new ResultVO<>(baseCode);
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<>(BaseCodeEnum.SUCCESS);
    }

    public static <T> ResultVO<T> success(String msg) {
        return new ResultVO<>(BaseCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(BaseCodeEnum.SUCCESS, data);
    }

    public static <T> ResultVO<T> error(BaseCode baseCode) {
        return new ResultVO<>(baseCode.getCode(), baseCode.getMsg());
    }

    public static <T> ResultVO<T> error(int code, String msg) {
        return new ResultVO<>(code, msg);
    }

    public static <T> ResultVO<T> fail(int code, String msg) {
        return new ResultVO<>(code, msg);
    }

    public static <T> ResultVO<T> fail(BaseCode baseCode) {
        return new ResultVO<>(baseCode.getCode(), baseCode.getMsg());
    }

    public static <T> ResultVO<T> fail(String msg) {
        return new ResultVO<>(BaseCodeEnum.FAIL.getCode(), msg);
    }

    public ResultVO() {
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(BaseCode baseCode) {
        this.code = baseCode.getCode();
        this.msg = baseCode.getMsg();
    }

    public ResultVO(BaseCode baseCode, T data) {
        this.code = baseCode.getCode();
        this.msg = baseCode.getMsg();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
