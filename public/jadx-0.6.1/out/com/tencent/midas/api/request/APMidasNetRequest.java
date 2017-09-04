package com.tencent.midas.api.request;

public class APMidasNetRequest extends APMidasBaseRequest {
    public static String NET_REQ_MP = "mp";
    public String reqType = "";

    public String getReqType() {
        return this.reqType;
    }

    public void setReqType(String str) {
        this.reqType = str;
    }
}
