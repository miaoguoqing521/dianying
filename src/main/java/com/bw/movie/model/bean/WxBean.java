package com.bw.movie.model.bean;

/**
 * 作者： 姓名
 * 日期： 2019/10/18 19:31
 */
public class WxBean {
    /**
     * message : 微信登陆失败
     * status : 1001
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
