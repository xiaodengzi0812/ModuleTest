package com.dengzi.dzframework.skin.net;

import java.io.Serializable;

/**
 * @Title: base业务bean
 * @Author: Djk
 * @Time: 2016/5/3
 * @Version:1.0.0
 */
public class BaseBean implements Serializable {
    protected int status;
    protected String message;

    public int getStatus() {
        return status;
    }

    // 判断是否访问成功
    public boolean isSuccess() {
        return status == 20000;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
