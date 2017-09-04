package com.qq.reader.common.conn.http;

public class HttpResponseException extends Exception {
    private static final long serialVersionUID = 1;
    private int stateCode = 200;

    public int getStateCode() {
        return this.stateCode;
    }

    public HttpResponseException(int i) {
        this.stateCode = i;
    }

    public String toString() {
        return super.toString() + "[" + this.stateCode + "]";
    }
}
