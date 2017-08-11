package com.youu.youu.restapi.bean;

import java.util.Map;

public class ScwReturn<T> {

    public int code; // 状态码：1成功，0失败
    public String msg; // 错误信息
    public T content; // 响应内容
    public Map<String, Object> ext; // 额外的数据

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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    // 快速成功的方法
    public static <T> ScwReturn<T> success(String msg, T content, Map<String, Object> ext) {
        ScwReturn<T> scwReturn = new ScwReturn<T>();
        scwReturn.setCode(1);
        scwReturn.setMsg(msg);
        scwReturn.setContent(content);
        scwReturn.setExt(ext);

        return scwReturn;
    }

    // 快速失败的方法
    public static <T> ScwReturn<T> fail(String msg, T content, Map<String, Object> ext) {
        ScwReturn<T> scwReturn = new ScwReturn<T>();
        scwReturn.setCode(0);
        scwReturn.setMsg(msg);
        scwReturn.setContent(content);
        scwReturn.setExt(ext);

        return scwReturn;
    }
}
