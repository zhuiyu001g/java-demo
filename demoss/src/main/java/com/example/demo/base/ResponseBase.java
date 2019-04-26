package com.example.demo.base;

import lombok.Data;

/**
 * 补偿队列：
 */
@Data
public class ResponseBase {
    private Integer rtnCode;
    private String msg;
    private Object data;

    public ResponseBase() {

    }

    public ResponseBase(Integer rtnCode, String msg, Object data) {
        super();
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
    }
}
