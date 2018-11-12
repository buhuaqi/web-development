package com.justl.domain.response;

import com.justl.utils.Constants;

import java.io.Serializable;

/**
 * 数据响应格式
 *
 * @author buhuaqi
 * @date 2018-10-29 10:18
 */
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = -3408598774435409654L;

    /**
     * 状态码
     **/
    private int code = Constants.SUCCESS;
    /**
     * 返回的信息，异常错误时可以添加错误信息
     **/
    private String message = "success";
    /**
     * 返回的具体数据
     **/
    private T data;

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setSuccess(String msg) {
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData [code=" + code + ", message=" + message + ",\r data=" + data
                + "]";
    }

}

