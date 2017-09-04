package com.tencent.midas.api;

import com.tencent.midas.api.request.APPurchase;

public class APMidasResponse {
    public static final int PAYCHANEL_ACCT_QBQD = 11;
    public static final int PAYCHANEL_ACCT_QDQB = 0;
    public static final int PAYCHANEL_GOLDCOUPONS = 10;
    public static final int PAYCHANEL_HF = 9;
    public static final int PAYCHANEL_MCARD = 5;
    public static final int PAYCHANEL_QQCARD = 4;
    public static final int PAYCHANEL_TENPAY_BANK = 2;
    public static final int PAYCHANEL_TENPAY_CFT = 1;
    public static final int PAYCHANEL_TENPAY_KJ = 3;
    public static final int PAYCHANEL_UNKOWN = -1;
    public static final int PAYCHANEL_WECHAT = 8;
    public static final int PAYCHANEL_YB = 7;
    public static final int PAYPROVIDESTATE_SUCC = 0;
    public static final int PAYPROVIDESTATE_UNKOWN = -1;
    public static final int PAYRESULT_ALREADY_OWNED = 1159;
    public static final int PAYRESULT_CANCEL = 2;
    public static final int PAYRESULT_ERROR = -1;
    public static final int PAYRESULT_PARAMERROR = 3;
    public static final int PAYRESULT_PENDING = 101;
    public static final int PAYRESULT_SUCC = 0;
    public static final int PAYRESULT_UNKOWN = 100;
    public static final int PAYSTATE_PAYCANCEL = 1;
    public static final int PAYSTATE_PAYERROR = 2;
    public static final int PAYSTATE_PAYSUCC = 0;
    public static final int PAYSTATE_PAYUNKOWN = -1;
    public String extendInfo;
    public APPurchase mAPPurchase;
    public int payChannel;
    public String payReserve1;
    public String payReserve2;
    public String payReserve3;
    public int payState;
    public int provideState;
    public int realSaveNum;
    public int resultCode;
    public int resultInerCode;
    public String resultMsg;

    public APMidasResponse() {
        this.resultMsg = null;
        this.extendInfo = null;
        this.payReserve1 = null;
        this.payReserve2 = null;
        this.payReserve3 = null;
        this.realSaveNum = 0;
        this.resultCode = -1;
        this.resultInerCode = 0;
        this.payChannel = -1;
        this.payState = -1;
        this.provideState = -1;
        this.resultMsg = "";
        this.extendInfo = "";
        this.payReserve1 = "";
        this.payReserve2 = "";
        this.payReserve3 = "";
        this.mAPPurchase = null;
    }

    public String getExtendInfo() {
        return this.extendInfo;
    }

    public int getPayChannel() {
        return this.payChannel;
    }

    public String getPayReserve1() {
        return this.payReserve1;
    }

    public String getPayReserve2() {
        return this.payReserve2;
    }

    public String getPayReserve3() {
        return this.payReserve3;
    }

    public int getPayState() {
        return this.payState;
    }

    public int getProvideState() {
        return this.provideState;
    }

    public int getRealSaveNum() {
        return this.realSaveNum;
    }

    public APPurchase getReceipt() {
        return this.mAPPurchase;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public int getResultInerCode() {
        return this.resultInerCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void reset() {
        this.realSaveNum = 0;
        this.resultCode = -1;
        this.resultInerCode = 0;
        this.payChannel = -1;
        this.payState = -1;
        this.provideState = -1;
        this.resultMsg = "";
        this.extendInfo = "";
        this.payReserve1 = "";
        this.payReserve2 = "";
        this.payReserve3 = "";
        this.mAPPurchase = null;
    }

    public void setExtendInfo(String str) {
        this.extendInfo = str;
    }

    public void setPayChannel(int i) {
        this.payChannel = i;
    }

    public void setPayReserve1(String str) {
        this.payReserve1 = str;
    }

    public void setPayReserve2(String str) {
        this.payReserve2 = str;
    }

    public void setPayReserve3(String str) {
        this.payReserve3 = str;
    }

    public void setPayState(int i) {
        this.payState = i;
    }

    public void setProvideState(int i) {
        this.provideState = i;
    }

    public void setRealSaveNum(int i) {
        this.realSaveNum = i;
    }

    public void setReceipt(APPurchase aPPurchase) {
        this.mAPPurchase = aPPurchase;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public void setResultInerCode(int i) {
        this.resultInerCode = i;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }
}
