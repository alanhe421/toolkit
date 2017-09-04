package com.qq.reader.common.conn.http;

import java.io.IOException;

public class HttpErrorException extends IOException {
    private static final long serialVersionUID = 8842974215324366086L;
    private String errorStr;
    private int stateCode = 200;

    public HttpErrorException(int i, String str) {
        this.stateCode = i;
        this.errorStr = str;
    }

    public String getErrorStr() {
        return this.errorStr;
    }

    public int getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(int i) {
        this.stateCode = i;
    }

    public void setErrorStr(String str) {
        this.errorStr = str;
    }

    public String toString() {
        return "[" + this.errorStr + "]  code : " + this.stateCode;
    }
}
