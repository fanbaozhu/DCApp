package com.xunchijn.dcappv1.util;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class Result<T> {
    //状态码
    private String code;
    //错误信息 与 data互斥
    private String message;
    //正确的信息集合
    private T data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
